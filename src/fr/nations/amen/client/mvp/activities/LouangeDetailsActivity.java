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
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.louangespublic.celllist.CellListLouangesManager;
import fr.nations.amen.client.louangespublic.celllist.LouangesManagerForm;
import fr.nations.amen.client.louangespublic.celllist.celltypes.custom.LouangesCellWithUiBinder;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.events.AppBusyEvent;
import fr.nations.amen.client.mvp.events.AppFreeEvent;
import fr.nations.amen.client.mvp.places.LouangeDetailsPlace;
import fr.nations.amen.client.mvp.presenters.LouangesPresenter;
import fr.nations.amen.client.mvp.views.LouangesView;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;

public class LouangeDetailsActivity extends AbstractActivity implements
		LouangesPresenter {

	private ClientFactory clientFactory;
	private LouangesView louangeView;
	private EventBus appEventBus;

	static private CellListLouangesManager celllLouangeManager;
	static private Louange currentSouvenir;

	static public void initLouange() {
		currentSouvenir = null;
	}

	private LouangesServiceAsync rpcService;
	private LouangeDetailsPlace place;
	private LouangesManagerForm details;
	private static boolean louangeSelected = false;
	//private static Range range;

	public LouangeDetailsActivity(LouangeDetailsPlace place2,
			ClientFactory clientFactory2) {
		this.clientFactory = clientFactory2;
		this.place = place2;
		this.appEventBus = clientFactory.getEventBus();
	}

	public LouangeDetailsPlace getPlace() {
		return place;
	}

	public void setPlace(LouangeDetailsPlace place) {
		this.place = place;
	}

	@Override
	public void go(HasWidgets container) {
	}

	@Override
	public void bind() {
		louangeView.setPresenter(this);

	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		appEventBus.fireEvent(new AppBusyEvent());
		louangeView = clientFactory.getLouangesView();

		if (celllLouangeManager == null)
			celllLouangeManager = new CellListLouangesManager(louangeView);

		details = celllLouangeManager.getLouangeManagerForm();
		
		details.setPlace(this.getPlace());

		if (place.getLouangeId().equals("999999")) {

			LouangesCellWithUiBinder scwb = celllLouangeManager.getLcwb();
			scwb.setIndexSelected(UniformDim.OUTOFLIST);

			SingleSelectionModel<Louange> selectionModel = celllLouangeManager
					.getSelectionModel();
			selectionModel.getSelectedSet().clear();

			CellList<Louange> sList = celllLouangeManager.getLouangeCells();
			details.setSouvenir(null);

			// importantn car sinon non rafraichissement
			sList.redraw();

			celllLouangeManager.setLouangeManagerForm(details);

			louangeView.setCell(celllLouangeManager);

			panel.setWidget(louangeView.asWidget());
			bind();

			appEventBus.fireEvent(new AppFreeEvent());

		} else if (louangeSelected == true) { // else if (currentSouvenir ==
												// null)
			louangeSelected = false;
			// situation normale : click sur la liste de cells

			details.setSouvenir(currentSouvenir);

			celllLouangeManager.setLouangeManagerForm(details);

			louangeView.setCell(celllLouangeManager);

			panel.setWidget(louangeView.asWidget());
			bind();
			appEventBus.fireEvent(new AppFreeEvent());

		} else {

			// algorithme pour retrouver le bon souvenir:
			// faire une requête assynchrone

			rpcService = GWT.create(LouangesService.class);

			// il faut obtenir la bonne categorie et l'afficher en conséquence :
			// auytr mapper livre samy jabber

			// int rStart = range.getStart()/2;

			rpcService.getLouangeByCategory(place.getCategory(), new Integer(
					place.getStartPage()).intValue(),
					new Integer(place.getStartPage()).intValue()
							+ UniformDim.PAGE_PUBLIC_SIZE,
					new AsyncCallback<Vector<Louange>>() {

						public void onFailure(Throwable caught) {
							Window.alert("Error" + caught.getMessage());
							appEventBus.fireEvent(new AppFreeEvent());
						}

						public void onSuccess(Vector<Louange> result) {

							// itération

							for (Louange elmt : result) {

								if (elmt.getId().equals(place.getLouangeId())) {
									currentSouvenir = elmt;

									break;
								}
							}

							if (currentSouvenir == null) {
								currentSouvenir = result.get(0);
							} // valeur fausse pour l'exécuton sans exception 
							// mais heureusement cas non réaliste

							details = celllLouangeManager
									.getLouangeManagerForm();

							details.setSouvenir(currentSouvenir);

							//
							CellList<Louange> sList = celllLouangeManager
									.getLouangeCells();
							SelectionModel<? super Louange> model = sList
									.getSelectionModel();
							model.setSelected(currentSouvenir, false);
							//
							celllLouangeManager.setLouangeManagerForm(details);

							louangeView.setCell(celllLouangeManager);

							panel.setWidget(louangeView.asWidget());
							bind();
							appEventBus.fireEvent(new AppFreeEvent());

						}
					});

		}

		// }

	}

	@Override
	public void louangeSelected(Louange current,
			CellListLouangesManager cellListSouvenirManager, Range range,
			String category) {
		
		celllLouangeManager = cellListSouvenirManager;
		currentSouvenir = current;
		//LouangeDetailsActivity.range = range;

		louangeSelected = true;

		clientFactory.getPlaceController().goTo(
				new LouangeDetailsPlace(current.getId() + "&"
						+ range.getStart() + "&" + category));

	}

}
