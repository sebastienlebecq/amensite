package fr.nations.amen.shared.forumeglise;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("serviceForumEglise")
public interface ForumEgliseService extends RemoteService
{

	void remove(ForumEglise actu);


	ForumEglise updateSouvenir(ForumEglise newActu);
	
	
	ForumEglise addSouvenir(ForumEglise actu);


	List<Commentaire> getComments(String idSouvenir, int start, int length);


	void storeComment(String id, Commentaire newInput);


	int getCountVisitor();


	List<Commentaire> getComments(String appellation);


	Vector<ForumEglise> getSouvenirList(int start, int length);


	Vector<ForumEglise> getSouvenirbyCategory(String category, int rangeStart,
			int rangeLength);


	List<ForumEglise> fetchPage(int start, int length);


	ForumEglise incrementNbPagesVues(String id);


	String getBlobStoreUploadImgUrl();


	ForumEglise registerIndexCellTable(String id, String string);


}