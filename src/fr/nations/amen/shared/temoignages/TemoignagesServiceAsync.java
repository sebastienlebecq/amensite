package fr.nations.amen.shared.temoignages;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.temoignages.Commentaire;

public interface TemoignagesServiceAsync {

//	Request updatePhotoDetails(Actu photoDetails, AsyncCallback<Actu> asyncCallback);
//	Request getPhotoDetails(String id, AsyncCallback<Actu> callback);
//	Request getEventList(int rangeStart, int rangeLength, AsyncCallback<Vector<Souvenir>> callback);
//	Request getPhotoList(int rangeStart, int rangeLength, String sortOn, boolean isAscending, AsyncCallback<Vector<Actu>> callback);
//	Request getYearsList(int rangeStart, int rangeLength,AsyncCallback<List<AsyncYears>> callback);
//	Request getMonthsList(int year, int rangeStart, int rangeLength, AsyncCallback<List<AsyncMonths>> callback);
//	Request getDaysList(int year, int month, int rangeStart, int rangeLength, AsyncCallback<List<AsyncDays>> callback);
//	Request getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength, AsyncCallback<List<Actu>> callback);


void addTemoignage(Temoignage actu, AsyncCallback<Temoignage> callback);

void remove(Temoignage actu, AsyncCallback<Void> callback);


void updateTemoignage(Temoignage actu, AsyncCallback<Temoignage> callback);


void getImage(String tagName, AsyncCallback<String> callback);

void getTemoignagesList(int rangeStart, int rangeLength,
		AsyncCallback<Vector<Temoignage>> callback);

void storeComment(String idSouvenir, Commentaire newInput,
		AsyncCallback<Void> callback);

void getComments(String id, AsyncCallback<List<Commentaire>> callback);

void getComments(String idSouvenir, int start, int length,
		AsyncCallback<List<Commentaire>> asyncCallback);

void getTemoignagebyCategory(String category, int start, int length,
		AsyncCallback<Vector<Temoignage>> asyncCallback);

void fetchPage(int start, int length, AsyncCallback<List<Temoignage>> callback);

void incrementNbPagesVues(String id, AsyncCallback<Temoignage> callback);

void getTemoignageByCategory(String category, int intValue, int i,
		AsyncCallback<Vector<Temoignage>> asyncCallback);

void registerIndexCellTable(String id, String index,
		AsyncCallback<Temoignage> callback2);

void getCountMax(AsyncCallback<Integer> asyncCallback);
}
