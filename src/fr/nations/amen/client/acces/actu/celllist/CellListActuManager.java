package fr.nations.amen.client.acces.actu.celllist;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.acces.actu.celllist.celltypes.custom.ActuManagerCellWithUiBinder;
import fr.nations.amen.client.actupublic.celllist.dataproviders.AllDataAsyncDataProvider;
import fr.nations.amen.shared.actus.Actu;


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
public class CellListActuManager extends Composite {

	interface CellTypesUiBinder extends UiBinder<Widget, CellListActuManager> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);

	AllDataAsyncDataProvider dataProviderAsync;

	ListDataProvider<Actu> dataProviderList;

	//final int PAGE_SIZE =6;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;
	
	// Display Cells
	@UiField(provided = true)
	
	CellList<Actu> actuList;
		  @UiField
	  ActuManagerForm actuManagerForm;
	  
	public CellListActuManager() {
		
		actuList = new CellList<Actu>(new ActuManagerCellWithUiBinder());
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

		
		//actuList.getDisplayedItems()
		
		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(actuList);
		handleUpdates();

		createWithAsyncDataProvider();
		//createWithListDataProvider();

		actuList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		actuList.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	}

	private void applyOptions() {
		actuList.setPageSize(UniformDim.PAGE_ADMIN_SIZE);
	}

	private void createWithAsyncDataProvider() {
		dataProviderAsync = new AllDataAsyncDataProvider();
		dataProviderAsync.addDataDisplay(actuList);
		dataProviderAsync.updateRowCount(10, true);
		
		actuManagerForm.setDataProvider(dataProviderAsync);
		actuManagerForm.setDisplay(actuList);
		
	}

//	private void createWithListDataProvider() {
//		List<Actu> theList;
//
//		dataProviderList = new ListDataProvider<Actu>();
//		dataProviderList.addDataDisplay(actuList);
//		theList = dataProviderList.getList();
//		PsuedoDataSource.populate(theList, dataProviderList);
//		// Keep the size of the list up to date - easier to know that we are
//		// showing 51-100 of 2000 etc
//		actuList.setRowCount(theList.size(), true);
//	}

	private void handleUpdates() {
		actuList.setValueUpdater(new ValueUpdater<Actu>() {
			@Override
			public void update(Actu value) {
				Window.alert("Handling update on photo: " + value.getDescription());
			}
		});
	}

	private void setUpSelectionModel() {
		final SingleSelectionModel<Actu> selectionModel = new SingleSelectionModel<Actu>();
		actuList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
//						if (selectionModel.getSelectedSet().size() > 5) {
//							Window.alert("Cannot select more than 5 items");
//							selectionModel.setSelected(selectionModel
//									.getSelectedSet().iterator().next(), false);
//						}
						 actuManagerForm.setActu(selectionModel.getSelectedObject());
					}
				});
	}
}
