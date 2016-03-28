package fr.nations.amen.client.mvp.views.hardcoded;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.nations.amen.client.Amen;
import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.compolesplus.ListeArticlespLus;
import fr.nations.amen.client.compolesplus.ListeLouangespEcoutes;
import fr.nations.amen.client.compolesplus.ListeVideospVues;
import fr.nations.amen.client.mvp.presenters.WelcomePresenter;
import fr.nations.amen.client.mvp.ui.AppMenuBar;
import fr.nations.amen.client.mvp.views.WelcomeView;
import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.client.rezosocio.ButtonFactory;
import fr.nations.amen.client.welcome.WelcomePresentation;
import fr.nations.amen.shared.conf.ConfigService;
import fr.nations.amen.shared.conf.ConfigServiceAsync;
import fr.nations.amen.shared.conf.ConfigSite;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.souvenirs.SouvenirsService;
import fr.nations.amen.shared.souvenirs.SouvenirsServiceAsync;

public class WelcomeViewImpl extends Composite implements WelcomeView {

	public enum MODE {
		PRODUCTION, TEST
	}

	public MODE mode = MODE.TEST;

	SouvenirsServiceAsync souvenirService = GWT.create(SouvenirsService.class);
	
	LouangesServiceAsync louangeService = GWT.create(LouangesService.class);

	ConfigServiceAsync cfService = GWT.create(ConfigService.class);

	private WelcomePresentation welcomePresentation;

	HTML visiteurLabel;

	private TabLayoutPanel tabPanel= new TabLayoutPanel(0, Unit.PX);;

	private TabLayoutPanel newsTabVideo;

	private HTML htmlPresentation = new HTML("bonjour");

	private FlowPanel  logoPresentation;

	private HTML imgPresHtml;

	private Timer tnews;

	private TabLayoutPanel newsTabChant;

	private TabLayoutPanel newsTabText;

	private VerticalPanel vTabPanel;
	
//	HTML goFacebook = new HTML();

