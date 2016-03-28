package fr.nations.amen.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fr.nations.amen.client.mvp.places.AuthenticatePlace;
import fr.nations.amen.client.mvp.places.DownloadPlace;
import fr.nations.amen.client.mvp.places.EglisePlace;
import fr.nations.amen.client.mvp.places.ForumEglisePlace;
import fr.nations.amen.client.mvp.places.LouangeDetailsPlace;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.mvp.places.TemoignagePlace;
import fr.nations.amen.client.mvp.places.WelcomePlace;
import fr.nations.amen.client.mvp.places.WherePlace;


@WithTokenizers({WelcomePlace.Tokenizer.class, WherePlace.Tokenizer.class,
	SouvenirDetailsPlace.Tokenizer.class, AuthenticatePlace.Tokenizer.class, 
	LouangeDetailsPlace.Tokenizer.class, TemoignagePlace.Tokenizer.class,
	ForumEglisePlace.Tokenizer.class, EglisePlace.Tokenizer.class,
	DownloadPlace.Tokenizer.class
	})
public interface AppPlacesHistoryMapper extends PlaceHistoryMapper{
}
