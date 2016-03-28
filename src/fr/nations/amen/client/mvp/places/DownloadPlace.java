package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DownloadPlace extends Place{

	public DownloadPlace() {
	}
	
	public static class Tokenizer implements PlaceTokenizer<DownloadPlace>{

		public DownloadPlace getPlace(String token) {
			return new DownloadPlace();
		}

		public String getToken(DownloadPlace place) {
			return null;
		}
	}

}
