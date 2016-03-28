package fr.nations.amen.server.souvenirs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.server.PMF;
import fr.nations.amen.server.louanges.LouangesEntry;
import fr.nations.amen.server.messagesaudio.ManagerMessageAudioEntries;
import fr.nations.amen.server.messagesaudio.MessageAudioEntry;
import fr.nations.amen.shared.messagesaudio.MessageAudio;
import fr.nations.amen.shared.souvenirs.Souvenir;

public class ManagerSouvenirEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerSouvenirEntries.class);
	private static boolean firsttime=false;

	public static SouvenirEntry insert(String cat, String appelation, String description, String albumRefPicasa,
			long dateActu, String refYoutube, String image, String urlpdf, String videoMp4) {

		SouvenirEntry entry = new SouvenirEntry(cat, appelation, description, dateActu,
				albumRefPicasa, refYoutube, image, urlpdf, videoMp4);
		entry.setNbPagesVues(0);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);

		return entry;
	}

	public static List<SouvenirEntry> getAllEntries(int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<SouvenirEntry> entries = new ArrayList<SouvenirEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 SouvenirEntry first = new SouvenirEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "", "", "");
		 SouvenirEntry second = new SouvenirEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "", "", "");
		 SouvenirEntry third = new SouvenirEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "", "", "");
		
		 Collection<SouvenirEntry> coll = new ArrayList<SouvenirEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour

		Query query = pm.newQuery(SouvenirEntry.class);
		// exception generated if no comment the first time
		query.setOrdering("appellation ASC");
		query.setRange(rangeStart, rangeLength);
		Object results = query.execute();
		if (results != null) {
			entries = (List<SouvenirEntry>) results;
		}

		return entries;
	}

	public static List<SouvenirEntry> getEntriesByCategory(String category, int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<SouvenirEntry> entries = new ArrayList<SouvenirEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 SouvenirEntry first = new SouvenirEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "", "", "");
		 SouvenirEntry second = new SouvenirEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "", "", "");
		 SouvenirEntry third = new SouvenirEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "", "", "");
		
		 Collection<SouvenirEntry> coll = new ArrayList<SouvenirEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(SouvenirEntry.class);
				// exception generated if no comment the first time
				query.setFilter("category == categoryParam");
				query.setOrdering("appellation DESC");
				query.declareParameters("String categoryParam");
				query.setRange(rangeStart, rangeLength);
				Object results = query.execute(category);
				if (results != null) {
					entries = (List<SouvenirEntry>) results;
				}
		 
		return entries;
	}
	
	
	
	public static void delete(Souvenir actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		SouvenirEntry e = pm.getObjectById(SouvenirEntry.class, Long.valueOf(actu.getId()));

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}
	
	public static SouvenirEntry findById(Long long1){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return  pm.getObjectById(SouvenirEntry.class, long1);
	}
	
	public static SouvenirEntry update(String category, String id, String appellation, String description, 
			String albumRefPicasa, long time, String refYoutube, String image, String index, String urlpdf, 
			String videoMp4) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		SouvenirEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(SouvenirEntry.class, Long.valueOf(id));
		 
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setAlbumRefPicasa(albumRefPicasa);
		            e.setTime(time);
		            e.setRefYoutube(refYoutube);
		            e.setCategory(category);
		            e.setImage(image);
		            e.setIndexInCells(index);
		            e.setUrlpdf(urlpdf);
		            e.setVideoMp4(videoMp4);
		            
		            //set number
		    		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		            e.setNbPagesVues(pageEntry.getNbVisitePage());
		            
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
	}

	public static List<Souvenir> getEntries(String category, int start,
			int length) {

		List<SouvenirEntry> list = ManagerSouvenirEntries.getEntriesByCategory(category,
				start, length);
		Vector<Souvenir> vect = new Vector<Souvenir>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (SouvenirEntry act : list) {

			Souvenir souvenir = new Souvenir(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setUrlpdf(act.getUrlpdf());
//			souvenir.setBlobKey(act.getBlobKey());
//			souvenir.setBlobKeyogg(act.getBlobKeyogg());
//			souvenir.setBlobKeywav(act.getBlobKeywav());
			souvenir.setCategory(category);
			souvenir.setNbPagesVues(act.getNbPagesVues());
			souvenir.setVideoGDrive(act.getVideoMp4());

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

	
	
	public static List<SouvenirEntry> getEntriesMoreSeen(int start, int length) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<SouvenirEntry> entries = new ArrayList<SouvenirEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 SouvenirEntry first = new SouvenirEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", "", "", "");
		 SouvenirEntry second = new SouvenirEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "", "", "");
		 SouvenirEntry third = new SouvenirEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", "", "", "");
		
		 Collection<SouvenirEntry> coll = new ArrayList<SouvenirEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(SouvenirEntry.class);
				// exception generated if no comment the first time
				query.setOrdering("nbPagesVues DESC");
				query.setRange(start, length);
				Object results = query.execute();
				if (results != null) {
					entries = (List<SouvenirEntry>) results;
				}
		 
		return entries;
	}
	
	public static int getCountMax() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		//Set objs = pm.getManagedObjects();
		List<SouvenirEntry> entries = new ArrayList<SouvenirEntry>();
		
		Query query = pm.newQuery(SouvenirEntry.class);
		Object results = query.execute();
		if (results != null) {
			entries = (List<SouvenirEntry>) results;
		}
		return entries.size();
	}

}
