package fr.nations.amen.client.actupublic.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import fr.nations.amen.shared.actus.Actu;

public class ActuForm extends Composite
// implements HasText
{
	

	private static ActuFormUiBinder uiBinder = GWT
			.create(ActuFormUiBinder.class);

	interface ActuFormUiBinder extends UiBinder<Widget, ActuForm> {
	}

	private DateTimeFormat dateTimeFormat;

	public ActuForm() {
		initWidget(uiBinder.createAndBindUi(this));
		dateTimeFormat = com.google.gwt.i18n.client.DateTimeFormat.getFormat("dd/mm/yy");
//		DateTimeFormat dateFormat = DateTimeFormat
//				.getFormat(PredefinedFormat.DATE_LONG);
//		dateActuBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setActu(null);
	}

	@UiField
	HTML idNumeroBox;

	@UiField
	HTML appellationBox;

	@UiField
	HTML descriptionBox;
	
	@UiField
	HTML dateActuBox;

	// @UiField
	// TextBox dureeBox;

	// @UiField
	// Image imgBox;

	// @UiField
	// Button button;

	private Actu actuInfo;

	public ActuForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		// button.setText(firstName);
	}

	// @UiHandler("button")
	// void onClick(ClickEvent e) {
	// Window.alert("Hello!");
	// }

	// public void setText(String text) {
	// button.setText(text);
	// }
	//
	// public String getText() {
	// return button.getText();
	// }
	
	
	
	public void setActu(Actu actu) {
		
		
		this.actuInfo = actu;
		// updateButton.setEnabled(contact != null);
		if (actu != null) {
			idNumeroBox.setHTML(actu.getId());
			descriptionBox.setHTML(actu.getDescription());
			dateActuBox.setHTML(dateTimeFormat.format(actu.getDate()));
			appellationBox.setHTML(actu.getAppellation());
			// Category category = actu.getCategory();
			// Category[] categories = ContactDatabase.get().queryCategories();
			// for (int i = 0; i < categories.length; i++) {
			// if (category == categories[i]) {
			// categoryBox.setSelectedIndex(i);
			// break;
			// }
			// }
		}
	}
}
