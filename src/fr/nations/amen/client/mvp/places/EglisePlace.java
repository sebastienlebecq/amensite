package fr.nations.amen.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EglisePlace extends Place{
	
	private String articleId;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public EglisePlace(String token) {
		//String[] args = token.split("&");
		this.articleId = token;
	}

	public static class Tokenizer implements PlaceTokenizer<EglisePlace>{

		public EglisePlace getPlace(String token) {
			return new EglisePlace(token);
		}

		public String getToken(EglisePlace place) {
			return  place.getArticleId();
		}
	}
}