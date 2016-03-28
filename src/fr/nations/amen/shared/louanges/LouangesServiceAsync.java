package fr.nations.amen.shared.louanges;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface LouangesServiceAsync {

void addSouvenir(Louange actu, AsyncCallback<Louange> callback);

void remove(Louange actu, AsyncCallback<Void> callback);


void updateSouvenir(Louange actu, AsyncCallback<Louange> callback);


void getComments(String appellation,
		AsyncCallback<List<Commentaire>> asyncCallback);

void storeComment(String id, Commentaire newInput, AsyncCallback<Void> asyncCallback);

void getCountVisitor(AsyncCallback<Integer> asyncCallback);

void getComments(String idSouvenir, int start, int length,
		AsyncCallback<List<Commentaire>> asyncCallback);

void getBlobStoreUploadUrl(AsyncCallback<String> callback);

void getSouvenirAllList(int start, int length,
		AsyncCallback<Vector<Louange>> asyncCallback);

void fetchPage(int start, int length, AsyncCallback<List<Louange>> callback);

void incrementNbPagesVues(String id, AsyncCallback<Louange> callback);

void getBlobStoreUploadImgUrl(AsyncCallback<String> asyncCallback);

void getLouangeByCategory(String category, int rangeStart, int rangeLength,
		AsyncCallback<Vector<Louange>> callback);

void registerIndexCellTable(String id, String string,
		AsyncCallback<Louange> callback);

void getArticleObject(String id, AsyncCallback<Louange> asyncCallback);

void getCountMaxComments(String id, AsyncCallback<Integer> asyncCallback);

void getCountMax(AsyncCallback<Integer> asyncCallback);





}
