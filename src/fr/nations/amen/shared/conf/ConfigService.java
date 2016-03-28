package fr.nations.amen.shared.conf;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("serviceConfig")
public interface ConfigService extends RemoteService{
	
	 ConfigSite saveConfig(ConfigSite cf);
	 
	 ConfigSite getConfigSite();

	String getBlobStoreUploadImgUrl();

	String getBlobStoreUploadDocUrl();

	String getBlobStoreUploadDocUrl(String userid, String password);

}
