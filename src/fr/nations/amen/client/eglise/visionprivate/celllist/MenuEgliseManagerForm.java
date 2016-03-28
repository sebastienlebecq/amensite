package fr.nations.amen.client.eglise.visionprivate.celllist;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.UniformDim.MODE;
import fr.nations.amen.shared.eglise.MenuEglise;
import fr.nations.amen.shared.eglise.MenuEgliseService;
import fr.nations.amen.shared.eglise.MenuEgliseServiceAsync;

public class MenuEgliseManagerForm extends Composite {
	
	//private ArrayList<String> idArray = new ArrayList<String>();
	MenuEgliseServiceAsync souvenirService = GWT.create(MenuEgliseService.class);
	
	private static SouvenirManagerFormUiBinder uiBinder = GWT
			.create(SouvenirManagerFormUiBinder.class);

	interface SouvenirManagerFormUiBinder extends UiBinder<Widget, MenuEgliseManagerForm> {
	}

	public MenuEgliseManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_LONG);
		dateSouvenirBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setSouvenir(null);
		
//		for (String cat : UniformDim.categoriesSouvenirs){
//			categories.addItem(cat);
//		}
		
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
		
		btnAddVideoRefYoutube.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!pathVideoRefYoutubeToAdd.getText().isEmpty()) {
					pathRefYoutubeBox.addItem(pathRefYoutubeBox
							.getItemCount() + ":" + pathVideoRefYoutubeToAdd.getText());
				}
			}
		});
		
		
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
	Button btnAdd;

//	@UiField
//	Button btnRemove;

	@UiField
	Button btnUpdate;

	
	@UiField
	Button btnAddUrlPdf;
	
	@UiField
	ListBox listUrlPdfBox;
	
	@UiField
	TextBox pathUrlPdfToAdd;
	
	@UiField
	Button btnAddVideoRefYoutube;
	
	@UiField
	ListBox pathRefYoutubeBox;
	
	@UiField
	TextBox pathVideoRefYoutubeToAdd;

	private MenuEglise souvenirInfo;
	//private AllDataAsyncDataProvider dataProvider;
	private CellList<MenuEglise> souvenirList;
	//private Souvenir actuInfo2;

	public MenuEgliseManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		btnAdd.setText("Add");
//		btnRemove.setText("Remove");
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
	
//	@UiHandler("btnRemove")
//	void handleRemoveButtonClick(ClickEvent e) {
//		//removeActu();
//		Window.alert("A venir");
//	}
	
	@UiHandler("btnUpdate")
	void handleUpdateButtonClick(ClickEvent e) {
		updateActu();
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
	
	
	private void updateActu() {
		final String idNumber = idNumberBox.getText().trim();
		MenuEglise newActu = new MenuEglise(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText());
		//newActu.setRefYoutube(refYoutubeBox.getText());
		//newActu.setCategory("cat"+this.categories.getSelectedIndex());
		//newActu.setUrlpdf(this.urlPdfBox.getText().trim());

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
		
//		if (UniformDim.mode == MODE.DEBUG)
//		Window.alert("category:"+this.categories.getSelectedIndex()
//				+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
		 AsyncCallback<MenuEglise> callback  = new AsyncCallback<MenuEglise>() {
				public void onFailure(Throwable error) {
					//do something, when fail
				}

				public void onSuccess(MenuEglise result) {
					//when successful, do something, about UI
					displayAddActu(result);
				}
			};
			
			souvenirService.updateMenuEglise(newActu, callback);
	}

	private void removeActu() {
		final String idNumber = idNumberBox.getText().trim();
		MenuEglise newSouvenir = new MenuEglise(idNumber);
		newSouvenir.setAppellation(this.appellationBox.getText());
		newSouvenir.setDescription(this.descriptionBox.getHTML());
		newSouvenir.setDate(this.dateSouvenirBox.getValue());
		//newSouvenir.setRefYoutube(refYoutubeBox.getText());
		
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
		MenuEglise newActu = new MenuEglise(idNumber);
		newActu.setAppellation(this.appellationBox.getText().trim());
		newActu.setDescription(this.descriptionBox.getHTML().trim());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText().trim());
		//newActu.setRefYoutube(refYoutubeBox.getText());
		//newActu.setCategory("cat"+this.categories.getSelectedIndex());
		//newActu.setUrlpdf(this.urlPdfBox.getText());
		
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
		
		//newActu.setCategory(this.categories.getItemText(this.indexCategory));
//		if (UniformDim.mode == MODE.DEBUG)
//		Window.alert("category:"+Integer.toString(this.categories.getSelectedIndex())
//				+" :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
//		if (idArray.contains(idNumber))
//			return;

		// (2) Create an asynchronous callback to handle the result.
				 AsyncCallback<MenuEglise> callback  = new AsyncCallback<MenuEglise>() {
					public void onFailure(Throwable error) {
						//do something, when fail
					}

					public void onSuccess(MenuEglise result) {
						//when successful, do something, about UI
						displayAddActu(result);
					}

				};

				// (3) Make the call. Control flow will continue immediately and later
				// 'callback' will be invoked when the RPC completes
				
				souvenirService.addMenuEglise(newActu, callback);
		
	}

	private void displayAddActu(MenuEglise savedActu) {
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("actu envoyée:"+savedActu.getId()+",appellation:"+savedActu.getAppellation());
		
		this.setSouvenir(savedActu);
		
		// mise à jour le display de la liste
		
			List<MenuEglise> lactu = new ArrayList<MenuEglise>();
			lactu.add(savedActu);
		
			Range range = souvenirList.getVisibleRange();
			
			souvenirList.setRowData(range.getStart(), lactu);
			   ((CellList<MenuEglise>) souvenirList).redraw();
			   
			   //autre méthode :
			  // actuList.setRowCount(result.size());
			 //  actuList.setRowData(0, result);
			   
			  // initializeBox();

	}

	private void initializeBox() {
		
		MenuEglise initActu = new MenuEglise("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		initActu.setRefYoutube("");
		setSouvenir(new MenuEglise(""));
		
		
	}

	public void setSouvenir(MenuEglise souvenir) {
		this.souvenirInfo = souvenir;
		listUrlPdfBox.clear();
		pathRefYoutubeBox.clear();
		
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
			//this.urlPdfBox.setText(souvenir.getUrlpdf());
			
			if (souvenir.getUrlpdf()!=null && souvenir.getUrlpdf().length()>1){
				
				String[] videosBefore = souvenir.getUrlpdf().split(";");
				listUrlPdfBox.clear();

				for (int i = 0; i < videosBefore.length; i++) {
					listUrlPdfBox.addItem(i + ":" + videosBefore[i]);
				}
				} else {
					listUrlPdfBox.clear();
				}		
			
			
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

	public void setDisplay(CellList<MenuEglise> actuList) {
		this.souvenirList = actuList;
		
	}
}
