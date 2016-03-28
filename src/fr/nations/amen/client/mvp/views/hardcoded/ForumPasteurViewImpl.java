package fr.nations.amen.client.mvp.views.hardcoded;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.BanniereView;
import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.forumpasteur.celltree.CellTreeExample;
import fr.nations.amen.client.mvp.presenters.ForumPasteurPresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.ForumPasteurView;

public class ForumPasteurViewImpl extends Composite implements ForumPasteurView {

	private Panel container;
	private ForumPasteurPresenter presenter;
	//private Button showMessagesList;
	//private Image logoImg;
	
	private CellTreeExample cellTreeExample;

	public ForumPasteurViewImpl() {
		super();
		
		  VerticalPanel vPanel = new VerticalPanel();
		  BanniereView logos = new BanniereView();
		//  logoImg = new Image("/images/banniere_amen.jpg");
		 // logoImg.setWidth("99%");
		  
		 // vPanel.add(logos);
	;
		   AppMenuBar menu = new AppMenuBar();
		   vPanel.add(menu);
		   menu.setWidth("1000px");
		   menu.setHeight("35px");
		  // vPanel.setBorderWidth(1);
		
		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		container = new SimplePanel();
		container.setSize("100%", "100%");
		
//		  showMessagesList = new Button("Show Messages");
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label(UniformDim.labelWait));
//		   fp.add(showMessagesList);
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
	public void setPresenter(ForumPasteurPresenter presenter) {
		this.presenter = presenter;

	}


	@Override
	public void setCell(final CellTreeExample cellTree) {
		
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
				Window.alert("reason:"+reason.getMessage());
			}

			@Override
			public void onSuccess() {
		    	//if (cellTreeExample==null) cellTreeExample = new CellTreeExample();
				cellTreeExample = cellTree;
		        setWidgetAsExample(cellTreeExample);				
			}
    	});
		
		
	}
	
	 private void setWidgetAsExample (Widget widget)
	    {
		 container.clear();
			Anchor signOut = new Anchor("Forum du Pasteur");
			signOut.setHref("http://forumpasteurnarcisse.blogspot.fr/2013/10/blog-post.html");
			
			Frame frameforum = new Frame(signOut.getHref());
			frameforum.setSize("100%", "99%");
		 container.add(frameforum);
		 //container.add(widget);
	    }

}
