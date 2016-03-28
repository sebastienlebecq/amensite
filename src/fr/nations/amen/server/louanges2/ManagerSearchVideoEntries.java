package fr.nations.amen.server.louanges2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.client.louanges2.SearchVideo;
import fr.nations.amen.server.PMF;


public class ManagerSearchVideoEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerSearchVideoEntries.class);
	private static boolean firsttime=false;

	public static SearchVideoEntry insert(String appelation, String description, String albumRefPicasa,
			long dateActu, String refYoutube) {

		SearchVideoEntry entry = new SearchVideoEntry(appelation, description, dateActu,
				albumRefPicasa, refYoutube);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);

		return entry;
	}

	public static List<SearchVideoEntry> getEntries(int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<SearchVideoEntry> entries = new ArrayList<SearchVideoEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 SearchVideoEntry first = new SearchVideoEntry("appelation0", longDescription,
		 new Date().getTime(),"","");
		 SearchVideoEntry second = new SearchVideoEntry("appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","");
		 SearchVideoEntry third = new SearchVideoEntry("appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1),"","");
		
		 Collection<SearchVideoEntry> coll = new ArrayList<SearchVideoEntry>(3);
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
		Query query = pm.newQuery(SearchVideoEntry.class);
		// exception generated if no comment the first time
		query.setOrdering("dateActu DESC");
		query.setRange(rangeStart, rangeLength);
		Object results = query.execute();
		if (results != null) {
			entries = (List<SearchVideoEntry>) results;
		}
		return entries;
	}

	public static void delete(SearchVideo actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		SearchVideoEntry e = pm.getObjectById(SearchVideoEntry.class, actu.getId());

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}

	public static SearchVideoEntry update(String id, String appellation, String description, 
			String albumRefPicasa, long time, String refYoutube) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		SearchVideoEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(SearchVideoEntry.class, Long.valueOf(id));
		 
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setAlbumRefPicasa(albumRefPicasa);
		            e.setTime(time);
		            e.setRefYoutube(refYoutube);
		            
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
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
