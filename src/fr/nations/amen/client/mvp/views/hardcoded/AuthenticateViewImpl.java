package fr.nations.amen.client.mvp.views.hardcoded;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.AccueilPrivateForm;
import fr.nations.amen.client.AuthentificationInfos;
import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.acces.ChoiceViewPanel;
import fr.nations.amen.client.acces.actu.celllist.ActualitesManager;
import fr.nations.amen.client.eglise.visionprivate.celllist.MenuEgliseManager;
import fr.nations.amen.client.forumeglise.celllist.ForumEgliseManager;
import fr.nations.amen.client.louanges.celllist.LouangesManager;
import fr.nations.amen.client.messagesaudio.celllist.MessagesAudioManager;
import fr.nations.amen.client.mvp.presenters.AuthenticatePresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.AuthenticateView;
import fr.nations.amen.client.souvenirs.celllist.SouvenirsManager;
import fr.nations.amen.client.temoignages.celllist.TemoignagesManager;

public class AuthenticateViewImpl extends Composite implements AuthenticateView {

	private DockLayoutPanel container;
	private AuthenticatePresenter presenter;
	FocusPanel feedback;
	private DockLayoutPanel view;

	public AuthenticateViewImpl() {
		super();

		VerticalPanel vPanel = new VerticalPanel();
		//LogosView logos = new LogosView();

		//vPanel.add(logos);
		AppMenuBar menu = new AppMenuBar();
		menu.setWidth("1000px");
		vPanel.add(menu);

		view = new DockLayoutPanel(Unit.PX);
		container = new DockLayoutPanel(Unit.PX);
		container.setSize("100%", "100%");

		FlowPanel fp = new FlowPanel();
		fp.add(new Label(UniformDim.labelWait));
		container.add(fp);

		container.forceLayout();

		int   hNorth =  UniformDim.hauteurMenu;

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
		// showMessagesList.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// if (presenter != null) {
		// // presenter.onshowForumPasteurButtonClicked();
		// }
		// }
		// });
	}

	@Override
	public void setPresenter(AuthenticatePresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void setCell(final ActualitesManager managerActu,
			final AuthentificationInfos authentication) {
//		GWT.runAsync(new RunAsyncCallback() {
//
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:" + reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {

				container.clear();

				container.addNorth(createFeedbackTab(authentication), 15);
				container.addSouth(new Label("amen, amen, amen!"), 20);
				container.add(managerActu.asWidget());
				container.forceLayout();

//			}
//		});

	}

	@Override
	public void setCell(final AuthentificationInfos authentication) {
//		GWT.runAsync(new RunAsyncCallback() {
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:" + reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {

				setWidgetAsExample(authentication.getInitPanel());
//			}
//		});

	}


	@Override
	public void setChoice(final ChoiceViewPanel choiceView,final AuthentificationInfos authentication) {
//		GWT.runAsync(new RunAsyncCallback() {
//
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:" + reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {
		container.clear();
		
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		container.add(choiceView);
		container.forceLayout();
//			}
//		});
	}
	
	@Override
	public void setCell(final SouvenirsManager managerSouvenir,
			final AuthentificationInfos authentication) {
//		GWT.runAsync(new RunAsyncCallback() {
//
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:" + reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {
		container.clear();
		
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		container.add(managerSouvenir);
		container.forceLayout();
//			}
//		});
		
	}	@Override
	public void setCell(ForumEgliseManager managerForumEglise,
			AuthentificationInfos authentication) {
		container.clear();
		
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		container.add(managerForumEglise);
		container.forceLayout();
		
	}
	
	
	
	@Override
	public void setCell(final TemoignagesManager managerTemoignages,
			final AuthentificationInfos authentication) {
//		GWT.runAsync(new RunAsyncCallback() {
//
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:" + reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {
		container.clear();
		
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		container.add(managerTemoignages);
		container.forceLayout();
//			}
//		});
		
	}
	
	@Override
	public void setCell(LouangesManager managerLouanges,
			AuthentificationInfos authentication) {
		container.clear();
		
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		container.add(managerLouanges);
		container.forceLayout();
	}
	
	@Override
	public void setCell(MenuEgliseManager managerMenuEglise,
			AuthentificationInfos authentication) {
		container.clear();
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		container.add(managerMenuEglise);
		container.forceLayout();
	}
	
	@Override
	public void setCell(final MessagesAudioManager managerMessagesAudio,
			final AuthentificationInfos authentication) {
//		GWT.runAsync(new RunAsyncCallback() {
//
//			@Override
//			public void onFailure(Throwable reason) {
//				Window.alert("reason:" + reason.getMessage());
//			}
//
//			@Override
//			public void onSuccess() {
		container.clear();
		
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		container.add(managerMessagesAudio);
		container.forceLayout();
//			}
//		});
		
	}
	
	@Override
	public void setCell(AccueilPrivateForm accueilPrivateForm,
			AuthentificationInfos authentication) {
		container.clear();
		
		container.addNorth(createFeedbackTab(authentication), 40);
		container.addSouth(new Label("amen, amen, amen!"), 20);
		
		container.add(new ScrollPanel(accueilPrivateForm));
		container.forceLayout();
		
	}
	
	private void setWidgetAsExample(Widget widget) {
		container.clear();
		container.add(widget);
	}

	private FocusPanel createFeedbackTab(AuthentificationInfos authentication) {

		// Create the FeedBack tab
		feedback = new FocusPanel();

		feedback.setStyleName("feedback");
		feedback.addStyleName("normal");

		feedback.add(authentication.getHTMLOk());

		/**
		 * If the user moves mouse over feedback tab, change its style
		 * (increases its size and changes colour - styles are in
		 * BasicProject.css)
		 */
		feedback.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				// Remove existing normal style
				// feedback.removeStyleName("normal");
				// Add the active style
				// feedback.addStyleName("active");
				// Set overflow of whole HTML page to hidden to minimise display
				// of scroll bars.
				// RootPanel.getBodyElement().getStyle().setProperty("overflow",
				// "hidden");
			}
		});

		/**
		 * If use moves mouse out of the feedback panel, return its style to
		 * normal (decreases its size and changes colour - styles are in
		 * BasicProject.css)
		 */
		feedback.addMouseOutHandler(new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				// feedback.removeStyleName("active");
				// feedback.addStyleName("normal");
				// RootPanel.getBodyElement().getStyle().setProperty("overflow",
				// "auto");
			}
		});

		/**
		 * If user clicks on the feedback tab we should start some feedback
		 * functionality. In this example, it simply displays an alert to the
		 * user.
		 */
		feedback.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Window.alert("You could provide feedback if this was implemented");
			}
		});

		return feedback;
	}











}
