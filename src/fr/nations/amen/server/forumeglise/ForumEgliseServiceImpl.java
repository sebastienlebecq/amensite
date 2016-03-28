package fr.nations.amen.server.forumeglise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.nations.amen.shared.forumeglise.Commentaire;
import fr.nations.amen.shared.forumeglise.ForumEglise;
import fr.nations.amen.shared.forumeglise.ForumEgliseService;

public class ForumEgliseServiceImpl extends RemoteServiceServlet implements
		ForumEgliseService {

	private static final Logger logger = Logger
			.getLogger(ForumEgliseServiceImpl.class);
	
	 BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public ForumEgliseServiceImpl() {

		BasicConfigurator.configure();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Vector<ForumEglise> getSouvenirList(int rangeStart, int rangeLength) {

		List<ForumEgliseEntry> list = ManagerForumEgliseEntries.getAllEntries(
				rangeStart, rangeLength);
		Vector<ForumEglise> vect = new Vector<ForumEglise>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (ForumEgliseEntry act : list) {

			ForumEglise souvenir = new ForumEglise(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setCategory(act.getCategory());
			souvenir.setNbPagesVues(act.getNbPagesVues());
			souvenir.setThubnailUrl(act.getImage());
			souvenir.setIndexInCells(act.getIndexInCells());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}
	
	@Override
	public Vector<ForumEglise> getSouvenirbyCategory(String category, int rangeStart, int rangeLength) {

		List<ForumEgliseEntry> list = ManagerForumEgliseEntries.getEntriesByCategory(category,
				rangeStart, rangeLength);
		Vector<ForumEglise> vect = new Vector<ForumEglise>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (ForumEgliseEntry act : list) {

			ForumEglise souvenir = new ForumEglise(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setCategory(act.getCategory());
			souvenir.setNbPagesVues(act.getNbPagesVues());
			souvenir.setThubnailUrl(act.getImage());
			souvenir.setIndexInCells(act.getIndexInCells());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	public ForumEglise addSouvenir(ForumEglise actu) {

		logger.info("call to addActu() whith " + "Actu(" + actu.getId() + ","
				+ actu.getAppellation() + "," + actu.getDescription() + ")");

		ForumEgliseEntry savecActuEntry = ManagerForumEgliseEntries.insert(actu.getCategory(),
				actu.getAppellation(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getThubnailUrl());
		ForumEglise savedActu = new ForumEglise(savecActuEntry.getId().toString());
		savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		savedActu.setAppellation(savecActuEntry.getAppellation());
		savedActu.setDescription(savecActuEntry.getDescription().toString());
		savedActu.setAlbumRefPicasa(savecActuEntry.getAlbumRefPicasa());
		savedActu.setRefYoutube(savecActuEntry.getRefYoutube());
		savedActu.setCategory(savecActuEntry.getCategory());
		savedActu.setThubnailUrl(savecActuEntry.getImage());
		savedActu.setIndexInCells(savecActuEntry.getIndexInCells());
		
		//create storeNbpages
		ManagerPages.storeNbPage(savecActuEntry.getId(),savecActuEntry.getDateActu(), 0);
	//	savedActu.setNbPagesVues(savecActuEntry.getNbPagesVues());

		logger.info("call to addActu() which return " + "savedActu("
				+ savedActu.getId() + "," + savedActu.getAppellation() + ","
				+ savedActu.getDescription() + ")");

		return savedActu;
	}

	public void remove(ForumEglise actu) {

		ManagerForumEgliseEntries.delete(actu);
	}

	@Override
	public ForumEglise updateSouvenir(ForumEglise actu) {

		ForumEgliseEntry updatedActuEntry = ManagerForumEgliseEntries.update(actu.getCategory(),
				actu.getId(), actu.getAppellation(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getThubnailUrl(), actu.getIndexInCells());
		
		ForumEglise updatedActu = new ForumEglise(updatedActuEntry.getId().toString());
		updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		updatedActu.setAppellation(updatedActuEntry.getAppellation());
		updatedActu
				.setDescription(updatedActuEntry.getDescription().toString());
		updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		updatedActu.setRefYoutube(updatedActuEntry.getRefYoutube());
		updatedActu.setCategory(updatedActuEntry.getCategory());
		updatedActu.setNbPagesVues(updatedActuEntry.getNbPagesVues());
		updatedActu.setThubnailUrl(updatedActuEntry.getImage());
		updatedActu.setIndexInCells(updatedActuEntry.getIndexInCells());

		return updatedActu;
	}

	@Override
	public List<Commentaire> getComments(String id) {
	//	SouvenirEntry souvenirEntry = ManagerSouvenirEntries.findById(Long.valueOf(id));
		
		List<CommentEntry> list = ManagerComments.getComments(id);

		List<Commentaire> lcoms = new ArrayList<Commentaire>();

		for (CommentEntry come : list) {

			Commentaire com = new Commentaire(id, come.getSignataire(),
					come.getComment(),
					new Date(come.getHireDate()).getTime(),
					//new Date().getTime(),
					come.getAppellation());
			lcoms.add(com);
		}

		return lcoms;
	}
	
	@Override
	public String getBlobStoreUploadImgUrl() {
		String url = blobstoreService.createUploadUrl("/amen/uploadImg");

		// change the computer name to standard localhost ip address, if in dev mode
		if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Development)
		{
			 url = url.replace("pc-geek", "127.0.0.1");
		    //url = url.replace("seb-PC", "127.0.0.1");
		}
		
		return url;
	}

	@Override
	public void storeComment(String idSouvenir, Commentaire newInput) {

		ManagerComments.addCommentaire(newInput);
		
	}

	@Override
	public int getCountVisitor() {

		return ManageCounter.getCurrentCount();
	}

	@Override
	public List<Commentaire> getComments(String idSouvenir, int start, int length) {
		 List<CommentEntry> list = ManagerComments.getComments(idSouvenir,start, length);
		 
			List<Commentaire> lcoms = new ArrayList<Commentaire>();
			
		 for (CommentEntry come : list) {
			 
			 Commentaire com = new Commentaire(new Long(come.getId()).toString(), come.getSignataire(),
						come.getComment(),
						new Date(come.getHireDate()).getTime(),
						come.getAppellation());
				lcoms.add(com);
			 
			 
		 }
		 
		return lcoms;
	}

	@Override
	public List<ForumEglise> fetchPage(int start, int length) {

		List<ForumEgliseEntry> list = ManagerForumEgliseEntries.getEntriesMoreSeen(start, length);
		Vector<ForumEglise> vect = new Vector<ForumEglise>(list.size());
		logger.info("call to fetchPage() which return vector:");
		for (ForumEgliseEntry act : list) {

			ForumEglise souvenir = new ForumEglise(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setCategory(act.getCategory());
			souvenir.setThubnailUrl(act.getImage());
			souvenir.setIndexInCells(act.getIndexInCells());
			
			PageEntry pageEntry = ManagerPages.getPageEntryByGp(act.getId().toString());
			
			if (pageEntry==null){
				//mettre à 0 le champ pageEntry
				ManagerPages.storeNbPage(Long.valueOf(souvenir.getId()), act.getDateActu(),0) ;
				souvenir.setNbPagesVues(0) ;
			} else {
				souvenir.setNbPagesVues(pageEntry.getNbVisitePage());
			}
			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	@Override
	public ForumEglise incrementNbPagesVues(String id) {
		
		//get entry of gp 
		ForumEgliseEntry mySouvenir = ManagerForumEgliseEntries.findById(new Long(id));
		
		//get entry of pageinfos
		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		int nbVisite = pageEntry.getNbVisitePage()+1;
		pageEntry.setNbVisitePage(nbVisite);

		PageEntry rpageEntry = ManagerPages.update(mySouvenir.getId(), pageEntry.getId(), mySouvenir.getDateActu(), nbVisite);

		ForumEglise savedActu = new ForumEglise(mySouvenir.getId().toString());
		savedActu.setDate(new Date(mySouvenir.getDateActu()));
		savedActu.setAppellation(mySouvenir.getAppellation());
		savedActu.setDescription(mySouvenir.getDescription().toString());
		savedActu.setAlbumRefPicasa(mySouvenir.getAlbumRefPicasa());
		savedActu.setRefYoutube(mySouvenir.getRefYoutube());
		savedActu.setCategory(mySouvenir.getCategory());
		savedActu.setNbPagesVues(rpageEntry.getNbVisitePage());
		savedActu.setThubnailUrl(mySouvenir.getImage());
		savedActu.setIndexInCells(mySouvenir.getIndexInCells());

		// il n'y a	 pas besoin d'updater le souvenir car c'est uniquement la classe pageentry quil'est
		//Souvenir modif = updateSouvenir(savedActu);
		return savedActu;

	}



//	@Override
//	public Vector<Souvenir> getSouvenirList(String category, int start,
//			int length) {
//
//		List<Souvenir> list = ManagerSouvenirEntries.getEntries(category,
//				start, length);
//		Vector<Souvenir> vect = new Vector<Souvenir>(list.size());
//		logger.info("call to getEventList() which return vector:");
//		for (Souvenir act : list) {
//
//			Souvenir souvenir = new Souvenir(act.getId().toString());
//			souvenir.setDate(act.getDate());
//			souvenir.setAppellation(act.getAppellation());
//			souvenir.setDescription(act.getDescription().toString());
//			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
//			souvenir.setRefYoutube(act.getRefYoutube());
////			souvenir.setBlobKey(act.getBlobKey());
////			souvenir.setBlobKeyogg(act.getBlobKeyogg());
////			souvenir.setBlobKeywav(act.getBlobKeywav());
//			souvenir.setCategory(category);
//
//			logger.info("actu(id:" + act.getId() + ", appelation:"
//					+ act.getAppellation() + "...)");
//
//			vect.add(souvenir);
//		}
//
//		return vect;
//	}

	@Override
	public ForumEglise registerIndexCellTable(String id, String index) {
		
		ForumEgliseEntry mySouvenir = ManagerForumEgliseEntries.findById(new Long(id));
		mySouvenir.setIndexInCells(index);
		
		ForumEgliseEntry updatedActuEntry = ManagerForumEgliseEntries.update(mySouvenir.getCategory(),
				mySouvenir.getId().toString(), mySouvenir.getAppellation(), mySouvenir.getDescription(),
				mySouvenir.getAlbumRefPicasa(), mySouvenir.getDateActu(),
				mySouvenir.getRefYoutube(), mySouvenir.getImage(), mySouvenir.getIndexInCells());
		
		ForumEglise updatedActu = new ForumEglise(updatedActuEntry.getId().toString());
		updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		updatedActu.setAppellation(updatedActuEntry.getAppellation());
		updatedActu
				.setDescription(updatedActuEntry.getDescription().toString());
		updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		updatedActu.setRefYoutube(updatedActuEntry.getRefYoutube());
		updatedActu.setCategory(updatedActuEntry.getCategory());
		updatedActu.setNbPagesVues(updatedActuEntry.getNbPagesVues());
		updatedActu.setThubnailUrl(updatedActuEntry.getImage());
		updatedActu.setIndexInCells(updatedActuEntry.getIndexInCells());

		logger.info("***************************updatedActuEntry.getCategory():"+updatedActuEntry.getCategory());
		
		return updatedActu;
	}
}
