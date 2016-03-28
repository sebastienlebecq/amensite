package fr.nations.amen.client.louanges.celllist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.event.dom.client.ClickEvent;

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
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.UniformDim.MODE;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;

public class LouangesManagerForm extends Composite {

	LouangesServiceAsync messagesAudioService = GWT
			.create(LouangesService.class);

	private static MessagesAudioManagerFormUiBinder uiBinder = GWT
			.create(MessagesAudioManagerFormUiBinder.class);

	interface MessagesAudioManagerFormUiBinder extends
			UiBinder<Widget, LouangesManagerForm> {
	}

	public LouangesManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));

		upload.setName("uploadedFile");
		blobKey.setName("blobKey");
		
		uploadogg.setName("uploadedogg");
		blobKey1.setName("blobKey1");
		
		uploadedwav.setName("uploadedwav");
		blobKey2.setName("blobKey2");
		
		uploadImg.setName("uploadImg");
		blobKeyImg.setName("blobKeyImg");


		docForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm.setMethod(FormPanel.METHOD_POST);
		
		docForm2.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm2.setMethod(FormPanel.METHOD_POST);

		docForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				String[] ids = event.getResults().split("/");

				blobKey.setText(ids[0]);
				blobKey1.setText(ids[1]);
				blobKey2.setText(ids[2]);
				
				blobKeyImg.setText(ids[3]);
			}

		});
		
		docForm2.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				//String[] ids = event.getResults().split("/");

				blobKeyImg.setText(event.getResults());
			}

		});

		btnSubmit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				messagesAudioService
						.getBlobStoreUploadUrl(new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								// Set the form action to the newly created
								// blobstore upload URL
								docForm.setAction(result.toString());
								System.out.println("envoi vers :"
										+ result.toString());

								// Submit the form to complete the upload
								docForm.submit();
								docForm.reset();
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

				messagesAudioService
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
		
		btnAddRefYoutube.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!pathRefYoutubeToAdd.getText().isEmpty()) {
					listRefYoutubeBox.addItem(listRefYoutubeBox
							.getItemCount() + ":" + pathRefYoutubeToAdd.getText());
				}
			}
		});
		
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_LONG);
		dateSouvenirBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setSouvenir(null);

		for (String cat : UniformDim.categoriesLouanges){
			categories.addItem(cat);
		}
		
		
	}
	
	@UiField
	TextBox numeroAmen;
	
	@UiField
	ListBox categories;

	@UiField
	SubmitButton btnSubmit;
	
	@UiField
	SubmitButton btnSubmit2;
	
	@UiField
	FormPanel docForm;
	
	@UiField
	FormPanel docForm2;

	@UiField
	FileUpload uploadedwav;

	@UiField
	FileUpload uploadogg;

	@UiField
	FileUpload upload;
	
	@UiField
	FileUpload uploadImg;
	
	@UiField
	TextBox blobKeyImg;

	@UiField
	TextBox blobKey;

	@UiField
	TextBox blobKey1;

	@UiField
	TextBox blobKey2;
	//
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
	ListBox listRefYoutubeBox;
	
	@UiField
	Button btnAddRefYoutube;
	
	@UiField
	TextBox pathRefYoutubeToAdd;
	
	

	@UiField
	Button btnAdd;

//	@UiField
//	Button btnRemove;

	@UiField
	Button btnUpdate;

	private Louange souvenirInfo;
	// private AllDataAsyncDataProvider dataProvider;
	private CellList<Louange> souvenirList;

	// private Souvenir actuInfo2;

	public LouangesManagerForm(String firstName) {
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
		if (this.numeroAmen.getText().equals("")) {
			Window.alert("le champ Numéro Amen doit etre rempli!!");
			return;
		}
	}

