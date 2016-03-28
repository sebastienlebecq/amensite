package fr.nations.amen.client.compolesplus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class TitleListeArticle extends Composite{
	
	private static TitleListeArticleUiBinder uiBinder = GWT
			.create(TitleListeArticleUiBinder.class);
	
	
	@UiField HTML visiteurLabel;
	
	interface TitleListeArticleUiBinder extends
	UiBinder<Widget, TitleListeArticle> {
}
	public TitleListeArticle() {
		//visiteurLabel.setText("Vous êtes le visiteur de cette page N°");
		initWidget(uiBinder.createAndBindUi(this));
	}
}
