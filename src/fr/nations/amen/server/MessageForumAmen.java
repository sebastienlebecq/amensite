package fr.nations.amen.server;

import java.text.DateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class MessageForumAmen {

	  @PrimaryKey
	  @Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	  private Long id;
	  
	  @Persistent
	  private String who;
	  
	  @Persistent
	  private Date when;
	  
	  @Persistent
	  private String message;
	  
	  @Persistent	  
	  private String subject;

	public MessageForumAmen(String who, String message, String subject) {
	    this.message = message;
	    this.who = who;
	    this.when = new Date();
	    this.subject = subject;
	    
	  }
	  
	  public String getWho() {
	    return who;
	  }

	  public Date getWhen() {
	    return when;
	  }

	  public String getMessage() {
	    return message;
	  }
	  
	  public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}
//		static public void insert(String who, String message) {
//			 
//		    GuestbookEntry entry = new GuestbookEntry(who, message);
//		    PersistenceManager pm = PMF.get().getPersistenceManager();
//		    pm.makePersistent(entry);
//		  }
//		  
//	static  public List<GuestbookEntry> getEntries() {
//
//		    PersistenceManager pm = PMF.get().getPersistenceManager();
//		    Query query = pm.newQuery(GuestbookEntry.class);
//		    query.setOrdering("when DESC");
//		    List<GuestbookEntry> entries = (List<GuestbookEntry>) query.execute();
//		    return entries;
//		  }
		  private static DateFormat formatDate  =
		            DateFormat.getDateInstance (DateFormat.MEDIUM);
		  private static DateFormat formatHeure =
		            DateFormat.getTimeInstance (DateFormat.SHORT);

		  public String toString ()
		  {
		    return "De " + this.who + " le "
		           + formatDate.format (this.when)
		           + " \u00e0 " + formatHeure.format(this.when)
		           + "\nSujet : " + this.subject
		           + "\n" + this.message;
		  }
}
