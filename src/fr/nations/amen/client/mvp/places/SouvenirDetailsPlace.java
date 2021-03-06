package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SouvenirDetailsPlace extends Place {

	private String photoId;
	private String startPage;
	private String category;


	//private String pageSize;

	
	public SouvenirDetailsPlace(String token) {
		
		String[] args = token.split("&");
		
		this.photoId = args[0];
		this.startPage = args[1];
		this.category = args[2];
	}
	
	public String getCategory() {
		return category;
	}

	public String getSouvenirId() {
		return photoId;
	}
	
	public String getStartPage() {
		return startPage;
	}

//	public String getPageSize() {
//		return pageSize;
//	}
	
	@Prefix("Souvenir")
	public static class Tokenizer implements PlaceTokenizer<SouvenirDetailsPlace>{

		public SouvenirDetailsPlace getPlace(String token) {

			
			return new SouvenirDetailsPlace(token);
			
		}

		public String getToken(SouvenirDetailsPlace place) {
			return place.getSouvenirId()+"&"+place.getStartPage()+"&"+place.getCategory();
		}

	}
}