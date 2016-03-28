package fr.nations.amen.client.mvp.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

import fr.nations.amen.client.UniformDim;

public class BusyIndicator //extends Composite 
{
	private static MyPopup 	popup = new MyPopup();
    HTML html = new HTML("<img src='/images/CRU_wait-animation.gif' />");
	
	
	
	static public void busy(){
		//Window.alert("busy");//ok donc affichage
		popup.center();
		
	}
	
	static public void free(){
		popup.hide();
	}

	
	public BusyIndicator(){
	}
	
	 private static class MyPopup extends PopupPanel {

	      public MyPopup() {
	         // PopupPanel's constructor takes 'auto-hide' as its boolean 
	         // parameter. If this is set, the panel closes itself 
	         // automatically when the user clicks outside of it.         
	         super(true);
	        // setGlassEnabled(true);
	         //center();
	         // PopupPanel is a SimplePanel, so you have to set it's widget 
	         // property to whatever you want its contents to be.
	         //setWidget(new Label(UniformDim.labelWait));
	         setWidget(new HTML("<img src='/images/CRU_wait-animation.gif' />"));
	         
	      }
	   }
}
