package fr.nations.amen.server.eglise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.nations.amen.server.souvenirs.ManagerSouvenirEntries;
import fr.nations.amen.shared.eglise.Commentaire;
import fr.nations.amen.shared.eglise.MenuEglise;
import fr.nations.amen.shared.eglise.MenuEgliseService;

public class MenuEgliseServiceImpl extends RemoteServiceServlet implements
MenuEgliseService {

	private static final Logger logger = Logger
			.getLogger(MenuEgliseServiceImpl.class);

	public MenuEgliseServiceImpl() {

		BasicConfigurator.configure();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Vector<MenuEglise> getMenuEgliseList(int rangeStart, int rangeLength) {

		List<MenuEgliseEntry> list = ManagerMenuEgliseEntries.getAllEntries(
				rangeStart, rangeLength);
		Vector<MenuEglise> vect = new Vector<MenuEglise>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (MenuEgliseEntry act : list) {

			MenuEglise MenuEglise = new MenuEglise(act.getId().toString());
			MenuEglise.setDate(new Date(act.getDateActu()));
			MenuEglise.setAppellation(act.getAppellation());
			MenuEglise.setDescription(act.getDescription().toString());
			MenuEglise.setAlbumRefPicasa(act.getAlbumRefPicasa());
			MenuEglise.setRefYoutube(act.getRefYoutube());
			MenuEglise.setCategory(act.getCategory());
			MenuEglise.setUrlpdf(act.getUrlpdf());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(MenuEglise);
		}

		return vect;
	}
	
	public MenuEglise getMenuEgliseById(Long menuItem) {

		logger.info("call to getMenuEgliseById() whith Id = "+menuItem);

		MenuEgliseEntry savecActuEntry = ManagerMenuEgliseEntries.findById(menuItem);
		MenuEglise savedActu = new MenuEglise(savecActuEntry.getId().toString());
		savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		savedActu.setAppellation(savecActuEntry.getAppellation());
		savedActu.setDescription(savecActuEntry.getDescription().toString());
		savedActu.setAlbumRefPicasa(savecActuEntry.getAlbumRefPicasa());
		savedActu.setRefYoutube(savecActuEntry.getRefYoutube());
		savedActu.setCategory(savecActuEntry.getCategory());
		savedActu.setUrlpdf(savecActuEntry.getUrlpdf());
		
		logger.info("call to addActu() which return " + "savedActu("
				+ savedActu.getId() + "," + savedActu.getAppellation() + ","
				+ savedActu.getDescription() + ")");

		return savedActu;
	}
	
	@Override
	public Vector<MenuEglise> getMenuEglisebyCategory(String category, int rangeStart, int rangeLength) {

		List<MenuEgliseEntry> list = ManagerMenuEgliseEntries.getEntriesByCategory(category,
				rangeStart, rangeLength);
		Vector<MenuEglise> vect = new Vector<MenuEglise>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (MenuEgliseEntry act : list) {

			MenuEglise MenuEglise = new MenuEglise(act.getId().toString());
			MenuEglise.setDate(new Date(act.getDateActu()));
			MenuEglise.setAppellation(act.getAppellation());
			MenuEglise.setDescription(act.getDescription().toString());
			MenuEglise.setAlbumRefPicasa(act.getAlbumRefPicasa());
			MenuEglise.setRefYoutube(act.getRefYoutube());
			MenuEglise.setCategory(act.getCategory());
			MenuEglise.setUrlpdf(act.getUrlpdf());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(MenuEglise);
		}

		return vect;
	}

	public MenuEglise addMenuEglise(MenuEglise actu) {

		logger.info("call to addActu() whith " + "Actu(" + actu.getId() + ","
				+ actu.getAppellation() + "," + actu.getDescription() + ")");

		MenuEgliseEntry savecActuEntry = ManagerMenuEgliseEntries.insert(actu.getCategory(),
				actu.getAppellation(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getUrlpdf());
		MenuEglise savedActu = new MenuEglise(savecActuEntry.getId().toString());
		savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		savedActu.setAppellation(savecActuEntry.getAppellation());
		savedActu.setDescription(savecActuEntry.getDescription().toString());
		savedActu.setAlbumRefPicasa(savecActuEntry.getAlbumRefPicasa());
		savedActu.setRefYoutube(savecActuEntry.getRefYoutube());
		savedActu.setCategory(savecActuEntry.getCategory());
		savedActu.setUrlpdf(savecActuEntry.getUrlpdf());

		logger.info("call to addActu() which return " + "savedActu("
				+ savedActu.getId() + "," + savedActu.getAppellation() + ","
				+ savedActu.getDescription() + ")");

		return savedActu;
	}	
	
	
	
	

	public void remove(MenuEglise actu) {

		ManagerMenuEgliseEntries.delete(actu);
	}

	@Override
	public MenuEglise updateMenuEglise(MenuEglise actu) {

		MenuEgliseEntry updatedActuEntry = ManagerMenuEgliseEntries.update(actu.getCategory(),
				actu.getId(), actu.getAppellation(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime(),
				actu.getRefYoutube(), actu.getUrlpdf());
		MenuEglise updatedActu = new MenuEglise(updatedActuEntry.getId().toString());
		updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		updatedActu.setAppellation(updatedActuEntry.getAppellation());
		updatedActu
				.setDescription(updatedActuEntry.getDescription().toString());
		updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		updatedActu.setRefYoutube(updatedActuEntry.getRefYoutube());
		updatedActu.setCategory(updatedActuEntry.getCategory());
		updatedActu.setUrlpdf(updatedActuEntry.getUrlpdf());

		return updatedActu;
	}

	@Override
	public List<Commentaire> getComments(String id) {
	//	MenuEgliseEntry MenuEgliseEntry = ManagerMenuEgliseEntries.findById(Long.valueOf(id));
		
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
	public void storeComment(String idMenuEglise, Commentaire newInput) {

		ManagerComments.addCommentaire(newInput);
		
	}

	@Override
	public int getCountVisitor() {

		return ManageCounter.getCurrentCount();
	}

	@Override
	public List<Commentaire> getComments(String idMenuEglise, int start, int length) {
		 List<CommentEntry> list = ManagerComments.getComments(idMenuEglise,start, length);
		 
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
	public int getCountMax() {
		return ManagerMenuEgliseEntries.getCountMax();
	}


//	@Override
//	public Vector<MenuEglise> getMenuEgliseList(String category, int start,
//			int length) {
//
//		List<MenuEglise> list = ManagerMenuEgliseEntries.getEntries(category,
//				start, length);
//		Vector<MenuEglise> vect = new Vector<MenuEglise>(list.size());
//		logger.info("call to getEventList() which return vector:");
//		for (MenuEglise act : list) {
//
//			MenuEglise MenuEglise = new MenuEglise(act.getId().toString());
//			MenuEglise.setDate(act.getDate());
//			MenuEglise.setAppellation(act.getAppellation());
//			MenuEglise.setDescription(act.getDescription().toString());
//			MenuEglise.setAlbumRefPicasa(act.getAlbumRefPicasa());
//			MenuEglise.setRefYoutube(act.getRefYoutube());
////			MenuEglise.setBlobKey(act.getBlobKey());
////			MenuEglise.setBlobKeyogg(act.getBlobKeyogg());
////			MenuEglise.setBlobKeywav(act.getBlobKeywav());
//			MenuEglise.setCategory(category);
//
//			logger.info("actu(id:" + act.getId() + ", appelation:"
//					+ act.getAppellation() + "...)");
//
//			vect.add(MenuEglise);
//		}
//
//		return vect;
//	}


}
