package fr.nations.amen.client.ounoustrouver;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.shared.conf.ConfigService;
import fr.nations.amen.shared.conf.ConfigServiceAsync;
import fr.nations.amen.shared.conf.ConfigSite;

/**
 * Demo showcase for the direction service, using TRANSIT mode.
 */
public class TransitDirectionsServiceMapWidget extends Composite {

	private VerticalPanel pWidget;

	ConfigServiceAsync cfService = GWT.create(ConfigService.class);

	private HorizontalPanel imgPresentation;

	private HTML imgPresHtml;

	private HTML pwidgetEmailHtml = new HTML();

	private HTML pwidgetAdresse = new HTML();

	private HorizontalPanel pWidgetPicasa;

	private HTML pwidgetCarte;

	public TransitDirectionsServiceMapWidget() {
		pWidget = new VerticalPanel();
		initWidget(pWidget);

		draw();
	}

	private void draw() {

		pWidget.clear();

		imgPresentation = new HorizontalPanel();
		imgPresHtml = new HTML("bonjour");
		imgPresentation.add(imgPresHtml);
		pWidget.add(imgPresentation);

		pWidget.add(new HTML("<br/>"));

		pwidgetCarte = new HTML("bonjour");
		pWidget.add(pwidgetCarte);
		
		/********** OK **************/
		// String idAlbum = "5939163535262315793";
		// ServiceAmenPicasa.getPhotos(idAlbum , pWidgetPicasa);
		/********************************/

		pWidget.add(new HTML("<br/>"));

		pWidget.add(pwidgetEmailHtml);

		pWidget.add(new HTML("<br/>"));

		pWidget.add(pwidgetAdresse);

		pWidgetPicasa = new HorizontalPanel();
		pWidgetPicasa.setStyleName("ctr");

		pWidget.add(pWidgetPicasa);

		cfService.getConfigSite(new AsyncCallback<ConfigSite>() {
			public void onSuccess(ConfigSite result) {
				

				pwidgetCarte
				.setHTML("<center>"+result.getUrlIntegration()+"</center>");
				pwidgetCarte.setStyleName("ctr");

				String refPicasa = result.getPhotoBatiment();
				// ok: String refPicasa = "5939163535262315793";
				ServiceAmenPicasa.getPhotos(refPicasa, pWidgetPicasa);

				String srcImg = "";

				if (result.getImgPresentNsTrouverUrl() == null
						|| result.getImgPresentNsTrouverUrl().equals("")) {

				} else {
					srcImg = "/amen/getSouveniruploadImg?blob-key="
							+ result.getImgPresentNsTrouverUrl();
				}
				imgPresHtml
						.setHTML("<img src='"
								+ srcImg
								+ "' style='width: 970px;border-color:black;border=10;' />");
				imgPresHtml.setWidth("970px");
				imgPresHtml.setStyleName("bandeau");

				pwidgetEmailHtml
						.setHTML("<div id='presenetationBlue' align='center'  style='width:400px; margin: auto;'>"
								+ result.getEmailHtml() + "</div>");

				pwidgetAdresse
						.setHTML("<div id='presenetationBlue' align='center' style='width:400px;margin: auto;'>"
								+ result.getAdresseHtml() + "</div>");

			}

			public void onFailure(Throwable caught) {
				Window.alert("error load image");
			}
		});

	}

}
