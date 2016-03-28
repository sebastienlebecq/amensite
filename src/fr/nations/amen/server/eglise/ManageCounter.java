package fr.nations.amen.server.eglise;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import fr.nations.amen.server.PMF;

public class ManageCounter {
	
	private static int counter = -1;
	private static VisitorCounter vCounter;

	public static synchronized int getCurrentCount() {
		
	    if (counter == -1) {                
           // counter =  VisitorCounter.getCounter();
            PersistenceManager pm = PMF.get().getPersistenceManager();
    		
//    		Query q = pm.newQuery(VisitorCounter.class);
//    		q.setFilter("id == idParam");
//    		q.declareParameters("Long idParam");
//    		Object results = q.execute(1L);
//    		if (results != null) {
            VisitorCounter vCounter = pm.getObjectById(VisitorCounter.class, 1L);
    		//vCounter = (VisitorCounter)results;
    		counter = vCounter.getCounter();
    //		}
    		
	    }
    counter++;
	PersistenceManager pm = PMF.get().getPersistenceManager();
	VisitorCounter Counter = pm.getObjectById(VisitorCounter.class, 1L);
	Counter.setCounter(counter);
	
    return counter;
	}

}
