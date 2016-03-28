package fr.nations.amen.client.download;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.shared.conf.ConfigService;
import fr.nations.amen.shared.conf.ConfigServiceAsync;
import fr.nations.amen.shared.conf.ConfigSite;

public class DownloadWidget extends Composite {
	
	private VerticalPanel pWidget;
	
	private HTML htmlPresentation;

	ConfigServiceAsync cfService = GWT.create(ConfigService.class);
	
	private HorizontalPanel imgPresentation;
	
	//private HTML imgPresHtml;

	private TextBox userid;

	private PasswordTextBox passwd;

	public DownloadWidget() {
		pWidget = new VerticalPanel();
		initWidget(pWidget);

		final FormPanel form = new FormPanel();
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new HTML("<br>Veuillez au pr&eacute;alable vous authentifier " +
				"pour pouvoir ensuite t&eacute;l&eacute;charger les documents mis &agrave; votre disposition:<br><br><br>"));

		holder.add(new Label("Login(email):"));
		userid = new TextBox();
		userid.setName("id");
		holder.add(userid);

		holder.add(new Label("Cle:"));
		passwd = new PasswordTextBox();
		passwd.setName("passwd");
		holder.add(passwd);
		
		SubmitButton btnSubmit3 = new SubmitButton("soumission");
		holder.add(btnSubmit3);
		
		btnSubmit3.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				 
				 cfService
					.getBlobStoreUploadDocUrl(userid.getText(),passwd.getText(),
							new AsyncCallback<String>() {

						@Override
						public void onSuccess(String result) {

							form.setAction(result.toString());

							if (!result.equals("")){

								draw();

							}
							
							else {
								
								Window.alert("Vous n avez pas les droits ; veuillez vous rapprocher de l" +
										"equipe pastorale dont les coordonnees se trouvent sous le menu 'Nous contacter'");
							
								form.reset();
							}
							
						}

						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
						}
					});
				 
				 
				 
				
			}
			});
		

		form.add(holder);

		this.pWidget.add(form);

	}
	
	
	private void draw() {


		final FlexTable layout = new FlexTable();
		layout.setCellSpacing(10);

		pWidget.clear();
		
		
		cfService.getConfigSite(new AsyncCallback<ConfigSite>() {


			public void onSuccess(ConfigSite result) {
				

				List<String> blobDocs = result.getDownloadBlobKeyDocs();
				
				int i =0;
				
				for (String bk : blobDocs){
					
					//HTML imgPresHtml = new HTML("");

					String srcImg = "/amen/getDownloaduploadDoc?blob-key="
					+ result.getDownloadBlobKeyDocs().get(i);//ok
					
//					imgPresHtml
//					.setHTML("<a href='"
//							+ srcImg
//							+ "'>"+bk+"</a>");
					
					layout.setWidget(i, 0, new HTML("Document numero "+i+":"));
					layout.setWidget(i, 1, new HTML("<a href='"
							+ srcImg + "'>"+bk+"</a>"));
					
					i++;
					
				}
				

		}// fin onSuccess(ConfigSite result)


		public void onFailure(Throwable caught) {
			Window.alert("error load image");
		}
	});
				
				
				
		
		
		
		
		
		

		layout.setStyleName("flex");

		pWidget.add(new HTML("<br/>"));
		pWidget.add(new HTML("Documents &agrave; t&eacute;l&eacute;charger :"));
		pWidget.add(new HTML("<br/><br/>"));
		imgPresentation = new HorizontalPanel();
		imgPresentation.add(layout);
		pWidget.add(imgPresentation);
	}
}
