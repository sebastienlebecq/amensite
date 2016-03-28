package fr.nations.amen.server.louanges;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


import fr.nations.amen.client.UniformDim;
import fr.nations.amen.server.louanges.CommentEntry;
import fr.nations.amen.server.louanges.ManageCounter;
import fr.nations.amen.server.louanges.ManagerComments;
import fr.nations.amen.server.souvenirs.ManagerPages;
import fr.nations.amen.server.souvenirs.ManagerSouvenirEntries;
import fr.nations.amen.server.souvenirs.PageEntry;
import fr.nations.amen.server.souvenirs.SouvenirEntry;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.Commentaire;
import fr.nations.amen.shared.souvenirs.Souvenir;

public class LouangesServiceImpl extends RemoteServiceServlet implements LouangesService{
	
	private static final Logger logger = Logger
			.getLogger(LouangesServiceImpl.class);
	
	 BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public LouangesServiceImpl() {

		BasicConfigurator.configure();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	public Vector<MessageAudio> getSouvenirList(int rangeStart, int rangeLength) {
//
//		List<MessageAudioEntry> list = ManagerMessageAudioEntries.getEntries(
//				rangeStart, rangeLength);
//		Vector<MessageAudio> vect = new Vector<MessageAudio>(list.size());
//		logger.info("call to getEventList() which return vector:");
//		for (MessageAudioEntry act : list) {
//
//			MessageAudio souvenir = new MessageAudio(act.getId().toString());
//			souvenir.setDate(new Date(act.getDateActu()));
//			souvenir.setAppellation(act.getAppellation());
//			souvenir.setDescription(act.getDescription().toString());
//			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
//			souvenir.setRefYoutube(act.getRefYoutube());
//			souvenir.setBlobKey(act.getBlobKey());
//			souvenir.setBlobKeyogg(act.getBlobKeyogg());
//			souvenir.setBlobKeywav(act.getBlobKeywav());
//
//			logger.info("actu(id:" + act.getId() + ", appelation:"
//					+ act.getAppellation() + "...)");
//
//			vect.add(souvenir);
//		}
//
//		return vect;
//	}

	public Louange addSouvenir(Louange actu) {

		logger.info("call to addActu() whith " + "Actu(" + actu.getId() + ","
				+ actu.getSujet() + "," + actu.getDescription()+", " + actu.getCategory()+")");

		LouangesEntry savecActuEntry = ManagerLouangesEntries.insert(actu.getCategory(),
				actu.getSujet(), 
				actu.getDescription(),
				actu.getAlbumRefPicasa(), 
				actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getBlobKey(), actu.getBlobKeyogg(), actu.getBlobKeywav(), actu.getNumero(),
				actu.getThubnailUrl(), actu.getIndexInCells());
		Louange savedActu = new Louange(savecActuEntry.getId().toString());
		savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		savedActu.setAppellation(savecActuEntry.getAppellation());
		savedActu.setDescription(savecActuEntry.getDescription().toString());
		savedActu.setAlbumRefPicasa(savecActuEntry.getAlbumRefPicasa());
		savedActu.setRefYoutube(savecActuEntry.getRefYoutube());
		savedActu.setBlobKey(savecActuEntry.getBlobKey());
		savedActu.setBlobKeyogg(savecActuEntry.getBlobKeyogg());
		savedActu.setBlobKeywav(savecActuEntry.getBlobKeywav());
		savedActu.setCategory(savecActuEntry.getCategory());
		savedActu.setNumero(savecActuEntry.getNumero());
		savedActu.setThubnailUrl(savecActuEntry.getImage());
		savedActu.setIndexInCells(savecActuEntry.getIndexInCells());
		
		//create storeNbpages
		ManagerPages.storeNbPage(savecActuEntry.getId(),savecActuEntry.getDateActu(), 0);
		
		logger.info("call to addActu() which return " + "savedActu("
				+ savedActu.getId() + "," + savedActu.getSujet() + ","
				+ savedActu.getDescription() +", " + actu.getCategory()+ ")");

		return savedActu;
	}

	public void remove(Louange actu) {

		ManagerLouangesEntries.delete(actu);
	}

	@Override
	public Louange updateSouvenir(Louange actu) {

		LouangesEntry updatedActuEntry = ManagerLouangesEntries.update(actu.getCategory(),
				actu.getId(), actu.getSujet(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getBlobKey(), actu.getBlobKeyogg(), actu.getBlobKeywav(), actu.getNumero(),
				actu.getThubnailUrl(), actu.getIndexInCells());
		
		logger.info("***************************actu.getCategory():"+actu.getCategory());
		Louange updatedActu = new Louange(updatedActuEntry.getId().toString());
		updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		updatedActu.setAppellation(updatedActuEntry.getAppellation());
		updatedActu
				.setDescription(updatedActuEntry.getDescription().toString());
		updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		updatedActu.setRefYoutube(updatedActuEntry.getRefYoutube());
		updatedActu.setBlobKey(updatedActuEntry.getBlobKey());
		updatedActu.setBlobKeyogg(updatedActuEntry.getBlobKeyogg());
		updatedActu.setBlobKeywav(updatedActuEntry.getBlobKeywav());
		updatedActu.setCategory(updatedActuEntry.getCategory());
		updatedActu.setNumero(updatedActuEntry.getNumero());
		updatedActu.setNbPagesVues(updatedActuEntry.getNbPagesVues());
		updatedActu.setThubnailUrl(updatedActuEntry.getImage());
		updatedActu.setIndexInCells(updatedActuEntry.getIndexInCells());

		logger.info("***************************updatedActuEntry.getCategory():"+updatedActuEntry.getCategory());
		
		return updatedActu;
	}

	@Override
	public List<Commentaire> getComments(String id) {
		
		List<Commentaire> lcoms = getComments(id, 0, UniformDim.SIZE_DIALOG);
		
	//	SouvenirEntry souvenirEntry = ManagerSouvenirEntries.findById(Long.valueOf(id));
		
//		List<CommentEntry> list = ManagerComments.getComments(id);
//
//		List<Commentaire> lcoms = new ArrayList<Commentaire>();
//
//		for (CommentEntry come : list) {
//
//			Commentaire com = new Commentaire(id, come.getSignataire(),
//					come.getComment(),
//					new Date(come.getHireDate()).getTime(),
//					//new Date().getTime(),
//					come.getAppellation());
//			lcoms.add(com);
//		}

		return lcoms;
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
			 
			 Commentaire com = new Commentaire(new Long(come.getId()).toString(),
					    come.getSignataire(),
						come.getComment(),
						new Date(come.getHireDate()).getTime(),
						come.getAppellation());
				lcoms.add(com);
			 
			 
		 }
		 
		return lcoms;
	}

	@Override
	public String getBlobStoreUploadUrl() {

		String url = blobstoreService.createUploadUrl("/amen/upload");

		// change the computer name to standard localhost ip address, if in dev mode
		if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Development)
		{
			 url = url.replace("pc-geek", "127.0.0.1");
		    //url = url.replace("seb-PC", "127.0.0.1");
		}
		
		
		return url;
	}
	
	@Override
	public String getBlobStoreUploadImgUrl() {
		String url = blobstoreService.createUploadUrl("/amen/uploadSouvenirImg");

		// change the computer name to standard localhost ip address, if in dev mode
		if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Development)
		{
			 url = url.replace("pc-geek", "127.0.0.1");
		    //url = url.replace("seb-PC", "127.0.0.1");
		}
		
		return url;
	}


