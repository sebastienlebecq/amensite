package fr.nations.amen.client.louangespublic.celllist;

import java.util.List;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;

/**
 * An AsynchDataProvider using RPC call to get all Actu Information from the server.
 *
 */
public class CatDataAsyncDataProvider extends AsyncDataProvider<Louange> {

	/**
	 * Reference to the RPC service this provider will use to get data.
	 */
	private LouangesServiceAsync rpcService;
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Create a new AllDataAsyncDataProvider instance and set up the 
	 * RPC framework that it will use.
	 * @param category 
	 */
	public CatDataAsyncDataProvider(String category) {
		this.category = category;
		rpcService = GWT.create(LouangesService.class);
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<Louange> display) {
		
		// Get the new range required.
		final Range range = display.getVisibleRange();
		
		//Window.alert("range.getStart():"+range.getStart()+",range.getLength():"+range.getLength() );
		
		// Call getPhotoList RPC call
		rpcService.getLouangeByCategory(category, range.getStart(), range.getStart()+ UniformDim.PAGE_PUBLIC_SIZE, 
				new AsyncCallback<Vector<Louange>>() {

			// There's been a failure in the RPC call
			// Normally you would handle that in a good way, 
			// here we just throw up an alert.
			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			// We've successfully for the data from the RPC call, 
			// Now we update the row data with that result starting 
			// at a particular row in the cell widget (usually the range start) 
			public void onSuccess(Vector<Louange> result) {
				updateRowData(range.getStart(), result);
			}
		});
	}
}

