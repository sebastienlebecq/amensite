package fr.nations.amen.client.forumpasteur.dataproviders;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import fr.nations.amen.shared.forumpasteur.AsyncDays;
import fr.nations.amen.shared.forumpasteur.ForumPasteurService;
import fr.nations.amen.shared.forumpasteur.ForumPasteurServiceAsync;


public class DayAsyncDataProvider extends AsyncDataProvider<AsyncDays> {

	private ForumPasteurServiceAsync rpcService;
	int month;
	int year;

	public DayAsyncDataProvider(int year, int month) {
		rpcService = GWT.create(ForumPasteurService.class);
		this.year = year;
		this.month = month;
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<AsyncDays> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();

		rpcService.getDaysList(year, month, range.getStart(), range.getLength(), new AsyncCallback<List<AsyncDays>>() {
			public void onSuccess(List<AsyncDays> result) {
				updateRowData(range.getStart(), result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}
		});
	}
}
