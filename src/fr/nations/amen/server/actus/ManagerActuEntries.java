package fr.nations.amen.server.actus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.server.PMF;
import fr.nations.amen.shared.actus.Actu;

public class ManagerActuEntries {

	private static final Logger logger = Logger
			.getLogger(ManagerActuEntries.class);
	private static boolean firsttime=false;

	public static ActuEntry insert(String appelation, String description,
			long dateActu) {

		ActuEntry entry = new ActuEntry(appelation, description, dateActu);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);

		return entry;
	}

	public static List<ActuEntry> getEntries(int rangeStart, int rangeLength) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ActuEntry> entries = new ArrayList<ActuEntry>();

		// pour le développement afin de ne pas générer d'exception :
		String longDescription="description0<br>description1";
		 if (firsttime) {
		 firsttime = false;
		 ActuEntry first = new ActuEntry("appelation0", longDescription,
		 new Date().getTime());
		 ActuEntry second = new ActuEntry("appelation1", "description1",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		 ActuEntry third = new ActuEntry("appelation2", "description2",
		 Date.UTC(1999, 1, 1, 1, 1, 1));
		
		 Collection<ActuEntry> coll = new ArrayList<ActuEntry>(3);
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
		Query query = pm.newQuery(ActuEntry.class);
		// exception generated if no comment the first time
		query.setOrdering("dateActu DESC");
		query.setRange(rangeStart, rangeLength);
		Object results = query.execute();
		if (results != null) {
			entries = (List<ActuEntry>) results;
		}
		return entries;
	}

	public static void delete(Actu actu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		ActuEntry e = pm.getObjectById(ActuEntry.class, actu.getId());

		try{
		pm.deletePersistent(e);
		}
		finally {
	        pm.close();
	    }

	}

	public static ActuEntry update(String id, String appellation, String description,
			 long time) {
		//https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
		ActuEntry e = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
			 e = pm.getObjectById(ActuEntry.class, Long.valueOf(id));
		 
		            e.setAppellation(appellation);
		            e.setDescription(description);
		            e.setTime(time);
		            
		   
		    } finally {
		        pm.close();
		    }
		
		return e;
	}
}
