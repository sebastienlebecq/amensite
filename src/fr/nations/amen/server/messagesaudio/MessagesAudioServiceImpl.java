package fr.nations.amen.server.messagesaudio;

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


import fr.nations.amen.server.messagesaudio.CommentEntry;
import fr.nations.amen.server.messagesaudio.ManageCounter;
import fr.nations.amen.server.messagesaudio.ManagerComments;
import fr.nations.amen.server.messagesaudio.ManagerPages;
import fr.nations.amen.server.messagesaudio.ManagerMessageAudioEntries;
import fr.nations.amen.server.messagesaudio.PageEntry;
import fr.nations.amen.server.temoignages.ManagerTemoignagesEntries;
import fr.nations.amen.server.temoignages.TemoignageEntry;

//import fr.nations.amen.server.souvenirs.ManagerPages;
//import fr.nations.amen.server.souvenirs.PageEntry;


import fr.nations.amen.shared.messagesaudio.MessageAudio;
import fr.nations.amen.shared.messagesaudio.MessagesAudioService;
import fr.nations.amen.shared.messagesaudio.Commentaire;


public class MessagesAudioServiceImpl extends RemoteServiceServlet implements MessagesAudioService{
	
	private static final Logger logger = Logger
			.getLogger(MessagesAudioServiceImpl.class);
	
