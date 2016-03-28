package fr.nations.amen.shared.souvenirs;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("serviceSouvenir")
public interface SouvenirsService extends RemoteService
{

	void remove(Souvenir actu);


	Souvenir updateSouvenir(Souvenir newActu);
	
	
	Souvenir addSouvenir(Souvenir actu);


	List<Commentaire> getComments(String idSouvenir, int start, int length);


	void storeComment(String id, Commentaire newInput);


	int getCountVisitor();


	List<Commentaire> getComments(String appellation);


	Vector<Souvenir> getSouvenirList(int start, int length);


	Vector<Souvenir> getSouvenirbyCategory(String category, int rangeStart,
			int rangeLength);


	List<Souvenir> fetchPage(int start, int length);


	Souvenir incrementNbPagesVues(String id);


	String getBlobStoreUploadImgUrl();


	Souvenir registerIndexCellTable(String id, String string);


	Souvenir getArticleObject(String id);


	int getCountMax();


}