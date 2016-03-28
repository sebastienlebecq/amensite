package fr.nations.amen.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.nations.amen.client.mvp.views.AuthenticateView;
import fr.nations.amen.client.mvp.views.DownloadView;
import fr.nations.amen.client.mvp.views.EventsView;
import fr.nations.amen.client.mvp.views.ForumEgliseView;
import fr.nations.amen.client.mvp.views.ForumPasteurView;
import fr.nations.amen.client.mvp.views.HistoriqueView;
import fr.nations.amen.client.mvp.views.LouangesView;
import fr.nations.amen.client.mvp.views.MessagesAudioView;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.mvp.views.TemoignagesView;
import fr.nations.amen.client.mvp.views.EgliseView;
import fr.nations.amen.client.mvp.views.WelcomeView;
import fr.nations.amen.client.mvp.views.WhereView;
import fr.nations.amen.client.mvp.views.hardcoded.AuthenticateViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.DownloadViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.EgliseViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.EventsViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.ForumEgliseViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.ForumPasteurViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.HistoriqueViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.LouangesViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.MessagesAudioViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.SouvenirsViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.TemoignagesViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.WelcomeViewImpl;
import fr.nations.amen.client.mvp.views.hardcoded.WhereViewImpl;


public class ClientFactoryImpl implements ClientFactory{

	private static EventBus eventBus;
	//private static PhotoAlbumServiceAsync rpcService;
	//private static PhotoDetailsView detailView;
	//private static PhotoListView listView;
	private static WelcomeView welcomeView;
	private static ForumPasteurView forumPasteurView;	
	private static ForumEgliseView forumEgliseView;
	private static EventsView eventsView;
	private static HistoriqueView historiqueView;	
	private static WhereView whereView;	
	private static SouvenirsView accesView;	
	private static AuthenticateView authenticateView;
	private static TemoignagesView temoignagesView;
	private static EgliseView egliseView;
	private static LouangesView louangesView;
	private static MessagesAudioView messagesAudioView;
	//private static EgliseView eglisView;
	private static DownloadView downloadView;
	
	private static PlaceController placeController;
	
	public EventBus getEventBus() {
		if (eventBus == null) eventBus = new SimpleEventBus();
		return eventBus;
	}

	public WelcomeView getWelcomeView() {
		if (welcomeView == null) welcomeView = new WelcomeViewImpl();
		return welcomeView;
	}
	
	public ForumPasteurView getForumPasteurView(){
		if (forumPasteurView == null) forumPasteurView = new ForumPasteurViewImpl();
		return forumPasteurView;
	}

	
	public EventsView getEventsView() {
		if (eventsView == null) eventsView = new EventsViewImpl();
		return eventsView;
	}
	
	public HistoriqueView getHistoriqueView(){
		if (historiqueView == null) historiqueView = new HistoriqueViewImpl();
		return historiqueView;
	}
	
	public WhereView getWhereView(){
		if (whereView == null) whereView = new WhereViewImpl();
		return whereView;
	}
	public SouvenirsView getSouvenirsView(){
		if (accesView == null) accesView = new SouvenirsViewImpl();
		return accesView;
	}
	public AuthenticateView getAuthenticateView(){
		if (authenticateView == null) authenticateView = new AuthenticateViewImpl();
		return authenticateView;
	}
	
	public TemoignagesView getTemoignagesView(){
		if (temoignagesView == null) temoignagesView = new TemoignagesViewImpl();
		return temoignagesView;
	}

	@Override
	public EgliseView getEgliseView() {
		egliseView = new EgliseViewImpl();
		return egliseView;
	}

	@Override
	public LouangesView getLouangesView() {
		if (louangesView == null) louangesView = new LouangesViewImpl();
		return louangesView;
	}

	@Override
	public MessagesAudioView getMessagesAudioView() {
		if (messagesAudioView == null) messagesAudioView = new MessagesAudioViewImpl();
		return messagesAudioView;
	}
	
	@Override
	public PlaceController getPlaceController() {
		if (placeController == null) placeController = new PlaceController(getEventBus());
		return placeController;
	}

	@Override
	public ForumEgliseView getForumEgliseView() {
		if (forumEgliseView == null) forumEgliseView = new ForumEgliseViewImpl();
		return forumEgliseView;
	}

	@Override
	public DownloadView getDownloadView() {
		if (downloadView == null) downloadView = new DownloadViewImpl();
		return downloadView;
	}
}
