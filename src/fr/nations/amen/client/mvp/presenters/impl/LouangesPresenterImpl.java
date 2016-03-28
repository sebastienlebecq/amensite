package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.event.shared.EventBus;

import fr.nations.amen.client.acces.LoginInfo;

import fr.nations.amen.client.louangespublic.celllist.CellListLouangesManager;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.events.AppBusyEvent;
import fr.nations.amen.client.mvp.events.AppFreeEvent;
import fr.nations.amen.client.mvp.presenters.LouangesPresenter;
import fr.nations.amen.client.mvp.views.LouangesView;
import fr.nations.amen.shared.louanges.Louange;

public class LouangesPresenterImpl implements LouangesPresenter {
	
private ClientFactory clientFactory = GWT.create(ClientFactory.class);
private LouangesView messagesAudioView;
private EventBus eventBus; 
private LoginInfo loginInfo;
private CellListLouangesManager cellMessagesAudioManagerPanel;

public LouangesPresenterImpl(LouangesView messagesAudioView) {
	this.messagesAudioView = messagesAudioView;
	this.eventBus = clientFactory.getEventBus();
	this.onshowMessagesAudio();
	bind();
}

private void onshowMessagesAudio() {
	eventBus.fireEvent(new AppBusyEvent());
	
	GWT.runAsync(new RunAsyncCallback(){
		@Override
		public void onFailure(Throwable reason) {
			eventBus.fireEvent(new AppFreeEvent());
		}

		@Override
		public void onSuccess() {
			if (cellMessagesAudioManagerPanel ==null) 
				cellMessagesAudioManagerPanel = new CellListLouangesManager(null);
	    	//souvenirView.setCell(celllSouvenirManagerPanel, loginInfo);
	    	
			messagesAudioView.setCell(cellMessagesAudioManagerPanel);
	    	History.newItem(Tokens.LOUANGES);	
			eventBus.fireEvent(new AppFreeEvent());
		}
	});
	
}

@Override
public void go(HasWidgets container) {
	container.clear();
	container.add(messagesAudioView.asWidget());
	
}

@Override
public void bind() {
	messagesAudioView.setPresenter(this);
	
}

@Override
public void louangeSelected(Louange current,
		CellListLouangesManager cellListSouvenirManager, Range range,
		String category) {
	// TODO Auto-generated method stub
	
}


}