	public WelcomeViewImpl() {

		VerticalPanel vPanelNorth = new VerticalPanel();

		final VerticalPanel vPanel = new VerticalPanel();

		AppMenuBar menu = new AppMenuBar();
		menu.setWidth("1010px");
		vPanelNorth.add(menu);

		logoPresentation = new FlowPanel();
		imgPresHtml = new HTML("bonjour");
		logoPresentation.add(imgPresHtml);
		
		vTabPanel = new VerticalPanel();
		
		
		//TODO : add share facebook
		String description = "Assurer l\u0027'exercice du culte evangelique, " +
				"enseigner le message de l\u0027Evangile de Jesus-Christ";
		
		String link = "http://www.mc-amen.net/";
		String name = "Mission Chretienne Amen";
		
		String srcImg = "http://www.mc-amen.net/images/sigleAmen.png";

		/*goFacebook.setHTML("<center><a href=\"https://www.facebook.com/dialog/feed?" +
				"app_id=401100423399950" +
				"&display=page" +
				"&description="  + description +
				"&link=" + link +
				"&redirect_uri=https://apps.facebook.com/mission-amen" +
				"&name="+name +
				//"&picture=http://www.mc-amen.net/images/20130707_114518r2.jpg\">Partager</a>");
				"&picture="+srcImg+"\"><img src='/images/shareFacebook.jpg' alt='Share on Facebook' height='40' width='200'></a></center><br>");

		vTabPanel.add(goFacebook);*/
		
		newsTabVideo = new TabLayoutPanel(0, Unit.PX);
		newsTabVideo.setAnimationVertical(false);
		newsTabVideo.setAnimationDuration(1000);
		newsTabVideo.setWidth("350px");
		newsTabVideo.setHeight("200px");
		
		vTabPanel.add(newsTabVideo);

		newsTabChant = new TabLayoutPanel(0, Unit.PX);
		newsTabChant.setAnimationVertical(false);
		newsTabChant.setAnimationDuration(1000);
		newsTabChant.setWidth("350px");
		newsTabChant.setHeight("200px");
		
		vTabPanel.add(newsTabChant);

		newsTabText = new TabLayoutPanel(0, Unit.PX);
		newsTabText.setAnimationVertical(false);
		newsTabText.setAnimationDuration(1000);
		newsTabText.setWidth("350px");
		newsTabText.setHeight("200px");

		vPanel.add(logoPresentation);
		vPanel.add(new HTML("<br/>"));

		// newsTabPanel.setStyleName("ctr");
		// vPanel.add(newsTabPanel);

		cfService.getConfigSite(new AsyncCallback<ConfigSite>() {

			public void onSuccess(ConfigSite result) {

				List<String> prefsVideo = result.getPrefsVideo();
				
				String srcImg = "/amen/getSouveniruploadImg?blob-key=";
				
				int counterVideo = makeEncartVideo(prefsVideo, newsTabVideo, souvenirService, 
						srcImg);
				
				

				List<String> prefsChant = result.getPrefsChanson();
				
				 srcImg = "/amen/getlouangeuploadImg?blob-key=";
				int counterChant = makeEncart(prefsChant, newsTabChant, louangeService, srcImg);



			//	List<String> prefsText = result.getPrefsTexte();

				

				final int counterMaxNews = counterVideo;
				final int counterMaxChant = counterChant;

				tnews = new Timer() {

					int count = 1;

					@Override
					public void run() {

						newsTabVideo.selectTab(count % counterMaxNews);
						newsTabChant.selectTab(count % counterMaxChant);
						count++;
					}
				};
				// Schedule the timer to run once in 3 seconds.
				tnews.scheduleRepeating(3000);

				History.addValueChangeHandler(new ValueChangeHandler<String>() {
					@Override
					public void onValueChange(ValueChangeEvent<String> event) {
						if (event.getValue().isEmpty()) {

							// Window.alert(event.getValue());
							tnews.scheduleRepeating(3000);
							tnews.run();
						} else {
							// selectTab(event.getValue());
							tnews.cancel();
						}
					}

				});

				htmlPresentation
						.setHTML("<div id='presenetationBlue' align='center' style='padding: 20px;'>"
								+ result.getDescription() + "</div>");

				//srcImg = "";

				if (result.getImgPresentationUrl() == null
						|| result.getImgPresentationUrl().equals("")) {

				} else {
					srcImg = "/amen/getSouveniruploadImg?blob-key="
							+ result.getImgPresentationUrl();
				}

				imgPresHtml
						.setHTML("<div align='center' style='width:"+result.getImgPresentationWidth()+
								"px;border-color:black;border=10;'><img src='"
								+ srcImg
								+ "' style='width:"+result.getImgPresentationWidth()+
								"px;border-color:black;border=10;' />" +
										"</div>");
				//imgPresHtml.setWidth("970px");//TODO: remplacer width par result.getImgPresHtmlWidth()
				imgPresHtml.setWidth(result.getImgPresentationWidth()+"px");
				imgPresHtml.setStyleName("bandeau");
				//imgPresHtml.setStyleName("ctr");
				
				//tabPanel = new TabLayoutPanel(0, Unit.PX);
				tabPanel.setAnimationVertical(false);
				tabPanel.setAnimationDuration(1000);

				
				
				ServiceAmenPicasa.getPhotos(
						result.getDirPhotoPicasa()//"5981049624052679185"
						, tabPanel);

				tabPanel.setWidth("400px");
				tabPanel.setHeight("455px");
				
				int countMax;
				
				if (result.getDirPhotoPicasaNb()==null){
					countMax = 1;
				} else {
				countMax = result.getDirPhotoPicasaNb();
				}
				boucleTemporelle(countMax);
				

			}// fin onSuccess(ConfigSite result)

			public void onFailure(Throwable caught) {
				Window.alert("error load image");
			}
		});

		// drawPlusOne(vPanel);
//		tabPanel = new TabLayoutPanel(0, Unit.PX);
//		tabPanel.setAnimationVertical(false);
//		tabPanel.setAnimationDuration(1000);
//
//		ServiceAmenPicasa.getPhotos("5981049624052679185", tabPanel);
//
//		tabPanel.setWidth("400px");
//		tabPanel.setHeight("455px");


		visiteurLabel = new HTML();

		ListeVideospVues lVideos = new ListeVideospVues();
		lVideos.setWidth("400px");

		ListeArticlespLus lArticles = new ListeArticlespLus();
		lArticles.setWidth("400px");

		// ListeAudiopEcoutes lAudio = new ListeAudiopEcoutes();
		// lAudio.setWidth("400px");

		ListeLouangespEcoutes llouanges = new ListeLouangespEcoutes();
		llouanges.setWidth("400px");

		VerticalPanel louangesPanel = new VerticalPanel();
		// louangesPanel.add(new
		// HTML("<div class=\"addthis_sharing_toolbox\"></div>"));
		louangesPanel.add(llouanges);

		VerticalPanel sPanel = new VerticalPanel();
		// sPanel.add(visiteurLabel);
		// sPanel.add(lVideos);

/*		ButtonFactory.getInstance().drawPlusOne(sPanel);
		ButtonFactory.getInstance().drawfblike(sPanel);
		ButtonFactory.getInstance().drawTwitter(sPanel);*/
		sPanel.add(lArticles);

		// vPanel.add(new
		// HTML("<div class=\"addthis_sharing_toolbox\"></div>"));

		FlexTable layout = new FlexTable();
		layout.setCellSpacing(20);
		layout.setWidget(0, 1, htmlPresentation);
		layout.setWidget(0, 2, vTabPanel);
		layout.setWidget(1, 1, tabPanel);

		layout.setWidget(1, 2, louangesPanel);
		layout.setWidget(2, 1, sPanel);

		layout.setWidget(2, 2, lVideos);

		layout.setStyleName("flex");

		vPanel.add(new HTML("<br/>"));

		// vPanel.add(htmlPresentation);

		vPanel.add(layout);

		// HTML html = new HTML(
		// "<div id='tweet-detail'><a href=\"http://twitter.com/share\" class=\"twitter-share-button\" data-text=\"AMEN\" data-url=\"[your url]\" data-counturl=\"http://stmichelamen.appspot.com\" data-count=\"horizontal\">Tweet</a></div>");
		// vPanel.add(html);

		vPanel.add(new HTML("<br/>"));
		vPanel.add(new HTML("<br/>"));
		vPanel.add(new HTML("<br/>"));
		vPanel.add(new HTML("<br/>"));

		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);

