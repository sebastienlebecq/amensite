package fr.nations.amen.shared.louanges2;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.nations.amen.client.louanges2.SearchVideo;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("serviceSearchVideo")
public interface SearchVideoService extends RemoteService
{

	SearchVideo updateSearchVideo(SearchVideo actu);

	Vector<SearchVideo> getVideoList(int rangeStart, int rangeLength);

	SearchVideo addSearchVideo(SearchVideo actu);

	Vector<SearchVideo> getVideoList(int start, int length, String sortOnName,
			boolean isAscending);

	List<AsyncMonths> getMonthsList(int year, int start, int length);

	List<SearchVideo> getVideoByDateList(int year, int month, int day,
			int start, int length);

	List<AsyncYears> getYearsList(int start, int length);

	List<AsyncDays> getVideoList(int year, int month, int start, int length);
	

}