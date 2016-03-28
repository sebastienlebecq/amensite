package fr.nations.amen.client.messagesaudio.celllist;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.UniformDim.MODE;
import fr.nations.amen.shared.messagesaudio.MessageAudio;
import fr.nations.amen.shared.messagesaudio.MessagesAudioService;
import fr.nations.amen.shared.messagesaudio.MessagesAudioServiceAsync;

public class MessagesAudioManagerForm extends Composite {

	MessagesAudioServiceAsync messagesAudioService = GWT
			.create(MessagesAudioService.class);

	private static MessagesAudioManagerFormUiBinder uiBinder = GWT
			.create(MessagesAudioManagerFormUiBinder.class);

	interface MessagesAudioManagerFormUiBinder extends
			UiBinder<Widget, MessagesAudioManagerForm> {
	}

	public MessagesAudioManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));

		upload.setName("uploadedFile");
		uploadogg.setName("uploadedogg");
		uploadedwav.setName("uploadedwav");
		blobKey.setName("blobKey");
		blobKey1.setName("blobKey1");
		blobKey2.setName("blobKey2");

		docForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm.setMethod(FormPanel.METHOD_POST);

		docForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				String[] ids = event.getResults().split("/");

				blobKey.setText(ids[0]);
				blobKey1.setText(ids[1]);
				blobKey2.setText(ids[2]);
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

		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_LONG);
		dateSouvenirBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setSouvenir(null);

		for (String cat : UniformDim.categoriesMessagesAudio){
			categories.addItem(cat);
		}
		
		
	}

	@UiField
	ListBox categories;

	@UiField
	SubmitButton btnSubmit;

	@UiField
	FormPanel docForm;

	@UiField
	FileUpload uploadedwav;

	@UiField
	FileUpload uploadogg;

	@UiField
	FileUpload upload;

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
	TextBox refYoutubeBox;

	@UiField
	Button btnAdd;

	@UiField
	Button btnRemove;

	@UiField
	Button btnUpdate;

	private MessageAudio souvenirInfo;
	// private AllDataAsyncDataProvider dataProvider;
	private CellList<MessageAudio> souvenirList;

	// private Souvenir actuInfo2;

	public MessagesAudioManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		btnAdd.setText("Add");
		btnRemove.setText("Remove");
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

	@UiHandler("btnRemove")
	void handleRemoveButtonClick(ClickEvent e) {
		// removeActu();
		Window.alert("A venir");
	}

	@UiHandler("btnUpdate")
	void handleUpdateButtonClick(ClickEvent e) {
		checkFieldsNotEmpty();
		updateActu();
	}

	private void updateActu() {

		final String idNumber = idNumberBox.getText().trim();
		MessageAudio newActu = new MessageAudio(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText());
		newActu.setRefYoutube(refYoutubeBox.getText());
		newActu.setBlobKey(blobKey.getText().trim());
		newActu.setBlobKeyogg(this.blobKey1.getText().trim());
		newActu.setBlobKeywav(this.blobKey2.getText().trim());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+this.categories.getSelectedIndex()
				+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
		//this.setIndexCategory(this.categories.getSelectedIndex());

		AsyncCallback<MessageAudio> callback = new AsyncCallback<MessageAudio>() {
			public void onFailure(Throwable error) {
				// do something, when fail
			}

			public void onSuccess(MessageAudio result) {
				// when successful, do something, about UI
				displayAddActu(result);
			}
		};

		messagesAudioService.updateSouvenir(newActu, callback);
	}

	private void removeActu() {
		final String idNumber = idNumberBox.getText().trim();
		MessageAudio newSouvenir = new MessageAudio(idNumber);
		newSouvenir.setAppellation(this.appellationBox.getText());
		newSouvenir.setDescription(this.descriptionBox.getHTML());
		newSouvenir.setDate(this.dateSouvenirBox.getValue());
		newSouvenir.setRefYoutube(refYoutubeBox.getText());
		newSouvenir.setBlobKey(this.blobKey.getText());
		newSouvenir.setBlobKeyogg(this.blobKey1.getText());
		newSouvenir.setBlobKeywav(this.blobKey2.getText());
		//newSouvenir.setCategory(this.categories.getItemText(indexCategory));

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
		MessageAudio newActu = new MessageAudio(idNumber);
		newActu.setAppellation(this.appellationBox.getText().trim());
		newActu.setDescription(this.descriptionBox.getHTML().trim());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText().trim());
		newActu.setRefYoutube(refYoutubeBox.getText());
		newActu.setBlobKey(blobKey.getText().trim());
		newActu.setBlobKeyogg(blobKey1.getText().trim());
		newActu.setBlobKeywav(blobKey2.getText().trim());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		//newActu.setCategory(this.categories.getItemText(this.indexCategory));
		
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+Integer.toString(this.categories.getSelectedIndex())
				+" :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
		
		//this.categories.setItemSelected(this.indexCategory, true);
		//this.setIndexCategory(indexCategory);

		// (2) Create an asynchronous callback to handle the result.
		AsyncCallback<MessageAudio> callback = new AsyncCallback<MessageAudio>() {
			public void onFailure(Throwable error) {
				// do something, when fail
			}

			public void onSuccess(MessageAudio result) {
				// when successful, do something, about UI
				displayAddActu(result);
			}

		};

		// (3) Make the call. Control flow will continue immediately and later
		// 'callback' will be invoked when the RPC completes

		messagesAudioService.addSouvenir(newActu, callback);

	}

	private void displayAddActu(MessageAudio savedActu) {
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("actu Renvoyée:" + savedActu.getId() + ",appellation:"
				+ savedActu.getAppellation()+ ", category:"+savedActu.getCategory());

		this.setSouvenir(savedActu);

		// mise à jour le display de la liste

		List<MessageAudio> lactu = new ArrayList<MessageAudio>();
		lactu.add(savedActu);

		Range range = souvenirList.getVisibleRange();

		souvenirList.setRowData(range.getStart(), lactu);
		((CellList<MessageAudio>) souvenirList).redraw();

		// autre méthode :
		// actuList.setRowCount(result.size());
		// actuList.setRowData(0, result);

		//initializeBox();

	}

	private void initializeBox() {

		MessageAudio initActu = new MessageAudio("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		initActu.setRefYoutube("");
		initActu.setBlobKey("");
		initActu.setBlobKeyogg("");
		initActu.setBlobKeywav("");
		initActu.setCategory("toutes");

		setSouvenir(new MessageAudio(""));
		this.categories.setItemSelected(0, true);
		

	}

	public void setSouvenir(MessageAudio souvenir) {
		this.souvenirInfo = souvenir;
		// btnAdd.setEnabled(actu != null);
		// btnRemove.setEnabled(actu != null);
		// btnUpdate.setEnabled(actu != null);
		if (souvenir != null) {
			idNumberBox.setText(souvenir.getId());
			appellationBox.setText(souvenir.getAppellation());
			descriptionBox.setHTML(souvenir.getDescription());
			dateSouvenirBox.setValue(souvenir.getDate());
			this.albumRefPicasaBox.setText(souvenir.getAlbumRefPicasa());
			this.refYoutubeBox.setText(souvenir.getRefYoutube());
			this.blobKey.setText(souvenir.getBlobKey());
			this.blobKey1.setText(souvenir.getBlobKeyogg());
			this.blobKey2.setText(souvenir.getBlobKeywav());
			
			//newActu.setCategory(categories.getItemText(this.categories.getSelectedIndex()));
			//Window.alert("category:"+indexCategory+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
			//this.setIndexCategory(this.categories.getSelectedIndex());
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

	public void setDisplay(CellList<MessageAudio> souvenirList2) {
		this.souvenirList = souvenirList2;

	}

}
