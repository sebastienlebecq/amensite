package fr.nations.amen.client.souvenirs.celllist;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.PageSizePager;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.souvenirs.celllist.celltypes.custom.SouvenirCellWithUiBinder;
import fr.nations.amen.client.souvenirs.celllist.dataproviders.AllDataAsyncDataProvider;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.souvenirs.SouvenirsService;
import fr.nations.amen.shared.souvenirs.SouvenirsServiceAsync;


/**
 * GWT in Action 2nd Edition . example of a CellList widget.
 * 
 * Shows how to use a CellList widget using custom built PhotoDetails cells, as
 * well as application of 2 basic pagers.
 * 
 * You can alter page size by varying the PAGE_SIZE variable
 * 
 *
 */
public class CellListSouvenirManager extends Composite {

	SouvenirsServiceAsync souvenirService = GWT.create(SouvenirsService.class);
	
	interface CellTypesUiBinder extends UiBinder<Widget, CellListSouvenirManager> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);

	AllDataAsyncDataProvider dataProviderAsync;

	ListDataProvider<Souvenir> dataProviderList;

	//final int PAGE_SIZE =6;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;
	
	// Display Cells
	@UiField(provided = true)
	
	CellList<Souvenir> souvenirList;
		  @UiField
	  SouvenirManagerForm souvenirManagerForm;
	  
	public CellListSouvenirManager() {
		
		souvenirList = new CellList<Souvenir>(new SouvenirCellWithUiBinder());
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

		
		//actuList.getDisplayedItems()
		
		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(souvenirList);
		handleUpdates();

		createWithAsyncDataProvider();
		//createWithListDataProvider();

		souvenirList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		souvenirList.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	}

	private void applyOptions() {
		souvenirList.setPageSize(UniformDim.PAGE_ADMIN_SIZE);
	}

	private void createWithAsyncDataProvider() {
		dataProviderAsync = new AllDataAsyncDataProvider();
		dataProviderAsync.addDataDisplay(souvenirList);
		
		souvenirService.getCountMax(new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			public void onSuccess(Integer countMax) {
				
				dataProviderAsync.updateRowCount(countMax, true);

			}
		});
		
		//dataProviderAsync.updateRowCount(10, true);
		
		//souvenirManagerForm.setDataProvider(dataProviderAsync);
		souvenirManagerForm.setDisplay(souvenirList);
		
	}

//	private void createWithListDataProvider() {
//		List<Souvenir> theList;
//
//		dataProviderList = new ListDataProvider<Souvenir>();
//		dataProviderList.addDataDisplay(souvenirList);
//		theList = dataProviderList.getList();
//		PsuedoDataSource.populate(theList, dataProviderList);
//		// Keep the size of the list up to date - easier to know that we are
//		// showing 51-100 of 2000 etc
//		souvenirList.setRowCount(theList.size(), true);
//	}

	private void handleUpdates() {
		souvenirList.setValueUpdater(new ValueUpdater<Souvenir>() {
			@Override
			public void update(Souvenir value) {
				Window.alert("Handling update on photo: " + value.getDescription());
			}
		});
	}

	private void setUpSelectionModel() {
		final SingleSelectionModel<Souvenir> selectionModel = new SingleSelectionModel<Souvenir>();
		souvenirList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
//						if (selectionModel.getSelectedSet().size() > 5) {
//							Window.alert("Cannot select more than 5 items");
//							selectionModel.setSelected(selectionModel
//									.getSelectedSet().iterator().next(), false);
//						}
						 souvenirManagerForm.setSouvenir(selectionModel.getSelectedObject());
					}
				});
	}
}
