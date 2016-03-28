package fr.nations.amen.client.mvp.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;

import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.places.WherePlace;
import fr.nations.amen.client.mvp.presenters.WherePresenter;
import fr.nations.amen.client.mvp.views.WhereView;
import fr.nations.amen.client.ounoustrouver.TransitDirectionsServiceMapWidget;



public class WhereActivity extends AbstractActivity implements WherePresenter {

	private ClientFactory clientFactory;
	private WhereView whereView;
	
	
	private Composite myTabLayoutPanel;

	public WhereActivity(WherePlace place, ClientFactory clientFactory2) {
		this.clientFactory = clientFactory2;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		whereView = clientFactory.getWhereView();
		
		// à rajouter
		if(myTabLayoutPanel==null)  myTabLayoutPanel = new TransitDirectionsServiceMapWidget();
		whereView.setCell(myTabLayoutPanel);
		
		
		panel.setWidget(whereView.asWidget());
		bind();		
	}

	public void bind() {
		whereView.setPresenter(this);
	}

	public void onshowPhotosButtonClicked() {
		//goTo(new PhotoListPlace("1"));
	}
	
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

	@Override
	public void go(HasWidgets container) {
		
	}

}