package fr.nations.amen.server;


import javax.jdo.PersistenceManager;


public class MessagesAmenUtils {
	 public static void insert(String who, String message, String subject) {
		 	MessageForumAmen entry = new MessageForumAmen(who, message, subject);
		    PersistenceManager pm = PMF.get().getPersistenceManager();
		    pm.makePersistent(entry);
		  }
		//  
//		  public static List<GuestbookEntry> getEntries() {
//		    PersistenceManager pm = PMF.get().getPersistenceManager();
//		    Query query = pm.newQuery(GuestbookEntry.class);
//		    query.setOrdering("when DESC");
//		    List<GuestbookEntry> entries = (List<GuestbookEntry>) query.execute();
//		    return entries;
//		 }
}
