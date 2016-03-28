package fr.nations.amen.client.mvp.views.hardcoded;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.BanniereView;
import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.presenters.DownloadPresenter;
import fr.nations.amen.client.mvp.presenters.WherePresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.DownloadView;

public class DownloadViewImpl  extends Composite  implements DownloadView {
	
	public DownloadViewImpl() {
		super();

		  VerticalPanel vPanel = new VerticalPanel();
		  
		   AppMenuBar menu = new AppMenuBar();
		   //menu.setWidth("1010px");
		   vPanel.add(menu);
		   
		  BanniereView logos = new BanniereView();
		  menu.setWidth("975px");
		 // vPanel.add(logos);

		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		container = new ScrollPanel();
		container.setSize("100%", "100%");
		
		  
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label(UniformDim.labelWait));
		   //fp.add(showMessagesList);
//		   
		  container.add(fp);
		  int   hNorth =  UniformDim.hauteurMenu;
		   view.addNorth(vPanel, hNorth);
		view.add(container);
		initWidget(view);
		view.setSize("100%", "100%");
		view.forceLayout();
		Window.enableScrolling(false);

	}

	private Panel container;
	private DownloadPresenter presenter;

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(DownloadPresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void setCell(final Composite myDownloadPanel) {
		
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
				Window.alert("reason:"+reason.getMessage());
			}

			@Override
			public void onSuccess() {
				
				//myScrollPanel = scroll;
		        setWidgetAsExample(myDownloadPanel);				
			}
    	});
		
		
	}
	
	 private void setWidgetAsExample (Widget widget)
	    {
		 container.clear();
		 container.add(widget);
	    }
	 
}
