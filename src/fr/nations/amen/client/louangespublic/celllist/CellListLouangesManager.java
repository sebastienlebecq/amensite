package fr.nations.amen.client.louangespublic.celllist;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.louangespublic.celllist.celltypes.custom.LouangesCellWithUiBinder;
import fr.nations.amen.client.mvp.presenters.LouangesPresenter;
import fr.nations.amen.client.mvp.views.LouangesView;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;


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
public class CellListLouangesManager extends Composite {

	interface CellTypesUiBinder extends
			UiBinder<Widget, CellListLouangesManager> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);

	AsyncDataProvider<Louange> dataProviderAsync;

	//final int PAGE_SIZE = 8;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;
	@UiField
	ListBox categories;

	// Display Cells
	@UiField(provided = true)
	CellList<Louange> louangeCells;
	@UiField
	LouangesManagerForm louangeManagerForm;
	
	public LouangesManagerForm getLouangeManagerForm() {
		return louangeManagerForm;
	}

	public void setLouangeManagerForm(LouangesManagerForm louangeManagerForm) {
		this.louangeManagerForm = louangeManagerForm;
	}

	LouangesServiceAsync louangeService = GWT.create(LouangesService.class);

	private LouangesView louangeView;

	private LouangesCellWithUiBinder lcwb;

	public LouangesCellWithUiBinder getLcwb() {
		return lcwb;
	}

	private ChangeHandler chg;

	public ChangeHandler getChg() {
		return chg;
	}

	private SingleSelectionModel<Louange> selectionModel;
	 
	public SingleSelectionModel<Louange> getSelectionModel() {
		return selectionModel;
	}
	
	public CellListLouangesManager(LouangesView louangeView) {
		
		this.louangeView = louangeView;
		
		lcwb = new LouangesCellWithUiBinder();

		louangeCells = new CellList<Louange>(lcwb);
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

		// actuList.getDisplayedItems()

		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(louangeCells);
		handleUpdates();

		createWithAsyncDataProvider();

		louangeCells
				.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		louangeCells.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
		
		for (String cat : UniformDim.categoriesLouanges){
			categories.addItem(cat);
		}
		
		chg = new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				lcwb.setIndexSelected(UniformDim.OUTOFLIST);
				
				dataProviderAsync.removeDataDisplay(louangeCells);
				louangeCells.redraw();

				dataProviderAsync = new CatDataAsyncDataProvider("cat"+categories.getSelectedIndex());
				dataProviderAsync.addDataDisplay(louangeCells);
				dataProviderAsync.updateRowCount(10, true);

				louangeManagerForm.setDisplay(louangeCells);
				

				LouangesManagerForm details = CellListLouangesManager.this.getLouangeManagerForm();
				details.setSouvenir(null);
				
				louangeCells.redraw();
			}

		};
		
		categories.addChangeHandler(chg);
	}


	public CellList<Louange> getLouangeCells() {
		return louangeCells;
	}


	private void applyOptions() {
		louangeCells.setPageSize(UniformDim.PAGE_PUBLIC_SIZE);
	}

	private void createWithAsyncDataProvider() {
		//dataProviderAsync = new AllDataAsyncDataProvider();
		dataProviderAsync = new CatDataAsyncDataProvider("cat0");
		dataProviderAsync.addDataDisplay(louangeCells);
		
		
		louangeService.getCountMax(new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			public void onSuccess(Integer countMax) {
				
				dataProviderAsync.updateRowCount(countMax, true);

			}
		});
		
		//dataProviderAsync.updateRowCount(100, true);
		
		

		// souvenirManagerForm.setDataProvider(dataProviderAsync);
		louangeManagerForm.setDisplay(louangeCells);

	}

	private void handleUpdates() {
		louangeCells.setValueUpdater(new ValueUpdater<Louange>() {
			@Override
			public void update(Louange value) {
				Window.alert("Handling update on photo: "
						+ value.getDescription());
				dataProviderAsync.removeDataDisplay(louangeCells);
				louangeCells.redraw();
			}
		});
	}

	private Range range;
	
	private void setUpSelectionModel() {
		selectionModel = new SingleSelectionModel<Louange>();
		louangeCells.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
				

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
	

						
						 AsyncCallback<Louange> callback  = new AsyncCallback<Louange>() {
								public void onFailure(Throwable error) {
									//do something, when fail
								}

								public void onSuccess(Louange result) {

									range = CellListLouangesManager.this.getLouangeCells().getVisibleRange();

									LouangesPresenter pres = louangeView.getPresenter();
									
									
									
									AsyncCallback<Louange> callback2  = new AsyncCallback<Louange>() {
										public void onFailure(Throwable error) {
											Window.alert("erreur : index non mis à jour!!");
										}

										@Override
										public void onSuccess(Louange result) {
											
											//Window.alert("index: "+result.getIndexInCells());
										}

									};
									
									
									louangeService.registerIndexCellTable(selectionModel.getSelectedObject().getId(), 
											new Integer(range.getStart()).toString(), callback2);
									
									pres.louangeSelected(result, CellListLouangesManager.this, range, "cat"+categories.getSelectedIndex());
									
									//Window.alert("Sois beni Heureux Internaute, " +
									//		"tu es le "+result.getNbPagesVues()+"eme visiteur de cette page!");

								}

							};
							
						

							
						louangeService.incrementNbPagesVues(selectionModel.getSelectedObject().getId(), callback);
					}
				});
	}
	
	
}
