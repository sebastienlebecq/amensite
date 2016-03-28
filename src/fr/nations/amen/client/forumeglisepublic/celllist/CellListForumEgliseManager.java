package fr.nations.amen.client.forumeglisepublic.celllist;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.PageSizePager;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.forumeglisepublic.celllist.celltypes.custom.ForumEgliseCellWithUiBinder;
import fr.nations.amen.client.forumeglisepublic.celllist.dataproviders.AllDataAsyncDataProvider;
import fr.nations.amen.client.forumeglisepublic.celllist.dataproviders.CatDataAsyncDataProvider;
import fr.nations.amen.client.mvp.presenters.ForumEglisePresenter;
import fr.nations.amen.client.mvp.views.ForumEgliseView;
import fr.nations.amen.shared.forumeglise.ForumEglise;
import fr.nations.amen.shared.forumeglise.ForumEgliseService;
import fr.nations.amen.shared.forumeglise.ForumEgliseServiceAsync;


public class CellListForumEgliseManager extends Composite {

	interface CellTypesUiBinder extends UiBinder<Widget, CellListForumEgliseManager> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);
	

	AsyncDataProvider<ForumEglise> dataProviderAsync;

	//final int PAGE_SIZE =6;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;
	
	// Display Cells
	@UiField(provided = true)
	CellList<ForumEglise> souvenirList;
	
	@UiField
	ForumEgliseManagerForm souvenirManagerForm;

//	@UiField
//	ListBox categories;
	
	ForumEgliseServiceAsync souvenirService = GWT.create(ForumEgliseService.class);


	private ForumEgliseView souvenirView;


	private ForumEgliseCellWithUiBinder scwb;


	private ChangeHandler chg;


	private SingleSelectionModel<ForumEglise> selectionModel;
	 
	public SingleSelectionModel<ForumEglise> getSelectionModel() {
		return selectionModel;
	}

	public ForumEgliseCellWithUiBinder getScwb() {
		return scwb;
	}

	public CellListForumEgliseManager(ForumEgliseView souvenirView2) {
		
		this.souvenirView = souvenirView2;

		scwb = new ForumEgliseCellWithUiBinder();
		souvenirList = new CellList<ForumEglise>(scwb);
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("120%", "100%");
		
		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(souvenirList);

		handleUpdates();

		createWithAsyncDataProvider();
		
		//souvenirList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		souvenirList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		souvenirList.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
		
//		for (String cat : UniformDim.categoriesSouvenirs){
//			categories.addItem(cat);
//		}
		
		chg = new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				//TODO: mettre en activité
				//remise à 0 et sélection du numéro zero
				//TODO : modif uibinder pour mettre le combobox ds la colonne de gauche dans ub docklayout
				
				scwb.setIndexSelected(UniformDim.OUTOFLIST);
				//selectionModel.getSelectedSet().clear();
				//CellListSouvenirManager.this.getScwb().setIndexSelected(0);

				dataProviderAsync.removeDataDisplay(souvenirList);
				souvenirList.redraw();

				//mise à jour du modèle
				dataProviderAsync = new CatDataAsyncDataProvider("cat0");
				dataProviderAsync.addDataDisplay(souvenirList);
				dataProviderAsync.updateRowCount(10, true);

				//rafraichissement de la vue
				souvenirManagerForm.setDisplay(souvenirList);
				//souvenirList.getVisibleItem(0);
				
				
				ForumEgliseManagerForm details = CellListForumEgliseManager.this.getSouvenirManagerForm();
				details.setSouvenir(null);
				
				souvenirList.redraw();
				
				//selectionModel.getSelectedSet().clear();
				//souvenirList.getVisibleItem(0);
			}

		};
		
		//categories.addChangeHandler(chg);

	}

	public CellList<ForumEglise> getSouvenirList() {
		return souvenirList;
	}

	public void setSouvenirList(CellList<ForumEglise> souvenirList) {
		this.souvenirList = souvenirList;
	}

	public ChangeHandler getChg() {
		return chg;
	}

	private void applyOptions() {
		souvenirList.setPageSize(UniformDim.PAGE_PUBLIC_SIZE);
		
	}

	private void createWithAsyncDataProvider() {
		//dataProviderAsync = new CatDataAsyncDataProvider("cat"+categories.getSelectedIndex());
		dataProviderAsync = new AllDataAsyncDataProvider();
		dataProviderAsync.addDataDisplay(souvenirList);
		dataProviderAsync.updateRowCount(10, true);
		
		//souvenirManagerForm.setDataProvider(dataProviderAsync);
		souvenirManagerForm.setDisplay(souvenirList);
		
	}

//	private void createWithListDataProvider() {
//		List<Souvenir> theList;
//
//		dataProviderList = new ListDataProvider<Souvenir>();
//		dataProviderList.addDataDisplay(souvenirList);
//		theList = dataProviderList.getList();
//		PsuedoDataSource.populate(theList, dataProviderList);
//		// Keep the size of the list up to date - easier to know that we are
//		// showing 51-100 of 2000 etc
//		souvenirList.setRowCount(theList.size(), true);
//	}

	private void handleUpdates() {
		souvenirList.setValueUpdater(new ValueUpdater<ForumEglise>() {
			@Override
			public void update(ForumEglise value) {
				Window.alert("Handling update on photo: " + value.getDescription());
				dataProviderAsync.removeDataDisplay(souvenirList);
				souvenirList.redraw();
			}
		});
	}
	private Range range;
	private void setUpSelectionModel() {
		selectionModel = new SingleSelectionModel<ForumEglise>();
		souvenirList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						
						 AsyncCallback<ForumEglise> callback  = new AsyncCallback<ForumEglise>() {
								public void onFailure(Throwable error) {
									//do something, when fail
								}

								public void onSuccess(ForumEglise result) {

									range = CellListForumEgliseManager.this.getSouvenirList().getVisibleRange();

									ForumEglisePresenter pres = souvenirView.getPresenter();
									
									
									
									AsyncCallback<ForumEglise> callback2  = new AsyncCallback<ForumEglise>() {
										public void onFailure(Throwable error) {
											Window.alert("erreur : index non mis à jour!!");
										}

										@Override
										public void onSuccess(ForumEglise result) {
											
											//Window.alert("index: "+result.getIndexInCells());
										}

									};
									
									
									souvenirService.registerIndexCellTable(selectionModel.getSelectedObject().getId(), 
											new Integer(range.getStart()).toString(), callback2);
									
									pres.forumSelected(result, CellListForumEgliseManager.this, range, "cat0");
									
									//Window.alert("Sois beni Heureux Internaute, " +
									//		"tu es le "+result.getNbPagesVues()+"eme visiteur de cette page!");

								}

							};
						
						souvenirService.incrementNbPagesVues(selectionModel.getSelectedObject().getId(), callback);
						
						
						
					}


				});
		

	}

	public ForumEgliseManagerForm getSouvenirManagerForm() {
		return souvenirManagerForm;
	}

	public void setSouvenirManagerForm(ForumEgliseManagerForm souvenirManagerForm) {
		this.souvenirManagerForm = souvenirManagerForm;
	}
}
