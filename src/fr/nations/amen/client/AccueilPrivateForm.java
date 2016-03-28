package fr.nations.amen.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;

import fr.nations.amen.shared.conf.ConfigService;
import fr.nations.amen.shared.conf.ConfigServiceAsync;
import fr.nations.amen.shared.conf.ConfigSite;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.souvenirs.SouvenirsService;
import fr.nations.amen.shared.souvenirs.SouvenirsServiceAsync;

public class AccueilPrivateForm extends Composite {

	ConfigServiceAsync configService = GWT.create(ConfigService.class);

	SouvenirsServiceAsync souvenirService = GWT.create(SouvenirsService.class);

	LouangesServiceAsync louangeService = GWT.create(LouangesService.class);

	@UiField
	TextBox idNumberBox;
	
	@UiField
	TextBox dirphotoNbBox;
	
	@UiField
	TextBox photoDefilantBox;
	
	@UiField
	TextBox imgPresWidthBox;
	
	@UiField
	TextBox idCleBox;

	@UiField
	RichTextArea descriptionBox;

	@UiField
	TextBox photoBatimentBox;

	@UiField
	RichTextArea emailBox;

	@UiField
	RichTextArea adresseBox;

	@UiField
	TextArea integrationBox;

	@UiField
	HTML appellationVideoHtml;

	@UiField
	ListBox preferenceVideoBox;

	@UiField
	ListBox prefDownloadBox;

	@UiField
	ListBox prefEmailBox;

	@UiField
	HTML appellationChansonHtml;
	
	@UiField
	HTML appellationEmailHtml;

	@UiField
	ListBox preferenceChansonBox;

//	@UiField
//	HTML appellationTexteHtml;
//
//	@UiField
//	ListBox preferenceTexteBox;

	@UiField
	Button btnAddPreferenceVideo;

	@UiField
	Button btnRemovePreferenceVideo;

	@UiField
	Button btnAddPreferenceChanson;

	@UiField
	Button btnAddDownloadDoc;

	@UiField
	Button btnAddDownloadEmail;

	@UiField
	Button btnRemovePreferenceChanson;

	@UiField
	Button btnRemoveDownloadDoc;

	@UiField
	Button btnRemoveDownloadEmail;

//	@UiField
//	Button btnAddPreferenceTexte;
//
//	@UiField
//	Button btnRemovePreferenceTexte;

	@UiField
	TextBox PreferenceToAdd;

	@UiField
	TextBox DocOrEmailName;

	@UiField
	FileUpload uploadImg;
	
	@UiField
	FileUpload uploadImg4;

	@UiField
	FileUpload uploaddoc3;

	@UiField
	TextBox blobKeyImg;
	
	@UiField
	TextBox blobKeyImg4;

	@UiField
	TextBox blobKeydoc3;
	
	@UiField
	FormPanel docForm2;

	@UiField
	FormPanel docForm3;
	@UiField
	FormPanel docForm4;
	@UiField
	SubmitButton btnSubmit2;

	@UiField
	SubmitButton btnSubmit3;

	@UiField
	SubmitButton btnSubmit4;
	
	@UiField
	Button btnValid;

