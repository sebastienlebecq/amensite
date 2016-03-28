package fr.nations.amen.server.forumeglise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.server.PMF;
import fr.nations.amen.shared.forumeglise.ForumEglise;

public class ManagerForumEgliseEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerForumEgliseEntries.class);
	private static boolean firsttime=false;

	public static ForumEgliseEntry insert(String cat, String appelation, String description, String albumRefPicasa,
			long dateActu, String refYoutube, String image) {

		ForumEgliseEntry entry = new ForumEgliseEntry(cat, appelation, description, dateActu,
				albumRefPicasa, refYoutube, image);
		entry.setNbPagesVues(0);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);

		return entry;
	}

	public static List<ForumEgliseEntry> getAllEntries(int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ForumEgliseEntry> entries = new ArrayList<ForumEgliseEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 ForumEgliseEntry first = new ForumEgliseEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "");
		 ForumEgliseEntry second = new ForumEgliseEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		 ForumEgliseEntry third = new ForumEgliseEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		
		 Collection<ForumEgliseEntry> coll = new ArrayList<ForumEgliseEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour

		Query query = pm.newQuery(ForumEgliseEntry.class);
		// exception generated if no comment the first time
		query.setOrdering("dateActu DESC");
		query.setRange(rangeStart, rangeLength);
		Object results = query.execute();
		if (results != null) {
			entries = (List<ForumEgliseEntry>) results;
		}

		return entries;
	}

	public static List<ForumEgliseEntry> getEntriesByCategory(String category, int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ForumEgliseEntry> entries = new ArrayList<ForumEgliseEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 ForumEgliseEntry first = new ForumEgliseEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "");
		 ForumEgliseEntry second = new ForumEgliseEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		 ForumEgliseEntry third = new ForumEgliseEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		
		 Collection<ForumEgliseEntry> coll = new ArrayList<ForumEgliseEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(ForumEgliseEntry.class);
				// exception generated if no comment the first time
				query.setFilter("category == categoryParam");
				query.setOrdering("appellation DESC");
				query.declareParameters("String categoryParam");
				query.setRange(rangeStart, rangeLength);
				Object results = query.execute(category);
				if (results != null) {
					entries = (List<ForumEgliseEntry>) results;
				}
		 
		return entries;
	}
	
	
	
	public static void delete(ForumEglise actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		ForumEgliseEntry e = pm.getObjectById(ForumEgliseEntry.class, Long.valueOf(actu.getId()));

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}
	
	public static ForumEgliseEntry findById(Long long1){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return  pm.getObjectById(ForumEgliseEntry.class, long1);
	}
	
	public static ForumEgliseEntry update(String category, String id, String appellation, String description, 
			String albumRefPicasa, long time, String refYoutube, String image, String index) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		ForumEgliseEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(ForumEgliseEntry.class, Long.valueOf(id));
		 
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setAlbumRefPicasa(albumRefPicasa);
		            e.setTime(time);
		            e.setRefYoutube(refYoutube);
		            e.setCategory(category);
		            e.setImage(image);
		            e.setIndexInCells(index);
		            
		            //set number
		    		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		            e.setNbPagesVues(pageEntry.getNbVisitePage());
		            
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
	}

	public static List<ForumEglise> getEntries(String category, int start,
			int length) {

		List<ForumEgliseEntry> list = ManagerForumEgliseEntries.getEntriesByCategory(category,
				start, length);
		Vector<ForumEglise> vect = new Vector<ForumEglise>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (ForumEgliseEntry act : list) {

			ForumEglise souvenir = new ForumEglise(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
//			souvenir.setBlobKey(act.getBlobKey());
//			souvenir.setBlobKeyogg(act.getBlobKeyogg());
//			souvenir.setBlobKeywav(act.getBlobKeywav());
			souvenir.setCategory(category);
			souvenir.setNbPagesVues(act.getNbPagesVues());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}
//	Blob imageFor(String name, HttpServletResponse res) {
//	    // find desired image
//	    PersistenceManager pm = PMF.get().getPersistenceManager();
//	    Query query = pm.newQuery("select from MyImage " +
//	        "where name = nameParam " +
//	        "parameters String nameParam");
//	    List<MyImage> results = (List<MyImage>)query.execute(name);
//	    Blob image = results.iterator().next().getImage();
//
//	    // serve the first image
//	    res.setContentType("image/jpeg");
//	    res.getOutputStream().write(image.getBytes());
//	}

	
	
	public static List<ForumEgliseEntry> getEntriesMoreSeen(int start, int length) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ForumEgliseEntry> entries = new ArrayList<ForumEgliseEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 ForumEgliseEntry first = new ForumEgliseEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "");
		 ForumEgliseEntry second = new ForumEgliseEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		 ForumEgliseEntry third = new ForumEgliseEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		
		 Collection<ForumEgliseEntry> coll = new ArrayList<ForumEgliseEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(ForumEgliseEntry.class);
				// exception generated if no comment the first time
				query.setOrdering("nbPagesVues DESC");
				query.setRange(start, length);
				Object results = query.execute();
				if (results != null) {
					entries = (List<ForumEgliseEntry>) results;
				}
		 
		return entries;
	}


}
