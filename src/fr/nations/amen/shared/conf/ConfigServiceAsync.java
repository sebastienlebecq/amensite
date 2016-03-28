package fr.nations.amen.shared.conf;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ConfigServiceAsync {


	void saveConfig(ConfigSite cf, AsyncCallback<ConfigSite> callback);

	void getConfigSite(AsyncCallback<ConfigSite> asyncCallback);

	void getBlobStoreUploadImgUrl(AsyncCallback<String> callback);

	void getBlobStoreUploadDocUrl(AsyncCallback<String> asyncCallback);

	void getBlobStoreUploadDocUrl(String userid, String password,
			AsyncCallback<String> asyncCallback);

	



}