	public AccueilPrivateForm() {
		initWidget(uiBinder.createAndBindUi(this));

		uploadImg.setName("uploadImg");
		uploadImg4.setName("uploadImg");
		
		blobKeyImg.setName("blobKeyImg");
		blobKeyImg4.setName("blobKeyImg4");

		uploaddoc3.setName("uploaddoc3");
		
		blobKeydoc3.setName("blobKeydoc3");

		docForm2.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm2.setMethod(FormPanel.METHOD_POST);

		docForm3.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm3.setMethod(FormPanel.METHOD_POST);
		
		docForm4.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm4.setMethod(FormPanel.METHOD_POST);

		docForm4.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				// String[] ids = event.getResults().split("/");

				blobKeyImg4.setText(event.getResults());
			}

		});
		
		docForm2.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				// String[] ids = event.getResults().split("/");

				blobKeyImg.setText(event.getResults());
			}

		});

		docForm3.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				// String[] ids = event.getResults().split("/");

				blobKeydoc3.setText(event.getResults());
			}

		});

		btnSubmit3.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				configService
						.getBlobStoreUploadDocUrl(new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								// Set the form action to the newly created
								// blobstore upload URL
								docForm3.setAction(result.toString());
								System.out.println("envoi vers :"
										+ result.toString());

								// Submit the form to complete the upload
								docForm3.submit();
								docForm3.reset();
							}

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();
							}
						});

			}
		});

		btnSubmit4.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				configService
						.getBlobStoreUploadImgUrl(new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								// Set the form action to the newly created
								// blobstore upload URL
								docForm4.setAction(result.toString());
								System.out.println("envoi vers :"
										+ result.toString());

								// Submit the form to complete the upload
								docForm4.submit();
								docForm4.reset();
							}

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();
							}
						});

			}
		});
		
		btnSubmit2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				configService
						.getBlobStoreUploadImgUrl(new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								// Set the form action to the newly created
								// blobstore upload URL
								docForm2.setAction(result.toString());
								System.out.println("envoi vers :"
										+ result.toString());

								// Submit the form to complete the upload
								docForm2.submit();
								docForm2.reset();
							}

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();
							}
						});

			}
		});

		loadData();

		btnAddDownloadEmail.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!DocOrEmailName.getText().isEmpty()) {
					prefEmailBox.addItem(prefEmailBox.getItemCount() + ":"
							+ DocOrEmailName.getText());
				}
			}
		});

		btnAddDownloadDoc.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!DocOrEmailName.getText().isEmpty()) {
					prefDownloadBox.addItem(prefDownloadBox.getItemCount()
							+ ":" + DocOrEmailName.getText());
				}
			}
		});

		btnAddPreferenceVideo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!PreferenceToAdd.getText().isEmpty()) {
					preferenceVideoBox.addItem(preferenceVideoBox
							.getItemCount() + ":" + PreferenceToAdd.getText());
				}
			}
		});
		btnAddPreferenceChanson.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!PreferenceToAdd.getText().isEmpty()) {
					preferenceChansonBox.addItem(preferenceChansonBox
							.getItemCount() + ":" + PreferenceToAdd.getText());
				}
			}
		});
//		btnAddPreferenceTexte.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				if (!PreferenceToAdd.getText().isEmpty()) {
//					preferenceTexteBox.addItem(preferenceTexteBox
//							.getItemCount() + ":" + PreferenceToAdd.getText());
//				}
//			}
//		});

		preferenceVideoBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent changeEvent) {

				// retrouver l'objet à partir de son id dans son url
				String id = getIdFromUrl(preferenceVideoBox
						.getValue(preferenceVideoBox.getSelectedIndex()));

				souvenirService.getArticleObject(id,
						new AsyncCallback<Souvenir>() {

							public void onFailure(Throwable caught) {
								Window.alert("Error" + caught.getMessage());

							}

							public void onSuccess(Souvenir result2) {

								appellationVideoHtml.setHTML(result2
										.getAppellation());

							}// fi onSuccess

						});

			}
		});

		preferenceChansonBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent changeEvent) {

				// retrouver l'objet à partir de son id dans son url
				String id = getIdFromUrl(preferenceChansonBox
						.getValue(preferenceChansonBox.getSelectedIndex()));

				louangeService.getArticleObject(id,
						new AsyncCallback<Louange>() {

							public void onFailure(Throwable caught) {
								Window.alert("Error" + caught.getMessage());

							}

							public void onSuccess(Louange result2) {

								appellationChansonHtml.setHTML(result2
										.getSujet());

							}// fi onSuccess

						});

			}
		});
		
		
