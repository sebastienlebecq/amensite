package fr.nations.amen.client.mvp.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;

import fr.nations.amen.client.download.DownloadWidget;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.places.DownloadPlace;
import fr.nations.amen.client.mvp.presenters.DownloadPresenter;
import fr.nations.amen.client.mvp.presenters.WherePresenter;
import fr.nations.amen.client.mvp.views.DownloadView;
import fr.nations.amen.client.mvp.views.WhereView;
import fr.nations.amen.client.ounoustrouver.TransitDirectionsServiceMapWidget;

public class DownloadActivity extends AbstractActivity implements DownloadPresenter{

	private ClientFactory clientFactory;
	private DownloadView downloadView;
	
	
	private Composite myDownloadPanel;
	
	public DownloadActivity(DownloadPlace place, ClientFactory clientFactory2) {
		this.clientFactory = clientFactory2;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		downloadView = clientFactory.getDownloadView();
		
		if(myDownloadPanel==null)  myDownloadPanel = new DownloadWidget();
		downloadView.setCell(myDownloadPanel);
		
		panel.setWidget(downloadView.asWidget());
		bind();		
	}

	public void bind() {
		downloadView.setPresenter(this);
	}
	
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
