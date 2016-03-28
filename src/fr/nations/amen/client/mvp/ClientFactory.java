package fr.nations.amen.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.nations.amen.client.mvp.views.AuthenticateView;
import fr.nations.amen.client.mvp.views.DownloadView;
import fr.nations.amen.client.mvp.views.EventsView;
import fr.nations.amen.client.mvp.views.ForumEgliseView;
import fr.nations.amen.client.mvp.views.ForumPasteurView;
import fr.nations.amen.client.mvp.views.HistoriqueView;
import fr.nations.amen.client.mvp.views.MessagesAudioView;
import fr.nations.amen.client.mvp.views.LouangesView;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.mvp.views.TemoignagesView;
import fr.nations.amen.client.mvp.views.EgliseView;
import fr.nations.amen.client.mvp.views.WelcomeView;
import fr.nations.amen.client.mvp.views.WhereView;


public interface ClientFactory {
	public EventBus getEventBus();
	//PhotoAlbumServiceAsync getPhotoServices();
	//PhotoDetailsView getPhotoView();
	//PhotoListView getListView();
	WelcomeView getWelcomeView();
	ForumPasteurView getForumPasteurView();
	EventsView getEventsView();
	HistoriqueView getHistoriqueView();
	WhereView getWhereView();
	SouvenirsView getSouvenirsView();
	AuthenticateView getAuthenticateView();
	TemoignagesView getTemoignagesView();
	EgliseView getEgliseView();
	LouangesView getLouangesView();
	MessagesAudioView getMessagesAudioView();
	
	public PlaceController getPlaceController();
	public ForumEgliseView getForumEgliseView();
	public DownloadView getDownloadView();
}
