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
import fr.nations.amen.client.mvp.presenters.ForumEglisePresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.ForumEgliseView;
import fr.nations.amen.client.mvp.views.ForumPasteurView;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.forumeglisepublic.celllist.CellListForumEgliseManager;

public class ForumEgliseViewImpl  extends Composite  implements ForumEgliseView{

	private Panel container;
	private ForumEglisePresenter presenter;
	
	CellListForumEgliseManager cellListSouvenirManager ;
	private DockLayoutPanel view;
	
	public CellListForumEgliseManager getCellListSouvenirManager() {
		return cellListSouvenirManager;
	}


	public void setCellListSouvenirManager(
			CellListForumEgliseManager cellListSouvenirManager) {
		this.cellListSouvenirManager = cellListSouvenirManager;
	}
	
	public ForumEgliseViewImpl() {
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
	public void setPresenter(ForumEglisePresenter presenter) {
		this.presenter = presenter;

	}


	@Override
	public void setCell(final CellListForumEgliseManager scroll) {

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
	public ForumEglisePresenter getPresenter() {
		return presenter;
	}


//	@Override
//	public void waiting() {
//		container.clear();
//		container.add(new Label(UniformDim.labelWait));
//		view.forceLayout();
//	}

}
