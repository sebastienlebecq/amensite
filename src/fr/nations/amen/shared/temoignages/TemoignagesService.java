package fr.nations.amen.shared.temoignages;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.temoignages.Commentaire;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("serviceTemoignage")
public interface TemoignagesService extends RemoteService
{
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	Vector<Temoignage> getTemoignagesList(int rangeStart, int rangeLength);
	



	void remove(Temoignage actu);


	Temoignage updateTemoignage(Temoignage newActu);
	
	
	Temoignage addTemoignage(Temoignage actu);


	String getImage(String tagName);


	void storeComment(String idSouvenir, Commentaire newInput);


	List<fr.nations.amen.shared.temoignages.Commentaire> getComments(String id);


	List<Commentaire> getComments(String idSouvenir, int start, int length);


	Vector<Temoignage> getTemoignagebyCategory(String category, int start,
			int length);


	List<Temoignage> fetchPage(int start, int length);




	Temoignage incrementNbPagesVues(String id);




	Vector<Temoignage> getTemoignageByCategory(String category, int intValue,
			int i);




	Temoignage registerIndexCellTable(String id, String index);




	int getCountMax();



	//Vector<Temoignage> getSouvenirList(int rangeStart, int rangeLength);


}