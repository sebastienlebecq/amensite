package fr.nations.amen.client;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class BanniereView extends Composite {

	private HorizontalPanel hPanel = new HorizontalPanel();
	private Image logoImg;
	private int heightLogoImg;


	public BanniereView() {


		Window.addResizeHandler(resizeHandler);

		logoImg = new Image("/images/banniere_amen.jpg");

		logoImg.setWidth("99%");
		
		this.setHeightLogoImg(logoImg.getHeight());
		
		logoImg.setStyleName("bandeau");
		this.hPanel.add(logoImg);
		//Window.addResizeHandler(resizeHandler);
		initWidget(hPanel);
	}

	private void setHeightLogoImg(int height) {
		this.heightLogoImg = height;
		
	}

	public int getHeightLogoImg() {
		return heightLogoImg;
	}

	/* ************* WIDGET CENTERING CODE *************** */
	private ResizeHandler resizeHandler = new ResizeHandler() {
		public void onResize(ResizeEvent event) {
			setWidgetToMaxWidthAndHeight();
		}
	};

	private void setWidgetToMaxWidthAndHeight() {

		//Window.alert("evt produit dans logosView");
		//hPanel.setWidth("100%");
		//logoImg.setWidth("99%");
		//this.setHeightLogoImg(logoImg.getHeight());

	}
}
