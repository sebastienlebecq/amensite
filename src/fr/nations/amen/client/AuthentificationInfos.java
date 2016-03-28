package fr.nations.amen.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.nations.amen.client.acces.LoginInfo;

public class AuthentificationInfos {

	private LoginInfo loginInfo;

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public AuthentificationInfos() {

	}

	public HTML getInitPanel() {
//		VerticalPanel vPanel2 = new VerticalPanel();
//		vPanel2.add(new Label(
//				"Please sign in to your Google Account to access the Amen application."));
////		vPanel2.add(new Label(
////				"Nous avons confiance en Google et en la NSA, car nous n'avons rien a cacher,"
////						+ " vu que nous sommes de bon chretiens!"));
//
		Anchor signIn = new Anchor("Sign In");
		signIn.setHref(loginInfo.getLoginUrl());
//
//		vPanel2.add(signIn);
//
//		return vPanel2;
		
		
		return new HTML("Please sign in to your Google Account to access the Amen application."
				+signIn+"   <img src='https://developers.google.com/appengine/images/appengine-silver-120x30.gif'" +
						" alt='Powered by Google App Engine' />");
		
	}

//	public Panel getPanel() {
//
//		if (loginInfo.isLoggedIn())	return getOkPanel();
//		return getInitPanel();
//	}

	public HTML getHTMLOk(){
		
		Anchor signOut = new Anchor("Sign Out");
		signOut.setHref(loginInfo.getLogoutUrl());
		
		return new HTML("Bonjour " + loginInfo.getNickname()
				+ " Si vous voulez sortir :"+signOut);
	}
}
