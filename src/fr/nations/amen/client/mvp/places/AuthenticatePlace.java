package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;


public class AuthenticatePlace extends Place {
	private String id;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public AuthenticatePlace(String id) {
		this.id = id;
	}

	
	public static class Tokenizer implements PlaceTokenizer<AuthenticatePlace>{

		public AuthenticatePlace getPlace(String token) {
			return new AuthenticatePlace(token);
			
		}

		public String getToken(AuthenticatePlace place) {
			return place.getId();
		}
	}
}
