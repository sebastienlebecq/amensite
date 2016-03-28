package fr.nations.amen.client.eglise.visionprivate.celllist.dataproviders;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import fr.nations.amen.shared.eglise.MenuEglise;
import fr.nations.amen.shared.eglise.MenuEgliseService;
import fr.nations.amen.shared.eglise.MenuEgliseServiceAsync;




/**
 * An AsynchDataProvider using RPC call to get all Actu Information from the server.
 *
 */
public class AllDataAsyncDataProvider extends AsyncDataProvider<MenuEglise> {

	/**
	 * Reference to the RPC service this provider will use to get data.
	 */
	private MenuEgliseServiceAsync rpcService;

	/**
	 * Create a new AllDataAsyncDataProvider instance and set up the 
	 * RPC framework that it will use.
	 */
	public AllDataAsyncDataProvider() {
		rpcService = GWT.create(MenuEgliseService.class);
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<MenuEglise> display) {
		
		// Get the new range required.
		final Range range = display.getVisibleRange();
		
		// Call getPhotoList RPC call
		rpcService.getMenuEglisebyCategory("cat0",range.getStart(), range.getLength(), new AsyncCallback<List<MenuEglise>>() {

			// There's been a failure in the RPC call
			// Normally you would handle that in a good way, 
			// here we just throw up an alert.
			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			// We've successfully for the data from the RPC call, 
			// Now we update the row data with that result starting 
			// at a particular row in the cell widget (usually the range start) 
			public void onSuccess(List<MenuEglise> result) {
				updateRowData(range.getStart(), result);
			}
		});
	}
}
