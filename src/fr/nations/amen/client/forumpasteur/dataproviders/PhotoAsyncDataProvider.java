package fr.nations.amen.client.forumpasteur.dataproviders;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import fr.nations.amen.shared.forumpasteur.ForumPasteurService;
import fr.nations.amen.shared.forumpasteur.ForumPasteurServiceAsync;
import fr.nations.amen.shared.forumpasteur.Messages;


public class PhotoAsyncDataProvider extends AsyncDataProvider<Messages> {

	private ForumPasteurServiceAsync rpcService;
	int month;
	int year;
	int day;

	public PhotoAsyncDataProvider(int year, int month, int day) {
		rpcService = GWT.create(ForumPasteurService.class);
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
	protected void onRangeChanged(HasData<Messages> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();

		rpcService.getPhotoByDateList(year, month, day, range.getStart(), range.getLength(), new AsyncCallback<List<Messages>>() {
			public void onSuccess(List<Messages> result) {
				updateRowData(range.getStart(), result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}
		});
	}
}
