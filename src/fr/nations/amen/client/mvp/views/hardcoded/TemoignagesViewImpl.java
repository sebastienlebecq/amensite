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

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;
import fr.nations.amen.client.mvp.presenters.TemoignagesPresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.TemoignagesView;
import fr.nations.amen.client.temoignagespublic.celllist.CellListTemoignagesManager;

public class TemoignagesViewImpl extends Composite implements TemoignagesView {

	private Panel container;
	private TemoignagesPresenter presenter;
	
	CellListTemoignagesManager cellListTemoignagesrManager ;
	
	public TemoignagesViewImpl() {
		  VerticalPanel vPanel = new VerticalPanel();
//		  LogosView logos = new LogosView();
//		  
//		  vPanel.add(logos);
		   AppMenuBar menu = new AppMenuBar();
		   menu.setWidth("1000px");
		   vPanel.add(menu);
		
		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		container = new SimplePanel();
		container.setSize("100%", "100%");
		
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label(UniformDim.labelWait));
//		   
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

	
	
	
	private void bind() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(TemoignagesPresenter presenter) {
		this.presenter = presenter;

	}


	@Override
	public void setCell(final CellListTemoignagesManager scroll) {

    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
				Window.alert("reason:"+reason.getMessage());
			}

			@Override
			public void onSuccess() {
				cellListTemoignagesrManager = scroll;
		        setWidgetAsExample(cellListTemoignagesrManager);				
			}
    	});
		
	}
	 private void setWidgetAsExample (Widget widget)
	    {
		
		 container.clear();
		 container.add(widget);
	    }




	@Override
	public TemoignagesPresenter getPresenter() {
		return presenter;
	}
}
