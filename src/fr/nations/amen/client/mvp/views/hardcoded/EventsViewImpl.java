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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.BanniereView;
import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.actupublic.celllist.CellListConsult;
import fr.nations.amen.client.mvp.presenters.EventsPresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.EventsView;

public class EventsViewImpl extends Composite implements EventsView, UniformDim {

	private Panel container;
	private EventsPresenter presenter;
	//private Image logoImg;
	
	CellListConsult cellListExample;

	public EventsViewImpl() {
		super();
		
		  VerticalPanel vPanel = new VerticalPanel();
		  BanniereView logos = new BanniereView();
		  
		  //vPanel.add(logos);

		   AppMenuBar menu = new AppMenuBar();
			menu.setWidth("1000px");
		   vPanel.add(menu);
		  // vPanel.setBorderWidth(1);
		
		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		container = new SimplePanel();
		container.setSize("100%", "100%");
		
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label(UniformDim.labelWait));
		   //fp.add(showMessagesList);
//		   
		  container.add(fp);
		//int   hNorth =  UniformDim.hauteurBanniere + UniformDim.hauteurMenu;
		  int   hNorth = UniformDim.hauteurMenu;
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
	public void setPresenter(EventsPresenter presenter) {
		this.presenter = presenter;

	}



	public void setCell(final CellListConsult cellList) {
		
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
				Window.alert("reason:"+reason.getMessage());
			}

			@Override
			public void onSuccess() {
				cellListExample = cellList;
		        setWidgetAsExample(cellListExample);				
			}
    	});
		
		
	}
	
	 private void setWidgetAsExample (Widget widget)
	    {
		
		 container.clear();
		 container.add(widget);
	    }


}