		ScrollPanel sp = new ScrollPanel(vPanel);
		sp.setAlwaysShowScrollBars(false);

		int hScroll = Amen.heightWindow;

		sp.setSize("1000px", hScroll + "px");

		// VerticalPanel vPanelGlobal = new VerticalPanel();
		// vPanelGlobal.add(sp);
		int hNorth = UniformDim.hauteurMenu;
		view.addNorth(vPanelNorth, hNorth);
		view.add(sp);

		initWidget(view);
		view.setSize("100%", "100%");
		view.forceLayout();
		Window.enableScrolling(false);

		// souvenirService.getCountVisitor(new AsyncCallback<Integer>() {
		//
		// public void onSuccess(Integer result) {
		// visiteurLabel
		// .setHTML("<p>*****Bienvenue heureux internaute, tu es le  "
		// + result.toString() + "ieme visiteur*****</p>");
		// }
		//
		// public void onFailure(Throwable caught) {
		// Window.alert("error getCountVisitor");
		// }
		// });
		// cfService.getConfigSite(
		// new AsyncCallback<ConfigSite>() {
		// public void onSuccess(ConfigSite result) {
		//
		// htmlPresentation.setHTML(
		// "<div id='presenetationBlue' align='center'>"
		// +result.getDescription()+"</div>");
		// //htmlPresentation.setStyleName("ctr");
		//
		// }
		//
		// public void onFailure(Throwable caught) {
		// // TODO: Show an error message.
		// }
		// });
		// TODO
		// final int counterMax = tabPanel.getWidgetCount();
		
//		int countMax = 11;
//		boucleTemporelle(countMax);

	}
	
	
	private void boucleTemporelle(int countMax) {
		final int counterMax =countMax;

		final Timer t = new Timer() {

			int count = 1;

			@Override
			public void run() {

				tabPanel.selectTab(count % counterMax);

				count++;
			}
		};

		// Schedule the timer to run once in 3 seconds.
		t.scheduleRepeating(3000);
		// t.cancel(); quand changement de Place

		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (event.getValue().isEmpty()) {

					// enregistrement du numéro du visiteur

					// Window.alert(event.getValue());
					t.scheduleRepeating(2000);
					t.run();

				} else {
					// selectTab(event.getValue());
					t.cancel();

				}
			}

		});
		
	}


	static class Encart{
		
		private static String getIdFromUrl(String urlVideo) {

			// http://127.0.0.1:8888/#Souvenir:20&0&cat0

			String strUrlParams = urlVideo.substring(urlVideo.lastIndexOf(":"));

			return strUrlParams.substring(1, strUrlParams.indexOf("&"));

		}
		
		private static <T> int makeEncart(List<String> prefsChant,
				final TabLayoutPanel newsTab, LouangesServiceAsync louangeService,
				final String srcImg2) {
			
			int counterVideo = 0;

			for (final String urlVideo : prefsChant) {

				++counterVideo;

				String id = getIdFromUrl(urlVideo);

				louangeService.getArticleObject(id,
						new AsyncCallback<Louange>() {

							public void onFailure(Throwable caught) {
								Window.alert("Error" + caught.getMessage());

							}

							public void onSuccess(Louange result2) {

								VerticalPanel v = new VerticalPanel();
								HTML imgHtml2 = new HTML("bonjour");
								String url = result2.getThubnailUrl();

								String srcImg = "";

								if (url == null || url.equals("")) {

								} else {
									srcImg = srcImg2
											+ url;
								}

								imgHtml2.setHTML("<a href='"
										+ urlVideo
										+ "'><img src='"
										+ srcImg
										+ "' style='width: 300px;border-color:black;border=10;align='center';' /></a>");

								v.add(new HTML("<H2>News: "
										+ result2.getSujet()
										+ "</H2>"));
								HorizontalPanel h2 = new HorizontalPanel();
								h2.add(imgHtml2);
								v.add(h2);
								newsTab.add(v);

							}// fi onSuccess

						});

			}// fi for
			return counterVideo;
		}
		
	}
	
	private int makeEncartVideo(List<String> prefsVideo,
			final TabLayoutPanel newsTab,
			SouvenirsServiceAsync service, final String srcImg2) {
		
		int counterVideo = 0;

		for (final String urlVideo : prefsVideo) {

			++counterVideo;

			String id = getIdFromUrl(urlVideo);

			service.getArticleObject(id,
					new AsyncCallback<Souvenir>() {

						public void onFailure(Throwable caught) {
							Window.alert("Error" + caught.getMessage());

						}

						public void onSuccess(Souvenir result2) {

							VerticalPanel v = new VerticalPanel();
							HTML imgHtml2 = new HTML("bonjour");
							String url = result2.getThubnailUrl();

							String srcImg = "";

							if (url == null || url.equals("")) {

							} else {
								srcImg = srcImg2 + url;
							}

							imgHtml2.setHTML("<a href='"
									+ urlVideo
									+ "'><img src='"
									+ srcImg
									+ "' style='width: 300px;border-color:black;border=10;align='center';' /></a>");

							v.add(new HTML("<H2>News: "
									+ result2.getAppellation()
									+ "</H2>"));
							HorizontalPanel h2 = new HorizontalPanel();
							h2.add(imgHtml2);
							v.add(h2);
							newsTab.add(v);

						}// fi onSuccess

					});

		}// fi for
		return counterVideo;
	}

	private int makeEncart(List<String> prefsChant,
			final TabLayoutPanel newsTab, LouangesServiceAsync louangeService,
			final String srcImg2) {
		
		int counterVideo = 0;

		for (final String urlVideo : prefsChant) {

			++counterVideo;

			String id = getIdFromUrl(urlVideo);

			louangeService.getArticleObject(id,
					new AsyncCallback<Louange>() {

						public void onFailure(Throwable caught) {
							Window.alert("Error" + caught.getMessage());

						}

						public void onSuccess(Louange result2) {

							VerticalPanel v = new VerticalPanel();
							HTML imgHtml2 = new HTML("bonjour");
							String url = result2.getThubnailUrl();

							String srcImg = "";

							if (url == null || url.equals("")) {

							} else {
								srcImg = srcImg2
										+ url;
							}

							imgHtml2.setHTML("<a href='"
									+ urlVideo
									+ "'><img src='"
									+ srcImg
									+ "' style='width: 300px;border-color:black;border=10;align='center';' /></a>");

							v.add(new HTML("<H2>News: "
									+ result2.getSujet()
									+ "</H2>"));
							HorizontalPanel h2 = new HorizontalPanel();
							h2.add(imgHtml2);
							v.add(h2);
							newsTab.add(v);

						}// fi onSuccess

					});

		}// fi for
		return counterVideo;
	}

	private WelcomePresenter presenter;

	public WelcomePresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(WelcomePresenter presenter) {
		this.presenter = presenter;

	}

	// private void drawPlusOne(VerticalPanel vPanel) {
	// //<!-- Place this tag in your head or just before your close body tag -->
	// //<script type="text/javascript"
	// src="https://apis.google.com/js/plusone.js"></script>
	// //<!-- Place this tag where you want the +1 button to render -->
	// //<g:plusone></g:plusone>
	//
	// // String s =
	// "<g:plusone href=\"http://stmichelamen.appspot.com\"></g:plusone>";
	// String s = "<g:plus action=\"share\"></g:plus>";
	// HTML h = new HTML(s);
	// // h.setStyleName("ctr");
	//
	// vPanel.add(h);
	//
	// Document doc = Document.get();
	// ScriptElement script = doc.createScriptElement();
	// script.setSrc("https://apis.google.com/js/plusone.js");
	// script.setType("text/javascript");
	// script.setLang("javascript");
	// doc.getBody().appendChild(script);
	// // return h;
	// }

	// private void drawfblike(VerticalPanel vPanel){
	//
	// String s =
	// "<div class='fb-like' data-share='true' data-width='450' data-show-faces='true'>"
	// +
	// "</div>";
	// HTML h = new HTML(s);
	// //h.setStyleName("ctr");
	// vPanel.add(h);
	//
	// }

	private void drawfb(VerticalPanel vPanel) {
		String s = "<div class=\"social\"><a class=\"twitter-share-button\" data-url=\"http://www.google.com/\" href=\"https://twitter.com/share\" data-lang=\"en\"></a>"
				+ "<div class=\"fb-like\" data-href=\"http://www.google.com/\" data-send=\"false\" data-width=\"90\" data-show-faces=\"false\" data-layout=\"button_count\"></div>"
				+ "</div>";

		HTML h = new HTML(s);
		vPanel.add(h);

		Document doc = Document.get();
		ScriptElement script = doc.createScriptElement();
		// script.setSrc("http://stmichelamen.appspot.com/js/fb.js");
		script.setSrc("http://127.0.0.1:8888/js/fb.js");
		script.setType("text/javascript");
		script.setLang("javascript");
		doc.getBody().appendChild(script);

	}

	private String getIdFromUrl(String urlVideo) {

		// http://127.0.0.1:8888/#Souvenir:20&0&cat0

		String strUrlParams = urlVideo.substring(urlVideo.lastIndexOf(":"));

		return strUrlParams.substring(1, strUrlParams.indexOf("&"));

	}

	private String getentete(String description) {
		if (description.length() >= 100)

			return description.substring(0, 100);
		else
			return description;
	}
}
