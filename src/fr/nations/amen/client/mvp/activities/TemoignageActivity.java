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
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.events.AppBusyEvent;
import fr.nations.amen.client.mvp.events.AppFreeEvent;
import fr.nations.amen.client.mvp.places.TemoignagePlace;
import fr.nations.amen.client.mvp.presenters.TemoignagesPresenter;
import fr.nations.amen.client.mvp.views.TemoignagesView;
import fr.nations.amen.client.temoignagespublic.celllist.CellListTemoignagesManager;
import fr.nations.amen.client.temoignagespublic.celllist.TemoignagesManagerForm;
import fr.nations.amen.client.temoignagespublic.celllist.celltypes.custom.TemoignageCellWithUiBinder;

import fr.nations.amen.shared.temoignages.Temoignage;
import fr.nations.amen.shared.temoignages.TemoignagesService;
import fr.nations.amen.shared.temoignages.TemoignagesServiceAsync;

public class TemoignageActivity extends AbstractActivity implements
TemoignagesPresenter {

private ClientFactory clientFactory;
private TemoignagesView louangeView;
private EventBus appEventBus;

static private CellListTemoignagesManager celllTemoignageManager;
static private Temoignage currentSouvenir;

static public void initLouange() {
currentSouvenir = null;
}

private TemoignagesServiceAsync rpcService;
private TemoignagePlace place;
private TemoignagesManagerForm details;
private static boolean temoignageSelected = false;
//private static Range range;

public TemoignageActivity(TemoignagePlace place2,
	ClientFactory clientFactory2) {
this.clientFactory = clientFactory2;
this.place = place2;
this.appEventBus = clientFactory.getEventBus();
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
louangeView = clientFactory.getTemoignagesView();

if (celllTemoignageManager == null)
	celllTemoignageManager = new CellListTemoignagesManager(louangeView);

details = celllTemoignageManager.getTemoignagesManagerForm();

if (place.getId().equals("999999")) {

	TemoignageCellWithUiBinder scwb = celllTemoignageManager.getTcwub();
	scwb.setIndexSelected(UniformDim.OUTOFLIST);

	SingleSelectionModel<Temoignage> selectionModel = celllTemoignageManager
			.getSelectionModel();
	//selectionModel.getSelectedSet().clear();

	CellList<Temoignage> sList = celllTemoignageManager.getTemoignageList();
	details.setTemoignage(null);

	// importantn car sinon non rafraichissement
	sList.redraw();

	celllTemoignageManager.setTemoignagesManagerForm(details);

	louangeView.setCell(celllTemoignageManager);

	panel.setWidget(louangeView.asWidget());
	bind();

	appEventBus.fireEvent(new AppFreeEvent());

} else if (temoignageSelected == true) { // else if (currentSouvenir ==
										// null)
	temoignageSelected = false;
	// situation normale : click sur la liste de cells

	details.setTemoignage(currentSouvenir);

	celllTemoignageManager.setTemoignagesManagerForm(details);

	louangeView.setCell(celllTemoignageManager);

	panel.setWidget(louangeView.asWidget());
	bind();
	appEventBus.fireEvent(new AppFreeEvent());

} else {

	// algorithme pour retrouver le bon souvenir:
	// faire une requête assynchrone

	rpcService = GWT.create(TemoignagesService.class);

	// il faut obtenir la bonne categorie et l'afficher en conséquence :
	// auytr mapper livre samy jabber

	// int rStart = range.getStart()/2;

	rpcService.getTemoignageByCategory(place.getCategory(), new Integer(
			place.getStartPage()).intValue(),
			new Integer(place.getStartPage()).intValue()
					+ UniformDim.PAGE_PUBLIC_SIZE,
			new AsyncCallback<Vector<Temoignage>>() {

				public void onFailure(Throwable caught) {
					Window.alert("Error" + caught.getMessage());
					appEventBus.fireEvent(new AppFreeEvent());
				}

				public void onSuccess(Vector<Temoignage> result) {

					// itération

					for (Temoignage elmt : result) {

						if (elmt.getId().equals(place.getId())) {
							currentSouvenir = elmt;

							break;
						}
					}

					if (currentSouvenir == null) {
						currentSouvenir = result.get(0);
					} // valeur fausse pour l'exécuton sans exception 
					// mais heureusement cas non réaliste

					details = celllTemoignageManager
							.getTemoignagesManagerForm();

					details.setTemoignage(currentSouvenir);

					//
					CellList<Temoignage> sList = celllTemoignageManager
							.getTemoignageList();
					SelectionModel<? super Temoignage> model = sList
							.getSelectionModel();
					model.setSelected(currentSouvenir, false);
					//
					celllTemoignageManager.setTemoignagesManagerForm(details);

					louangeView.setCell(celllTemoignageManager);

					panel.setWidget(louangeView.asWidget());
					bind();
					appEventBus.fireEvent(new AppFreeEvent());

				}
			});

}

// }

}

@Override
public void temoignageSelected(Temoignage current,
	CellListTemoignagesManager cellListSouvenirManager, Range range,
	String category) {
celllTemoignageManager = cellListSouvenirManager;
currentSouvenir = current;
//LouangeDetailsActivity.range = range;

temoignageSelected = true;

clientFactory.getPlaceController().goTo(
		new TemoignagePlace(current.getId() + "&"
				+ range.getStart() + "&" + category));

}

}
