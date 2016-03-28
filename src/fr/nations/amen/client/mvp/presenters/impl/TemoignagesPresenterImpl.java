package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.event.shared.EventBus;

import fr.nations.amen.client.acces.LoginInfo;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.events.AppBusyEvent;
import fr.nations.amen.client.mvp.events.AppFreeEvent;
import fr.nations.amen.client.mvp.presenters.TemoignagesPresenter;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.mvp.views.TemoignagesView;
import fr.nations.amen.client.souvenirspublic.celllist.CellListSouvenirManager;
import fr.nations.amen.client.temoignagespublic.celllist.CellListTemoignagesManager;
import fr.nations.amen.shared.temoignages.Temoignage;

public class TemoignagesPresenterImpl implements TemoignagesPresenter {

	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	private final TemoignagesView temoignagesView;
	private EventBus eventBus; 
	private LoginInfo loginInfo;
	private CellListTemoignagesManager cellTemoignagesManagerPanel;
	
	public TemoignagesPresenterImpl(TemoignagesView temoignagesView) {
		this.temoignagesView = temoignagesView;
		this.eventBus = clientFactory.getEventBus();
		this.onshowTemoignages();
		bind();
	} 


	private void onshowTemoignages() {
		
//		eventBus.fireEvent(new AppBusyEvent());
//		
//		GWT.runAsync(new RunAsyncCallback(){
//			@Override
//			public void onFailure(Throwable reason) {
//				eventBus.fireEvent(new AppFreeEvent());
//			}
//
//			@Override
//			public void onSuccess() {
//				if (cellTemoignagesManagerPanel ==null) 
//					cellTemoignagesManagerPanel = new CellListTemoignagesManager();
//		    	//souvenirView.setCell(celllSouvenirManagerPanel, loginInfo);
//		    	
//				temoignagesView.setCell(cellTemoignagesManagerPanel);
//		    	History.newItem(Tokens.TEMOIGNAGES);	
//				eventBus.fireEvent(new AppFreeEvent());
//			}
//    	});
//		
	}


	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(temoignagesView.asWidget());

	}

	@Override
	public void bind() {
		temoignagesView.setPresenter(this);

	}


	@Override
	public void temoignageSelected(Temoignage current,
			CellListTemoignagesManager cellListTemoignageManager, Range range,
			String category) {
		// TODO Auto-generated method stub
		
	}

}