//		preferenceTexteBox.addChangeHandler(new ChangeHandler() {
//			public void onChange(ChangeEvent changeEvent) {
//
//				appellationTexteHtml.setHTML(preferenceTexteBox
//						.getValue(preferenceTexteBox.getSelectedIndex()));
//
//			}
//		});
		
		prefEmailBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent changeEvent) {

				appellationEmailHtml.setHTML(prefEmailBox
						.getValue(prefEmailBox.getSelectedIndex()));

			}
		});
		
		//TODO : retrouver le titre du document dès que la box change
	}

	private void loadData() {

		configService.getConfigSite(new AsyncCallback<ConfigSite>() {
			public void onSuccess(ConfigSite result) {

				idNumberBox.setText(result.getIdNumber());
				photoDefilantBox.setText(result.getDirPhotoPicasa());
				if (result.getDirPhotoPicasaNb()!=null)
					dirphotoNbBox.setText(result.getDirPhotoPicasaNb().toString());
				idCleBox.setText(result.getIdCle());
				descriptionBox.setHTML(result.getDescription());
				photoBatimentBox.setText(result.getPhotoBatiment());
				blobKeyImg.setText(result.getImgPresentationUrl());
				blobKeyImg4.setText(result.getImgPresentNsTrouverUrl());
				emailBox.setHTML(result.getEmailHtml());
				adresseBox.setHTML(result.getAdresseHtml());
				integrationBox.setText(result.getUrlIntegration());
				imgPresWidthBox.setText(result.getImgPresentationWidth());

				preferenceVideoBox.clear();
				List<String> videos = result.getPrefsVideo();

				for (int i = 0; i < videos.size(); i++) {
					preferenceVideoBox.addItem(i + ":" + videos.get(i));
				}

				prefDownloadBox.clear();
				List<String> blobKeyDocs = result.getDownloadBlobKeyDocs();

				for (int i = 0; i < blobKeyDocs.size(); i++) {
					prefDownloadBox.addItem(i + ":" + blobKeyDocs.get(i));
				}

				prefEmailBox.clear();
				List<String> emails = result.getDownloadEmails();

				for (int i = 0; i < emails.size(); i++) {
					prefEmailBox.addItem(i + ":" + emails.get(i));
				}

				preferenceChansonBox.clear();
				List<String> chants = result.getPrefsChanson();

				for (int i = 0; i < chants.size(); i++) {
					preferenceChansonBox.addItem(i + ":" + chants.get(i));
				}

//				preferenceTexteBox.clear();
//				List<String> Textes = result.getPrefsTexte();
//
//				for (int i = 0; i < Textes.size(); i++) {
//					preferenceTexteBox.addItem(i + ":" + Textes.get(i));
//				}

			}

			public void onFailure(Throwable caught) {
				// TODO: Show an error message.
			}
		});

	}

	private static AccueilPrivateFormUiBinder uiBinder = GWT
			.create(AccueilPrivateFormUiBinder.class);

	interface AccueilPrivateFormUiBinder extends
			UiBinder<Widget, AccueilPrivateForm> {
	}

	@UiHandler("btnValid")
	void handleAddButtonClick(ClickEvent e) {
		checkFieldsNotEmpty();
		soumissionConf();

	}
	
	@UiHandler("btnRemoveDownloadEmail")
	void handleRemoveDownloadEmailsClick(ClickEvent e) {

		if (prefEmailBox.getSelectedIndex() != -1)
			prefEmailBox
					.removeItem(prefEmailBox.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < prefEmailBox.getItemCount(); i++) {

			String content = prefEmailBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		prefEmailBox.clear();

		for (String ar : arl) {
			prefEmailBox.addItem(ar);
		}

	}
	
	@UiHandler("btnRemoveDownloadDoc")
	void handleRemoveDownloadDocButtonClick(ClickEvent e) {

		if (prefDownloadBox.getSelectedIndex() != -1)
			prefDownloadBox
					.removeItem(prefDownloadBox.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < prefDownloadBox.getItemCount(); i++) {

			String content = prefDownloadBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		prefDownloadBox.clear();

		for (String ar : arl) {
			prefDownloadBox.addItem(ar);
		}

	}

	@UiHandler("btnRemovePreferenceVideo")
	void handleRemoveButtonClick(ClickEvent e) {

		if (preferenceVideoBox.getSelectedIndex() != -1)
			preferenceVideoBox
					.removeItem(preferenceVideoBox.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < preferenceVideoBox.getItemCount(); i++) {

			String content = preferenceVideoBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		preferenceVideoBox.clear();

		for (String ar : arl) {
			preferenceVideoBox.addItem(ar);
		}

	}

	@UiHandler("btnRemovePreferenceChanson")
	void handleRemoveChansonButtonClick(ClickEvent e) {

		if (preferenceChansonBox.getSelectedIndex() != -1)
			preferenceChansonBox.removeItem(preferenceChansonBox
					.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < preferenceChansonBox.getItemCount(); i++) {

			String content = preferenceChansonBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		preferenceChansonBox.clear();

		for (String ar : arl) {
			preferenceChansonBox.addItem(ar);
		}

	}

//	@UiHandler("btnRemovePreferenceTexte")
//	void handleRemoveTexteButtonClick(ClickEvent e) {
//
//		if (preferenceTexteBox.getSelectedIndex() != -1)
//			preferenceTexteBox
//					.removeItem(preferenceTexteBox.getSelectedIndex());
//
//		List<String> arl = new ArrayList<String>();
//
//		for (int i = 0; i < preferenceTexteBox.getItemCount(); i++) {
//
//			String content = preferenceTexteBox.getItemText(i);
//			int begin = content.indexOf(":");
//			arl.add(i + content.substring(begin));
//		}
//
//		preferenceTexteBox.clear();
//
//		for (String ar : arl) {
//			preferenceTexteBox.addItem(ar);
//		}
//
//	}

	private void checkFieldsNotEmpty() {
		if (photoBatimentBox.getText().equals("")) {
			Window.alert("le champ Reference de l'Image d'accueil doit etre rempli!!");
			return;
		}
		if (this.descriptionBox.getHTML().equals("")) {
			Window.alert("le champ Texte doit etre rempli!!");
			return;
		}
	}

	private void soumissionConf() {

		ConfigSite cf = new ConfigSite();
		cf.setLogo(photoBatimentBox.getText());
		cf.setDescription(descriptionBox.getHTML());
		cf.setIdNumber(idNumberBox.getText());
		cf.setDirPhotoPicasa(photoDefilantBox.getText());
		if (dirphotoNbBox!=null)
		cf.setDirPhotoPicasaNb(Integer.valueOf(dirphotoNbBox.getText()));
		cf.setIdCle(idCleBox.getText());
		cf.setImgPresentationUrl(this.blobKeyImg.getText().trim());
		cf.setImgPresentNsTrouverUrl(this.blobKeyImg4.getText().trim());
		cf.setPhotoBatiment(photoBatimentBox.getText());
		cf.setAdresseHtml(adresseBox.getHTML());
		cf.setEmailHtml(emailBox.getHTML());
		cf.setUrlIntegration(integrationBox.getText());
		if (imgPresWidthBox!=null)
		cf.setImgPresentationWidth(imgPresWidthBox.getText());
		//dirphotoNbBox.setText(result.getDirPhotoPicasaNb().toString());
		//imgPresWidthBox.setText(result.getImgPresentationWidth());
		
		// preferenceVideoBox.clear();
		List<String> strs = new ArrayList<String>();

		for (int i = 0; i < preferenceVideoBox.getItemCount(); i++) {

			int begin = preferenceVideoBox.getItemText(i).indexOf(":") + 1;
			strs.add(preferenceVideoBox.getItemText(i).substring(begin));

		}

		cf.setPrefsVideo(strs);

		/***********************/

		List<String> strsChanson = new ArrayList<String>();

		for (int i = 0; i < preferenceChansonBox.getItemCount(); i++) {

			int begin = preferenceChansonBox.getItemText(i).indexOf(":") + 1;
			strsChanson.add(preferenceChansonBox.getItemText(i)
					.substring(begin));

		}

		cf.setPrefsChanson(strsChanson);

		/*******************/

//		List<String> strsTexte = new ArrayList<String>();
//
//		for (int i = 0; i < preferenceTexteBox.getItemCount(); i++) {
//
//			int begin = preferenceTexteBox.getItemText(i).indexOf(":") + 1;
//			strsTexte.add(preferenceTexteBox.getItemText(i).substring(begin));
//
//		}

//		cf.setPrefsTexte(strsTexte);

		/*************************/

		List<String> strsdownloadBlobKeyDocs = new ArrayList<String>();

		for (int i = 0; i < prefDownloadBox.getItemCount(); i++) {

			int begin = prefDownloadBox.getItemText(i).indexOf(":") + 1;
			strsdownloadBlobKeyDocs.add(prefDownloadBox.getItemText(i)
					.substring(begin));
		}
		cf.setDownloadBlobKeyDocs(strsdownloadBlobKeyDocs);
		/**********************/
		List<String> strsdownloadEmails = new ArrayList<String>();

		for (int i = 0; i < prefEmailBox.getItemCount(); i++) {

			int begin = prefEmailBox.getItemText(i).indexOf(":") + 1;
			strsdownloadEmails.add(prefEmailBox.getItemText(i)
					.substring(begin));
		}
		cf.setDownloadEmails(strsdownloadEmails);
		/**********************/

		AsyncCallback<ConfigSite> callback = new AsyncCallback<ConfigSite>() {
			public void onFailure(Throwable error) {
				// do something, when fail
			}

			public void onSuccess(ConfigSite result) {
				// when successful, do something, about UI
				if (result != null) {
					Window.alert("configuration enregistree!!");
				}
				displayConfig(result);

			}

		};

		configService.saveConfig(cf, callback);

	}

	private void displayConfig(ConfigSite result) {
		idNumberBox.setText(result.getIdNumber());
		photoDefilantBox.setText(result.getDirPhotoPicasa());
		idCleBox.setText(result.getIdCle());
		descriptionBox.setHTML(result.getDescription());
		photoBatimentBox.setText(result.getPhotoBatiment());
		emailBox.setHTML(result.getEmailHtml());
		adresseBox.setHTML(result.getAdresseHtml());
		integrationBox.setText(result.getUrlIntegration());
		this.blobKeyImg4.setText(result.getImgPresentNsTrouverUrl());
		if(result.getDirPhotoPicasaNb()!=null)
			dirphotoNbBox.setText(result.getDirPhotoPicasaNb().toString());
		imgPresWidthBox.setText(result.getImgPresentationWidth());
		
		/****************/
		preferenceVideoBox.clear();
		List<String> videos = result.getPrefsVideo();

		for (int i = 0; i < videos.size(); i++) {
			preferenceVideoBox.addItem(i + ":" + videos.get(i));
		}
		/*************/
		preferenceChansonBox.clear();
		List<String> Chansons = result.getPrefsChanson();

		for (int i = 0; i < Chansons.size(); i++) {
			preferenceChansonBox.addItem(i + ":" + Chansons.get(i));
		}
		/********************/
//		preferenceTexteBox.clear();
//		List<String> Textes = result.getPrefsTexte();
//
//		for (int i = 0; i < Textes.size(); i++) {
//			preferenceTexteBox.addItem(i + ":" + Textes.get(i));
//		}
		/************************/

		this.prefDownloadBox.clear();
		List<String> bdocs = result.getDownloadBlobKeyDocs();

		for (int i = 0; i < bdocs.size(); i++) {
			prefDownloadBox.addItem(i + ":" + bdocs.get(i));
		}
		/****************************/
		this.prefEmailBox.clear();
		List<String> bmails = result.getDownloadEmails();

		for (int i = 0; i < bmails.size(); i++) {
			prefEmailBox.addItem(i + ":" + bmails.get(i));
		}
		
	}

	private String getIdFromUrl(String urlVideo) {

		// http://127.0.0.1:8888/#Souvenir:20&0&cat0

		String strUrlParams = urlVideo.substring(urlVideo.lastIndexOf(":"));

		return strUrlParams.substring(1, strUrlParams.indexOf("&"));

	}
}
