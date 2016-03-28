package fr.nations.amen.client.mvp.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.places.WelcomePlace;
import fr.nations.amen.client.mvp.presenters.WelcomePresenter;
import fr.nations.amen.client.mvp.views.WelcomeView;


public class WelcomeActivity extends AbstractActivity implements WelcomePresenter {

	private ClientFactory clientFactory;
	private WelcomeView welcomeView;

	public WelcomeActivity(WelcomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		welcomeView = clientFactory.getWelcomeView();
		panel.setWidget(welcomeView.asWidget());
		bind();		
	
	}

	public void bind() {
		welcomeView.setPresenter(this);
	}

	public void onshowPhotosButtonClicked() {
		//goTo(new PhotoListPlace("1"));
	}
	
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }






}
