package fr.nations.amen.client.compolesplus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class TitleListeAudi extends Composite {

	private static TitleListeAudiUiBinder uiBinder = GWT
			.create(TitleListeAudiUiBinder.class);
	
	
	@UiField HTML visiteurLabel;
	
	interface TitleListeAudiUiBinder extends
	UiBinder<Widget, TitleListeAudi> {
}
	public TitleListeAudi() {
		//visiteurLabel.setText("Vous êtes le visiteur de cette page N°");
		initWidget(uiBinder.createAndBindUi(this));
	}
}
