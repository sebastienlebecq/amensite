package fr.nations.amen.server.temoignages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.server.PMF;
import fr.nations.amen.server.souvenirs.SouvenirEntry;
import fr.nations.amen.server.temoignages.ManagerPages;
import fr.nations.amen.server.temoignages.PageEntry;
import fr.nations.amen.shared.temoignages.Temoignage;

public class ManagerTemoignagesEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerTemoignagesEntries.class);
	private static boolean firsttime=false;

	public static TemoignageEntry insert(String cat, String appelation, String description, String albumRefPicasa,
			long dateActu) {

		TemoignageEntry entry = new TemoignageEntry(cat,appelation, description, dateActu);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		entry.setNbPagesVues(0);
		pm.makePersistent(entry);

		return entry;
	}

	public static List<TemoignageEntry> getEntries(int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<TemoignageEntry> entries = new ArrayList<TemoignageEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 TemoignageEntry first = new TemoignageEntry("cat0","appelation0", longDescription,
		 new Date().getTime());
		 TemoignageEntry second = new TemoignageEntry("cat0","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		 TemoignageEntry third = new TemoignageEntry("cat0","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		
		 Collection<TemoignageEntry> coll = new ArrayList<TemoignageEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour

		// TODO rechercher la requete JDO prenant en compte les paramètres
		Query query = pm.newQuery(TemoignageEntry.class);
		// exception generated if no comment the first time
		query.setOrdering("dateActu DESC");
		query.setRange(rangeStart, rangeLength);
		Object results = query.execute();
		if (results != null) {
			entries = (List<TemoignageEntry>) results;
		}
		return entries;
	}

	public static void delete(Temoignage actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		TemoignageEntry e = pm.getObjectById(TemoignageEntry.class, actu.getId());

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}

	public static TemoignageEntry update(String cat, String id, String appellation, String description, 
			String albumRefPicasa, long time, String index) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		TemoignageEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(TemoignageEntry.class, Long.valueOf(id));
			 		e.setCategory(cat);
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setAlbumRefPicasa(albumRefPicasa);
		            e.setTime(time);
		            e.setIndexInCells(index);
		            
		            PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		            e.setNbPagesVues(pageEntry.getNbVisitePage());
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
	}

	public static List<TemoignageEntry> getEntriesByCategory(String category,
			int start, int length) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<TemoignageEntry> entries = new ArrayList<TemoignageEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 TemoignageEntry first = new TemoignageEntry("cat0","appelation0", longDescription,
		 new Date().getTime());
		 TemoignageEntry second = new TemoignageEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		 TemoignageEntry third = new TemoignageEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		
		 Collection<TemoignageEntry> coll = new ArrayList<TemoignageEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(TemoignageEntry.class);
				// exception generated if no comment the first time
				query.setFilter("category == categoryParam");
				query.setOrdering("dateActu DESC");
				query.declareParameters("String categoryParam");
				query.setRange(start, length);
				Object results = query.execute(category);
				if (results != null) {
					entries = (List<TemoignageEntry>) results;
				}
		 
		return entries;
	}

	public static List<TemoignageEntry> getEntriesMoreSeen(int start, int length) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<TemoignageEntry> entries = new ArrayList<TemoignageEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 TemoignageEntry first = new TemoignageEntry("cat0","appelation0", longDescription,
		 new Date().getTime());
		 TemoignageEntry second = new TemoignageEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		 TemoignageEntry third = new TemoignageEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		
		 Collection<TemoignageEntry> coll = new ArrayList<TemoignageEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(TemoignageEntry.class);
				// exception generated if no comment the first time
				query.setOrdering("nbPagesVues DESC");
				query.setRange(start, length);
				Object results = query.execute();
				if (results != null) {
					entries = (List<TemoignageEntry>) results;
				}
		 
		return entries;
	}

	public static TemoignageEntry findById(Long long1) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return  pm.getObjectById(TemoignageEntry.class, long1);
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
	
	public static int getCountMax() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		//Set objs = pm.getManagedObjects();
		List<TemoignageEntry> entries = new ArrayList<TemoignageEntry>();
		
		Query query = pm.newQuery(TemoignageEntry.class);
		Object results = query.execute();
		if (results != null) {
			entries = (List<TemoignageEntry>) results;
		}
		return entries.size();
	}

}
