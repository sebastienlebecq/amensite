package fr.nations.amen.client.mvp.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;

import fr.nations.amen.client.AuthentificationInfos;
import fr.nations.amen.client.acces.ChoiceViewPanel;
import fr.nations.amen.client.acces.LoginInfo;
import fr.nations.amen.client.acces.LoginService;
import fr.nations.amen.client.acces.LoginServiceAsync;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.places.AuthenticatePlace;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.mvp.presenters.AuthenticatePresenter;
import fr.nations.amen.client.mvp.views.AuthenticateView;


public class AuthenticateActivity extends AbstractActivity implements  AuthenticatePresenter {

private AuthenticateView authenticateView;
	
	public AuthenticateView getAuthenticateView() {
		return authenticateView;
	}

	private LoginInfo loginInfo;
	
	 AuthentificationInfos authentication = new AuthentificationInfos();

	 public static ChoiceViewPanel choiceView;

	private ClientFactory clientFactory;

	private AuthenticatePlace place;
	
	
	public AuthenticateActivity(AuthenticatePlace place2, ClientFactory clientFactory2) {
		this.clientFactory = clientFactory2;
		this.place = place2;
	
	}



	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		authenticateView =  clientFactory.getAuthenticateView();
		
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
							
							if (choiceView == null) choiceView = new ChoiceViewPanel(authentication);
							
							choiceView.setPresenter(AuthenticateActivity.this);

							authenticateView.setChoice(choiceView, authentication);
							panel.setWidget(authenticateView.asWidget());
							bind();
							
							//loadPresenter(panel);
							
							//clientFactory.getPlaceController().goTo(new AuthenticatePlace("loadLogin"));
							
							//
						} else {
							//loadLogin(panel);	//chemin ok
							authenticateView.setCell(authentication);
							
							panel.setWidget(authenticateView.asWidget());
							bind();
							
							//clientFactory.getPlaceController().goTo(new AuthenticatePlace("loadPreseneter"));
//							Window.alert("Authentification erronee!! Essaie encore une fois..." +
//									"Les voies du Seigneur sont impenetrables!");
						}
						
					}
				};
				// (3) Make the call. Control flow will continue immediately and later
				// 'callback' will be invoked when the RPC completes.
				loginService.login(GWT.getHostPageBaseURL(), acb);
				

	}

//private void loadPresenter(final AcceptsOneWidget panel) {
//		
//		//load choice view and redirect after click on the selected view
//		
//		if (choiceView == null) choiceView = new ChoiceViewPanel(authentication);
//		
//		choiceView.setPresenter(this);
//
//		authenticateView.setChoice(choiceView, authentication);
//		
////		if (managerActu ==null) managerActu = new ActualitesManager();
////		authenticateView.setCell(managerActu, authentication);
//		panel.setWidget(authenticateView.asWidget());
//		bind();
//		}
//	
//	private void loadLogin(final AcceptsOneWidget panel) {
//
//		authenticateView.setCell(authentication);
//		
//		panel.setWidget(authenticateView.asWidget());
//		bind();
//	}

	@Override
	public void go(HasWidgets container) {}



	@Override
	public void bind() {
		authenticateView.setPresenter(this);
		
	}

}
