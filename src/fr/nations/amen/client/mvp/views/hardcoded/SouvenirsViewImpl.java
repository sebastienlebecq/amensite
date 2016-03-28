package fr.nations.amen.client.mvp.views.hardcoded;

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

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.souvenirspublic.celllist.CellListSouvenirManager;

public class SouvenirsViewImpl  extends Composite  implements SouvenirsView {

	private Panel container;
	private SouvenirsPresenter presenter;
	
	CellListSouvenirManager cellListSouvenirManager ;
	private DockLayoutPanel view;
	
	public CellListSouvenirManager getCellListSouvenirManager() {
		return cellListSouvenirManager;
	}


	public void setCellListSouvenirManager(
			CellListSouvenirManager cellListSouvenirManager) {
		this.cellListSouvenirManager = cellListSouvenirManager;
	}
	
	public SouvenirsViewImpl() {
		super();
		  VerticalPanel vPanel = new VerticalPanel();
		   AppMenuBar menu = new AppMenuBar();
		   menu.setWidth("1000px");
		   vPanel.add(menu);
		
		view = new DockLayoutPanel(Unit.PX);
		container = new SimplePanel();
		container.setSize("100%", "100%");
		
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label(UniformDim.labelWait));
//		   
		  container.add(fp);
		  int   hNorth = UniformDim.hauteurMenu;
		   view.addNorth(vPanel, hNorth);
		view.add(container);
		initWidget(view);
		view.setSize("100%", "100%");
		view.forceLayout();
		Window.enableScrolling(true);
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
	public void setPresenter(SouvenirsPresenter presenter) {
		this.presenter = presenter;

	}


	@Override
	public void setCell(final CellListSouvenirManager scroll) {

//    	GWT.runAsync(new RunAsyncCallback(){
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:"+reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {
				cellListSouvenirManager = scroll;
		        setWidgetAsExample(cellListSouvenirManager);				
//			}
//    	});
		
	}
	 private void setWidgetAsExample (Widget widget)
	    {
		
		 container.clear();
		 container.add(widget);
	    }


	@Override
	public SouvenirsPresenter getPresenter() {
		return presenter;
	}


//	@Override
//	public void waiting() {
//		container.clear();
//		container.add(new Label(UniformDim.labelWait));
//		view.forceLayout();
//	}

}
