package fr.nations.amen.server.messagesaudio;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import fr.nations.amen.server.PMF;


public class ManagerPages {
	

public static PageEntry getPageEntryByGp(String id) {
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	Query q = pm.newQuery(PageEntry.class);
	q.setFilter("gpSouvenir == idSouvenirParam");
	q.setOrdering("hireDate desc");
	q.declareParameters("Long idSouvenirParam");
	
	Object res = q.execute(Long.valueOf(id));
	PageEntry result = null;
	List<PageEntry> entries = null;
	
	if (res != null) {
		entries = (List<PageEntry>) res;
	}
	if (entries.isEmpty()){
		return null;
	} else {
	return entries.get(0);
	}
}

public static PageEntry update(Long idGP, Long longId, long hireDate, int nbVisitePage) {
	//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
	PageEntry e = null;
	PersistenceManager pm = PMF.get().getPersistenceManager();
	 try {
		 e = pm.getObjectById(PageEntry.class, Long.valueOf(longId));
	 
	            e.setGpSouvenir(idGP);
	            e.setHireDate(hireDate);
	            e.setNbVisitePage(nbVisitePage);      
	   
	    } finally {
	        pm.close();
	    }
	
	return e;
}



public static void storeNbPage(Long longGP, Long longDate, int value) {
	
	PageEntry entry = new PageEntry();
	
	entry.setHireDate(longDate);
	entry.setGpSouvenir(longGP);
	entry.setNbVisitePage(value);
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	pm.makePersistent(entry);

}

}
