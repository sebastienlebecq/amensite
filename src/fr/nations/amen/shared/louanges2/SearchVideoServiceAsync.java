package fr.nations.amen.shared.louanges2;

import java.util.List;
import java.util.Vector;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.nations.amen.client.louanges2.SearchVideo;

public interface SearchVideoServiceAsync {

	void updateSearchVideo(SearchVideo actu, AsyncCallback<SearchVideo> callback);

	void getVideoList(int rangeStart, int rangeLength,
			AsyncCallback<Vector<SearchVideo>> callback);

	void addSearchVideo(SearchVideo actu, AsyncCallback<SearchVideo> callback);

	void getVideoList(int start, int length, String sortOnName,
			boolean isAscending,
			AsyncCallback<Vector<SearchVideo>> asyncCallback);

	void getMonthsList(int year, int start, int length,
			AsyncCallback<List<AsyncMonths>> asyncCallback);

	void getVideoByDateList(int year, int month, int day, int start,
			int length, AsyncCallback<List<SearchVideo>> asyncCallback);

	void getYearsList(int start, int length,
			AsyncCallback<List<AsyncYears>> asyncCallback);

	void getVideoList(int year, int month, int start, int length,
			AsyncCallback<List<AsyncDays>> asyncCallback);



}
