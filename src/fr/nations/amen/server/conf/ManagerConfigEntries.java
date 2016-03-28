package fr.nations.amen.server.conf;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.server.PMF;
import fr.nations.amen.server.souvenirs.SouvenirEntry;
import fr.nations.amen.shared.conf.ConfigSite;

public class ManagerConfigEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerConfigEntries.class);
	private static boolean firsttime=false;

	public static ConfigEntry insert(long ldate, String appellation, String description, String imgPresUrl,
			String imgPresNsContacterUrl, String emailHtml, String adresseHtml, String photoBatiment, String integCode,
			 List<String> prefsvideo, List<String> prefsChant, /*List<String> prefsTexte, */
			List<String> downloadBlobKeyDocs, List<String> downloadEmails, String idCle, 
			String dirPicasa, Integer dirPicasaNb, String imgPresentationWidth) {

		//newActu.setDate(this.dateSouvenirBox.getValue());
		String concatPrefs = "";
		for (String pref : prefsvideo){
			concatPrefs  = concatPrefs +";"+ pref;
		}
		//enlève le premier char
		concatPrefs = concatPrefs.substring(1);
		
		/*********/
		String concatChantPrefs = "";
		for (String pref : prefsChant){
			concatChantPrefs  = concatChantPrefs +";"+ pref;
		}
		//enlève le premier char
		concatChantPrefs = concatChantPrefs.substring(1);
		/*********/
//		String concatTextePrefs = "";
//		for (String pref : prefsTexte){
//			concatTextePrefs  = concatTextePrefs +";"+ pref;
//		}
		//enlève le premier char
//		concatTextePrefs = concatTextePrefs.substring(1);
		/*********/
		String concatDownloadBlobKeyDocs = "";
		for (String pref : downloadBlobKeyDocs){
			concatDownloadBlobKeyDocs  = concatDownloadBlobKeyDocs +";"+ pref;
		}
		//enlève le premier char
		concatDownloadBlobKeyDocs = concatDownloadBlobKeyDocs.substring(1);
		/*************/
		String concatDownloadEmails = "";
		for (String pref : downloadEmails){
			concatDownloadEmails  = concatDownloadEmails +";"+ pref;
		}
		//enlève le premier char
		concatDownloadEmails = concatDownloadEmails.substring(1);
		
		/*********/
		ConfigEntry entry = new ConfigEntry(ldate, appellation, description, imgPresUrl, imgPresNsContacterUrl,
				emailHtml, adresseHtml, photoBatiment, integCode,concatPrefs, concatChantPrefs, /*concatTextePrefs,*/
				concatDownloadBlobKeyDocs, concatDownloadEmails, idCle, dirPicasa, dirPicasaNb, 
				imgPresentationWidth);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);

		return entry;
	}
	
	public static ConfigEntry update(String id, String appellation, String description, 
			String imgPresUrl,String imgPresNsContacterUrl,
			String emailHtml, String adresseHtml, String photoBatiment, String integCode, 
			List<String> prefsVideo, List<String> prefsChant, /*List<String> prefsTexte,*/
			List<String> downloadBlobKeyDocs, List<String> downloadEmails, String idCle, 
			String dirPicasa, Integer dirPicasaNb, String imgPresentationWidth)
			  {
		String concatPrefs = "";
		for (String pref : prefsVideo){
			concatPrefs = concatPrefs + ";"+ pref;
		}
		concatPrefs = concatPrefs.substring(1);
		/***************/
		String concatChantPrefs = "";
		for (String pref : prefsChant){
			concatChantPrefs  = concatChantPrefs +";"+ pref;
		}
		//enlève le premier char
		concatChantPrefs = concatChantPrefs.substring(1);
		/****************/
//		String concatTextePrefs = "";
//		for (String pref : prefsTexte){
//			concatTextePrefs  = concatTextePrefs +";"+ pref;
//		}
//		//enlève le premier char
//		concatTextePrefs = concatTextePrefs.substring(1);
		/******************/
		
		String concatDownloadBlobKeyDocs = "";
		for (String pref : downloadBlobKeyDocs){
			concatDownloadBlobKeyDocs  = concatDownloadBlobKeyDocs +";"+ pref;
		}
		//enlève le premier char
		concatDownloadBlobKeyDocs = concatDownloadBlobKeyDocs.substring(1);
		/******************/
		String concatDownloadEmails = "";
		for (String pref : downloadEmails){
			concatDownloadEmails  = concatDownloadEmails +";"+ pref;
		}
		//enlève le premier char
		concatDownloadEmails = concatDownloadEmails.substring(1);
		
		/*****************/
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		ConfigEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(ConfigEntry.class, Long.valueOf(id));
		 
		            e.setPhotoBatiment(photoBatiment);
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setImgPresentationUrl(imgPresUrl);
		            e.setImgPresentNousTrouverUrl(imgPresNsContacterUrl);
		            e.setEmailHtml(emailHtml);
		            e.setAdresseHtml(adresseHtml);
		            e.setIntegCode(integCode);
		            e.setPrefsVideo(concatPrefs);
		            e.setDownloadBlobKeyDocs(concatDownloadBlobKeyDocs);
		            e.setDownloadEmails(concatDownloadEmails);
		            e.setIdCle(idCle);
		            e.setDirPhotoPicasa(dirPicasa);
		            e.setDirPhotoPicasaNb(dirPicasaNb);
		            e.setImgPresentationWidth(imgPresentationWidth);

		    } finally {
		        pm.close();
		    }
		
		return e;
	}

	public static void delete(ConfigSite cf) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		ConfigEntry e = pm.getObjectById(ConfigEntry.class, cf.getIdNumber());

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }
		
	}
	
	public static ConfigEntry getConfigEntry(int elmt) {
		List<ConfigEntry> entry = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(ConfigEntry.class);
		// exception generated if no comment the first time
		query.setOrdering("dateActu DESC");
		//query.setRange(rangeStart, rangeLength);

		Object result = query.execute();
		if (result != null) {
			
			entry = (List<ConfigEntry>) result;
		}
		return entry.get(elmt);
	}

}
