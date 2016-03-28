package fr.nations.amen.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import fr.nations.amen.client.mvp.activities.AuthenticateActivity;
import fr.nations.amen.client.mvp.activities.DownloadActivity;
import fr.nations.amen.client.mvp.activities.EgliseActivity;
import fr.nations.amen.client.mvp.activities.ForumEgliseActivity;
import fr.nations.amen.client.mvp.activities.LouangeDetailsActivity;
import fr.nations.amen.client.mvp.activities.SouvenirDetailsActivity;
import fr.nations.amen.client.mvp.activities.TemoignageActivity;
import fr.nations.amen.client.mvp.activities.WelcomeActivity;
import fr.nations.amen.client.mvp.activities.WhereActivity;
import fr.nations.amen.client.mvp.places.AuthenticatePlace;
import fr.nations.amen.client.mvp.places.DownloadPlace;
import fr.nations.amen.client.mvp.places.EglisePlace;
import fr.nations.amen.client.mvp.places.ForumEglisePlace;
import fr.nations.amen.client.mvp.places.LouangeDetailsPlace;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.mvp.places.TemoignagePlace;
import fr.nations.amen.client.mvp.places.WelcomePlace;
import fr.nations.amen.client.mvp.places.WherePlace;




public class AppActivityMapper implements ActivityMapper{

    private ClientFactory clientFactory;

    public AppActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

	public Activity getActivity(Place place) {
		if (place instanceof WelcomePlace)
			return new WelcomeActivity((WelcomePlace)place, clientFactory);
		else if (place instanceof WherePlace)
			return new WhereActivity((WherePlace)place, clientFactory);
		else if (place instanceof DownloadPlace)
			return new DownloadActivity((DownloadPlace)place, clientFactory);
		else if (place instanceof SouvenirDetailsPlace)
			return new SouvenirDetailsActivity((SouvenirDetailsPlace)place, clientFactory);
		else if (place instanceof AuthenticatePlace)
			return new AuthenticateActivity((AuthenticatePlace)place, clientFactory);
		else if (place instanceof LouangeDetailsPlace)
			return new LouangeDetailsActivity((LouangeDetailsPlace)place, clientFactory);
		else if (place instanceof TemoignagePlace)
			return new TemoignageActivity((TemoignagePlace)place, clientFactory);
		else if (place instanceof ForumEglisePlace)
			return new ForumEgliseActivity((ForumEglisePlace)place, clientFactory);
		else if (place instanceof EglisePlace)
			return new EgliseActivity((EglisePlace)place, clientFactory);
//		else if (place instanceof PhotoListPlace)
//				return new PhotoListActivity((PhotoListPlace)place, clientFactory);
//		else if (place instanceof PhotoDetailsPlace)
//				return new PhotoDetailsActivity((PhotoDetailsPlace)place, clientFactory);
		else return null;
	}
}
