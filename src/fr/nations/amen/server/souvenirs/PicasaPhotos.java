package fr.nations.amen.server.souvenirs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;

public class PicasaPhotos {
	
//	private PicasawebService myService;
//	private static final String API_PREFIX = "https://picasaweb.google.com/data/feed/api/user/";
//
//	public PicasaPhotos() {
//		super();
//		myService = new PicasawebService("exampleCo-exampleApp-1");
//		try {
//			myService.setUserCredentials("slebecq@gmail.com",
//					"130789qcebel");
//			
//			
//			 List<AlbumEntry> albums = getAlbums("default");
//			 
//			int count = 0;
//			 for (AlbumEntry entry : albums) {
//				System.out.println("Album " + count++ + ") " + entry.getTitle().getPlainText());
//				System.out.println(entry.getDescription().getPlainText());
//			      }
//			 
//			 
//			 
//			
//		} catch (AuthenticationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//	public List<AlbumEntry> getAlbums(String username) throws IOException,
//	ServiceException {
//
//String albumUrl = API_PREFIX + username;
//UserFeed userFeed = getFeed(albumUrl, UserFeed.class);
//
//List<GphotoEntry> entries = userFeed.getEntries();
//List<AlbumEntry> albums = new ArrayList<AlbumEntry>();
//for (GphotoEntry entry : entries) {
//	GphotoEntry adapted = entry.getAdaptedEntry();
//	if (adapted instanceof AlbumEntry) {
//		albums.add((AlbumEntry) adapted);
//	}
//}
//return albums;
//}
//
//public List<PhotoEntry> getPhotos(AlbumEntry album) throws IOException,
//	ServiceException {
//
//String feedHref = getLinkByRel(album.getLinks(), Link.Rel.FEED);
//AlbumFeed albumFeed = getFeed(feedHref, AlbumFeed.class);
//
//List<GphotoEntry> entries = albumFeed.getEntries();
//List<PhotoEntry> photos = new ArrayList<PhotoEntry>();
//for (GphotoEntry entry : entries) {
//	GphotoEntry adapted = entry.getAdaptedEntry();
//	if (adapted instanceof PhotoEntry) {
//		photos.add((PhotoEntry) adapted);
//	}
//}
//
//return photos;
//}
//
///**
//* Helper function to allow retrieval of a feed by string url, which will
//* create the URL object for you. Most of the Link objects have a string
//* href which must be converted into a URL by hand, this does the
//* conversion.
//*/
//public <T extends GphotoFeed> T getFeed(String feedHref, Class<T> feedClass)
//	throws IOException, ServiceException {
//System.out.println("Get Feed URL: " + feedHref);
//return myService.getFeed(new URL(feedHref), feedClass);
//}
//
///**
//* Helper function to get a link by a rel value.
//*/
//public String getLinkByRel(List<Link> links, String relValue) {
//for (Link link : links) {
//	if (relValue.equals(link.getRel())) {
//		return link.getHref();
//	}
//}
//throw new IllegalArgumentException("Missing " + relValue + " link.");
//}
//
///**
//* Helper function to add a kind parameter to a url.
//*/
//public String addKindParameter(String url, String kind) {
//if (url.contains("?")) {
//	return url + "&kind=" + kind;
//} else {
//	return url + "?kind=" + kind;
//}
//}

}
