package fr.nations.amen.client.welcome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WelcomePresentation extends Composite implements HasText {

	private static WelcomePresentationUiBinder uiBinder = GWT
			.create(WelcomePresentationUiBinder.class);
	
	
	@UiField HTML visiteurLabel;
	
	  interface Resources extends ClientBundle {
	        @Source("ColombgReflected.png") public ImageResource logoColomb();
	  }

	interface WelcomePresentationUiBinder extends
			UiBinder<Widget, WelcomePresentation> {
	}

	public WelcomePresentation() {
		//visiteurLabel.setText("Vous êtes le visiteur de cette page N°");
		initWidget(uiBinder.createAndBindUi(this));
		
		
//		 History.addValueChangeHandler(new ValueChangeHandler<String>() {
//	         @Override
//	         public void onValueChange(ValueChangeEvent<String> event) {
//	        	 if (event.getValue().isEmpty()){
//	        		 
//	        		 //enregistrement du numéro du visiteur
//	        		 
//	        		 
//	        		// Window.alert(event.getValue());
//
//	        	 } 
//	         }
//
//	      });
	}


	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		visiteurLabel.setText(text);
		
	}

//	public void setText(String text) {
//		button.setText(text);
//	}
//
//	public String getText() {
//		return button.getText();
//	}

}
