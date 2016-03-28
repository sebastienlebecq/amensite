package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.event.shared.EventBus;

import fr.nations.amen.client.acces.LoginInfo;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.events.AppBusyEvent;
import fr.nations.amen.client.mvp.events.AppFreeEvent;
import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;
import fr.nations.amen.client.mvp.ui.AppLoadingView;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.souvenirspublic.celllist.CellListSouvenirManager;
import fr.nations.amen.shared.souvenirs.Souvenir;

public class SouvenirsPresenterImpl implements SouvenirsPresenter {
	
	private final SouvenirsView souvenirView;
	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	private LoginInfo loginInfo;
	private EventBus eventBus; 
	private CellListSouvenirManager celllSouvenirManagerPanel;
	
	AppLoadingView loading;
	
	public SouvenirsPresenterImpl(SouvenirsView souvenirView) {
		this.souvenirView = souvenirView;
		this.eventBus = clientFactory.getEventBus();
		this.onshowSouvenir();
		bind();
	}

	private void onshowSouvenir() {
		
		eventBus.fireEvent(new AppBusyEvent());
		//souvenirView.waiting();
		
		
		GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
				eventBus.fireEvent(new AppFreeEvent());
			}

			@Override
			public void onSuccess() {
//				if (celllSouvenirManagerPanel ==null) 
//					celllSouvenirManagerPanel = new CellListSouvenirManager();
//		    	//souvenirView.setCell(celllSouvenirManagerPanel, loginInfo);
//		    	
//		    	souvenirView.setCell(celllSouvenirManagerPanel);
//		    	eventBus.fireEvent(new AppFreeEvent());
//		    	History.newItem(Tokens.SOUVENIRS);			
			}
    	});
		
	}

	
	public void bind() {
		souvenirView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(souvenirView.asWidget());
	}

	@Override
	public void souvenirSelected(Souvenir current,
			CellListSouvenirManager cellListSouvenirManager, Range range, String c) {
		// TODO Auto-generated method stub
		
	}
	
	

}
