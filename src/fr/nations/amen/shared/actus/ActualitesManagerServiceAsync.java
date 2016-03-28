package fr.nations.amen.shared.actus;

import java.util.List;
import java.util.Vector;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ActualitesManagerServiceAsync {

	Request updatePhotoDetails(Actu photoDetails, AsyncCallback<Actu> asyncCallback);
	Request getActuList(int rangeStart, int rangeLength, AsyncCallback<Vector<Actu>> callback);
	Request getActuList(int rangeStart, int rangeLength, String sortOn, boolean isAscending, AsyncCallback<Vector<Actu>> callback);
}
