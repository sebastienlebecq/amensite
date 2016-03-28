package fr.nations.amen.shared.forumeglise;

import java.util.List;
import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ForumEgliseServiceAsync {

void addSouvenir(ForumEglise actu, AsyncCallback<ForumEglise> callback);

void remove(ForumEglise actu, AsyncCallback<Void> callback);


void updateSouvenir(ForumEglise actu, AsyncCallback<ForumEglise> callback);

void getComments(String appellation,
		AsyncCallback<List<Commentaire>> asyncCallback);

void storeComment(String id, Commentaire newInput, AsyncCallback<Void> asyncCallback);

void getCountVisitor(AsyncCallback<Integer> asyncCallback);

void getComments(String idSouvenir, int start, int length,
		AsyncCallback<List<Commentaire>> asyncCallback);

void getSouvenirList(int start, int length,
		AsyncCallback<Vector<ForumEglise>> asyncCallback);

void getSouvenirbyCategory(String category, int rangeStart, int rangeLength,
		AsyncCallback<Vector<ForumEglise>> callback);

void fetchPage(int start, int length, AsyncCallback<List<ForumEglise>> callback);

void incrementNbPagesVues(String id, AsyncCallback<ForumEglise> callback);

void getBlobStoreUploadImgUrl(AsyncCallback<String> asyncCallback);

void registerIndexCellTable(String id, String string,
		AsyncCallback<ForumEglise> callback2);




}
