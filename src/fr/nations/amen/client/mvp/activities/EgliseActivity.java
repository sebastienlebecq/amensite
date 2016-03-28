package fr.nations.amen.client.mvp.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.eglise.visionpublic.MenuEgliseVision;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.places.EglisePlace;
import fr.nations.amen.client.mvp.presenters.EglisePresenter;
import fr.nations.amen.client.mvp.presenters.WelcomePresenter;
import fr.nations.amen.client.mvp.views.EgliseView;
import fr.nations.amen.client.mvp.views.WelcomeView;
import fr.nations.amen.shared.eglise.MenuEglise;
import fr.nations.amen.shared.eglise.MenuEgliseService;
import fr.nations.amen.shared.eglise.MenuEgliseServiceAsync;

public class EgliseActivity extends AbstractActivity implements EglisePresenter {
	
	private ClientFactory clientFactory;
	private EgliseView egliseView;
	private EglisePlace place;
	private ScrollPanel scroll;
	
	MenuEgliseServiceAsync mEgliseService = GWT.create(MenuEgliseService.class);
	
	public EgliseActivity(EglisePlace place2, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.place = place2;
	}


	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		
		
		if (place.getArticleId().equals("999")){
			egliseView = clientFactory.getEgliseView();
			
			
			
			
			panel.setWidget(egliseView.asWidget());
			bind();	
			
		} else {
			
			
			
			Long id = new Long(place.getArticleId());
			
			mEgliseService.getMenuEgliseById(id, new AsyncCallback<MenuEglise>() {

				

				public void onSuccess(MenuEglise result) {
					
					MenuEgliseVision eglisePanel = new  MenuEgliseVision(result);
					scroll = new ScrollPanel(eglisePanel);
					//eglisePanel.setMenuEglise(result);
					
					egliseView = clientFactory.getEgliseView();
					egliseView.setCell(scroll);
					panel.setWidget(egliseView.asWidget());
					bind();	
					
					
				}

				public void onFailure(Throwable caught) {
					// TODO: Show an error message.
				}
			});
			
			
			
		}
		
		

	}
	
	public void bind() {
		egliseView.setPresenter(this);
	}

}
