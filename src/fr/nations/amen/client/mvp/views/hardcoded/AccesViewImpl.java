package fr.nations.amen.client.mvp.views.hardcoded;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
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
import fr.nations.amen.client.mvp.presenters.AccesPresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.AccesView;

public class AccesViewImpl extends Composite implements AccesView {

	private Panel container;
	private AccesPresenter presenter;
	
	ScrollPanel myScroll;
	
	public AccesViewImpl() {
		super();
		
		  VerticalPanel vPanel = new VerticalPanel();
		  BanniereView logos = new BanniereView();
		  
		  vPanel.add(logos);
		   AppMenuBar menu = new AppMenuBar();
		   menu.setWidth("1000px");
		   vPanel.add(menu);
		
		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		container = new ScrollPanel();
		container.setSize("300", "500");
		
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label(UniformDim.labelWait));
		   
		 container.add(fp);
		 int   hNorth =  UniformDim.hauteurBanniere + UniformDim.hauteurMenu;
		   view.addNorth(vPanel, hNorth);
		view.add(container);
		initWidget(view);
		
		view.forceLayout();
		Window.enableScrolling(false);
		bind();
	}
	
	
	

	
	
	public Widget asWidget() {
		return this;
	}

	public void bind() {
//		  showMessagesList.addClickHandler(new ClickHandler() {   
//		      public void onClick(ClickEvent event) {
//		        if (presenter != null) {
//		         // presenter.onshowForumPasteurButtonClicked();
//		        }
//		      }
//		    });
	}

	@Override
	public void setPresenter(AccesPresenter presenter) {
		this.presenter = presenter;

	}


	@Override
	public void setCell(final ScrollPanel scroll) {
		
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
				Window.alert("reason:"+reason.getMessage());
			}

			@Override
			public void onSuccess() {
				
				myScroll = scroll;
				//myScroll.setSize("300px", "500px");
		        setWidgetAsExample(myScroll);				
			}
    	});
		
		
	}
	
	 private void setWidgetAsExample (Widget widget)
	    {
		 container.clear();
		 container.add(widget);
	    }












}
