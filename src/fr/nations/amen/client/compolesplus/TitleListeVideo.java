package fr.nations.amen.client.compolesplus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class TitleListeVideo extends Composite{
	
	private static TitleListeVideoUiBinder uiBinder = GWT
			.create(TitleListeVideoUiBinder.class);
	
	
	@UiField HTML visiteurLabel;
	
	interface TitleListeVideoUiBinder extends
	UiBinder<Widget, TitleListeVideo> {
}
	public TitleListeVideo() {
		//visiteurLabel.setText("Vous êtes le visiteur de cette page N°");
		initWidget(uiBinder.createAndBindUi(this));
	}
}
