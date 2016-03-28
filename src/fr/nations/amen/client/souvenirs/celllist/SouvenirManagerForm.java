package fr.nations.amen.client.souvenirs.celllist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.UniformDim.MODE;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.souvenirs.SouvenirsService;
import fr.nations.amen.shared.souvenirs.SouvenirsServiceAsync;

public class SouvenirManagerForm extends Composite {
	
	//private ArrayList<String> idArray = new ArrayList<String>();
	SouvenirsServiceAsync souvenirService = GWT.create(SouvenirsService.class);

	
	
	private static SouvenirManagerFormUiBinder uiBinder = GWT
			.create(SouvenirManagerFormUiBinder.class);

	interface SouvenirManagerFormUiBinder extends UiBinder<Widget, SouvenirManagerForm> {
	}

	public SouvenirManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));
		

		
		
		uploadImg.setName("uploadImg");
		blobKeyImg.setName("blobKeyImg");
		//pathVideoMp4.setName("blobKeyVideoMp4");
		//uploadVideoMp4.setName("uploadImg");
		
//		blobKeyPdf.setName("blobKeyPdf");
//		uploadPdf.setName("uploadPdf");
		
		docForm2.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm2.setMethod(FormPanel.METHOD_POST);
		
//		docFormVideo.setEncoding(FormPanel.ENCODING_MULTIPART);
//		docFormVideo.setMethod(FormPanel.METHOD_POST);
//		
//		docFormPdf.setEncoding(FormPanel.ENCODING_MULTIPART);
//		docFormPdf.setMethod(FormPanel.METHOD_POST);
		
		docForm2.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				//String[] ids = event.getResults().split("/");

				blobKeyImg.setText(event.getResults());
			}

		});
		
//		docFormVideo.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//			@Override
//			public void onSubmitComplete(SubmitCompleteEvent event) {
//				System.out.println("result:" + event.getResults());
//
//				//String[] ids = event.getResults().split("/");
//
//				pathVideoMp4.setText(event.getResults());
//			}
//
//		});
		
//		docFormPdf.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//			@Override
//			public void onSubmitComplete(SubmitCompleteEvent event) {
//				System.out.println("result:" + event.getResults());
//
//				//String[] ids = event.getResults().split("/");
//
//				blobKeyPdf.setText(event.getResults());
//			}
//
//		});
		
//		btnSubmitVideoMp4.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//
//				souvenirService
//						.getBlobStoreUploadImgUrl(new AsyncCallback<String>() {
//
//							@Override
//							public void onSuccess(String result) {
//								// Set the form action to the newly created
//								// blobstore upload URL
//								docFormVideo.setAction(result.toString());
//								System.out.println("envoi vers :"
//										+ result.toString());
//
//								// Submit the form to complete the upload
//								docFormVideo.submit();
//								docFormVideo.reset();
//							}
//
//							@Override
//							public void onFailure(Throwable caught) {
//								caught.printStackTrace();
//							}
//						});
//
//			}
//		});
		
		btnSubmit2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				souvenirService
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
		
		btnAddVideoGDrive.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!pathVideoGDriveToAdd.getText().isEmpty()) {
					pathVideoGDriveBox.addItem(pathVideoGDriveBox
							.getItemCount() + ":" + pathVideoGDriveToAdd.getText());
				}
			}
		});
		
		btnAddVideoRefYoutube.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!pathVideoRefYoutubeToAdd.getText().isEmpty()) {
					pathRefYoutubeBox.addItem(pathRefYoutubeBox
							.getItemCount() + ":" + pathVideoRefYoutubeToAdd.getText());
				}
			}
		});
		
		btnAddUrlPdf.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!pathUrlPdfToAdd.getText().isEmpty()) {
					listUrlPdfBox.addItem(listUrlPdfBox
							.getItemCount() + ":" + pathUrlPdfToAdd.getText());
					pathUrlPdfToAdd.setText("");
					
				}
				
			}
			
		});
		
		
