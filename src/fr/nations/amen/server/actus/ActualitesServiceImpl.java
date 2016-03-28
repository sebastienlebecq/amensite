package fr.nations.amen.server.actus;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.nations.amen.shared.actus.Actu;
import fr.nations.amen.shared.actus.ActualitesService;

public class ActualitesServiceImpl extends RemoteServiceServlet implements ActualitesService{
	
	private static final Logger logger = Logger.getLogger(ActualitesServiceImpl.class);
	
	public ActualitesServiceImpl() {

		BasicConfigurator.configure();
    	
		
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	public List<AsyncDays> getDaysList(int year, int month, int rangeStart,
//			int rangeLength) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<AsyncMonths> getMonthsList(int year, int rangeStart,
//			int rangeLength) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Actu> getPhotoByDateList(int year, int month, int day,
//			int rangeStart, int rangeLength) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Actu getPhotoDetails(String id) throws ActuNotFoundException {
//		return null;
//	}

	@Override
	public Vector<Actu> getEventList(int rangeStart, int rangeLength) {
		
		List<ActuEntry> list = ManagerActuEntries.getEntries(rangeStart, rangeLength);
		Vector<Actu> vect = new Vector<Actu>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (ActuEntry act : list){
			
			Actu actu = new Actu(act.getId().toString());
			actu.setDate(new Date(act.getDateActu()));
			actu.setAppellation(act.getAppellation());
			actu.setDescription(act.getDescription());
			
			logger.info("actu(id:"+act.getId()+", appelation:"+act.getAppellation()+"...)");
			
			vect.add(actu);
		}
		

		
		return vect;
	}
	
	 public Actu addActu(Actu actu){
		 
		 logger.info("call to addActu() whith " +
			 		"Actu("+actu.getId()+","+
			 		 actu.getAppellation()+","+
			 		 actu.getDescription()
			 		 +")");
	
		 ActuEntry savecActuEntry = ManagerActuEntries.insert(actu.getAppellation(), actu.getDescription(), actu.getDate().getTime());
		 Actu savedActu = new Actu(savecActuEntry.getId().toString());
		 savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		 savedActu.setAppellation(savecActuEntry.getAppellation());
		 savedActu.setDescription(savecActuEntry.getDescription());	 
		 
		 logger.info("call to addActu() which return " +
		 		"savedActu("+savedActu.getId()+","+
		 		 savedActu.getAppellation()+","+
		 		savedActu.getDescription()
		 		 +")");
		
		  return savedActu;
	 }
	 
	 public void remove(Actu actu){
		 
		 ManagerActuEntries.delete(actu);
	 }

	@Override
	public Actu updateActu(Actu actu) {
		// TODO Auto-generated method stub
		 ActuEntry updatedActuEntry = ManagerActuEntries.update(actu.getId(),actu.getAppellation(), 
				 actu.getDescription(), actu.getDate().getTime());
		 Actu updatedActu = new Actu(updatedActuEntry.getId().toString());
		 updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		 updatedActu.setAppellation(updatedActuEntry.getAppellation());
		 updatedActu.setDescription(updatedActuEntry.getDescription());	 
		 
		 
		return updatedActu;
	}

//	@Override
//	public Actu updatePhotoDetails(Actu actu) {
//		System.out.println("updatePhotoDetails(Actu actu) from ActualitésServiceImpl");
//		ActuEntry actuEntry = ActuEntry.insert(actu.getTitle(), actu.getLargeUrl(), actu.getDate().getTime());
//		
//		Actu actu2 = new Actu(actuEntry.getId().toString());
//		actu2.setDate(new Date(actuEntry.getWhen()));
//		actu2.setTitle(actuEntry.getWho());
//		actu2.setLargeUrl(actuEntry.getDescription());
//		return actu2;
//	}

}
