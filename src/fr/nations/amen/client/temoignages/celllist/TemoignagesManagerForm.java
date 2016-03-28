package fr.nations.amen.client.temoignages.celllist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.UniformDim.MODE;
import fr.nations.amen.shared.temoignages.Temoignage;
import fr.nations.amen.shared.temoignages.TemoignagesService;
import fr.nations.amen.shared.temoignages.TemoignagesServiceAsync;

public class TemoignagesManagerForm extends Composite {
	
	//private ArrayList<String> idArray = new ArrayList<String>();
	TemoignagesServiceAsync temoignagesService = GWT.create(TemoignagesService.class);
	
	private static TemoignagesManagerFormUiBinder uiBinder = GWT
			.create(TemoignagesManagerFormUiBinder.class);

	interface TemoignagesManagerFormUiBinder extends UiBinder<Widget, TemoignagesManagerForm> {
	}

	public TemoignagesManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_LONG);
		dateSouvenirBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setSouvenir(null);
		for (String cat : UniformDim.categoriesTextes){
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

	private Temoignage souvenirInfo;
	//private AllDataAsyncDataProvider dataProvider;
	private CellList<Temoignage> temoignageList;
	//private Souvenir actuInfo2;

	public TemoignagesManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		btnAdd.setText("Add");
		//btnRemove.setText("Remove");
		btnUpdate.setText("Update");
	}

	@UiHandler("btnAdd")
	void handleAddButtonClick(ClickEvent e) {
		addActu();
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
		Temoignage newActu = new Temoignage(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+this.categories.getSelectedIndex()
				+", str :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
		 AsyncCallback<Temoignage> callback  = new AsyncCallback<Temoignage>() {
				public void onFailure(Throwable error) {
					//do something, when fail
				}

				public void onSuccess(Temoignage result) {
					//when successful, do something, about UI
					displayAddActu(result);
				}
			};
			
			temoignagesService.updateTemoignage(newActu, callback);
	}

	private void removeActu() {
		final String idNumber = idNumberBox.getText().trim();
		Temoignage newSouvenir = new Temoignage(idNumber);
		newSouvenir.setAppellation(this.appellationBox.getText());
		newSouvenir.setDescription(this.descriptionBox.getHTML());
		newSouvenir.setDate(this.dateSouvenirBox.getValue());
		
		AsyncCallback<Void> callback  = new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
				//do something, when fail
			}

			public void onSuccess(Void result) {
				//when successful, do something, about UI
				displayRemoveActu();
			}

		};

		temoignagesService.remove(newSouvenir, callback);

	}

	protected void displayRemoveActu() {
		Window.alert("actualité effacée!!");
		
	}

	private void addActu() {
		
		final String idNumber = idNumberBox.getText().trim();
		Temoignage newActu = new Temoignage(idNumber);
		newActu.setAppellation(this.appellationBox.getText().trim());
		newActu.setDescription(this.descriptionBox.getHTML().trim());
		newActu.setDate(this.dateSouvenirBox.getValue());
		newActu.setAlbumRefPicasa(this.albumRefPicasaBox.getText().trim());
		newActu.setCategory("cat"+this.categories.getSelectedIndex());
		//newActu.setCategory(this.categories.getItemText(this.indexCategory));
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("category:"+Integer.toString(this.categories.getSelectedIndex())
				+" :"+this.categories.getItemText(this.categories.getSelectedIndex()));
		
//		if (idArray.contains(idNumber))
//			return;

		// (2) Create an asynchronous callback to handle the result.
				 AsyncCallback<Temoignage> callback  = new AsyncCallback<Temoignage>() {
					public void onFailure(Throwable error) {
						//do something, when fail
					}

					public void onSuccess(Temoignage result) {
						//when successful, do something, about UI
						displayAddActu(result);
					}

				};

				// (3) Make the call. Control flow will continue immediately and later
				// 'callback' will be invoked when the RPC completes
				
				temoignagesService.addTemoignage(newActu, callback);
		
	}

	private void displayAddActu(Temoignage savedActu) {
		if (UniformDim.mode == MODE.DEBUG)
		Window.alert("actu envoyée:"+savedActu.getId()+",appellation:"+savedActu.getAppellation());
		
		this.setSouvenir(savedActu);
		
		// mise à jour le display de la liste
		
			List<Temoignage> lactu = new ArrayList<Temoignage>();
			lactu.add(savedActu);
		
			Range range = temoignageList.getVisibleRange();
			
			temoignageList.setRowData(range.getStart(), lactu);
			   ((CellList<Temoignage>) temoignageList).redraw();
			   
			   //autre méthode :
			  // actuList.setRowCount(result.size());
			 //  actuList.setRowData(0, result);
			   
			   initializeBox();

	}

	private void initializeBox() {
		
		Temoignage initActu = new Temoignage("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		setSouvenir(new Temoignage(""));
		
		
	}

	public void setSouvenir(Temoignage souvenir) {
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
		//	Window.alert("souvenir.getCategory():"+souvenir.getCategory()+",index:"+Integer.valueOf(souvenir.getCategory().substring(3)));
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

	public void setDisplay(CellList<Temoignage> actuList) {
		this.temoignageList = actuList;
		
	}
}
