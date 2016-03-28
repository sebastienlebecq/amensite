package fr.nations.amen.server.louanges2;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.nations.amen.client.louanges2.SearchVideo;
import fr.nations.amen.shared.louanges2.AsyncDays;
import fr.nations.amen.shared.louanges2.AsyncMonths;
import fr.nations.amen.shared.louanges2.AsyncYears;
import fr.nations.amen.shared.louanges2.SearchVideoService;

public class SearchVideoServiceImpl extends RemoteServiceServlet implements SearchVideoService{
	
	private static final Logger logger = Logger.getLogger(SearchVideoServiceImpl.class);
	
	public SearchVideoServiceImpl() {

		BasicConfigurator.configure();
    	
		
	}

	private static final long serialVersionUID = 1L;

	@Override
	 public SearchVideo addSearchVideo(SearchVideo actu){
		 
		 logger.info("call to addActu() whith " +
			 		"Actu("+actu.getId()+","+
			 		 actu.getAppellation()+","+
			 		 actu.getDescription()
			 		 +")");
	
		 SearchVideoEntry savecActuEntry = ManagerSearchVideoEntries.insert(actu.getAppellation(),
				 actu.getDescription(),actu.getAlbumRefPicasa(), 
				 actu.getDate().getTime(), actu.getRefYoutube());
		 SearchVideo savedActu = new SearchVideo(savecActuEntry.getId().toString());
		 savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		 savedActu.setAppellation(savecActuEntry.getAppellation());
		 savedActu.setDescription(savecActuEntry.getDescription().toString());	 
		 savedActu.setAlbumRefPicasa(savecActuEntry.getAlbumRefPicasa());
		 savedActu.setRefYoutube(savecActuEntry.getRefYoutube());
		 
		 logger.info("call to addActu() which return " +
		 		"savedActu("+savedActu.getId()+","+
		 		 savedActu.getAppellation()+","+
		 		savedActu.getDescription()
		 		 +")");
		
		  return savedActu;
	 }
	 
	 public void remove(SearchVideo actu){
		 
		 ManagerSearchVideoEntries.delete(actu);
	 }

	@Override
	public SearchVideo updateSearchVideo(SearchVideo actu) {
		
		 SearchVideoEntry updatedActuEntry = ManagerSearchVideoEntries.update(actu.getId(),actu.getAppellation(), 
				 actu.getDescription(), actu.getAlbumRefPicasa(), actu.getDate().getTime()
				 , actu.getRefYoutube());
		 SearchVideo updatedActu = new SearchVideo(updatedActuEntry.getId().toString());
		 updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		 updatedActu.setAppellation(updatedActuEntry.getAppellation());
		 updatedActu.setDescription(updatedActuEntry.getDescription().toString());	 
		 updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		 updatedActu.setRefYoutube(updatedActuEntry.getRefYoutube());
		 
		return updatedActu;
	}
	
	@Override
	public Vector<SearchVideo> getVideoList(int rangeStart, int rangeLength) {
		
		List<SearchVideoEntry> list = ManagerSearchVideoEntries.getEntries(rangeStart, rangeLength);
		Vector<SearchVideo> vect = new Vector<SearchVideo>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (SearchVideoEntry act : list){
			
			SearchVideo souvenir = new SearchVideo(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			
			logger.info("actu(id:"+act.getId()+", appelation:"+act.getAppellation()+"...)");
			
			vect.add(souvenir);
		}
		return vect;
	}
	
	@Override
	public Vector<SearchVideo> getVideoList(int start, int length,
			String sortOnName, boolean isAscending) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AsyncMonths> getMonthsList(int year, int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SearchVideo> getVideoByDateList(int year, int month, int day,
			int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AsyncYears> getYearsList(int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AsyncDays> getVideoList(int year, int month, int start,
			int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
