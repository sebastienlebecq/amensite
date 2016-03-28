package fr.nations.amen.client.louanges2.dataproviders;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.louanges2.SearchVideo;
import fr.nations.amen.shared.louanges2.SearchVideoService;
import fr.nations.amen.shared.louanges2.SearchVideoServiceAsync;


public class PhotoAsyncDataProvider extends AsyncDataProvider<SearchVideo> {

	private SearchVideoServiceAsync rpcService;
	int month;
	int year;
	int day;

	public PhotoAsyncDataProvider(int year, int month, int day) {
		rpcService = GWT.create(SearchVideoService.class);
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<SearchVideo> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();

		rpcService.getVideoByDateList(year, month, day, range.getStart(), range.getLength(), new AsyncCallback<List<SearchVideo>>() {
			public void onSuccess(List<SearchVideo> result) {
				updateRowData(range.getStart(), result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}
		});
	}
}
