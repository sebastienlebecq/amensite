package fr.nations.amen.server.eglise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.server.PMF;
import fr.nations.amen.server.messagesaudio.ManagerMessageAudioEntries;
import fr.nations.amen.server.messagesaudio.MessageAudioEntry;
import fr.nations.amen.server.souvenirs.SouvenirEntry;
import fr.nations.amen.shared.messagesaudio.MessageAudio;
import fr.nations.amen.shared.eglise.MenuEglise;

public class ManagerMenuEgliseEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerMenuEgliseEntries.class);
	private static boolean firsttime=false;

	public static MenuEgliseEntry insert(String cat, String appelation, String description, String albumRefPicasa,
			long dateActu, String refYoutube, String urlpdf) {

		MenuEgliseEntry entry = new MenuEgliseEntry(cat, appelation, description, dateActu,
				albumRefPicasa, refYoutube, urlpdf);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);

		return entry;
	}

	public static List<MenuEgliseEntry> getAllEntries(int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<MenuEgliseEntry> entries = new ArrayList<MenuEgliseEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 MenuEgliseEntry first = new MenuEgliseEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "");
		 MenuEgliseEntry second = new MenuEgliseEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		 MenuEgliseEntry third = new MenuEgliseEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		
		 Collection<MenuEgliseEntry> coll = new ArrayList<MenuEgliseEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour

		Query query = pm.newQuery(MenuEgliseEntry.class);
		// exception generated if no comment the first time
		query.setOrdering("appellation ASC");
		query.setRange(rangeStart, rangeLength);
		Object results = query.execute();
		if (results != null) {
			entries = (List<MenuEgliseEntry>) results;
		}

		return entries;
	}

	public static List<MenuEgliseEntry> getEntriesByCategory(String category, int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<MenuEgliseEntry> entries = new ArrayList<MenuEgliseEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 MenuEgliseEntry first = new MenuEgliseEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "");
		 MenuEgliseEntry second = new MenuEgliseEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		 MenuEgliseEntry third = new MenuEgliseEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "");
		
		 Collection<MenuEgliseEntry> coll = new ArrayList<MenuEgliseEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(MenuEgliseEntry.class);
				// exception generated if no comment the first time
				query.setFilter("category == categoryParam");
				query.setOrdering("dateActu DESC");
				query.declareParameters("String categoryParam");
				query.setRange(rangeStart, rangeLength);
				Object results = query.execute(category);
				if (results != null) {
					entries = (List<MenuEgliseEntry>) results;
				}
		 
		return entries;
	}
	
	
	
	public static void delete(MenuEglise actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		MenuEgliseEntry e = pm.getObjectById(MenuEgliseEntry.class, Long.valueOf(actu.getId()));

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}
	
	public static MenuEgliseEntry findById(Long long1){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return  pm.getObjectById(MenuEgliseEntry.class, long1);
	}
	
	public static MenuEgliseEntry update(String category, String id, String appellation, String description, 
			String albumRefPicasa, long time, String refYoutube, String urlpdf) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		MenuEgliseEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(MenuEgliseEntry.class, Long.valueOf(id));
		 
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setAlbumRefPicasa(albumRefPicasa);
		            e.setTime(time);
		            e.setRefYoutube(refYoutube);
		            e.setCategory(category);
		            e.setUrlpdf(urlpdf);
		            
		            
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
	}

	public static List<MenuEglise> getEntries(String category, int start,
			int length) {

		List<MenuEgliseEntry> list = ManagerMenuEgliseEntries.getEntriesByCategory(category,
				start, length);
		Vector<MenuEglise> vect = new Vector<MenuEglise>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (MenuEgliseEntry act : list) {

			MenuEglise MenuEglise = new MenuEglise(act.getId().toString());
			MenuEglise.setDate(new Date(act.getDateActu()));
			MenuEglise.setAppellation(act.getAppellation());
			MenuEglise.setDescription(act.getDescription().toString());
			MenuEglise.setAlbumRefPicasa(act.getAlbumRefPicasa());
			MenuEglise.setRefYoutube(act.getRefYoutube());
//			MenuEglise.setBlobKey(act.getBlobKey());
//			MenuEglise.setBlobKeyogg(act.getBlobKeyogg());
//			MenuEglise.setBlobKeywav(act.getBlobKeywav());
			MenuEglise.setCategory(category);
			MenuEglise.setUrlpdf(act.getUrlpdf());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(MenuEglise);
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
	
	public static int getCountMax() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		//Set objs = pm.getManagedObjects();
		List<MenuEgliseEntry> entries = new ArrayList<MenuEgliseEntry>();
		
		Query query = pm.newQuery(MenuEgliseEntry.class);
		Object results = query.execute();
		if (results != null) {
			entries = (List<MenuEgliseEntry>) results;
		}
		return entries.size();
	}
}
