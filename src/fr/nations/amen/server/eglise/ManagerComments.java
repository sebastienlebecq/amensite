package fr.nations.amen.server.eglise;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import fr.nations.amen.server.PMF;
import fr.nations.amen.shared.eglise.Commentaire;

public class ManagerComments {
	
	private static final Logger logger = Logger
			.getLogger(ManagerComments.class);
	
	public static List<CommentEntry> getComments(String id) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(CommentEntry.class);
		q.setFilter("gpSouvenir == idSouvenirParam");
		q.setOrdering("hireDate desc");
		q.declareParameters("Long idSouvenirParam");
		List<CommentEntry> results2 = new ArrayList<CommentEntry>();
		 List<CommentEntry> results = null;
		try {
		  results = (List<CommentEntry>) q.execute(Long.valueOf(id));
		  if (!results.isEmpty()) {
		    for (CommentEntry p : results) {
		      results2.add(p);
		    }
		  } 
//		    else {
//		    // Handle "no results" case
//		  }
		} finally {
		  q.closeAll();
		}	
		return results2;
		
	}

	public static void addCommentaire(Commentaire newInput) {
		
		CommentEntry entry = new CommentEntry();
		entry.setAppellation(newInput.getAppellation());
		entry.setComment(newInput.getComment());
		entry.setHireDate(newInput.getHireDate());
		entry.setSignataire(newInput.getSignataire());
		entry.setGpSouvenir(Long.valueOf(newInput.getId()));
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(entry);
		
	}

	public static List<CommentEntry> getComments(String idSouvenir, int start, int length) {
PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(CommentEntry.class);
		q.setFilter("gpSouvenir == idSouvenirParam");
		q.setOrdering("hireDate desc");
		q.declareParameters("Long idSouvenirParam");
		q.setRange(start, length);
		List<CommentEntry> results2 = new ArrayList<CommentEntry>();
		 Object results = q.execute(Long.valueOf(idSouvenir));
			if (results != null) {
				results2 = (List<CommentEntry>) results;
			}
			return results2;
		
		
	}
}
