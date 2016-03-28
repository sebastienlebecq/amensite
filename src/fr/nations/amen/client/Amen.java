package fr.nations.amen.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.mvp.MvpContent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Amen implements EntryPoint {
	
	private Integer widthRLP;
	
	private Double widthEast = 5d/100;

	private Double widthWest = 5d/100;

	private RootLayoutPanel rootLayout;

	private DockLayoutPanel dockLayout;

	private LayoutPanel layout;

	public static int heightWindow;
	//private LayoutPanel layout;	

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

	    rootLayout = RootLayoutPanel.get();
		widthRLP = rootLayout.getOffsetWidth();
		rootLayout.addStyleName("panLateral");
		
		heightWindow = Window.getClientHeight();
		
		
		
//		dockLayout = new DockLayoutPanel(Unit.PCT);
//		dockLayout.setWidth(widthRLP.toString()+"px");
//		dockLayout.setStyleName("body");
//		
//		SimplePanel west = new SimplePanel();
//		west.setStyleName("panLateral");
//		dockLayout.addWest(west, this.getWidthWest()*100);
//		
//		SimplePanel east = new SimplePanel();
//		east.setStyleName("panLateral");
//		dockLayout.addEast(east, this.getWidthEast()*100);
//
//		Widget mainView = new MvpContent();
//		dockLayout.add(mainView);
//		dockLayout.forceLayout();
		
		layout = new LayoutPanel();
		//layout.setWidth(widthRLP.toString()+"px");
		layout.setWidth("1000px");
		layout.setStyleName("body");
		
		SimplePanel west = new SimplePanel();
		west.setStyleName("panLateral");
		
		SimplePanel east = new SimplePanel();
		east.setStyleName("panLateral");

		layout.add(west);
		layout.add(east);
		
		Widget mainView = new MvpContent();
		layout.add(mainView);
		
		layout.setWidgetLeftWidth(west, 0, Unit.PCT, 0, Unit.PCT);
		//layout.setWidgetLeftWidth(west, 0, Unit.PCT, 0, Unit.PCT);
		
		//layout.setWidgetRightWidth(east, 0, Unit.PCT, this.getWidthEast()*100, Unit.PCT);
		layout.setWidgetRightWidth(east, 0, Unit.PCT, 0, Unit.PCT);
		
		layout.setWidgetLeftRight(mainView, 0, Unit.PCT, 0, Unit.PCT);
		
		layout.setWidgetLeftRight(mainView,  0, Unit.PCT, 0, Unit.PCT);
		layout.forceLayout();
		
		 Window.addResizeHandler(resizeHandler);	
		// rootLayout.add(dockLayout);
		rootLayout.add(layout);
		
	}
	   /* *************  WIDGET CENTERING CODE *************** */
    private ResizeHandler resizeHandler = new ResizeHandler()
    {
        public void onResize (ResizeEvent event)
        {
            setWidgetToMaxWidthAndHeight();
           // heightWindow = Window.getClientHeight();
        }
    };



    private void setWidgetToMaxWidthAndHeight ()
    {
    	//dockLayout.setWidth("100%");
    	//layout.setHeight("100%");
    	
		//widthRLP = rootLayout.getOffsetWidth();
    }


	public Double getWidthEast() {
		return widthEast;
	}


	public void setWidthEast(Double widthEast) {
		this.widthEast = widthEast;
	}


	public Double getWidthWest() {
		return widthWest;
	}


	public void setWidthWest(Double widthWest) {
		this.widthWest = widthWest;
	}


	public Integer getWidthRLP() {
		return widthRLP;
	}

	public void setWidthRLP(int widthRLP) {
		this.widthRLP = widthRLP;
	}
}
