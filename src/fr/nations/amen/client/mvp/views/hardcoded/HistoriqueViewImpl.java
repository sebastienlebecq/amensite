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

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.presenters.HistoriquePresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.HistoriqueView;

public class HistoriqueViewImpl  extends Composite implements HistoriqueView {

	private Panel container;
	private HistoriquePresenter presenter;
	
	ScrollPanel myTabLayoutPanel;
	
	public HistoriqueViewImpl() {
		super();
		
		  VerticalPanel vPanel = new VerticalPanel();
		 // BanniereView logos = new BanniereView();
		  
		  //vPanel.add(logos);
		   AppMenuBar menu = new AppMenuBar();
		   menu.setWidth("1000px");
		   vPanel.add(menu);
		
		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		container = new ScrollPanel();
		container.setSize("100%", "100%");
		
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label(UniformDim.labelWait));
		  // fp.add(showMessagesList);
		   
		  container.add(fp);
		  int   hNorth =  UniformDim.hauteurMenu;
		   view.addNorth(vPanel, hNorth);
		view.add(container);
		initWidget(view);
		view.setSize("100%", "100%");
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
	public void setPresenter(HistoriquePresenter presenter) {
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
				
				myTabLayoutPanel = scroll;
				//myTabLayoutPanel.setSize("100%", "100%");
				
				//cellListExample = cellList;
		        setWidgetAsExample(myTabLayoutPanel);				
			}
    	});
		
		
	}
	
	 private void setWidgetAsExample (Widget widget)
	    {
		 container.clear();
		 container.add(widget);
	    }












}
