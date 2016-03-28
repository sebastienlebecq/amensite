package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.acces.LoginInfo;
import fr.nations.amen.client.acces.LoginService;
import fr.nations.amen.client.acces.LoginServiceAsync;
import fr.nations.amen.client.acces.actu.celllist.CellListActuManager;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.presenters.AccesPresenter;
import fr.nations.amen.client.mvp.views.AccesView;

public class AccesPresenterImpl implements AccesPresenter {
	private final AccesView accesView;
	
	private LoginInfo loginInfo;
	//private ScrollPanel loginPanel = new ScrollPanel();
	Panel loginPanel = new FlowPanel();
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the Amen application.");
	
	private CellListActuManager celllActuManagerPanel;
	
	public AccesPresenterImpl(AccesView accesView) {
		this.accesView = accesView;

		this.onshowAcces();
		bind();
	}

	private void onshowAcces() {
		
		//apel à la servlet d'authentification
		// (1) Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		// (2) Create an asynchronous callback to handle the result.
		AsyncCallback<LoginInfo> acb = new AsyncCallback<LoginInfo>() {


			public void onFailure(Throwable error) {
			}

			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					loadPresenter();
				} else {
					Window.alert("Authentification erronee!! Essaie encore une fois..." +
							"Les voies du Seigneur sont impenetrables!");
					loadLogin();
				}
			}

			private void loadPresenter() {

				
				
				loginPanel.clear();
				signOutLink.setHref(loginInfo.getLogoutUrl());

				loginPanel.add(signOutLink);
				loginPanel.add(new Label("Bonjour "+loginInfo.getNickname()));

				if (celllActuManagerPanel ==null) celllActuManagerPanel = new CellListActuManager();
				loginPanel.add(celllActuManagerPanel);
				 ScrollPanel loginP = new ScrollPanel(loginPanel);
		    	accesView.setCell(loginP);
		    	
		    	History.newItem(Tokens.ACCES);
			}
		};
		// (3) Make the call. Control flow will continue immediately and later
		// 'callback' will be invoked when the RPC completes.
		loginService.login(GWT.getModuleBaseURL(), acb);

		
	}

	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		//RootPanel.get("noteList").add(loginPanel);
	}
	
	public void bind() {
		accesView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(accesView.asWidget());
	}
}
