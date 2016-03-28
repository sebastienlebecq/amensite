package fr.nations.amen.client.acces.actu.celllist;

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
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.actupublic.celllist.dataproviders.AllDataAsyncDataProvider;
import fr.nations.amen.server.actus.ActuEntry;
import fr.nations.amen.shared.actus.Actu;
import fr.nations.amen.shared.actus.ActualitesService;
import fr.nations.amen.shared.actus.ActualitesServiceAsync;

public class ActuManagerForm extends Composite {
	
	private ArrayList<String> idArray = new ArrayList<String>();
	ActualitesServiceAsync actuService = GWT.create(ActualitesService.class);
	
	private static ActuManagerFormUiBinder uiBinder = GWT
			.create(ActuManagerFormUiBinder.class);

	interface ActuManagerFormUiBinder extends UiBinder<Widget, ActuManagerForm> {
	}

	public ActuManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_LONG);
		dateActuBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		// Initialize the contact to null.
		setActu(null);
	}

	@UiField
	TextBox idNumberBox;

	@UiField
	TextBox appellationBox;

	@UiField
	RichTextArea  descriptionBox;

	@UiField
	DateBox dateActuBox;

	// @UiField
	// Image imgBox;

	@UiField
	Button btnAdd;

	@UiField
	Button btnRemove;

	@UiField
	Button btnUpdate;

	private Actu actuInfo;
	private AllDataAsyncDataProvider dataProvider;
	private CellList<Actu> actuList;
	private Actu actuInfo2;

	public ActuManagerForm(String firstName) {
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
		if (dateActuBox.getTextBox().getText().equals("")) {
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
		//removeActu();
		Window.alert("A venir");
	}
	
	@UiHandler("btnUpdate")
	void handleUpdateButtonClick(ClickEvent e) {
		checkFieldsNotEmpty();
		updateActu();
	}
	
	private void updateActu() {
		final String idNumber = idNumberBox.getText().trim();
		Actu newActu = new Actu(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateActuBox.getValue());
		
		 AsyncCallback<Actu> callback  = new AsyncCallback<Actu>() {
				public void onFailure(Throwable error) {
					//do something, when fail
				}

				public void onSuccess(Actu result) {
					//when successful, do something, about UI
					displayAddActu(result);
				}
			};
			
			actuService.updateActu(newActu, callback);
	}

	private void removeActu() {
		final String idNumber = idNumberBox.getText().trim();
		Actu newActu = new Actu(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateActuBox.getValue());
		
		AsyncCallback<Void> callback  = new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
				//do something, when fail
			}

			public void onSuccess(Void result) {
				//when successful, do something, about UI
				displayRemoveActu();
			}

		};

		actuService.remove(newActu, callback);

	}

	protected void displayRemoveActu() {
		Window.alert("actualité effacée!!");
		
	}

	private void addActu() {
		
		final String idNumber = idNumberBox.getText().trim();
		Actu newActu = new Actu(idNumber);
		newActu.setAppellation(this.appellationBox.getText());
		newActu.setDescription(this.descriptionBox.getHTML());
		newActu.setDate(this.dateActuBox.getValue());

//		if (idArray.contains(idNumber))
//			return;

		// (2) Create an asynchronous callback to handle the result.
				 AsyncCallback<Actu> callback  = new AsyncCallback<Actu>() {
					public void onFailure(Throwable error) {
						//do something, when fail
					}

					public void onSuccess(Actu result) {
						//when successful, do something, about UI
						displayAddActu(result);
					}

				};

				// (3) Make the call. Control flow will continue immediately and later
				// 'callback' will be invoked when the RPC completes
				
				actuService.addActu(newActu, callback);
		
	}

	private void displayAddActu(Actu savedActu) {
		
		Window.alert("actu envoyée:"+savedActu.getId()+",appellation:"+savedActu.getAppellation());
		
		this.setActu(savedActu);
		
		// mise à jour le display de la liste
		
			List<Actu> lactu = new ArrayList<Actu>();
			lactu.add(savedActu);
		
			Range range = actuList.getVisibleRange();
			
			actuList.setRowData(range.getStart(), lactu);
			   ((CellList<Actu>) actuList).redraw();
			   
			   //autre méthode :
			  // actuList.setRowCount(result.size());
			 //  actuList.setRowData(0, result);
			   
			   initializeBox();

	}

	private void initializeBox() {
		
		Actu initActu = new Actu("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		setActu(new Actu(""));
		
		
	}

	public void setActu(Actu actu) {
		this.actuInfo = actu;
//		btnAdd.setEnabled(actu != null);
//		btnRemove.setEnabled(actu != null);
//		btnUpdate.setEnabled(actu != null);
		if (actu != null) {
			idNumberBox.setText(actu.getId());
			appellationBox.setText(actu.getAppellation());
			descriptionBox.setHTML(actu.getDescription());
			dateActuBox.setValue(actu.getDate());
		}
	}


	public void setDataProvider(AllDataAsyncDataProvider dataProviderAsync) {
		this.dataProvider =dataProviderAsync; 
		
	}

	public void setDisplay(CellList<Actu> actuList) {
		this.actuList = actuList;
		
	}
}
