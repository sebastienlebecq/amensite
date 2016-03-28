package fr.nations.amen.client.picasa;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

import fr.nations.amen.client.jsoverlays.Entry;
import fr.nations.amen.client.jsoverlays.Feed;
import fr.nations.amen.client.jsoverlays.ui.Photo;

public class ServiceAmenPicasa {
	
	static String email = "stmichelamen@gmail.com";
	//TODO: parametriser email dans configuration du site

	public static void getPhotos(String refPicasa, final Panel photoAreaBox) {
		

		
		String url = "https://picasaweb.google.com/data/feed/api/user/"+email+"/albumid/" +
				refPicasa//"5918358923357309953" 
				+ "?alt=json-in-script";

//		// Build a JsonpRequestBuilder object
		JsonpRequestBuilder req = new JsonpRequestBuilder();
		// Set timeout of request to be 3 seconds
		req.setTimeout(6000);
		// Make request
		req.requestObject(url, new AsyncCallback<Feed>(){
			
			// Handle a failure
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught);
			}

			// Handle success
			public void onSuccess(Feed result) {
				displayPhotos(result.getEntries(),photoAreaBox);
			}			
		});
	
	}

	public static void getPhotos(String refPicasa, final TabLayoutPanel photoAreaBox) {
		
		
		String url = "https://picasaweb.google.com/data/feed/api/user/"+email+"/albumid/" +
				refPicasa//"5918358923357309953" 
				+ "?alt=json-in-script";

//		// Build a JsonpRequestBuilder object
		JsonpRequestBuilder req = new JsonpRequestBuilder();
		// Set timeout of request to be 3 seconds
		req.setTimeout(6000);
		// Make request
		req.requestObject(url, new AsyncCallback<Feed>(){
			
			// Handle a failure
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught);
			}

			// Handle success
			public void onSuccess(Feed result) {
				displayPhotos(result.getEntries(),photoAreaBox);
			}			
		});
		
		
	}

		
	
	protected static void displayPhotos(JsArray<Entry> photos,
			TabLayoutPanel photoAreaBox) {
		// Loop through the array
		for (int loop=0; loop<photos.length(); loop++){
			// Get the photo details
			Entry photoDetails = photos.get(loop);
			// Create a new Photo widget with appropriate information
			Photo photo = new Photo("Title: "+photoDetails.getTitle(), 
									"",
									manageUrl(photoDetails.getContent().getSrc()));
			photo.setSize("400px", "455px");
			photo.setStyleName("ctr");
			//photo.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			// Add to the screen
			
			
//			 FlexTable layout = new FlexTable();
//			    layout.setCellSpacing(6);
//			    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
//
//			    // Add a title to the form
//			  //  layout.setHTML(0, 0, photoDetails.getTitle());
//			    cellFormatter.setColSpan(1, 0, 0);
//			    cellFormatter.setHorizontalAlignment(
//			        1, 0, HasHorizontalAlignment.ALIGN_CENTER);
//
//			    // Add some standard form options
////			    layout.setHTML(1, 0, constants.cwDecoratorPanelFormName());
//			    layout.setWidget(1, 0, photo);
////			    layout.setHTML(2, 0, constants.cwDecoratorPanelFormDescription());
////			    layout.setWidget(2, 1, new TextBox());
//
//			    // Wrap the content in a DecoratorPanel
//			    DecoratorPanel decPanel = new DecoratorPanel();
//			    decPanel.setWidget(layout);
//			    decPanel.addStyleName("photo");
			
			photoAreaBox.add(photo);
			
			
		}
		
	}

	/**
	 * Loop through a bunch of entries and create a Photo widget and add to the display
	 * @param photos An array of photos
	 * @param photoAreaBox 
	 */
	private static void displayPhotos(JsArray<Entry> photos, Panel photoAreaBox){
		// Loop through the array
		for (int loop=0; loop<photos.length(); loop++){
			// Get the photo details
			Entry photoDetails = photos.get(loop);
			// Create a new Photo widget with appropriate information
			Photo photo = new Photo("Title: "+photoDetails.getTitle(), 
									"",
									manageUrl(photoDetails.getContent().getSrc()));
			photo.setSize("600px", "520px");
			//photo.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			// Add to the screen
			
			
			 FlexTable layout = new FlexTable();
			    layout.setCellSpacing(0);
			    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

			    // Add a title to the form
			  //  layout.setHTML(0, 0, photoDetails.getTitle());
			   // cellFormatter.setColSpan(1, 0, 0);
			    cellFormatter.setHorizontalAlignment(
			        1, 0, HasHorizontalAlignment.ALIGN_CENTER);

			    // Add some standard form options
//			    layout.setHTML(1, 0, constants.cwDecoratorPanelFormName());
			    layout.setWidget(1, 0, photo);
//			    layout.setHTML(2, 0, constants.cwDecoratorPanelFormDescription());
//			    layout.setWidget(2, 1, new TextBox());

			    // Wrap the content in a DecoratorPanel
			    DecoratorPanel decPanel = new DecoratorPanel();
			    decPanel.setWidget(layout);
			    decPanel.addStyleName("photo");
			    decPanel.addStyleName("ctr");
			  
			
			photoAreaBox.add(decPanel);
			
		}
		
	}
	private static List<Photo> arrayPhotos;
	
public static List<Photo> getPhotos(String refPicasa) {

		
		String url = "https://picasaweb.google.com/data/feed/api/user/"+email+"/albumid/" +
				refPicasa//"5918358923357309953" 
				+ "?alt=json-in-script";

//		// Build a JsonpRequestBuilder object
		JsonpRequestBuilder req = new JsonpRequestBuilder();
		// Set timeout of request to be 3 seconds
		req.setTimeout(3000);
		// Make request
		req.requestObject(url, new AsyncCallback<Feed>(){
			

			// Handle a failure
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught);
			}

			// Handle success
			public void onSuccess(Feed result) {
				 arrayPhotos = displayPhotos(result.getEntries());
			}			
		});
	return arrayPhotos;
	}
	private static List<Photo> displayPhotos(JsArray<Entry> photos){
		
		List<Photo> arrayPhotos = new ArrayList<Photo>(photos.length());
		// Loop through the array
		for (int loop=0; loop<photos.length(); loop++){
			// Get the photo details
			Entry photoDetails = photos.get(loop);
			// Create a new Photo widget with appropriate information
			Photo photo = new Photo("", 
									"",
									manageUrl(photoDetails.getContent().getSrc()));
			//photo.setSize("300px", "200px");
			//photo.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			// Add to the screen
			arrayPhotos.add(photo);
		}
		
		return arrayPhotos;
	}
	/**
	 * Picasa has started returning "https" as part of photo URLs, which we can't show within our 
	 * IFRAME managed GWT application.
	 * However, just using http works fine to get the image, so we'll check and replace if needed.
	 * @param url
	 * @return
	 */
	private static String manageUrl(String url){
		GWT.log(url);
		String result = url;
		if (result.startsWith("https"))
			result = "http"+result.substring(5);
		GWT.log(result);
		return result;
	}






}
