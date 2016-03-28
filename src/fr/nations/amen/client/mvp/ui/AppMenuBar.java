package fr.nations.amen.client.mvp.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.Tokens;

import fr.nations.amen.client.mvp.places.DownloadPlace;
import fr.nations.amen.client.mvp.places.EglisePlace;
import fr.nations.amen.client.mvp.places.ForumEglisePlace;
import fr.nations.amen.client.mvp.places.LouangeDetailsPlace;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.mvp.places.AuthenticatePlace;
import fr.nations.amen.client.mvp.places.TemoignagePlace;
import fr.nations.amen.client.mvp.places.WelcomePlace;
import fr.nations.amen.client.mvp.places.WherePlace;


public class AppMenuBar extends Composite{

	interface AppMenuBarUiBinder extends UiBinder<Widget, AppMenuBar> {}
	private static AppMenuBarUiBinder uiBinder = GWT.create(AppMenuBarUiBinder.class);
	private ClientFactory clientFactory = GWT.create(ClientFactory.class);

	public AppMenuBar(){
		initWidget(uiBinder.createAndBindUi(this));
		setUp();
	}
	
	@UiField MenuItem welcome;
	//@UiField MenuItem list;
	//@UiField MenuItem forumPasteur;
	@UiField MenuItem forumEglise;
	//@UiField MenuItem quisommesnous;
	//@UiField MenuItem events;
	//@UiField MenuItem historique;
	@UiField MenuItem eglise;	
	@UiField MenuItem where;
	@UiField MenuItem acces;
	@UiField MenuItem temoignages;
	@UiField MenuItem appAndroid;
	@UiField MenuItem louanges;
	//@UiField MenuItem organisation;
	//@UiField MenuItem rechLouange;
	@UiField MenuItem souvenirs;
	@UiField MenuItem download;
	//@UiField MenuItem messagesaudio;
	//@UiField MenuItem croyance;
	
	@SuppressWarnings("deprecation")
	private void setUp(){
		welcome.setCommand(new Command(){
			public void execute() {
				goTo(new WelcomePlace());
				//History.newItem(Tokens.HOME);
			}			
		});
		
//		messagesaudio.setCommand(new Command(){
//			public void execute() {
//				History.newItem(Tokens.MESSAGESAUDIO);
//			}			
//		});
		
		acces.setCommand(new Command(){
			public void execute() {
				goTo(new AuthenticatePlace("init"));//*
				//History.newItem(Tokens.AUTHENTICATE);
			}			
		});
		
//		forumPasteur.setCommand(new Command(){
//			@Override
//			public void execute() {
//				History.newItem(Tokens.FORUMPASTEUR);
//			}
//		});
		
//		events.setCommand(new Command(){
//			@Override
//			public void execute() {
//				History.newItem(Tokens.EVENTS);
//			}
//		});
		
//		historique.setCommand(new Command(){
//			@Override
//			public void execute() {
//				History.newItem(Tokens.HISTORIQUE);
//			}
//		});
		
		eglise.setCommand(new Command(){
			@Override
			public void execute() {
				//History.newItem(Tokens.VISION);
				goTo(new EglisePlace("999"));
			}
		});
		where.setCommand(new Command(){
			@Override
			public void execute() {
				goTo(new WherePlace());
				//History.newItem(Tokens.WHERE);
			}
		});
		
		download.setCommand(new Command(){
			@Override
			public void execute() {
				goTo(new DownloadPlace());
				//History.newItem(Tokens.WHERE);
			}
		});
		
		souvenirs.setCommand(new Command(){
			@Override
			public void execute() {
				//Window.alert("A venir...");
				goTo(new SouvenirDetailsPlace("999999&99&cat0"));
				//History.newItem(Tokens.SOUVENIRS);
			}
		});
		
		
		forumEglise.setCommand(new Command(){
			@Override
			public void execute() {
				//Window.alert("A venir...");
				goTo(new ForumEglisePlace("999999&99&cat0"));
			}
		});
		
		temoignages.setCommand(new Command(){
			@Override
			public void execute() {
				goTo(new TemoignagePlace("999999&99&cat0"));
				//History.newItem(Tokens.TEMOIGNAGES);
			}
		});
		
		appAndroid.setCommand(new Command(){
			@Override
			public void execute() {
				Window.alert("A venir...");
			}
		});
		
		louanges.setCommand(new Command(){
			@Override
			public void execute() {
				//Window.alert("A venir...");
				//History.newItem(Tokens.LOUANGES);
				goTo(new LouangeDetailsPlace("999999&99&cat0"));
			}
		});
//		
//		quisommesnous.setCommand(new Command(){
//			@Override
//			public void execute() {
//				History.newItem(Tokens.APROPOS);
//			}
//		});
//		
//		organisation.setCommand(new Command(){
//			@Override
//			public void execute() {
//				History.newItem(Tokens.ORGANISATION);
//			}
//		});		
//		
//		croyance.setCommand(new Command(){
//			@Override
//			public void execute() {
//				History.newItem(Tokens.CONFESSIONFOI);
//			}
//		});
		
		
//		

	}

	  public void goTo(Place place) {
	        clientFactory.getPlaceController().goTo(place);
	    }
	
}
