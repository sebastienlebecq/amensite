package fr.nations.amen.server.temoignages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.nations.amen.server.louanges.ManagerLouangesEntries;
import fr.nations.amen.server.souvenirs.ManagerSouvenirEntries;
import fr.nations.amen.server.souvenirs.SouvenirEntry;
import fr.nations.amen.server.temoignages.ManagerPages;
import fr.nations.amen.server.temoignages.PageEntry;
import fr.nations.amen.server.temoignages.CommentEntry;
import fr.nations.amen.server.temoignages.ManagerComments;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.temoignages.Commentaire;
import fr.nations.amen.shared.temoignages.Temoignage;
import fr.nations.amen.shared.temoignages.TemoignagesService;

public class TemoignagesServiceImpl extends RemoteServiceServlet implements
		TemoignagesService {

	private static final Logger logger = Logger
			.getLogger(TemoignagesServiceImpl.class);

	public TemoignagesServiceImpl() {

		BasicConfigurator.configure();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Vector<Temoignage> getTemoignagesList(int rangeStart, int rangeLength) {

		List<TemoignageEntry> list = ManagerTemoignagesEntries.getEntries(
				rangeStart, rangeLength);
		Vector<Temoignage> vect = new Vector<Temoignage>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (TemoignageEntry act : list) {

			Temoignage souvenir = new Temoignage(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			//souvenir.setNbPagesVues(act.getNbPagesVues());
			souvenir.setIndexInCells(act.getIndexInCells());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	public Temoignage addTemoignage(Temoignage actu) {

		logger.info("call to addActu() whith " + "Actu(" + actu.getId() + ","
				+ actu.getAppellation() + "," + actu.getDescription() + ")");

		TemoignageEntry savecActuEntry = ManagerTemoignagesEntries.insert(actu
				.getCategory(), actu.getAppellation(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime());
		Temoignage savedActu = new Temoignage(savecActuEntry.getId().toString());
		savedActu.setDate(new Date(savecActuEntry.getDateActu()));
		savedActu.setAppellation(savecActuEntry.getAppellation());
		savedActu.setDescription(savecActuEntry.getDescription().toString());
		savedActu.setAlbumRefPicasa(savecActuEntry.getAlbumRefPicasa());
		savedActu.setIndexInCells(savecActuEntry.getIndexInCells());

		ManagerPages.storeNbPage(savecActuEntry.getId(),
				savecActuEntry.getDateActu(), 0);

		logger.info("call to addActu() which return " + "savedActu("
				+ savedActu.getId() + "," + savedActu.getAppellation() + ","
				+ savedActu.getDescription() + ")");

		return savedActu;
	}

	public void remove(Temoignage actu) {

		ManagerTemoignagesEntries.delete(actu);
	}

	@Override
	public Temoignage updateTemoignage(Temoignage actu) {

		TemoignageEntry updatedActuEntry = ManagerTemoignagesEntries.update(actu.getCategory(),
				actu.getId().toString(), actu.getAppellation(), actu.getDescription(),
				actu.getAlbumRefPicasa(), actu.getDate().getTime(),
				actu.getIndexInCells());
		
		Temoignage updatedActu = new Temoignage(updatedActuEntry.getId()
				.toString());
		updatedActu.setDate(new Date(updatedActuEntry.getDateActu()));
		updatedActu.setAppellation(updatedActuEntry.getAppellation());
		updatedActu
				.setDescription(updatedActuEntry.getDescription().toString());
		updatedActu.setAlbumRefPicasa(updatedActuEntry.getAlbumRefPicasa());
		updatedActu.setNbPagesVues(updatedActuEntry.getNbPagesVues());
		updatedActu.setIndexInCells(updatedActuEntry.getIndexInCells());

		return updatedActu;
	}

	@Override
	public String getImage(String tagName) {

		return "";
	}

	@Override
	public List<Commentaire> getComments(String id) {
		// SouvenirEntry souvenirEntry =
		// ManagerSouvenirEntries.findById(Long.valueOf(id));

		List<CommentEntry> list = ManagerComments.getComments(id);

		List<Commentaire> lcoms = new ArrayList<Commentaire>();

		for (CommentEntry come : list) {

			Commentaire com = new Commentaire(id, come.getSignataire(),
					come.getComment(), new Date(come.getHireDate()).getTime(),
					// new Date().getTime(),
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
	public List<Commentaire> getComments(String idSouvenir, int start,
			int length) {
		List<CommentEntry> list = ManagerComments.getComments(idSouvenir,
				start, length);

		List<Commentaire> lcoms = new ArrayList<Commentaire>();

		for (CommentEntry come : list) {

			Commentaire com = new Commentaire(idSouvenir, come.getSignataire(),
					come.getComment(), new Date(come.getHireDate()).getTime(),
					come.getAppellation());

		}
		return lcoms;
	}

	@Override
	public Vector<Temoignage> getTemoignagebyCategory(String category,
			int start, int length) {
		List<TemoignageEntry> list = ManagerTemoignagesEntries
				.getEntriesByCategory(category, start, length);
		Vector<Temoignage> vect = new Vector<Temoignage>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (TemoignageEntry act : list) {

			Temoignage souvenir = new Temoignage(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			// souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setCategory(act.getCategory());
			// souvenir.setNbPagesVues(act.getNbPagesVues());
			souvenir.setIndexInCells(act.getIndexInCells());

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	@Override
	public List<Temoignage> fetchPage(int start, int length) {
//TODO: la methode renvoit une liste viden alors
		List<TemoignageEntry> list = ManagerTemoignagesEntries
				.getEntriesMoreSeen(start, length);
		Vector<Temoignage> vect = new Vector<Temoignage>(list.size());
		logger.info("call to fetchPage() which return vector:");
		for (TemoignageEntry act : list) {

			Temoignage souvenir = new Temoignage(act.getId().toString());
			souvenir.setDate(new Date(act.getDateActu()));
			souvenir.setAppellation(act.getAppellation());
			souvenir.setDescription(act.getDescription().toString());
			souvenir.setAlbumRefPicasa(act.getAlbumRefPicasa());
			// souvenir.setRefYoutube(act.getRefYoutube());
			souvenir.setCategory(act.getCategory());
			souvenir.setIndexInCells(act.getIndexInCells());

			PageEntry pageEntry = ManagerPages.getPageEntryByGp(act.getId()
					.toString());

			if (pageEntry == null) {
				// mettre à 0 le champ pageEntry
				ManagerPages.storeNbPage(Long.valueOf(souvenir.getId()),
						act.getDateActu(), 0);
				souvenir.setNbPagesVues(0);
			} else {
				souvenir.setNbPagesVues(pageEntry.getNbVisitePage());
			}
			logger.info("Temoignage(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(souvenir);
		}

		return vect;
	}

	@Override
	public Temoignage incrementNbPagesVues(String id) {
	

		//get entry of gp 
		TemoignageEntry mySouvenir = ManagerTemoignagesEntries.findById(new Long(id));
		
		//get entry of pageinfos
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
		
//		TemoignageEntry temoigneEntry2 = ManagerTemoignagesEntries.update(mySouvenir.getCategory(), id, 
//				mySouvenir.getAppellation(), mySouvenir.getDescription(), mySouvenir.getAlbumRefPicasa() , mySouvenir.getDateActu());
		

		TemoignageEntry updatedActuEntry = ManagerTemoignagesEntries.update(mySouvenir.getCategory(),
				mySouvenir.getId().toString(), mySouvenir.getAppellation(), mySouvenir.getDescription(),
				mySouvenir.getAlbumRefPicasa(), mySouvenir.getDateActu(),
				mySouvenir.getIndexInCells());

		Temoignage savedActu = new Temoignage(mySouvenir.getId().toString());
		savedActu.setDate(new Date(mySouvenir.getDateActu()));
		savedActu.setAppellation(mySouvenir.getAppellation());
		savedActu.setDescription(mySouvenir.getDescription().toString());
		savedActu.setAlbumRefPicasa(mySouvenir.getAlbumRefPicasa());
		//savedActu.setRefYoutube(mySouvenir.getRefYoutube());
		savedActu.setCategory(mySouvenir.getCategory());
		savedActu.setNbPagesVues( rpageEntry.getNbVisitePage());
		savedActu.setIndexInCells(mySouvenir.getIndexInCells());

		// il n'y a	 pas besoin d'updater le souvenir car c'est uniquement la classe pageentry quil'est
		//Souvenir modif = updateSouvenir(savedActu);
		return savedActu;
}

	@Override
	public Vector<Temoignage> getTemoignageByCategory(String category,
			int start, int end) {
		List<TemoignageEntry> list = ManagerTemoignagesEntries.getEntriesByCategory(category,
				start, end);
		Vector<Temoignage> vect = new Vector<Temoignage>(list.size());
		logger.info("call to getEventList() which return vector:");
		for (TemoignageEntry act : list) {

			Temoignage updatedActu = new Temoignage(act.getId()
					.toString());
			updatedActu.setDate(new Date(act.getDateActu()));
			updatedActu.setAppellation(act.getAppellation());
			updatedActu
					.setDescription(act.getDescription().toString());
			updatedActu.setAlbumRefPicasa(act.getAlbumRefPicasa());
			updatedActu.setNbPagesVues(act.getNbPagesVues());
			updatedActu.setIndexInCells(act.getIndexInCells());
			

			logger.info("actu(id:" + act.getId() + ", appelation:"
					+ act.getAppellation() + "...)");

			vect.add(updatedActu);
		}

		return vect;
		
	}
	

	@Override
	public Temoignage registerIndexCellTable(String id, String index) {
		TemoignageEntry mySouvenir = ManagerTemoignagesEntries.findById(new Long(id));
		mySouvenir.setIndexInCells(index);
		
		//update(String cat, String id, String appellation, String description, 
		//String albumRefPicasa, long time)
		
		TemoignageEntry updatedActuEntry = ManagerTemoignagesEntries.update(mySouvenir.getCategory(),
				mySouvenir.getId().toString(), mySouvenir.getAppellation(), mySouvenir.getDescription(),
				mySouvenir.getAlbumRefPicasa(), mySouvenir.getDateActu(),
				mySouvenir.getIndexInCells());
		
		
		Temoignage updatedActu = new Temoignage(mySouvenir.getId()
				.toString());
		updatedActu.setDate(new Date(mySouvenir.getDateActu()));
		updatedActu.setAppellation(mySouvenir.getAppellation());
		updatedActu
				.setDescription(mySouvenir.getDescription().toString());
		updatedActu.setAlbumRefPicasa(mySouvenir.getAlbumRefPicasa());
		updatedActu.setNbPagesVues(mySouvenir.getNbPagesVues());
		updatedActu.setIndexInCells(mySouvenir.getIndexInCells());

		logger.info("***************************updatedActuEntry.getCategory():"+updatedActuEntry.getCategory());
		
		return updatedActu;
	}
	
	@Override
	public int getCountMax() {
		int max = ManagerTemoignagesEntries.getCountMax();
		return max;
	}
}
