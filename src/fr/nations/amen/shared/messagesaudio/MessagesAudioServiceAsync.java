package fr.nations.amen.shared.messagesaudio;

import java.util.List;
import java.util.Vector;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.nations.amen.server.actus.ActuEntry;
import fr.nations.amen.shared.souvenirs.Souvenir;

public interface MessagesAudioServiceAsync {

void addSouvenir(MessageAudio actu, AsyncCallback<MessageAudio> callback);

void remove(MessageAudio actu, AsyncCallback<Void> callback);


void updateSouvenir(MessageAudio actu, AsyncCallback<MessageAudio> callback);


void getSouvenirList(String category, int rangeStart, int rangeLength,
		AsyncCallback<Vector<MessageAudio>> callback);

void getComments(String appellation,
		AsyncCallback<List<Commentaire>> asyncCallback);

void storeComment(String id, Commentaire newInput, AsyncCallback<Void> asyncCallback);

void getCountVisitor(AsyncCallback<Integer> asyncCallback);

void getComments(String idSouvenir, int start, int length,
		AsyncCallback<List<Commentaire>> asyncCallback);

void getBlobStoreUploadUrl(AsyncCallback<String> callback);

void getSouvenirAllList(int start, int length,
		AsyncCallback<Vector<MessageAudio>> asyncCallback);

void fetchPage(int start, int length, AsyncCallback<List<MessageAudio>> callback);

void incrementNbPagesVues(String id, AsyncCallback<MessageAudio> callback);




}
