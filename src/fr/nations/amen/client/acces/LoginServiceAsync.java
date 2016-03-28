package fr.nations.amen.client.acces;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
  public void login(String requestUri, AsyncCallback<LoginInfo> async);

void getLoginInfo(AsyncCallback<LoginInfo> callback);
}