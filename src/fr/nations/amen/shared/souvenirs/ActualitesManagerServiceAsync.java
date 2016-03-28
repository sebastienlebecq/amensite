package fr.nations.amen.shared.souvenirs;

import java.util.List;
import java.util.Vector;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ActualitesManagerServiceAsync {

	Request updatePhotoDetails(Souvenir photoDetails, AsyncCallback<Souvenir> asyncCallback);
	Request getPhotoDetails(String id, AsyncCallback<Souvenir> callback);
	Request getActuList(int rangeStart, int rangeLength, AsyncCallback<Vector<Souvenir>> callback);
	Request getActuList(int rangeStart, int rangeLength, String sortOn, boolean isAscending, AsyncCallback<Vector<Souvenir>> callback);
	Request getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength, AsyncCallback<List<Souvenir>> callback);
}
