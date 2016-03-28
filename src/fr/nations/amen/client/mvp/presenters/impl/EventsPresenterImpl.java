package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;


import fr.nations.amen.client.actupublic.celllist.CellListConsult;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.views.EventsView;
import fr.nations.amen.client.mvp.presenters.EventsPresenter;

public class EventsPresenterImpl implements EventsPresenter {
	private final EventsView eventsView;
	
	CellListConsult cellListExample;

	public EventsPresenterImpl(EventsView eventsView) {
		this.eventsView = eventsView;

		this.onshowEvent();
		bind();
	}

	private void onshowEvent() {
	
//		GWT.runAsync(new RunAsyncCallback(){
//			@Override
//			public void onFailure(Throwable reason) {
//			}
//
//			@Override
//			public void onSuccess() {
				if (cellListExample==null) cellListExample = new CellListConsult();
		    	
		    	eventsView.setCell(cellListExample);
		    	History.newItem(Tokens.EVENTS);			
//			}
//    	});
		
	}

	public void bind() {
		eventsView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(eventsView.asWidget());
	}


}