//		btnSubmitPdf.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//
//				souvenirService
//						.getBlobStoreUploadImgUrl(new AsyncCallback<String>() {
//
//							@Override
//							public void onSuccess(String result) {
//								// Set the form action to the newly created
//								// blobstore upload URL
//								docFormPdf.setAction(result.toString());
//								System.out.println("envoi vers :"
//										+ result.toString());
//
//								// Submit the form to complete the upload
//								docFormPdf.submit();
//								docFormPdf.reset();
//							}
//
//							@Override
//							public void onFailure(Throwable caught) {
//								caught.printStackTrace();
//							}
//						});
//
//			}
//		});
		
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_LONG);
		dateSouvenirBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setSouvenir(null);
		
		for (String cat : UniformDim.categoriesSouvenirs){
			categories.addItem(cat);
		}
	}
	
	@UiField
	ListBox categories;

	@UiField
	TextBox idNumberBox;

	@UiField
	TextBox appellationBox;

	@UiField
	RichTextArea descriptionBox;

	@UiField
	DateBox dateSouvenirBox;

	@UiField
	TextBox albumRefPicasaBox;

	@UiField
	Button btnAdd;

//	@UiField
//	Button btnRemove;

	@UiField
	Button btnUpdate;

	@UiField
	FileUpload uploadImg;
	
//	@UiField
//	FileUpload uploadVideoMp4;

//	@UiField
//	FileUpload uploadPdf;
	
	@UiField
	TextBox blobKeyImg;
	
//	@UiField
//	TextBox pathVideoMp4;
	
	@UiField
	ListBox pathVideoGDriveBox;
	
	@UiField
	Button btnAddVideoGDrive;
	
	@UiField
	Button btnAddVideoRefYoutube;
	
	@UiField
	ListBox pathRefYoutubeBox;
	
	@UiField
	TextBox pathVideoRefYoutubeToAdd;
	
	@UiField
	TextBox pathVideoGDriveToAdd;
	
	
//	@UiField
//	TextBox blobKeyPdf;
	
	@UiField
	FormPanel docForm2;
	
//	@UiField
//	FormPanel docFormVideo;
	
//	@UiField
//	FormPanel docFormPdf;
	
	@UiField
	SubmitButton btnSubmit2;
	
//	@UiField
//	SubmitButton btnSubmitVideoMp4;
//	@UiField
//	SubmitButton btnSubmitPdf;
	
//	@UiField
//	TextBox urlPdfBox;

	@UiField
	Button btnAddUrlPdf;
	
	@UiField
	ListBox listUrlPdfBox;
	
	@UiField
	TextBox pathUrlPdfToAdd;
	
	
	
	
	private Souvenir souvenirInfo;
	//private AllDataAsyncDataProvider dataProvider;
	private CellList<Souvenir> souvenirList;
	//private Souvenir actuInfo2;

	public SouvenirManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		btnAdd.setText("Add");
		//btnRemove.setText("Remove");
		btnUpdate.setText("Update");
	}
	
	

	@UiHandler("btnAdd")
	void handleAddButtonClick(ClickEvent e) {
		checkFieldsNotEmpty();
		addActu();
		
	}
	
	private void checkFieldsNotEmpty() {
		if (dateSouvenirBox.getTextBox().getText().equals("")) {
			Window.alert("le champ Date doit etre rempli!!");
			return;
		}
		if (this.appellationBox.getText().equals("")) {
			Window.alert("le champ Sujet doit etre rempli!!");
			return;
		}
	}
	
