package fr.nations.amen.client.youtube;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ObjectElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class YoutubePanel extends Composite implements HasText {

	private static YoutubePanelUiBinder uiBinder = GWT
			.create(YoutubePanelUiBinder.class);

	interface YoutubePanelUiBinder extends UiBinder<Widget, YoutubePanel> {
	}

	public YoutubePanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	//@UiField
	//Button button;
	
	@UiField
	ObjectElement videoElement;
	
	public void displayVideo(String videoId) {
	       String videoUrl = "http://www.youtube.com/v/".concat(videoId);
	       videoElement.setData(videoUrl);  //change data attribute of object element
	       String innerHtml = "<param name=\"movie\" value=\""+ videoUrl +"\" />";
	       //add param element, of course yo can add as many param elements as needed to customize
	       videoElement.setInnerHTML(innerHtml);
	}

	public YoutubePanel(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		//button.setText(firstName);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

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
