package fr.nations.amen.client.louanges2.dataproviders;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import fr.nations.amen.shared.louanges2.AsyncDays;
import fr.nations.amen.shared.louanges2.AsyncMonths;
import fr.nations.amen.shared.louanges2.SearchVideoService;
import fr.nations.amen.shared.louanges2.SearchVideoServiceAsync;


public class MonthAsyncDataProvider extends AsyncDataProvider<AsyncMonths> {

	private SearchVideoServiceAsync rpcService;
	
	int year;

	public MonthAsyncDataProvider(int year) {
		this.year = year;
		rpcService = GWT.create(SearchVideoService.class);
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	protected void onRangeChanged(HasData<AsyncMonths> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();

		rpcService.getMonthsList(year, range.getStart(), range.getLength(), new AsyncCallback<List<AsyncMonths>>() {
			public void onSuccess(List<AsyncMonths> result) {
				updateRowData(range.getStart(), result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}
		});
	}
}
