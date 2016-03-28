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
import fr.nations.amen.client.messagesaudiopublic.celllist.CellListMessagesAudioManager;
import fr.nations.amen.client.mvp.presenters.MessagesAudioPresenter;

import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.MessagesAudioView;

public class MessagesAudioViewImpl  extends Composite implements MessagesAudioView {

	public MessagesAudioViewImpl() {
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
		//bind();
	}

	private Panel container;
	
	private MessagesAudioPresenter presenter;
	private CellListMessagesAudioManager cellListMessagesAudioManager;
	
	private DockLayoutPanel view;

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(MessagesAudioPresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void setCell(CellListMessagesAudioManager scroll) {
		cellListMessagesAudioManager = scroll;
        setWidgetAsExample(cellListMessagesAudioManager);	
		
	}

	 private void setWidgetAsExample (Widget widget)
	    {
		
		 container.clear();
		 container.add(widget);
	    }
}
