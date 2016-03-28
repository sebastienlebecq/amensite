package fr.nations.amen.shared.actus;

import java.util.Vector;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.nations.amen.server.actus.ActuEntry;

public interface ActualitesServiceAsync {

//	Request updatePhotoDetails(Actu photoDetails, AsyncCallback<Actu> asyncCallback);
//	Request getPhotoDetails(String id, AsyncCallback<Actu> callback);
	Request getEventList(int rangeStart, int rangeLength, AsyncCallback<Vector<Actu>> callback);
//	Request getPhotoList(int rangeStart, int rangeLength, String sortOn, boolean isAscending, AsyncCallback<Vector<Actu>> callback);
//	Request getYearsList(int rangeStart, int rangeLength,AsyncCallback<List<AsyncYears>> callback);
//	Request getMonthsList(int year, int rangeStart, int rangeLength, AsyncCallback<List<AsyncMonths>> callback);
//	Request getDaysList(int year, int month, int rangeStart, int rangeLength, AsyncCallback<List<AsyncDays>> callback);
//	Request getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength, AsyncCallback<List<Actu>> callback);


void addActu(Actu actu, AsyncCallback<Actu> callback);

void remove(Actu actu, AsyncCallback<Void> callback);


void updateActu(Actu actu, AsyncCallback<Actu> callback);
}
