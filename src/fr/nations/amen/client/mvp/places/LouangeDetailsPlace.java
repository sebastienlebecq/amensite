package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class LouangeDetailsPlace  extends Place{

	private String louangeId;
	public String getLouangeId() {
		return louangeId;
	}

	public void setLouangeId(String louangeId) {
		this.louangeId = louangeId;
	}

	public String getStartPage() {
		return startPage;
	}

	public String getCategory() {
		return category;
	}

	private String startPage;
	private String category;
	
	public LouangeDetailsPlace(String token) {
	
		String[] args = token.split("&");

		this.louangeId =  args[0];
		this.startPage = args[1];
		this.category = args[2];
	}
	
	@Prefix("Louange")
	public static class Tokenizer implements PlaceTokenizer<LouangeDetailsPlace>{

		public LouangeDetailsPlace getPlace(String token) {

			
			return new LouangeDetailsPlace(token);
			
		}

		public String getToken(LouangeDetailsPlace place) {
			return place.getLouangeId()+"&"+place.getStartPage()+"&"+place.getCategory();
		}

	}
}
