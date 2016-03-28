package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import fr.nations.amen.client.AuthentificationInfos;
import fr.nations.amen.client.acces.ChoiceViewPanel;
import fr.nations.amen.client.acces.LoginInfo;
import fr.nations.amen.client.acces.LoginService;
import fr.nations.amen.client.acces.LoginServiceAsync;
import fr.nations.amen.client.acces.actu.celllist.ActualitesManager;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.presenters.AuthenticatePresenter;
import fr.nations.amen.client.mvp.views.AuthenticateView;

public class AuthenticatePresenterImpl implements AuthenticatePresenter {
	


	private AuthenticateView authenticateView;
	
	public AuthenticateView getAuthenticateView() {
		return authenticateView;
	}

	private LoginInfo loginInfo;
	
	AuthentificationInfos authentication = new AuthentificationInfos();

	private ChoiceViewPanel choiceView;

	public AuthenticatePresenterImpl(AuthenticateView authenticateView) {
		this.authenticateView = authenticateView;
		
		onAuthenticate();

		bind();
	}

	private void onAuthenticate() {
	
		//apel à la servlet d'authentification
		// (1) Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		// (2) Create an asynchronous callback to handle the result.
		AsyncCallback<LoginInfo> acb = new AsyncCallback<LoginInfo>() {


			public void onFailure(Throwable error) {
			}

			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				authentication.setLoginInfo(loginInfo);
				if (loginInfo.isLoggedIn()) {
					loadPresenter();
				} else {
					loadLogin();
//					Window.alert("Authentification erronee!! Essaie encore une fois..." +
//							"Les voies du Seigneur sont impenetrables!");
				}
				
			}
		};
		// (3) Make the call. Control flow will continue immediately and later
		// 'callback' will be invoked when the RPC completes.
		loginService.login(GWT.getHostPageBaseURL(), acb);
		History.newItem(Tokens.AUTHENTICATE);
	}
	
	private void loadPresenter() {
		
		//load choice view and redirect after click on the selected view
		
		if (choiceView == null) choiceView = new ChoiceViewPanel(authentication);
		
	//	choiceView.setPresenter(this);

		authenticateView.setChoice(choiceView, authentication);
		
//		if (managerActu ==null) managerActu = new ActualitesManager();
//		authenticateView.setCell(managerActu, authentication);
	    	
		}
	
	private void loadLogin() {

		authenticateView.setCell(authentication);
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(authenticateView.asWidget());;
		
	}

	@Override
	public void bind() {
		authenticateView.setPresenter(this);
		
	}
}
