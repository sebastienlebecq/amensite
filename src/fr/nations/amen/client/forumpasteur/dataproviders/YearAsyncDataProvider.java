package fr.nations.amen.client.forumpasteur.dataproviders;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import fr.nations.amen.shared.forumpasteur.AsyncYears;
import fr.nations.amen.shared.forumpasteur.ForumPasteurService;
import fr.nations.amen.shared.forumpasteur.ForumPasteurServiceAsync;


public class YearAsyncDataProvider extends AsyncDataProvider<AsyncYears> {

	private ForumPasteurServiceAsync rpcService;

	public YearAsyncDataProvider() {
		rpcService = GWT.create(ForumPasteurService.class);
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<AsyncYears> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();

		rpcService.getYearsList(range.getStart(), range.getLength(), new AsyncCallback<List<AsyncYears>>() {
			public void onSuccess(List<AsyncYears> result) {
				updateRowData(range.getStart(), result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}
		});
	}
}