//	@UiHandler("btnRemove")
//	void handleRemoveButtonClick(ClickEvent e) {
//
//		Window.alert("A venir");
//	}

	@UiHandler("btnRemoveRefYoutube")
	void handleRemoveVideoRefYoutubeButtonClick(ClickEvent e) {

		if (listRefYoutubeBox.getSelectedIndex() != -1)
			listRefYoutubeBox
					.removeItem(listRefYoutubeBox.getSelectedIndex());

		List<String> arl = new ArrayList<String>();

		for (int i = 0; i < listRefYoutubeBox.getItemCount(); i++) {

			String content = listRefYoutubeBox.getItemText(i);
			int begin = content.indexOf(":");
			arl.add(i + content.substring(begin));
		}

		listRefYoutubeBox.clear();

		for (String ar : arl) {
			listRefYoutubeBox.addItem(ar);
		}

	}
	
	
	@UiHandler("btnUpdate")
	void handleUpdateButtonClick(ClickEvent e) {
		checkFieldsNotEmpty();
		updateActu();
	}

	private void updateActu() {

		final String idNumber = idNumberBox.getText().trim();
		Louange newActu = new Louange(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText());
		//newActu.setRefYoutube(refYoutubeBox.getText());
		newActu.setBlobKey(blobKey.getText().trim());
		newActu.setBlobKeyogg(this.blobKey1.getText().trim());
		newActu.setBlobKeywav(this.blobKey2.getText().trim());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		newActu.setNumero(this.numeroAmen.getText().trim());
		newActu.setThubnailUrl(this.blobKeyImg.getText().trim());
		
		
		String strs2 = new String();
		for (int i = 0; i < listRefYoutubeBox.getItemCount(); i++) {

			int begin = listRefYoutubeBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs2= listRefYoutubeBox.getItemText(i).substring(begin);
			else
				strs2= strs2+";"+listRefYoutubeBox.getItemText(i).substring(begin);
		}
		newActu.setRefYoutube(strs2);
		
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+this.categories.getSelectedIndex()
				+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
		//this.setIndexCategory(this.categories.getSelectedIndex());

		AsyncCallback<Louange> callback = new AsyncCallback<Louange>() {
			public void onFailure(Throwable error) {
				// do something, when fail
			}

			public void onSuccess(Louange result) {
				// when successful, do something, about UI
				displayAddActu(result);
			}
		};

		messagesAudioService.updateSouvenir(newActu, callback);
	}

	private void removeActu() {
		final String idNumber = idNumberBox.getText().trim();
		Louange newSouvenir = new Louange(idNumber);
		newSouvenir.setAppellation(this.appellationBox.getText());
		newSouvenir.setDescription(this.descriptionBox.getHTML());
		newSouvenir.setDate(this.dateSouvenirBox.getValue());
		//newSouvenir.setRefYoutube(refYoutubeBox.getText());
		newSouvenir.setBlobKey(this.blobKey.getText());
		newSouvenir.setBlobKeyogg(this.blobKey1.getText());
		newSouvenir.setBlobKeywav(this.blobKey2.getText());
		//newSouvenir.setCategory(this.categories.getItemText(indexCategory));
		
		String strs2 = new String();
		for (int i = 0; i < listRefYoutubeBox.getItemCount(); i++) {

			int begin = listRefYoutubeBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs2= listRefYoutubeBox.getItemText(i).substring(begin);
			else
				strs2= strs2+";"+listRefYoutubeBox.getItemText(i).substring(begin);
		}
		newSouvenir.setRefYoutube(strs2);

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
				// do something, when fail
			}

			public void onSuccess(Void result) {
				// when successful, do something, about UI
				displayRemoveActu();
			}

		};

		messagesAudioService.remove(newSouvenir, callback);

	}

	protected void displayRemoveActu() {
		Window.alert("actualité effacée!!");

	}

	private void addActu() {

		final String idNumber = idNumberBox.getText().trim();
		Louange newActu = new Louange(idNumber);
		newActu.setAppellation(this.appellationBox.getText().trim());
		newActu.setDescription(this.descriptionBox.getHTML().trim());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText().trim());
		//newActu.setRefYoutube(refYoutubeBox.getText());
		newActu.setBlobKey(blobKey.getText().trim());
		newActu.setBlobKeyogg(blobKey1.getText().trim());
		newActu.setBlobKeywav(blobKey2.getText().trim());
		newActu.setThubnailUrl(this.blobKeyImg.getText().trim());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		//newActu.setCategory(this.categories.getItemText(this.indexCategory));
		
		
		String strs2 = new String();
		for (int i = 0; i < listRefYoutubeBox.getItemCount(); i++) {

			int begin = listRefYoutubeBox.getItemText(i).indexOf(":") + 1;
			if (i==0) 
				strs2= listRefYoutubeBox.getItemText(i).substring(begin);
			else
				strs2= strs2+";"+listRefYoutubeBox.getItemText(i).substring(begin);
		}
		newActu.setRefYoutube(strs2);
		
		
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+Integer.toString(this.categories.getSelectedIndex())
				+" :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		newActu.setNumero(this.numeroAmen.getText().trim())	;
		
		//this.categories.setItemSelected(this.indexCategory, true);
		//this.setIndexCategory(indexCategory);

		// (2) Create an asynchronous callback to handle the result.
		AsyncCallback<Louange> callback = new AsyncCallback<Louange>() {
			public void onFailure(Throwable error) {
				// do something, when fail
			}

			public void onSuccess(Louange result) {
				// when successful, do something, about UI
				displayAddActu(result);
			}

		};

		// (3) Make the call. Control flow will continue immediately and later
		// 'callback' will be invoked when the RPC completes

		messagesAudioService.addSouvenir(newActu, callback);

	}

	private void displayAddActu(Louange savedActu) {

		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("actu Renvoyée:" + savedActu.getId() + ",appellation:"
				+ savedActu.getSujet()+ ", category:"+savedActu.getCategory());

		this.setSouvenir(savedActu);

		// mise à jour le display de la liste

		List<Louange> lactu = new ArrayList<Louange>();
		lactu.add(savedActu);

		Range range = souvenirList.getVisibleRange();

		souvenirList.setRowData(range.getStart(), lactu);
		((CellList<Louange>) souvenirList).redraw();

		// autre méthode :
		// actuList.setRowCount(result.size());
		// actuList.setRowData(0, result);

		//initializeBox();

	}

	private void initializeBox() {

		Louange initActu = new Louange("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		initActu.setRefYoutube("");
		initActu.setBlobKey("");
		initActu.setBlobKeyogg("");
		initActu.setBlobKeywav("");
		initActu.setCategory("toutes");

		setSouvenir(new Louange(""));
		this.categories.setItemSelected(0, true);
		

	}

	public void setSouvenir(Louange louange) {
		
		
		this.souvenirInfo = louange;
		listRefYoutubeBox.clear();
		pathRefYoutubeToAdd.setText("");
		
		// btnAdd.setEnabled(actu != null);
		// btnRemove.setEnabled(actu != null);
		// btnUpdate.setEnabled(actu != null);
		if (louange != null) {
			idNumberBox.setText(louange.getId());
			appellationBox.setText(louange.getSujet());
			descriptionBox.setHTML(louange.getDescription());
			dateSouvenirBox.setValue(louange.getDate());
			this.albumRefPicasaBox.setText(louange.getAlbumRefPicasa());
			//this.refYoutubeBox.setText(louange.getRefYoutube());
			this.blobKey.setText(louange.getBlobKey());
			this.blobKey1.setText(louange.getBlobKeyogg());
			this.blobKey2.setText(louange.getBlobKeywav());
			this.numeroAmen.setText(louange.getNumero());
			this.blobKeyImg.setText(louange.getThubnailUrl());
			
			if (louange.getRefYoutube()!=null && louange.getRefYoutube().length()>1){
				
				String[] videosBefore = louange.getRefYoutube().split(";");
				
				listRefYoutubeBox.clear();
				//List<String> videos = result.getPrefsVideo();

				for (int i = 0; i < videosBefore.length; i++) {
					listRefYoutubeBox.addItem(i + ":" + videosBefore[i]);
				}
				} else {
					listRefYoutubeBox.clear();
				}
			
			//newActu.setCategory(categories.getItemText(this.categories.getSelectedIndex()));
			//Window.alert("category:"+indexCategory+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
			//this.setIndexCategory(this.categories.getSelectedIndex());
			if (UniformDim.mode == MODE.DEBUG)
			Window.alert("souvenir.getCategory():"+louange.getCategory()+",index:"+Integer.valueOf(louange.getCategory().substring(3)));
			//int newIndex =getIndexOf(souvenir.getCategory());
			if (louange.getCategory()!=null){
				if (UniformDim.mode == MODE.DEBUG)
				Window.alert("souvenir.getCategory():"+louange.getCategory()+",index:"+Integer.valueOf(louange.getCategory().substring(3)));
				this.categories.setItemSelected(new Integer(Integer.valueOf(louange.getCategory().substring(3))), true);

			}
		}
	}


	public void setDisplay(CellList<Louange> souvenirList2) {
		this.souvenirList = souvenirList2;

	}

}