	@Override
	public Vector<Louange> getLouangeByCategory(String category, int rangeStart,
			int rangeLength) {

		List<LouangesEntry> list = ManagerLouangesEntries.getEntries(category,
				rangeStart, rangeLength);
		Vector<Louange> vect = new Vector<Louange>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (LouangesEntry act : list) {

			Louange souvenir = new Louange(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setBlobKey(act.getBlobKey());
			souvenir.setBlobKeyogg(act.getBlobKeyogg());
			souvenir.setBlobKeywav(act.getBlobKeywav());
			souvenir.setCategory(category);
			souvenir.setNumero(act.getNumero());
			souvenir.setThubnailUrl(act.getImage());
			souvenir.setIndexInCells(act.getIndexInCells());
			
			souvenir.setNbPagesVues(act.getNbPagesVues());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	@Override
	public Vector<Louange> getSouvenirAllList(int rangeStart,
			int rangeLength) {
		List<LouangesEntry> list = ManagerLouangesEntries.getAllEntries(
				rangeStart, rangeLength);
		Vector<Louange> vect = new Vector<Louange>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (LouangesEntry act : list) {

			Louange souvenir = new Louange(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setBlobKey(act.getBlobKey());
			souvenir.setBlobKeyogg(act.getBlobKeyogg());
			souvenir.setBlobKeywav(act.getBlobKeywav());
			souvenir.setCategory(act.getCategory());
			souvenir.setNumero(act.getNumero());
			souvenir.setThubnailUrl(act.getImage());
			souvenir.setIndexInCells(act.getIndexInCells());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	@Override
	public List<Louange> fetchPage(int start, int length) {

		List<LouangesEntry> list = ManagerLouangesEntries.getEntriesMoreSeen(start, length);
		Vector<Louange> vect = new Vector<Louange>(list.size());
		logger.info("call to fetchPage() which return vector:");
		for (LouangesEntry act : list) {

			Louange souvenir = new Louange(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setBlobKey(act.getBlobKey());
			souvenir.setBlobKeyogg(act.getBlobKeyogg());
			souvenir.setBlobKeywav(act.getBlobKeywav());
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
	public Louange incrementNbPagesVues(String id) {
		
		//get entry of gp 
		LouangesEntry mySouvenir = ManagerLouangesEntries.findById(new Long(id));
		
		//get entry of pageinfos
		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		int nbVisite = pageEntry.getNbVisitePage()+1;
		pageEntry.setNbVisitePage(nbVisite);

		PageEntry rpageEntry = ManagerPages.update(mySouvenir.getId(), pageEntry.getId(), mySouvenir.getDateActu(), nbVisite);

		Louange savedActu = new Louange(mySouvenir.getId().toString());
		savedActu.setDate(new Date(mySouvenir.getDateActu()));
		savedActu.setAppellation(mySouvenir.getAppellation());
		savedActu.setDescription(mySouvenir.getDescription().toString());
		savedActu.setAlbumRefPicasa(mySouvenir.getAlbumRefPicasa());
		savedActu.setRefYoutube(mySouvenir.getRefYoutube());
		savedActu.setBlobKey(mySouvenir.getBlobKey());
		savedActu.setBlobKeyogg(mySouvenir.getBlobKeyogg());
		savedActu.setBlobKeywav(mySouvenir.getBlobKeywav());
		savedActu.setCategory(mySouvenir.getCategory());
		savedActu.setNbPagesVues(rpageEntry.getNbVisitePage());
		savedActu.setThubnailUrl(mySouvenir.getImage());
		savedActu.setIndexInCells(mySouvenir.getIndexInCells());

		// il n'y a	 pas besoin d'updater le souvenir car c'est uniquement la classe pageentry quil'est
		//Souvenir modif = updateSouvenir(savedActu);
		return savedActu;

	}

	@Override
	public Louange registerIndexCellTable(String id, String start) {
		//get entry of gp 
		LouangesEntry mySouvenir = ManagerLouangesEntries.findById(new Long(id));
		mySouvenir.setIndexInCells(start);
		
		LouangesEntry updatedActuEntry = ManagerLouangesEntries.update(mySouvenir.getCategory(),
				mySouvenir.getId().toString(), mySouvenir.getAppellation(), mySouvenir.getDescription(),
				mySouvenir.getAlbumRefPicasa(), mySouvenir.getDateActu(),
				mySouvenir.getRefYoutube(), mySouvenir.getBlobKey(), mySouvenir.getBlobKeyogg(),
				mySouvenir.getBlobKeywav(), mySouvenir.getNumero(),
				mySouvenir.getImage(), mySouvenir.getIndexInCells());

		Louange updatedActu = new Louange(updatedActuEntry.getId().toString());
		updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		updatedActu.setAppellation(updatedActuEntry.getAppellation());
		updatedActu
				.setDescription(updatedActuEntry.getDescription().toString());
		updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		updatedActu.setRefYoutube(updatedActuEntry.getRefYoutube());
		updatedActu.setBlobKey(updatedActuEntry.getBlobKey());
		updatedActu.setBlobKeyogg(updatedActuEntry.getBlobKeyogg());
		updatedActu.setBlobKeywav(updatedActuEntry.getBlobKeywav());
		updatedActu.setCategory(updatedActuEntry.getCategory());
		updatedActu.setNumero(updatedActuEntry.getNumero());
		updatedActu.setNbPagesVues(updatedActuEntry.getNbPagesVues());
		updatedActu.setThubnailUrl(updatedActuEntry.getImage());
		updatedActu.setIndexInCells(updatedActuEntry.getIndexInCells());

		logger.info("***************************updatedActuEntry.getCategory():"+updatedActuEntry.getCategory());
		
		return updatedActu;
	}

	@Override
	public Louange getArticleObject(String id) {
		
		LouangesEntry updatedActuEntry = ManagerLouangesEntries.findById(new Long(id));
		
		Louange updatedActu = new Louange(updatedActuEntry.getId().toString());
		updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		updatedActu.setAppellation(updatedActuEntry.getAppellation());
		updatedActu
				.setDescription(updatedActuEntry.getDescription().toString());
		updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		updatedActu.setRefYoutube(updatedActuEntry.getRefYoutube());
		updatedActu.setBlobKey(updatedActuEntry.getBlobKey());
		updatedActu.setBlobKeyogg(updatedActuEntry.getBlobKeyogg());
		updatedActu.setBlobKeywav(updatedActuEntry.getBlobKeywav());
		updatedActu.setCategory(updatedActuEntry.getCategory());
		updatedActu.setNumero(updatedActuEntry.getNumero());
		updatedActu.setNbPagesVues(updatedActuEntry.getNbPagesVues());
		updatedActu.setThubnailUrl(updatedActuEntry.getImage());
		updatedActu.setIndexInCells(updatedActuEntry.getIndexInCells());


		return updatedActu;
	}

	//nécessaire pour les tableaux de la page d'accueil
	@Override
	public int getCountMax() {
		int max = ManagerLouangesEntries.getCountMax();
		return max;
	}

	@Override
	public int getCountMaxComments(String id) {
		int max = ManagerComments.getCountMax(id);
		return max;
	}


}
