package fr.nations.amen.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;


public class MvpContent extends Composite{

	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	SimplePanel container = new SimplePanel();
	DockLayoutPanel container2 = new DockLayoutPanel(Unit.PX);
	
	// This would be the onModuleLoad method of a normal application (but as we wrap into our GWTiA framework, it sits on its own)
	public MvpContent(){
	    EventBus eventBus = clientFactory.getEventBus();
	   // PhotoAlbumServiceAsync rpcService = clientFactory.getPhotoServices();
	    new AmenWebApp(eventBus, container);
	    container2.add(container);
	    initWidget(container2);
	    container2.setSize("100%","100%");
	    container2.forceLayout();

	  //  app.go(container);
	  //  History.fireCurrentHistoryState();
	}
	
}
