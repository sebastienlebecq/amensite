package fr.nations.amen.client.mvp.activities;

import java.util.Vector;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.events.AppBusyEvent;
import fr.nations.amen.client.mvp.events.AppFreeEvent;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.souvenirspublic.celllist.CellListSouvenirManager;
import fr.nations.amen.client.souvenirspublic.celllist.SouvenirManagerForm;
import fr.nations.amen.client.souvenirspublic.celllist.celltypes.custom.SouvenirCellWithUiBinder;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.souvenirs.SouvenirsService;
import fr.nations.amen.shared.souvenirs.SouvenirsServiceAsync;
import com.google.gwt.view.client.Range;

public class SouvenirDetailsActivity extends AbstractActivity implements SouvenirsPresenter{

	private ClientFactory clientFactory;
	private SouvenirsView souvenirView;
	private EventBus appEventBus;
	
	static private CellListSouvenirManager celllSouvenirManager;
	static private Souvenir currentSouvenir;
	
	static public void initSouvenir(){
		currentSouvenir=null;
	}
	
	private SouvenirsServiceAsync rpcService;
	private SouvenirDetailsPlace place;
	private SouvenirManagerForm details;
	private boolean souvenirSelected=false;
	//private static Range range;
	
	public SouvenirDetailsActivity(SouvenirDetailsPlace place2,
			ClientFactory clientFactory2) {
		this.clientFactory = clientFactory2;
		this.place = place2;
		this.appEventBus = clientFactory.getEventBus();

		
	}

	public SouvenirDetailsPlace getPlace() {
		return place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		appEventBus.fireEvent(new AppBusyEvent());
		souvenirView = clientFactory.getSouvenirsView();
	
		if (celllSouvenirManager ==null) 
			celllSouvenirManager = new CellListSouvenirManager(souvenirView);
		
		details = celllSouvenirManager.getSouvenirManagerForm();
		

		details.setPlace(this.getPlace());
		
		if (place.getSouvenirId().equals("999999")){
		
			
			// évolution à envisager
/*		rpcService.getSouvenirbyCategory("cat0", 0, 1, new AsyncCallback<Vector<Souvenir>>() {

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			public void onSuccess(Vector<Souvenir> result) {

					currentSouvenir = result.get(0);
					
					
					
					//SouvenirCellWithUiBinder.indexSelected=0;
				
					if (celllSouvenirManager ==null) 
						celllSouvenirManager = new CellListSouvenirManager(souvenirView);
					
					
					//celllSouvenirManager.getScwb().setIndexSelected(0);
					
					//celllSouvenirManager.getScwb().setValue(context, parent, value);
					//generate event pour mettre à zero l'ndex
					//celllSouvenirManager.getChg().onChange(null);
					
					CellList<Souvenir> sList = celllSouvenirManager.getSouvenirList();
					//sList.setPageStart(pageStart)
					//sList.fireEvent(event)
					SelectionModel<? super Souvenir> model = sList.getSelectionModel();
					model.setSelected(currentSouvenir, false);
					
					SouvenirManagerForm details = celllSouvenirManager.getSouvenirManagerForm();
					
					
					//details.setSouvenir(currentSouvenir);
					
					details.setSouvenir(null);
					
					celllSouvenirManager.setSouvenirManagerForm(details);
					
					souvenirView.setCell(celllSouvenirManager);

					panel.setWidget(souvenirView.asWidget());
					bind();

			}
		});*/
		
			SouvenirCellWithUiBinder scwb = celllSouvenirManager.getScwb();
			scwb.setIndexSelected(UniformDim.OUTOFLIST);
			
			SingleSelectionModel<Souvenir> selectionModel = celllSouvenirManager.getSelectionModel();
			selectionModel.getSelectedSet().clear();
			

			
			CellList<Souvenir> sList = celllSouvenirManager.getSouvenirList();
			details.setSouvenir(null);
			
			
			
			//importantn car sinon non rafraichissement
			sList.redraw();
			
			celllSouvenirManager.setSouvenirManagerForm(details);
			
			souvenirView.setCell(celllSouvenirManager);

			panel.setWidget(souvenirView.asWidget());
			bind();

			appEventBus.fireEvent(new AppFreeEvent());
		
		} else  // end if (place.getSouvenirId().equals("999999"))
			
		
			
		if (souvenirSelected == true) {  // else 	if (currentSouvenir == null)

				souvenirSelected=false;
				//situation normale
				details.setSouvenir(currentSouvenir);
				
				//details.setPlace(place);
				
				celllSouvenirManager.setSouvenirManagerForm(details);
				
				
				souvenirView.setCell(celllSouvenirManager);

				panel.setWidget(souvenirView.asWidget());
				bind();
				appEventBus.fireEvent(new AppFreeEvent());
					
				}
				else {
					
					// si appel via une url dont les informations ont été stochées dans une place, 
					// alors construction d'un objet celllSouvenirManager avec bonne plagen sélection et 
					//objet souvenir obtenu par rpcservice.
						
						
						//algorithme pour retrouver le bon souvenir:
						// faire une requête assynchrone 
						
						rpcService = GWT.create(SouvenirsService.class);
						
						//il faut obtenir la bonne categorie et l'afficher en conséquence : auytr mapper livre samy jabber
						
						//int rStart = range.getStart()/2;
						
						
						
						rpcService.getSouvenirbyCategory(place.getCategory(), 
								new Integer(place.getStartPage()).intValue(), 
								new Integer(place.getStartPage()).intValue()+UniformDim.PAGE_PUBLIC_SIZE, 
								new AsyncCallback<Vector<Souvenir>>() {

							public void onFailure(Throwable caught) {
								Window.alert("Error" + caught.getMessage());
								appEventBus.fireEvent(new AppFreeEvent());
							}

							public void onSuccess(Vector<Souvenir> result) {

								//itération
								
								
								
								for (Souvenir elmt : result){
									
									if (elmt.getId().equals(place.getSouvenirId())){
										currentSouvenir = elmt;
										
										break;
									}
								}
								
								if(result.isEmpty()){
									Window.alert("souvenir non trouvé!!");
								}
								
								if (currentSouvenir == null) { 
									currentSouvenir = result.get(0);} //valeur fausse   
								
								
								details = celllSouvenirManager.getSouvenirManagerForm();
								
								details.setSouvenir(currentSouvenir);
								
								//
								CellList<Souvenir> sList = celllSouvenirManager.getSouvenirList();
								SelectionModel<? super Souvenir> model = sList.getSelectionModel();
								model.setSelected(currentSouvenir, false);
								//
								celllSouvenirManager.setSouvenirManagerForm(details);
								
								souvenirView.setCell(celllSouvenirManager);
							
								panel.setWidget(souvenirView.asWidget());
								bind();
								appEventBus.fireEvent(new AppFreeEvent());
								
							}
							});
								
							}//end else
					
				}

			

		//}
		
		
		//}
		

		
	

	@Override
	public void go(HasWidgets container) {}

	@Override
	public void bind() {
		souvenirView.setPresenter(this);
		
	}


	@Override
	public void souvenirSelected(Souvenir current,
			CellListSouvenirManager cellListSouvenirManager, Range range, String category) {
		
		celllSouvenirManager = cellListSouvenirManager;
		currentSouvenir = current;
		//range = range;
		
		souvenirSelected = true;
	
		//souvenirManagerForm.setSouvenir(current);
		
		clientFactory.getPlaceController().goTo(
				new SouvenirDetailsPlace(current.getId()+"&"+ range.getStart()+"&"+category));
		
		
	}


}
