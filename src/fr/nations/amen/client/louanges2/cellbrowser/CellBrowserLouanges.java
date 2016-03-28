package fr.nations.amen.client.louanges2.cellbrowser;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.PageSizePager;
import com.google.gwt.user.cellview.client.CellBrowser.Builder;
import com.google.gwt.user.cellview.client.CellBrowser.PagerFactory;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import fr.nations.amen.client.louanges2.cellwidget.common.Years;
import fr.nations.amen.client.louanges2.treeviewmodel.AsyncTreeViewModel;
import fr.nations.amen.client.louanges2.treeviewmodel.ListTreeViewModel;
import fr.nations.amen.client.souvenirspublic.celllist.SouvenirManagerForm;
import fr.nations.amen.client.souvenirspublic.celllist.celltypes.custom.SouvenirCellWithUiBinder;
import fr.nations.amen.client.souvenirspublic.celllist.dataproviders.AllDataAsyncDataProvider;
import fr.nations.amen.shared.souvenirs.Souvenir;


public class CellBrowserLouanges extends Composite {

	interface CellBrowserUiBinder extends UiBinder<Widget, CellBrowserLouanges> {}
	private static CellBrowserUiBinder uiBinder = GWT.create(CellBrowserUiBinder.class);

	AllDataAsyncDataProvider dataProviderAsync;
	
	final int PAGE_SIZE =6;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;
	
	// Display Cells
	@UiField(provided = true)
	
	CellList<Souvenir> souvenirList;

	

	public CellBrowserLouanges() {

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
		souvenirList.setPageSize(PAGE_SIZE);
	}

	private void createWithAsyncDataProvider() {
		dataProviderAsync = new AllDataAsyncDataProvider();
		dataProviderAsync.addDataDisplay(souvenirList);
		dataProviderAsync.updateRowCount(10, true);
		
		//souvenirManagerForm.setDataProvider(dataProviderAsync);

		
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
					
					}
				});
	}
}
