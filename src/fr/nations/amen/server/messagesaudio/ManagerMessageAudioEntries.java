package fr.nations.amen.server.messagesaudio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;


import fr.nations.amen.server.PMF;
import fr.nations.amen.server.messagesaudio.ManagerPages;
import fr.nations.amen.server.messagesaudio.PageEntry;
import fr.nations.amen.shared.messagesaudio.MessageAudio;


public class ManagerMessageAudioEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerMessageAudioEntries.class);
	private static boolean firsttime=false;

	public static MessageAudioEntry insert(String category, String appelation, String description, String albumRefPicasa,
			long dateActu, String refYoutube, String blobKey, String blobKeyogg, String blobKeywav) {

		MessageAudioEntry entry = new MessageAudioEntry(category, appelation, description, dateActu,
				albumRefPicasa, refYoutube, blobKey, blobKeyogg, blobKeywav);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		entry.setNbPagesVues(0);
		pm.makePersistent(entry);

		return entry;
	}

	public static List<MessageAudioEntry> getEntries(String category, int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<MessageAudioEntry> entries = new ArrayList<MessageAudioEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 MessageAudioEntry first = new MessageAudioEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","","", "", "");
		 MessageAudioEntry second = new MessageAudioEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "");
		 MessageAudioEntry third = new MessageAudioEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "");
		
		 Collection<MessageAudioEntry> coll = new ArrayList<MessageAudioEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour

//		if (category.toLowerCase().trim().equals("cat0")){
//			
//			Query query = pm.newQuery(MessageAudioEntry.class);
//			// exception generated if no comment the first time
//			query.setOrdering("dateActu DESC");
//			query.setRange(rangeStart, rangeLength);
//			Object results = query.execute();
//			if (results != null) {
//				entries = (List<MessageAudioEntry>) results;
//			}
//			
//			
//		} else {
//			
			Query query = pm.newQuery(MessageAudioEntry.class);
			// exception generated if no comment the first time
			query.setFilter("category == categoryParam");
			query.setOrdering("dateActu DESC");
			query.declareParameters("String categoryParam");
			query.setRange(rangeStart, rangeLength);
			Object results = query.execute(category);
			if (results != null) {
				entries = (List<MessageAudioEntry>) results;
	//		}
			
		}
		 

		return entries;
	}

	public static void delete(MessageAudio actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		MessageAudioEntry e = pm.getObjectById(MessageAudioEntry.class, actu.getId());

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}

	public static MessageAudioEntry update(String category,String id, String appellation, String description, 
			String albumRefPicasa, long time, String refYoutube, String blobKey, String blobKeyogg, String blobKeywav) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		MessageAudioEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(MessageAudioEntry.class, Long.valueOf(id));
		 
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setAlbumRefPicasa(albumRefPicasa);
		            e.setTime(time);
		            e.setRefYoutube(refYoutube);
		            e.setBlobKey(blobKey);
		            e.setBlobKeyogg(blobKeyogg);
		            e.setBlobKeywav(blobKeywav);
		            e.setCategory(category);
		            
		            //set number
		    		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		            e.setNbPagesVues(pageEntry.getNbVisitePage());
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
	}

	public static List<MessageAudioEntry> getAllEntries(
			int rangeStart, int rangeLength) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<MessageAudioEntry> entries = new ArrayList<MessageAudioEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 MessageAudioEntry first = new MessageAudioEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","","", "", "");
		 MessageAudioEntry second = new MessageAudioEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "");
		 MessageAudioEntry third = new MessageAudioEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","","", "", "");
		
		 Collection<MessageAudioEntry> coll = new ArrayList<MessageAudioEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
			
			Query query = pm.newQuery(MessageAudioEntry.class);
			// exception generated if no comment the first time
			query.setOrdering("dateActu DESC");
			query.setRange(rangeStart, rangeLength);
			Object results = query.execute();
			if (results != null) {
				entries = (List<MessageAudioEntry>) results;
			}

		return entries;
	}

	public static List<MessageAudioEntry> getEntriesMoreSeen(int start,
			int length) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<MessageAudioEntry> entries = new ArrayList<MessageAudioEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 MessageAudioEntry first = new MessageAudioEntry("cat0","appelation0", longDescription,
		 new Date().getTime(),"","", longDescription, longDescription, longDescription);
		 MessageAudioEntry second = new MessageAudioEntry("cat1","appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", longDescription, longDescription, longDescription);
		 MessageAudioEntry third = new MessageAudioEntry("cat2","appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","", longDescription, longDescription, longDescription);
		
		 Collection<MessageAudioEntry> coll = new ArrayList<MessageAudioEntry>(3);
		 coll.add(first);
		 coll.add(second);
		 coll.add(third);
		
		 pm.makePersistentAll(coll);
		
		 entries.add(first);
		entries.add(second);
		entries.add(third);
		 return entries;
		 }// fin pour
				
				Query query = pm.newQuery(MessageAudioEntry.class);
				// exception generated if no comment the first time
				query.setOrdering("nbPagesVues DESC");
				query.setRange(start, length);
				Object results = query.execute();
				if (results != null) {
					entries = (List<MessageAudioEntry>) results;
				}
		 
		return entries;
	}

	public static MessageAudioEntry findById(Long long1) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return  pm.getObjectById(MessageAudioEntry.class, long1);
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
}

