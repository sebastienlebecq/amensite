package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.presenters.WelcomePresenter;
import fr.nations.amen.client.mvp.views.WelcomeView;
import fr.nations.amen.client.welcome.WelcomePresentation;

public class WelcomePresenterImpl implements WelcomePresenter {

	private final WelcomeView welcomeView;
	//WelcomePresentation welcomePresentation;

	public WelcomePresenterImpl(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
	//	this.onshowWelcome();
		bind();
	}
//	private void onshowWelcome() {
//		
//		GWT.runAsync(new RunAsyncCallback(){
//
//			@Override
//			public void onFailure(Throwable reason) {
//			}
//
//			@Override
//			public void onSuccess() {
//
//				if(welcomePresentation==null) welcomePresentation = new WelcomePresentation();
//		    	
//				ScrollPanel scroll = new ScrollPanel(welcomePresentation );
//				
//				welcomeView.setCell(scroll);
//		    	
//		    	History.newItem(Tokens.HOME);
//			
//			}
//    	});
//		
//	}
	public void bind() {
		welcomeView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(welcomeView.asWidget());
	}

	public void onshowPhotosButtonClicked() {
		History.newItem(Tokens.HOME);
	}
}
