package fr.nations.amen.client.mvp;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;

import fr.nations.amen.client.mvp.events.AppBusyEvent;
import fr.nations.amen.client.mvp.events.AppBusyHandler;
import fr.nations.amen.client.mvp.events.AppFreeEvent;
import fr.nations.amen.client.mvp.events.AppFreeHandler;
import fr.nations.amen.client.mvp.places.WelcomePlace;
import fr.nations.amen.client.mvp.ui.BusyIndicator;

public class AmenWebApp
// ValueChangeHandler<String> 
{

	EventBus eventBus;
	//PhotoAlbumServiceAsync rpcService;
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	
    private Place defaultPlace = new WelcomePlace();



	public AmenWebApp(EventBus eventBus, AcceptsOneWidget appWidget) {
		PlaceController placeController = clientFactory.getPlaceController();
		 // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);
        


        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlacesHistoryMapper historyMapper= GWT.create(AppPlacesHistoryMapper.class);
         PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        // Goes to the place represented on URL else default placE
        
        //TODO: surcharger method dans historyHandler qui teste l'existance de hashtag
        
        historyHandler.handleCurrentHistory();
		this.eventBus = eventBus;
		//this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		// Listen for History Change Events
		//History.addValueChangeHandler(this);

		// Listen for AppBusy events on the event bus
		eventBus.addHandler(AppBusyEvent.getType(), new AppBusyHandler() {
			public void onAppBusyEvent(AppBusyEvent event) {
				BusyIndicator.busy();
			}
		});

		// Listen for AppFree events on the event bus
		eventBus.addHandler(AppFreeEvent.getType(), new AppFreeHandler() {
			public void onAppFreeEvent(AppFreeEvent event) {
				BusyIndicator.free();
			}
		});
	}

	// Display and handle the display of thumbnails.
//	private void doPhotoListDisplay(final String page) {
//		// History.newItem(Tokens.LIST + "&" + page, false);
//		new PhotoListPresenterImpl(clientFactory.getListView()).go(container);
//	}
//
//	// Display and handle the Editing of a Photo's details
//	private void doPhotoDetailsEdit(final String photoId) {
//
//		new PhotoDetailsPresenterImpl(clientFactory.getPhotoView(), photoId)
//				.go(container);
//
//	}

	// Display the Welcome Screen
//	private void doWelcome() {
//		new WelcomePresenterImpl(clientFactory.getWelcomeView()).go(container);
//
//	}
//
//	private void doForumPasteur() {
//		new ForumPasteurPresenterImpl(clientFactory.getForumPasteurView())
//				.go(container);
//
//	}
//
//	private void doEvents() {
//		new EventsPresenterImpl(clientFactory.getEventsView()).go(container);
//
//	}
//
//	private void doHistorique() {
//		new HistoriquePresenterImpl(clientFactory.getHistoriqueView())
//				.go(container);
//	}
//
//	private void doWhere() {
//
//		new WherePresenterImpl(clientFactory.getWhereView()).go(container);
//	}
//
//	private void doAuthenticate() {
//		new AuthenticatePresenterImpl(clientFactory.getAuthenticateView())
//				.go(container);
//
//	}
//
//	private void doSouvenirs() {
//
//		new SouvenirsPresenterImpl(clientFactory.getSouvenirsView())
//				.go(container);
//
//	}
//
//	private void doTemoignages() {
//		new TemoignagesPresenterImpl(clientFactory.getTemoignagesView())
//				.go(container);
//
//	}
//
//	private void doVision(String longId, String item) {
//		new VisionPresenterImpl(clientFactory.getVisionView(), longId, item)
//				.go(container);
//
//	}
//
//	private void doLouanges() {
//		new LouangesPresenterImpl(clientFactory.getLouangesView())
//				.go(container);
//
//	}
//
//	private void doMessagesAudio() {
//		new MessagesAudioPresenterImpl(clientFactory.getMessagesAudioView())
//				.go(container);
//
//	}
//
//	public void onValueChange(ValueChangeEvent<String> event) {
//		String token = event.getValue();
//
//		if ((token != null) && (!token.equals(Tokens.HOME))) {
//			if (token.startsWith(Tokens.LIST)) {
//				String[] bits = token.split("&");
//				String page = "0";
//				if (bits.length > 1)
//					page = bits[1];
//				this.doPhotoListDisplay(page);
//			} else if (token.contains(Tokens.DETAIL)) {
//				String[] bits = token.split("&");
//				doPhotoDetailsEdit(bits[1]);
//			} else
				
//				if (token.contains(Tokens.FORUMPASTEUR)) {
//				doForumPasteur();
//			} else if (token.contains(Tokens.EVENTS)) {
//				doEvents();
//			} else if (token.contains(Tokens.HISTORIQUE)) {
//				doVision(findId(Tokens.HISTORIQUE), Tokens.HISTORIQUE);
//			} else if (token.contains(Tokens.WHERE)) {
//				doWhere();
//			} else if (token.contains(Tokens.AUTHENTICATE)) {
//				doAuthenticate();
//			} else if (token.contains(Tokens.SOUVENIRS)) {
//				doSouvenirs();
//			} else if (token.contains(Tokens.TEMOIGNAGES)) {
//				doTemoignages();
//			} else if (token.contains(Tokens.VISION)) {
//				doVision(findId(Tokens.VISION), Tokens.VISION);
//			} else if (token.contains(Tokens.LOUANGES)) {
//				doLouanges();
//			} else if (token.contains(Tokens.ORGANISATION)) {
//				doVision(findId(Tokens.ORGANISATION), Tokens.ORGANISATION);
//			} else if (token.contains(Tokens.CONFESSIONFOI)) {
//				doVision(findId(Tokens.CONFESSIONFOI), Tokens.CONFESSIONFOI);
//			} else if (token.contains(Tokens.APROPOS)) {
//				doVision(findId(Tokens.APROPOS), Tokens.APROPOS);
//			} else if (token.contains(Tokens.MESSAGESAUDIO)) {
//				doMessagesAudio();
//			}
//		} else {
//			doWelcome();
//		}
//	}
//
//	private String findId(String strTokens) {
//		
//		for (String [] cpltok : UniformDim.idMenuEglise){
//			if (cpltok[0].equals(strTokens))
//				return cpltok[1];
//		}
//		
//		return "";
//	}
//
//	HasWidgets container;
//
//	public void go(HasWidgets container) {
//		this.container = container;
//	}
}
