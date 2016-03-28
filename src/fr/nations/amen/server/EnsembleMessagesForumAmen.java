package fr.nations.amen.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class EnsembleMessagesForumAmen {

	private ArrayList<MessageForumAmen> messages = new ArrayList<MessageForumAmen>();

	public ArrayList<MessageForumAmen> getMessages() {
		return messages;
	}

	/**
	 * Ajoute un message a cet ensemble.
	 */
	public void ajouter(MessageForumAmen message) {
		this.messages.add(message);
	}

	public Iterator<MessageForumAmen> iterator() {
		return this.messages.iterator();
	}
	
	

	public void rechercherSujets() {
		// PreparedStatement rechercheSujets =
		// connecteur.getConnexion().prepareStatement (
		// "SELECT ID, AUTEUR, MAX(DATECREATION) AS DATECREATION,"
		// + " SUJET, TEXTE FROM MESSAGE GROUP BY SUJET"
		// + " ORDER BY DATECREATION DESC");
		// rechercher(rechercheSujets, null);
		// rechercheSujets.close();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(MessageForumAmen.class);
		query.setOrdering("when DESC");
		List<MessageForumAmen> entries = (List<MessageForumAmen>) query.execute();
		remplir(entries);
	}

	private void remplir(List<MessageForumAmen> entries) {

		for (MessageForumAmen message : entries) {

			this.ajouter(message);
		}

	}

	public void rechercherMessagesSujet(String sujet) {
		// PreparedStatement rechercheMessagesSujet =
		// connecteur.getConnexion().prepareStatement (
		// "SELECT * FROM MESSAGE"
		// + " WHERE SUJET=? ORDER BY DATECREATION ASC");
		// rechercher (rechercheMessagesSujet, new Object [] {sujet});
		// rechercheMessagesSujet.close();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(MessageForumAmen.class,
				"subject == subjectParam order by when desc");
		query.declareParameters("String subjectParam");

		List<MessageForumAmen> results = (List<MessageForumAmen>) query.execute(sujet);

		remplir(results);
	}

}
