package fr.nations.amen.client.forumpasteur.manager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class ForumPasteurManagerGui extends Composite implements HasText {

	private static ForumPasteurManagerGuiUiBinder uiBinder = GWT
			.create(ForumPasteurManagerGuiUiBinder.class);

	interface ForumPasteurManagerGuiUiBinder extends
			UiBinder<Widget, ForumPasteurManagerGui> {
	}

	public ForumPasteurManagerGui() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	public ForumPasteurManagerGui(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

}
