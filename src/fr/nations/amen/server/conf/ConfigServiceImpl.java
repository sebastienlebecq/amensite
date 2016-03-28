package fr.nations.amen.server.conf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.mortbay.util.Password;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.nations.amen.shared.conf.ConfigService;
import fr.nations.amen.shared.conf.ConfigSite;

public class ConfigServiceImpl extends RemoteServiceServlet implements
		ConfigService {

	private static final Logger logger = Logger
			.getLogger(ConfigServiceImpl.class);

	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	public ConfigServiceImpl() {

		BasicConfigurator.configure();

	}

	private static final long serialVersionUID = 1L;

	public void remove(ConfigSite actu) {

		ManagerConfigEntries.delete(actu);
	}

	public ConfigSite saveConfig(ConfigSite cf) {

		long ldate = new Date().getTime();

		ConfigEntry registeredCf = ManagerConfigEntries.insert(ldate,
				cf.getLogo(), cf.getDescription(),
				cf.getImgPresentationUrl(),cf.getImgPresentNsTrouverUrl() , cf.getEmailHtml(),
				cf.getAdresseHtml(), cf.getPhotoBatiment(),
				cf.getUrlIntegration(), cf.getPrefsVideo(),
				cf.getPrefsChanson(), /*cf.getPrefsTexte(),*/ cf.getDownloadBlobKeyDocs(), 
				cf.getDownloadEmails(), cf.getIdCle(),
				cf.getDirPhotoPicasa(), cf.getDirPhotoPicasaNb(), cf.getImgPresentationWidth());

		ConfigSite cfnew = new ConfigSite();
		cfnew.setIdNumber(registeredCf.getId().toString());
		cfnew.setDescription(registeredCf.getDescription().toString());
		cfnew.setPhotoBatiment(registeredCf.getPhotoBatiment());
		cfnew.setImgPresentationUrl(registeredCf.getImgPresentationUrl());
		cfnew.setAdresseHtml(registeredCf.getAdresseHtml());
		cfnew.setEmailHtml(registeredCf.getEmailHtml());
		cfnew.setUrlIntegration(registeredCf.getIntegCode());
		cfnew.setIdCle(registeredCf.getIdCle());
		cfnew.setImgPresentNsTrouverUrl(registeredCf.getImgPresentNousTrouverUrl());
		cfnew.setDirPhotoPicasa(registeredCf.getDirPhotoPicasa());
		cfnew.setDirPhotoPicasaNb(registeredCf.getDirPhotoPicasaNb());
		cfnew.setImgPresentationWidth(registeredCf.getImgPresentationWidth());

		String[] prefs = registeredCf.getPrefsVideo().split(";");

		List<String> arrayListPref = new ArrayList<String>();

		for (String pref : prefs) {
			arrayListPref.add(pref);
		}

		cfnew.setPrefsVideo(arrayListPref);

		/*******************/
		String[] prefsChant = registeredCf.getPrefsChanson().split(";");

		List<String> arrayListPrefChant = new ArrayList<String>();

		for (String pref : prefsChant) {
			arrayListPrefChant.add(pref);
		}

		cfnew.setPrefsChanson(arrayListPrefChant);

		/*********************/

//		String[] prefsTexte = registeredCf.getPrefsTexte().split(";");
//
//		List<String> arrayListPrefTexte = new ArrayList<String>();
//
//		for (String pref : prefsTexte) {
//			arrayListPrefTexte.add(pref);
//		}
//
//		cfnew.setPrefsTexte(arrayListPrefTexte);

		/**********************/
		String[] downloadBlobKeyDocs = registeredCf.getDownloadBlobKeyDocs().split(";");

		List<String> arrayDownloadBlobKeyDocs = new ArrayList<String>();

		for (String pref : downloadBlobKeyDocs) {
			arrayDownloadBlobKeyDocs.add(pref);
		}
		
		cfnew.setDownloadBlobKeyDocs(arrayDownloadBlobKeyDocs);
		/*****************/
		
		String[] downloadEmails =  registeredCf.getDownloadEmails().split(";");
		
		List<String> arrayDownloadEmails = new ArrayList<String>();

		for (String pref : downloadEmails) {
			arrayDownloadEmails.add(pref);
		}
		
		cfnew.setDownloadEmails(arrayDownloadEmails);
		/****************/
		return cf;
	}

	public ConfigSite getConfigSite() {

		ConfigEntry cfEntry = ManagerConfigEntries.getConfigEntry(0);

		ConfigSite cf = new ConfigSite();
		cf.setIdNumber(cfEntry.getId().toString());
		cf.setIdCle(cfEntry.getIdCle());
		cf.setDescription(cfEntry.getDescription().toString());
		cf.setPhotoBatiment(cfEntry.getPhotoBatiment());
		cf.setImgPresentationUrl(cfEntry.getImgPresentationUrl());
		cf.setImgPresentNsTrouverUrl(cfEntry.getImgPresentNousTrouverUrl());
		cf.setAdresseHtml(cfEntry.getAdresseHtml());
		cf.setEmailHtml(cfEntry.getEmailHtml());
		cf.setUrlIntegration(cfEntry.getIntegCode());
		cf.setIdCle(cfEntry.getIdCle());
		cf.setDirPhotoPicasa(cfEntry.getDirPhotoPicasa());
		cf.setDirPhotoPicasaNb(cfEntry.getDirPhotoPicasaNb());
		cf.setImgPresentationWidth(cfEntry.getImgPresentationWidth());

		if (cfEntry.getPrefsVideo() != null) {
			String[] prefs = cfEntry.getPrefsVideo().split(";");

			List<String> arrayListPref = new ArrayList<String>();

			for (String pref : prefs) {
				arrayListPref.add(pref);
			}

			cf.setPrefsVideo(arrayListPref);
		}

		/****************/
		if (cfEntry.getPrefsChanson() != null) {
			String[] prefsChant = cfEntry.getPrefsChanson().split(";");

			List<String> arrayListPrefChant = new ArrayList<String>();

			for (String pref : prefsChant) {
				arrayListPrefChant.add(pref);
			}

			cf.setPrefsChanson(arrayListPrefChant);
		}

		/********************/
//		if (cfEntry.getPrefsTexte() != null) {
//			String[] prefsTexte = cfEntry.getPrefsTexte().split(";");
//
//			List<String> arrayListPrefTexte = new ArrayList<String>();
//
//			for (String pref : prefsTexte) {
//				arrayListPrefTexte.add(pref);
//			}
//
//			cf.setPrefsTexte(arrayListPrefTexte);
//		}

		/********************/
		if(cfEntry.getDownloadBlobKeyDocs()!=null){
			
			String[] prefDownloadBlobKeyDocs = cfEntry.getDownloadBlobKeyDocs().split(";");

			List<String> arrayDownloadBlobKeyDocs = new ArrayList<String>();

			for (String pref : prefDownloadBlobKeyDocs) {
				arrayDownloadBlobKeyDocs.add(pref);
			}

			cf.setDownloadBlobKeyDocs(arrayDownloadBlobKeyDocs);
		}
		/******************/
		if(cfEntry.getDownloadEmails()!=null){
			
			String[] prefDownloadEmails = cfEntry.getDownloadEmails().split(";");

			List<String> arrayDownloadEmails = new ArrayList<String>();

			for (String pref : prefDownloadEmails) {
				arrayDownloadEmails.add(pref);
			}

			cf.setDownloadEmails(arrayDownloadEmails);
		}
		
		/*******************/

		return cf;
	}

	@Override
	public String getBlobStoreUploadImgUrl() {
		String url = blobstoreService.createUploadUrl("/amen/uploadImg");

		// change the computer name to standard localhost ip address, if in dev
		// mode
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) {
			url = url.replace("pc-geek", "127.0.0.1");
			// url = url.replace("seb-PC", "127.0.0.1");
		}

		return url;
	}

	@Override
	public String getBlobStoreUploadDocUrl() {
		String url = blobstoreService.createUploadUrl("/amen/uploadDoc");

		// change the computer name to standard localhost ip address, if in dev
		// mode
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) {
			url = url.replace("pc-geek", "127.0.0.1");
			// url = url.replace("seb-PC", "127.0.0.1");
		}

		return url;
	}

	@Override
	public String getBlobStoreUploadDocUrl(String userid, String password) {
		
		//TODO: Test si autorisation, puis génération
		
		ConfigSite cf = getConfigSite();
		
		List<String> emails = cf.getDownloadEmails();
		
		
		
		if (emails.contains(userid)){
			
			if (password.equals(cf.getIdCle())){
				
				return "autorisation";
				//TODO : à coder
			}
			
		}

		return "";
	}

}
