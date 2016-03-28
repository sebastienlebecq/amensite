package fr.nations.amen.server.louanges2;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.nations.amen.client.acces.LoginInfo;
import fr.nations.amen.client.acces.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	public LoginInfo login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();
		
		if (user != null && userService.isUserAdmin()) {
			System.out.println("autentification réussie");
			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(user.getEmail());
			loginInfo.setNickname(user.getNickname());
			//loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));

		} else {
			System.out.println("Echec d'autentification");
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
			//loginInfo.setLoginUrl(userService.createLoginURL("/"));
		}
		
		return loginInfo;
	}

	@Override
	public LoginInfo getLoginInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}