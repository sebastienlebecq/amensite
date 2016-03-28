package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class TemoignagePlace  extends Place{

	private String id;
	public String getId() {
		return id;
	}

	public void setLouangeId(String louangeId) {
		this.id = louangeId;
	}

	public String getStartPage() {
		return startPage;
	}

	public String getCategory() {
		return category;
	}

	private String startPage;
	private String category;
	
	public TemoignagePlace(String token) {
	
		String[] args = token.split("&");

		this.id =  args[0];
		this.startPage = args[1];
		this.category = args[2];
	}
	
	@Prefix("Temoignage")
	public static class Tokenizer implements PlaceTokenizer<TemoignagePlace>{

		public TemoignagePlace getPlace(String token) {

			
			return new TemoignagePlace(token);
			
		}

		public String getToken(TemoignagePlace place) {
			return place.getId()+"&"+place.getStartPage()+"&"+place.getCategory();
		}

	}

}
