package fr.nations.amen.client.rezosocio;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ButtonFactory {
	
	private static ButtonFactory instance;

	public void drawTwitter(VerticalPanel vPanel){
		String s = "<a href='https://twitter.com/share' class='twitter-share-button' " +
				"data-via='webmasterAmen' data-size='large' data-dnt='true'>Tweet</a>" +
		"<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0]," +
		"p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id))" +
		"{js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';" +
		"fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>";
		
		HTML h = new HTML(s);
		vPanel.add(h);
	}

	public void drawPlusOne(VerticalPanel vPanel) {
	    //<!-- Place this tag in your head or just before your close body tag -->
	    //<script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>
	    //<!-- Place this tag where you want the +1 button to render -->
	    //<g:plusone></g:plusone>

		String s = "<g:plus action=\"share\"></g:plus>";
	    HTML h = new HTML(s);
	   // h.setStyleName("ctr");
	    
	    vPanel.add(h);
	    
	    Document doc = Document.get();
	    ScriptElement script = doc.createScriptElement();
	    script.setSrc("https://apis.google.com/js/plusone.js");
	    script.setType("text/javascript");
	    script.setLang("javascript");
	    doc.getBody().appendChild(script);
	    //return h;
	  }
	
public void drawfblike(VerticalPanel vPanel){
		
		String s = "<div class='fb-like' data-share='true' data-width='450' data-show-faces='true'>" +
				"</div>";
		 HTML h = new HTML(s);
		 //h.setStyleName("ctr");
		 vPanel.add(h);
		
	}



public static ButtonFactory getInstance() {

if (instance== null) {
	
	instance = new ButtonFactory();
}
	return instance;
}

}
