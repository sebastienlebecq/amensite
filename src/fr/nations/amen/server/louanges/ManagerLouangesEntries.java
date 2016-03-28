package fr.nations.amen.server.louanges;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;


import fr.nations.amen.server.PMF;
import fr.nations.amen.server.souvenirs.ManagerPages;
import fr.nations.amen.server.souvenirs.PageEntry;
import fr.nations.amen.shared.louanges.Louange;


public class ManagerLouangesEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerLouangesEntries.class);
	private static boolean firsttime=false;

	public static LouangesEntry insert(String category, String appelation, String description, String albumRefPicasa,
			long dateActu, String refYoutube, String blobKey, String blobKeyogg, String blobKeywav, 
			String numero, String image, String indexInCells) {

		LouangesEntry entry = new LouangesEntry(category, appelation, description, dateActu,
				albumRefPicasa, refYoutube, blobKey, blobKeyogg, blobKeywav, numero, image, indexInCells);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);

		return entry;
	}

	public static List<LouangesEntry> getEntries(String category, int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<LouangesEntry> entries = new ArrayList<LouangesEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 LouangesEntry first = new LouangesEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","","", "", "","1","","");
		 LouangesEntry second = new LouangesEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "","2","","");
		 LouangesEntry third = new LouangesEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "","3","","");
		
		 Collection<LouangesEntry> coll = new ArrayList<LouangesEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
			
			Query query = pm.newQuery(LouangesEntry.class);
			// exception generated if no comment the first time
			query.setFilter("category == categoryParam");
			query.setOrdering("numero ASC");
			query.declareParameters("String categoryParam");
			query.setRange(rangeStart, rangeLength);
			Object results = query.execute(category);
			if (results != null) {
				entries = (List<LouangesEntry>) results;
		}
		 
		return entries;
	}

	public static void delete(Louange actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		LouangesEntry e = pm.getObjectById(LouangesEntry.class, actu.getId());

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}

	public static LouangesEntry update(String category,String id, String appellation, String description, 
			String albumRefPicasa, long time, String refYoutube, String blobKey, String blobKeyogg, String blobKeywav,
			String numero, String image, String indexInCells) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		LouangesEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(LouangesEntry.class, Long.valueOf(id));
		 
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setAlbumRefPicasa(albumRefPicasa);
		            e.setTime(time);
		            e.setRefYoutube(refYoutube);
		            e.setBlobKey(blobKey);
		            e.setBlobKeyogg(blobKeyogg);
		            e.setBlobKeywav(blobKeywav);
		            e.setCategory(category);
		            e.setNumero(numero);
		            e.setImage(image);
		            e.setIndexInCells(indexInCells);
		            
		            //set number
		    		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		            e.setNbPagesVues(pageEntry.getNbVisitePage());
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
	}

	public static List<LouangesEntry> getAllEntries(
			int rangeStart, int rangeLength) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<LouangesEntry> entries = new ArrayList<LouangesEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 LouangesEntry first = new LouangesEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","","", "", "","1", "","");
		 LouangesEntry second = new LouangesEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "", "2", "","");
		 LouangesEntry third = new LouangesEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "","3", "","");
		
		 Collection<LouangesEntry> coll = new ArrayList<LouangesEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
			
			Query query = pm.newQuery(LouangesEntry.class);
			query.setOrdering("appellation ASC");
			query.setRange(rangeStart, rangeLength);
			Object results = query.execute();
			if (results != null) {
				entries = (List<LouangesEntry>) results;
			}

		return entries;
	}

	public static LouangesEntry findById(Long long1) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return  pm.getObjectById(LouangesEntry.class, long1);
	}

	public static List<LouangesEntry> getEntriesMoreSeen(int start, int length) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<LouangesEntry> entries = new ArrayList<LouangesEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 LouangesEntry first = new LouangesEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", longDescription, longDescription, longDescription, longDescription, "","");
		 LouangesEntry second = new LouangesEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", longDescription, longDescription, longDescription, longDescription, "","");
		 LouangesEntry third = new LouangesEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", longDescription, longDescription, longDescription, longDescription, "","");
		
		 Collection<LouangesEntry> coll = new ArrayList<LouangesEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(LouangesEntry.class);
				// exception generated if no comment the first time
				query.setOrdering("nbPagesVues DESC");
				query.setRange(start, length);
				Object results = query.execute();
				if (results != null) {
					entries = (List<LouangesEntry>) results;
				}
		 
		return entries;
	}

	public static int getCountMax() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		//Set objs = pm.getManagedObjects();
		List<LouangesEntry> entries = new ArrayList<LouangesEntry>();
		
		Query query = pm.newQuery(LouangesEntry.class);
		Object results = query.execute();
		if (results != null) {
			entries = (List<LouangesEntry>) results;
		}
		return entries.size();
	}
}