//	@UiHandler("btnRemove")
//	void handleRemoveButtonClick(ClickEvent e) {
//		//removeActu();
//		Window.alert("A venir");
//	}
	
	@UiHandler("btnRemoveVideoGDrive")
	void handleRemoveVideoButtonClick(ClickEvent e) {

		if (pathVideoGDriveBox.getSelectedIndex() != -1)
			pathVideoGDriveBox
					.removeItem(pathVideoGDriveBox.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < pathVideoGDriveBox.getItemCount(); i++) {

			String content = pathVideoGDriveBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		pathVideoGDriveBox.clear();

		for (String ar : arl) {
			pathVideoGDriveBox.addItem(ar);
		}

	}
	
	@UiHandler("btnRemoveUrlPdf")
	void handleRemoveUrlPdfButtonClick(ClickEvent e) {

		if (listUrlPdfBox.getSelectedIndex() != -1)
			listUrlPdfBox
					.removeItem(listUrlPdfBox.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < listUrlPdfBox.getItemCount(); i++) {

			String content = listUrlPdfBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		listUrlPdfBox.clear();

		for (String ar : arl) {
			listUrlPdfBox.addItem(ar);
		}

	}

	@UiHandler("btnRemoveVideoRefYoutube")
	void handleRemoveVideoRefYoutubeButtonClick(ClickEvent e) {

		if (pathRefYoutubeBox.getSelectedIndex() != -1)
			pathRefYoutubeBox
					.removeItem(pathRefYoutubeBox.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < pathRefYoutubeBox.getItemCount(); i++) {

			String content = pathRefYoutubeBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		pathRefYoutubeBox.clear();

		for (String ar : arl) {
			pathRefYoutubeBox.addItem(ar);
		}

	}
	
	@UiHandler("btnUpdate")
	void handleUpdateButtonClick(ClickEvent e) {
		updateActu();
	}
	
	private void updateActu() {
		final String idNumber = idNumberBox.getText().trim();
		Souvenir newActu = new Souvenir(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText());
		//newActu.setRefYoutube(refYoutubeBox.getText());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		newActu.setThubnailUrl(this.blobKeyImg.getText().trim());
		//newActu.setUrlpdf(this.urlPdfBox.getText().trim());
	//	newActu.setVideoGDrive(this.pathVideoMp4.getText().trim());
		
		String strs = new String();
		for (int i = 0; i < pathVideoGDriveBox.getItemCount(); i++) {

			int begin = pathVideoGDriveBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs= pathVideoGDriveBox.getItemText(i).substring(begin);
			else
				strs= strs+";"+pathVideoGDriveBox.getItemText(i).substring(begin);
		}
		newActu.setVideoGDrive(strs);
		
		
		String strs2 = new String();
		for (int i = 0; i < pathRefYoutubeBox.getItemCount(); i++) {

			int begin = pathRefYoutubeBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs2= pathRefYoutubeBox.getItemText(i).substring(begin);
			else
				strs2= strs2+";"+pathRefYoutubeBox.getItemText(i).substring(begin);
		}
		newActu.setRefYoutube(strs2);
		
		String strs3 = new String();
		for (int i = 0; i < listUrlPdfBox.getItemCount(); i++) {

			int begin = listUrlPdfBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs3= listUrlPdfBox.getItemText(i).substring(begin);
			else
				strs3= strs3+";"+listUrlPdfBox.getItemText(i).substring(begin);
		}
		newActu.setUrlpdf(strs3);
		
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+this.categories.getSelectedIndex()
				+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
		 AsyncCallback<Souvenir> callback  = new AsyncCallback<Souvenir>() {
				public void onFailure(Throwable error) {
					//do something, when fail
				}

				public void onSuccess(Souvenir result) {
					//when successful, do something, about UI
					if (result!=null){
						Window.alert("mise à jour effectuée!!");
					}
					displayAddActu(result);
				
				}
			};
			
			souvenirService.updateSouvenir(newActu, callback);
	}

	private void removeActu() {
		final String idNumber = idNumberBox.getText().trim();
		Souvenir newSouvenir = new Souvenir(idNumber);
		newSouvenir.setAppellation(this.appellationBox.getText());
		newSouvenir.setDescription(this.descriptionBox.getHTML());
		newSouvenir.setDate(this.dateSouvenirBox.getValue());
		//newSouvenir.setRefYoutube(refYoutubeBox.getText());
	//	newSouvenir.setUrlpdf(this.urlPdfBox.getText());
	//	newSouvenir.setVideoGDrive(this.pathVideoMp4.getText());
		
		String strs3 = new String();
		for (int i = 0; i < listUrlPdfBox.getItemCount(); i++) {

			int begin = listUrlPdfBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs3= listUrlPdfBox.getItemText(i).substring(begin);
			else
				strs3= strs3+";"+listUrlPdfBox.getItemText(i).substring(begin);
		}
		newSouvenir.setUrlpdf(strs3);
		
		String strs2 = new String();

		for (int i = 0; i < pathRefYoutubeBox.getItemCount(); i++) {

			int begin = pathRefYoutubeBox.getItemText(i).indexOf(":") + 1;
			
			if (i==0) 
				strs2= pathRefYoutubeBox.getItemText(i).substring(begin);
			else
				strs2= strs2+";"+pathRefYoutubeBox.getItemText(i).substring(begin);

		}
		newSouvenir.setRefYoutube(strs2);
		
		AsyncCallback<Void> callback  = new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
				//do something, when fail
			}

			public void onSuccess(Void result) {
				//when successful, do something, about UI
				displayRemoveActu();
			}

		};

		souvenirService.remove(newSouvenir, callback);

	}

	protected void displayRemoveActu() {
		Window.alert("actualité effacée!!");
		
	}

	private void addActu() {
		
		final String idNumber = idNumberBox.getText().trim();
		Souvenir newActu = new Souvenir(idNumber);
		newActu.setAppellation(this.appellationBox.getText().trim());
		newActu.setDescription(this.descriptionBox.getHTML().trim());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText().trim());
		//newActu.setRefYoutube(refYoutubeBox.getText());
		newActu.setThubnailUrl(this.blobKeyImg.getText().trim());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		//newActu.setUrlpdf(this.urlPdfBox.getText());
	//	newActu.setVideoGDrive(this.pathVideoMp4.getText());
		
		String strs2 = new String();
		for (int i = 0; i < pathRefYoutubeBox.getItemCount(); i++) {

			int begin = pathRefYoutubeBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs2= pathRefYoutubeBox.getItemText(i).substring(begin);
			else
				strs2= strs2+";"+pathRefYoutubeBox.getItemText(i).substring(begin);
		}
		newActu.setRefYoutube(strs2);
		
		
		String strs3 = new String();
		for (int i = 0; i < listUrlPdfBox.getItemCount(); i++) {

			int begin = listUrlPdfBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs3= listUrlPdfBox.getItemText(i).substring(begin);
			else
				strs3= strs3+";"+listUrlPdfBox.getItemText(i).substring(begin);
		}
		newActu.setUrlpdf(strs3);
		
		
		
		String strs = new String();

		for (int i = 0; i < pathVideoGDriveBox.getItemCount(); i++) {

			int begin = pathVideoGDriveBox.getItemText(i).indexOf(":") + 1;
			
			if (i==0) 
				strs= pathVideoGDriveBox.getItemText(i).substring(begin);
			else
				strs= strs+";"+pathVideoGDriveBox.getItemText(i).substring(begin);

		}

		newActu.setVideoGDrive(strs);
		
		//newActu.setCategory(this.categories.getItemText(this.indexCategory));
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+Integer.toString(this.categories.getSelectedIndex())
				+" :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
//		if (idArray.contains(idNumber))
//			return;

		// (2) Create an asynchronous callback to handle the result.
				 AsyncCallback<Souvenir> callback  = new AsyncCallback<Souvenir>() {
					public void onFailure(Throwable error) {
						//do something, when fail
					}

					public void onSuccess(Souvenir result) {
						//when successful, do something, about UI
						Window.alert("ajout réussi!!");
						displayAddActu(result);
					}

				};

				// (3) Make the call. Control flow will continue immediately and later
				// 'callback' will be invoked when the RPC completes
				
				souvenirService.addSouvenir(newActu, callback);
		
	}

	private void displayAddActu(Souvenir savedActu) {
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("actu envoyée:"+savedActu.getId()+",appellation:"+savedActu.getAppellation());
		
		this.setSouvenir(savedActu);
		
		// mise à jour le display de la liste
		
			List<Souvenir> lactu = new ArrayList<Souvenir>();
			lactu.add(savedActu);
		
			Range range = souvenirList.getVisibleRange();
			
			souvenirList.setRowData(range.getStart(), lactu);
			   ((CellList<Souvenir>) souvenirList).redraw();
			   
			   //autre méthode :
			  // actuList.setRowCount(result.size());
			 //  actuList.setRowData(0, result);
			   
			  // initializeBox();

	}

	private void initializeBox() {
		
		Souvenir initActu = new Souvenir("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		initActu.setRefYoutube("");
		initActu.setUrlpdf("");
		setSouvenir(new Souvenir(""));
		
		
	}

	public void setSouvenir(Souvenir souvenir) {
		this.souvenirInfo = souvenir;
		pathVideoGDriveBox.clear();
		pathRefYoutubeBox.clear();
		listUrlPdfBox.clear();
		
//		btnAdd.setEnabled(actu != null);
//		btnRemove.setEnabled(actu != null);
//		btnUpdate.setEnabled(actu != null);
		if (souvenir != null) {
			idNumberBox.setText(souvenir.getId());
			appellationBox.setText(souvenir.getAppellation());
			descriptionBox.setHTML(souvenir.getDescription());
			dateSouvenirBox.setValue(souvenir.getDate());
			this.albumRefPicasaBox.setText(souvenir.getAlbumRefPicasa());
			//this.refYoutubeBox.setText(souvenir.getRefYoutube());
			this.blobKeyImg.setText(souvenir.getThubnailUrl());
			//this.urlPdfBox.setText(souvenir.getUrlpdf());
					//this.pathVideoMp4.setText(souvenir.getVideoGDrive());
					
			

			if (souvenir.getRefYoutube()!=null && souvenir.getRefYoutube().length()>1){
				
			String[] videosBefore = souvenir.getRefYoutube().split(";");
			
			pathRefYoutubeBox.clear();
			//List<String> videos = result.getPrefsVideo();

			for (int i = 0; i < videosBefore.length; i++) {
				pathRefYoutubeBox.addItem(i + ":" + videosBefore[i]);
			}
			} else {
				pathRefYoutubeBox.clear();
			}
			
			
					if (souvenir.getVideoGDrive()!=null && souvenir.getVideoGDrive().length()>1){
					
					String[] videosBefore = souvenir.getVideoGDrive().split(";");
					pathVideoGDriveBox.clear();

					for (int i = 0; i < videosBefore.length; i++) {
						pathVideoGDriveBox.addItem(i + ":" + videosBefore[i]);
					}
					} else {
						pathVideoGDriveBox.clear();
					}
					
					if (souvenir.getUrlpdf()!=null && souvenir.getUrlpdf().length()>1){
						
						String[] videosBefore = souvenir.getUrlpdf().split(";");
						listUrlPdfBox.clear();

						for (int i = 0; i < videosBefore.length; i++) {
							listUrlPdfBox.addItem(i + ":" + videosBefore[i]);
						}
						} else {
							listUrlPdfBox.clear();
						}		
					
					
			if (UniformDim.mode == MODE.DEBUG)
			Window.alert("souvenir.getCategory():"+souvenir.getCategory()+",index:"+Integer.valueOf(souvenir.getCategory().substring(3)));
			//int newIndex =getIndexOf(souvenir.getCategory());
			if (souvenir.getCategory()!=null){
				if (UniformDim.mode == MODE.DEBUG)
				Window.alert("souvenir.getCategory():"+souvenir.getCategory()+",index:"+Integer.valueOf(souvenir.getCategory().substring(3)));
				this.categories.setItemSelected(new Integer(Integer.valueOf(souvenir.getCategory().substring(3))), true);

			}
		}
	}


//	public void setDataProvider(AllDataAsyncDataProvider dataProviderAsync) {
//		this.dataProvider =dataProviderAsync; 
//		
//	}

	public void setDisplay(CellList<Souvenir> actuList) {
		this.souvenirList = actuList;
		
	}
}