	 BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public MessagesAudioServiceImpl() {

		BasicConfigurator.configure();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageAudio addSouvenir(MessageAudio actu) {

		logger.info("call to addActu() whith " + "Actu(" + actu.getId() + ","
				+ actu.getAppellation() + "," + actu.getDescription()+", " + actu.getCategory()+")");

		MessageAudioEntry savecActuEntry = ManagerMessageAudioEntries.insert(actu.getCategory(),
				actu.getAppellation(), 
				actu.getDescription(),
				actu.getAlbumRefPicasa(), 
				actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getBlobKey(), actu.getBlobKeyogg(), actu.getBlobKeywav());
		MessageAudio savedActu = new MessageAudio(savecActuEntry.getId().toString());
		savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		savedActu.setAppellation(savecActuEntry.getAppellation());
		savedActu.setDescription(savecActuEntry.getDescription().toString());
		savedActu.setAlbumRefPicasa(savecActuEntry.getAlbumRefPicasa());
		savedActu.setRefYoutube(savecActuEntry.getRefYoutube());
		savedActu.setBlobKey(savecActuEntry.getBlobKey());
		savedActu.setBlobKeyogg(savecActuEntry.getBlobKeyogg());
		savedActu.setBlobKeywav(savecActuEntry.getBlobKeywav());
		savedActu.setCategory(savecActuEntry.getCategory());
		//create storeNbpages
		ManagerPages.storeNbPage(savecActuEntry.getId(),savecActuEntry.getDateActu(), 0);
		
		logger.info("call to addActu() which return " + "savedActu("
				+ savedActu.getId() + "," + savedActu.getAppellation() + ","
				+ savedActu.getDescription() +", " + actu.getCategory()+ ")");

		return savedActu;
	}

	public void remove(MessageAudio actu) {

		ManagerMessageAudioEntries.delete(actu);
	}

	@Override
	public MessageAudio updateSouvenir(MessageAudio actu) {

		MessageAudioEntry updatedActuEntry = ManagerMessageAudioEntries.update(actu.getCategory(),
				actu.getId(), actu.getAppellation(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getBlobKey(), actu.getBlobKeyogg(), actu.getBlobKeywav());
		logger.info("***************************actu.getCategory():"+actu.getCategory());
		MessageAudio updatedActu = new MessageAudio(updatedActuEntry.getId().toString());
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
		updatedActu.setNbPagesVues(updatedActuEntry.getNbPagesVues());

		logger.info("***************************updatedActuEntry.getCategory():"+updatedActuEntry.getCategory());
		
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
	public String getBlobStoreUploadUrl() {

		String url = blobstoreService.createUploadUrl("/amen/upload");

		// change the computer name to standard localhost ip address, if in dev mode
		if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Development)
		{
		    url = url.replace("User-PC", "127.0.0.1");
		   // url = url.replace("pc-geek", "127.0.0.1");
		}
		
		
		return url;
	}

	@Override
	public Vector<MessageAudio> getSouvenirList(String category, int rangeStart,
			int rangeLength) {

		List<MessageAudioEntry> list = ManagerMessageAudioEntries.getEntries(category,
				rangeStart, rangeLength);
		Vector<MessageAudio> vect = new Vector<MessageAudio>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (MessageAudioEntry act : list) {

			MessageAudio souvenir = new MessageAudio(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setBlobKey(act.getBlobKey());
			souvenir.setBlobKeyogg(act.getBlobKeyogg());
			souvenir.setBlobKeywav(act.getBlobKeywav());
			souvenir.setCategory(category);
			
			souvenir.setNbPagesVues(act.getNbPagesVues());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	@Override
	public Vector<MessageAudio> getSouvenirAllList(int rangeStart,
			int rangeLength) {
		List<MessageAudioEntry> list = ManagerMessageAudioEntries.getAllEntries(
				rangeStart, rangeLength);
		Vector<MessageAudio> vect = new Vector<MessageAudio>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (MessageAudioEntry act : list) {

			MessageAudio souvenir = new MessageAudio(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setBlobKey(act.getBlobKey());
			souvenir.setBlobKeyogg(act.getBlobKeyogg());
			souvenir.setBlobKeywav(act.getBlobKeywav());
			souvenir.setCategory(act.getCategory());
			souvenir.setNbPagesVues(act.getNbPagesVues());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	@Override
	public List<MessageAudio> fetchPage(int start, int length) {
		List<MessageAudioEntry> list = ManagerMessageAudioEntries.getEntriesMoreSeen(start, length);
		Vector<MessageAudio> vect = new Vector<MessageAudio>(list.size());
		logger.info("call to fetchPage() which return vector:");
		for (MessageAudioEntry act : list) {

			MessageAudio souvenir = new MessageAudio(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setCategory(act.getCategory());
			
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
	public MessageAudio incrementNbPagesVues(String id) {
		//get entry of gp 
		MessageAudioEntry mySouvenir = ManagerMessageAudioEntries.findById(new Long(id));
		
		//get entry of pageinfos
//		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
//		int nbVisite = pageEntry.getNbVisitePage()+1;
//		pageEntry.setNbVisitePage(nbVisite);
		
		int nbVisite =0;
		PageEntry pageEntry = ManagerPages.getPageEntryByGp(id);
		if (pageEntry==null){
			
			ManagerPages.storeNbPage(Long.valueOf(mySouvenir.getId()),
					mySouvenir.getDateActu(), 0);
			
			pageEntry = ManagerPages.getPageEntryByGp(id);
		}
			
		if (pageEntry.getNbVisitePage()!=null){
		nbVisite = pageEntry.getNbVisitePage()+1;
		}
		pageEntry.setNbVisitePage(nbVisite);

		PageEntry rpageEntry = ManagerPages.update(mySouvenir.getId(), pageEntry.getId(), mySouvenir.getDateActu(), nbVisite);
		
		//update(String category,String id, String appellation, String description, 
		//		String albumRefPicasa, long time, String refYoutube, String blobKey, String blobKeyogg, String blobKeywav)
		
		MessageAudioEntry temoigneEntry2 = ManagerMessageAudioEntries.update(mySouvenir.getCategory(), id, 
				mySouvenir.getAppellation(), mySouvenir.getDescription(), mySouvenir.getAlbumRefPicasa() , mySouvenir.getDateActu(),
				mySouvenir.getRefYoutube(), mySouvenir.getBlobKey(),mySouvenir.getBlobKeyogg(), mySouvenir.getBlobKeywav());

		MessageAudio savedActu = new MessageAudio(mySouvenir.getId().toString());
		savedActu.setDate(new Date(mySouvenir.getDateActu()));
		savedActu.setAppellation(mySouvenir.getAppellation());
		savedActu.setDescription(mySouvenir.getDescription().toString());
		savedActu.setAlbumRefPicasa(mySouvenir.getAlbumRefPicasa());
		savedActu.setRefYoutube(mySouvenir.getRefYoutube());
		savedActu.setCategory(mySouvenir.getCategory());
		savedActu.setNbPagesVues(rpageEntry.getNbVisitePage());

		// il n'y a	 pas besoin d'updater le souvenir car c'est uniquement la classe pageentry quil'est
		//Souvenir modif = updateSouvenir(savedActu);
		return savedActu;

	}




}
