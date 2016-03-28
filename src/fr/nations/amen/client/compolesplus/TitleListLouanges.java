package fr.nations.amen.client.compolesplus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;


public class TitleListLouanges extends Composite {
	private static TitleListLouangesUiBinder uiBinder = GWT
			.create(TitleListLouangesUiBinder.class);
	
	
	@UiField HTML visiteurLabel;
	
	interface TitleListLouangesUiBinder extends
	UiBinder<Widget, TitleListLouanges> {
}
	public TitleListLouanges() {
		//visiteurLabel.setText("Vous êtes le visiteur de cette page N°");
		initWidget(uiBinder.createAndBindUi(this));
	}
}
