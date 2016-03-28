package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WelcomePlace extends Place{
	
	public WelcomePlace(){
	}
	
//	public String getBreadCrumb(){
//		return BreadCrumbs.WELCOME;
//	}

	public static class Tokenizer implements PlaceTokenizer<WelcomePlace>{

		public WelcomePlace getPlace(String token) {
			return new WelcomePlace();
		}

		public String getToken(WelcomePlace place) {
			return "Welcome";
		}
	}
}
