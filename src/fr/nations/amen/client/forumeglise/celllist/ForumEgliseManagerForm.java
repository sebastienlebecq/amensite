package fr.nations.amen.client.forumeglise.celllist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
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
import fr.nations.amen.shared.forumeglise.ForumEglise;
import fr.nations.amen.shared.forumeglise.ForumEgliseService;
import fr.nations.amen.shared.forumeglise.ForumEgliseServiceAsync;

public class ForumEgliseManagerForm extends Composite {
	
	//private ArrayList<String> idArray = new ArrayList<String>();
	ForumEgliseServiceAsync souvenirService = GWT.create(ForumEgliseService.class);
	
	private static SouvenirManagerFormUiBinder uiBinder = GWT
			.create(SouvenirManagerFormUiBinder.class);

	interface SouvenirManagerFormUiBinder extends UiBinder<Widget, ForumEgliseManagerForm> {
	}

	public ForumEgliseManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));
		
		uploadImg.setName("uploadImg");
		blobKeyImg.setName("blobKeyImg");
		
		docForm2.setEncoding(FormPanel.ENCODING_MULTIPART);
		docForm2.setMethod(FormPanel.METHOD_POST);
		
		docForm2.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				System.out.println("result:" + event.getResults());

				//String[] ids = event.getResults().split("/");

				blobKeyImg.setText(event.getResults());
			}

		});
		
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
		
		
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_LONG);
		dateSouvenirBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setSouvenir(null);
		
//		for (String cat : UniformDim.categoriesSouvenirs){
//			categories.addItem(cat);
//		}
	}
	
//	@UiField
//	ListBox categories;

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

//	@UiField
//	Button btnRemove;

	@UiField
	Button btnUpdate;

	@UiField
	FileUpload uploadImg;
	
	@UiField
	TextBox blobKeyImg;
	
	@UiField
	FormPanel docForm2;
	
	@UiField
	SubmitButton btnSubmit2;
	
	private ForumEglise souvenirInfo;
	//private AllDataAsyncDataProvider dataProvider;
	private CellList<ForumEglise> souvenirList;
	//private Souvenir actuInfo2;

	public ForumEgliseManagerForm(String firstName) {
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
	
	@UiHandler("btnUpdate")
	void handleUpdateButtonClick(ClickEvent e) {
		updateActu();
	}
	
	private void updateActu() {
		final String idNumber = idNumberBox.getText().trim();
		ForumEglise newActu = new ForumEglise(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText());
		newActu.setRefYoutube(refYoutubeBox.getText());
		//newActu.setCategory("cat"+this.categories.getSelectedIndex());
		newActu.setThubnailUrl(this.blobKeyImg.getText().trim());
		//if (UniformDim.mode == MODE.DEBUG)
//		Window.alert("category:"+this.categories.getSelectedIndex()
//				+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
		 AsyncCallback<ForumEglise> callback  = new AsyncCallback<ForumEglise>() {
				public void onFailure(Throwable error) {
					//do something, when fail
				}

				public void onSuccess(ForumEglise result) {
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
		ForumEglise newSouvenir = new ForumEglise(idNumber);
		newSouvenir.setAppellation(this.appellationBox.getText());
		newSouvenir.setDescription(this.descriptionBox.getHTML());
		newSouvenir.setDate(this.dateSouvenirBox.getValue());
		newSouvenir.setRefYoutube(refYoutubeBox.getText());
		
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
		ForumEglise newActu = new ForumEglise(idNumber);
		newActu.setAppellation(this.appellationBox.getText().trim());
		newActu.setDescription(this.descriptionBox.getHTML().trim());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText().trim());
		newActu.setRefYoutube(refYoutubeBox.getText());
		newActu.setThubnailUrl(this.blobKeyImg.getText().trim());
	//	newActu.setCategory("cat"+this.categories.getSelectedIndex());
		//newActu.setCategory(this.categories.getItemText(this.indexCategory));
//		if (UniformDim.mode == MODE.DEBUG)
//		Window.alert("category:"+Integer.toString(this.categories.getSelectedIndex())
//				+" :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
//		if (idArray.contains(idNumber))
//			return;

		// (2) Create an asynchronous callback to handle the result.
				 AsyncCallback<ForumEglise> callback  = new AsyncCallback<ForumEglise>() {
					public void onFailure(Throwable error) {
						//do something, when fail
					}

					public void onSuccess(ForumEglise result) {
						//when successful, do something, about UI
						Window.alert("ajout réussi!!");
						displayAddActu(result);
					}

				};

				// (3) Make the call. Control flow will continue immediately and later
				// 'callback' will be invoked when the RPC completes
				
				souvenirService.addSouvenir(newActu, callback);
		
	}

	private void displayAddActu(ForumEglise savedActu) {
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("actu envoyée:"+savedActu.getId()+",appellation:"+savedActu.getAppellation());
		
		this.setSouvenir(savedActu);
		
		// mise à jour le display de la liste
		
			List<ForumEglise> lactu = new ArrayList<ForumEglise>();
			lactu.add(savedActu);
		
			Range range = souvenirList.getVisibleRange();
			
			souvenirList.setRowData(range.getStart(), lactu);
			   ((CellList<ForumEglise>) souvenirList).redraw();
			   
			   //autre méthode :
			  // actuList.setRowCount(result.size());
			 //  actuList.setRowData(0, result);
			   
			  // initializeBox();

	}

	private void initializeBox() {
		
		ForumEglise initActu = new ForumEglise("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		initActu.setRefYoutube("");
		setSouvenir(new ForumEglise(""));
		
		
	}

	public void setSouvenir(ForumEglise souvenir) {
		this.souvenirInfo = souvenir;
//		btnAdd.setEnabled(actu != null);
//		btnRemove.setEnabled(actu != null);
//		btnUpdate.setEnabled(actu != null);
		if (souvenir != null) {
			idNumberBox.setText(souvenir.getId());
			appellationBox.setText(souvenir.getAppellation());
			descriptionBox.setHTML(souvenir.getDescription());
			dateSouvenirBox.setValue(souvenir.getDate());
			this.albumRefPicasaBox.setText(souvenir.getAlbumRefPicasa());
			this.refYoutubeBox.setText(souvenir.getRefYoutube());
			this.blobKeyImg.setText(souvenir.getThubnailUrl());
			if (UniformDim.mode == MODE.DEBUG)
			Window.alert("souvenir.getCategory():"+souvenir.getCategory()+",index:"+Integer.valueOf(souvenir.getCategory().substring(3)));
			//int newIndex =getIndexOf(souvenir.getCategory());
			if (souvenir.getCategory()!=null){
				if (UniformDim.mode == MODE.DEBUG)
				Window.alert("souvenir.getCategory():"+souvenir.getCategory()+",index:"+Integer.valueOf(souvenir.getCategory().substring(3)));
				//this.categories.setItemSelected(new Integer(Integer.valueOf(souvenir.getCategory().substring(3))), true);

			}
		}
	}


//	public void setDataProvider(AllDataAsyncDataProvider dataProviderAsync) {
//		this.dataProvider =dataProviderAsync; 
//		
//	}

	public void setDisplay(CellList<ForumEglise> actuList) {
		this.souvenirList = actuList;
		
	}
}
