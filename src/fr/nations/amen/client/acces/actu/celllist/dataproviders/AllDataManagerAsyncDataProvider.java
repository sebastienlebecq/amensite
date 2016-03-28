package fr.nations.amen.client.acces.actu.celllist.dataproviders;

import java.util.List;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.shared.actus.Actu;
import fr.nations.amen.shared.actus.ActualitesManagerService;
import fr.nations.amen.shared.actus.ActualitesManagerServiceAsync;
import fr.nations.amen.shared.actus.ActualitesService;
import fr.nations.amen.shared.actus.ActualitesServiceAsync;




/**
 * An AsynchDataProvider using RPC call to get all Actu Information from the server.
 *
 */
public class AllDataManagerAsyncDataProvider extends AsyncDataProvider<Actu> {

	/**
	 * Reference to the RPC service this provider will use to get data.
	 */
	private ActualitesManagerServiceAsync rpcService;

	/**
	 * Create a new AllDataAsyncDataProvider instance and set up the 
	 * RPC framework that it will use.
	 */
	public AllDataManagerAsyncDataProvider() {
		rpcService = GWT.create(ActualitesManagerService.class);
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<Actu> display) {
		
		// Get the new range required.
		final Range range = display.getVisibleRange();
		
		// Call getPhotoList RPC call
		rpcService.getActuList(range.getStart(),  range.getStart()+ UniformDim.PAGE_ADMIN_SIZE, new AsyncCallback<Vector<Actu>>() {

			// There's been a failure in the RPC call
			// Normally you would handle that in a good way, 
			// here we just throw up an alert.
			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			// We've successfully for the data from the RPC call, 
			// Now we update the row data with that result starting 
			// at a particular row in the cell widget (usually the range start) 
			public void onSuccess(Vector<Actu> result) {
				updateRowData(range.getStart(), result);
			}
		});
	}
}
