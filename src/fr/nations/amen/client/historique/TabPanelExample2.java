package fr.nations.amen.client.historique;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabPanelExample2 extends Composite 
{

	private static TabPanelExample2UiBinder uiBinder = GWT
			.create(TabPanelExample2UiBinder.class);

    interface Resources extends ClientBundle {
        @Source("logo2009.png") public ImageResource logo2009();
        @Source("logo2010.png") public ImageResource logo2010();
        @Source("logo2011.png") public ImageResource logo2011();
        @Source("title_histoire.png") public ImageResource imgTitle();
        
        @Source("titleapropos.png") public ImageResource imgAproposTitle();
        @Source("organisation.png") public ImageResource imgOrganisation();
        @Source("confessionDeFoi.png") public ImageResource imgConfessionDeFoi();
        @Source("visiontitle.png") public ImageResource imgvisiontitle();
    }
	
	interface TabPanelExample2UiBinder extends
			UiBinder<Widget, TabPanelExample2> {
	}

	public TabPanelExample2() {
		initWidget(uiBinder.createAndBindUi(this));
		tabs.selectTab(0);
		tabs.setAnimationEnabled(true);
		
	}


	 @UiField TabPanel tabs;


//	@UiHandler("button")
//	void onClick(ClickEvent e) {
//		Window.alert("Hello!");
//	}
//
//	public void setText(String text) {
//		button.setText(text);
//	}
//
//	public String getText() {
//		return button.getText();
//	}

}
