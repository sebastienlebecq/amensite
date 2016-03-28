package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WherePlace extends Place{
	
	public WherePlace(){
	}
	
//	public String getBreadCrumb(){
//		return BreadCrumbs.WELCOME;
//	}

	public static class Tokenizer implements PlaceTokenizer<WherePlace>{

		public WherePlace getPlace(String token) {
			return new WherePlace();
		}

		public String getToken(WherePlace place) {
			return null;
		}
	}
}