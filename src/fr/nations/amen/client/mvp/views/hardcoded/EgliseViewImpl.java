package fr.nations.amen.client.mvp.views.hardcoded;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.Amen;
import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.eglise.visionpublic.ListeEgliseSujet;
import fr.nations.amen.client.mvp.presenters.EglisePresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.EgliseView;
import fr.nations.amen.shared.conf.ConfigService;
import fr.nations.amen.shared.conf.ConfigServiceAsync;
import fr.nations.amen.shared.conf.ConfigSite;

public class EgliseViewImpl  extends Composite implements EgliseView {
	
	private Panel container;
	private EglisePresenter presenter;
	ConfigServiceAsync cfService = GWT.create(ConfigService.class);

	private HorizontalPanel imgPresentation = new HorizontalPanel();

	private HTML imgPresHtml;
	
	public EgliseViewImpl() {
		super();
		
VerticalPanel vPanelNorth = new VerticalPanel();
		

		VerticalPanel vPanel = new VerticalPanel();

		AppMenuBar menu = new AppMenuBar();
		menu.setWidth("1010px");
		vPanelNorth.add(menu);

//		Image logoImg = new Image("/images/banniere_amen.jpg");
//		logoImg.setWidth("975px");
//		logoImg.setStyleName("bandeau");
//		vPanel.add(logoImg);
		
		imgPresHtml = new HTML("bonjour");
		imgPresHtml.setStyleName("ctr");
		imgPresentation.add(imgPresHtml);
		imgPresentation.setStyleName("ctr");
		vPanel.add(imgPresentation);
		
		cfService.getConfigSite(new AsyncCallback<ConfigSite>() {
			public void onSuccess(ConfigSite result) {
				
				String srcImg = "";

				if (result.getImgPresentationUrl() == null
						|| result.getImgPresentationUrl().equals("")) {

				} else {
					srcImg = "/amen/getSouveniruploadImg?blob-key="
							+ result.getImgPresentationUrl();
				}
				imgPresHtml
						.setHTML("<img src='"
								+ srcImg
								+ "' style='width: 970px;border-color:black;border=10;' />");
				imgPresHtml.setWidth("970px");
				imgPresHtml.setStyleName("bandeau");

			}

			public void onFailure(Throwable caught) {
				Window.alert("error load image");
			}
		});
		
		ListeEgliseSujet llouanges = new ListeEgliseSujet();
		llouanges.setWidth("400px");
		
		 FlexTable layout = new FlexTable();
		 layout.setCellSpacing(40);
		layout.setWidget(0, 0, llouanges);
		
		layout.setStyleName("flex");
		 vPanel.add(layout);

		 vPanel.add(new HTML("<br/>"));
			vPanel.add(new HTML("<br/>"));
			vPanel.add(new HTML("<br/>"));
			vPanel.add(new HTML("<br/>"));
			vPanel.add(new HTML("<br/>"));
			vPanel.add(new HTML("<br/>"));
			
			DockLayoutPanel view = new DockLayoutPanel(Unit.PX);

			container = new ScrollPanel(vPanel);
			//container.setAlwaysShowScrollBars(false);
			
			int hScroll = Amen.heightWindow;

			container.setSize("1000px", hScroll+"px");
			
			int   hNorth =  UniformDim.hauteurMenu;
			   view.addNorth(vPanelNorth, hNorth);
			view.add(container);
			
			initWidget(view);
			view.setSize("100%", "100%");
			view.forceLayout();
			Window.enableScrolling(false);
			
			
		 
		 
		
	}

	private void bind() {
		// TODO Auto-generated method stub
		
	}


	
	ScrollPanel myTabLayoutPanel;
	
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setCell(final ScrollPanel scroll) {

//    	GWT.runAsync(new RunAsyncCallback(){
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:"+reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {
				
				myTabLayoutPanel = scroll;
				//myTabLayoutPanel.setSize("100%", "100%");
				
				//cellListExample = cellList;
		        setWidgetAsExample(myTabLayoutPanel);				
//			}
//    	});

	}
	 private void setWidgetAsExample (Widget widget)
	    {
		 container.clear();
		 container.add(widget);
	    }

	@Override
	public void setPresenter(EglisePresenter visionPresenter) {
		this.presenter = visionPresenter;

	}

}
