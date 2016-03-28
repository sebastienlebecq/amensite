package fr.nations.amen.shared.souvenirs;

import java.util.List;
import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.nations.amen.shared.IArticle;


public interface SouvenirsServiceAsync extends IArticle{

void addSouvenir(Souvenir actu, AsyncCallback<Souvenir> callback);

void remove(Souvenir actu, AsyncCallback<Void> callback);


void updateSouvenir(Souvenir actu, AsyncCallback<Souvenir> callback);

void getComments(String appellation,
		AsyncCallback<List<Commentaire>> asyncCallback);

void storeComment(String id, Commentaire newInput, AsyncCallback<Void> asyncCallback);

void getCountVisitor(AsyncCallback<Integer> asyncCallback);

void getComments(String idSouvenir, int start, int length,
		AsyncCallback<List<Commentaire>> asyncCallback);

void getSouvenirList(int start, int length,
		AsyncCallback<Vector<Souvenir>> asyncCallback);

void getSouvenirbyCategory(String category, int rangeStart, int rangeLength,
		AsyncCallback<Vector<Souvenir>> callback);

void fetchPage(int start, int length, AsyncCallback<List<Souvenir>> callback);

void incrementNbPagesVues(String id, AsyncCallback<Souvenir> callback);

void getBlobStoreUploadImgUrl(AsyncCallback<String> asyncCallback);

void registerIndexCellTable(String id, String string,
		AsyncCallback<Souvenir> callback2);

void getArticleObject(String id, AsyncCallback<Souvenir> asyncCallback);

void getCountMax(AsyncCallback<Integer> asyncCallback);




}
