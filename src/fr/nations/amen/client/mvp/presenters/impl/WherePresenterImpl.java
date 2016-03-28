package fr.nations.amen.client.mvp.presenters.impl;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
//import com.google.gwt.maps.client.LoadApi;
//import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.presenters.WherePresenter;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.mvp.views.WhereView;
import fr.nations.amen.client.ounoustrouver.TransitDirectionsServiceMapWidget;

public class WherePresenterImpl implements WherePresenter {
	private final WhereView whereView;

	private TransitDirectionsServiceMapWidget myTabLayoutPanel;

	public WherePresenterImpl(WhereView whereView) {
		this.whereView = whereView;

		this.onshowWhereView();
		bind();
	}

	private void onshowWhereView() {
	
//		GWT.runAsync(new RunAsyncCallback(){
//			
//
//			@Override
//			public void onFailure(Throwable reason) {
//			}
//
//			@Override
//			public void onSuccess() {
			   // boolean sensor = true;
			    // load all the libs for use in the maps
//			    ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
//			    loadLibraries.add(LoadLibrary.ADSENSE);
//			    loadLibraries.add(LoadLibrary.DRAWING);
//			    loadLibraries.add(LoadLibrary.GEOMETRY);
//			    loadLibraries.add(LoadLibrary.PANORAMIO);
//			    loadLibraries.add(LoadLibrary.PLACES);
//			    loadLibraries.add(LoadLibrary.WEATHER);
//			    loadLibraries.add(LoadLibrary.VISUALIZATION);
//
//			    Runnable onLoad = new Runnable() {
//			      @Override
//			      public void run() {
			        draw();
			     // }
//
//				
//			    };
//
//			    LoadApi.go(onLoad, loadLibraries, sensor);

			
//			}
//    	});
//		
	}
	private void draw() {
		
		if(myTabLayoutPanel==null)  myTabLayoutPanel = new TransitDirectionsServiceMapWidget();

		//ScrollPanel scroll = new ScrollPanel(myTabLayoutPanel);
		
		whereView.setCell(myTabLayoutPanel);
    	History.newItem(Tokens.WHERE);	
		
	}
	public void bind() {
		whereView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(whereView.asWidget());
	}


}

