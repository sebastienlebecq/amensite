package fr.nations.amen.client.temoignagespublic.celllist;

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
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.presenters.TemoignagesPresenter;
import fr.nations.amen.client.mvp.views.TemoignagesView;
import fr.nations.amen.client.temoignagespublic.celllist.celltypes.custom.TemoignageCellWithUiBinder;
import fr.nations.amen.client.temoignagespublic.celllist.dataproviders.CatDataAsyncDataProvider;
import fr.nations.amen.shared.temoignages.Temoignage;
import fr.nations.amen.shared.temoignages.TemoignagesService;
import fr.nations.amen.shared.temoignages.TemoignagesServiceAsync;



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
public class CellListTemoignagesManager extends Composite {
	

	interface CellTypesUiBinder extends UiBinder<Widget, CellListTemoignagesManager> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);

	CatDataAsyncDataProvider dataProviderAsync;

	ListDataProvider<Temoignage> dataProviderList;

	//final int PAGE_SIZE =6;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;
	
	// Display Cells
	@UiField(provided = true)
	
	CellList<Temoignage> temoignageList;
		  public CellList<Temoignage> getTemoignageList() {
		return temoignageList;
	}

	public void setTemoignageList(CellList<Temoignage> temoignageList) {
		this.temoignageList = temoignageList;
	}

		@UiField
	  TemoignagesManagerForm temoignagesManagerForm;
			public TemoignagesManagerForm getTemoignagesManagerForm() {
			return temoignagesManagerForm;
		}

		public void setTemoignagesManagerForm(
				TemoignagesManagerForm temoignagesManagerForm) {
			this.temoignagesManagerForm = temoignagesManagerForm;
		}

			@UiField
			ListBox categories;
			
	TemoignagesServiceAsync temoignageService = GWT.create(TemoignagesService.class);

	private TemoignagesView temoignageView;

	private ChangeHandler chg;

	private SingleSelectionModel<Temoignage> selectionModel;

	private TemoignageCellWithUiBinder tcwub;

	public TemoignageCellWithUiBinder getTcwub() {
		return tcwub;
	}

	public ChangeHandler getChg() {
		return chg;
	}
			
	public CellListTemoignagesManager(TemoignagesView temoignageView) {
		
		tcwub = new TemoignageCellWithUiBinder();
		
		temoignageList = new CellList<Temoignage>(tcwub);
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

		this.temoignageView = temoignageView;
		
		//actuList.getDisplayedItems()
		
		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(temoignageList);
		handleUpdates();
		for (String cat : UniformDim.categoriesTextes){
			categories.addItem(cat);
		}
		createWithAsyncDataProvider();
		//createWithListDataProvider();

		temoignageList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		temoignageList.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
		
		chg = new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				dataProviderAsync.removeDataDisplay(temoignageList);
				temoignageList.redraw();

				dataProviderAsync = new CatDataAsyncDataProvider("cat"+categories.getSelectedIndex());
				dataProviderAsync.addDataDisplay(temoignageList);
				
				
				temoignageService.getCountMax(new AsyncCallback<Integer>() {

						public void onFailure(Throwable caught) {
							Window.alert("Error" + caught.getMessage());
						}

						public void onSuccess(Integer countMax) {
							
							dataProviderAsync.updateRowCount(countMax, true);

						}
					});
				
				//dataProviderAsync.updateRowCount(10, true);

				temoignagesManagerForm.setDisplay(temoignageList);
				temoignageList.redraw();
			}
		};

		categories.addChangeHandler(chg);
	}

	public TemoignagesView getTemoignageView() {
		return temoignageView;
	}

	public void setTemoignageView(TemoignagesView temoignageView) {
		this.temoignageView = temoignageView;
	}

	private void applyOptions() {
		temoignageList.setPageSize(UniformDim.PAGE_PUBLIC_SIZE);
	}

	private void createWithAsyncDataProvider() {
		//dataProviderAsync = new CatDataAsyncDataProvider(null);
		dataProviderAsync = new CatDataAsyncDataProvider("cat"+categories.getSelectedIndex());
		dataProviderAsync.addDataDisplay(temoignageList);
		
		
		temoignageService.getCountMax(new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			public void onSuccess(Integer countMax) {
				
				dataProviderAsync.updateRowCount(countMax, true);

			}
		});
		
		
		//dataProviderAsync.updateRowCount(10, true);
		
		//souvenirManagerForm.setDataProvider(dataProviderAsync);
		temoignagesManagerForm.setDisplay(temoignageList);
		
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
		temoignageList.setValueUpdater(new ValueUpdater<Temoignage>() {
			@Override
			public void update(Temoignage value) {
				Window.alert("Handling update on photo: " + value.getDescription());
			}
		});
	}
	private Range range;
	
	private void setUpSelectionModel() {
		selectionModel = new SingleSelectionModel<Temoignage>();
		temoignageList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
//						if (selectionModel.getSelectedSet().size() > 5) {
//							Window.alert("Cannot select more than 5 items");
//							selectionModel.setSelected(selectionModel
//									.getSelectedSet().iterator().next(), false);
//						}
						//temoignagesManagerForm.setTemoignage(selectionModel.getSelectedObject());
						
						 AsyncCallback<Temoignage> callback  = new AsyncCallback<Temoignage>() {
							

								public void onFailure(Throwable error) {
									Window.alert("lajax s'est mal passé:"+error.getMessage()+", cause: "+error.getCause());
								}

								public void onSuccess(Temoignage result) {
									range = CellListTemoignagesManager.this.getTemoignageList().getVisibleRange();

									TemoignagesPresenter pres = temoignageView.getPresenter();
									
									
									
									AsyncCallback<Temoignage> callback2  = new AsyncCallback<Temoignage>() {
										public void onFailure(Throwable error) {
											Window.alert("erreur : index non mis à jour!!");
										}

										@Override
										public void onSuccess(Temoignage result) {
											
											//Window.alert("index: "+result.getIndexInCells());
										}

									};
									
									
									temoignageService.registerIndexCellTable(selectionModel.getSelectedObject().getId(), 
											new Integer(range.getStart()).toString(), callback2);
									
									pres.temoignageSelected(result, CellListTemoignagesManager.this, range, "cat"+categories.getSelectedIndex());
									
									//Window.alert("Sois beni Heureux Internaute, " +
									//		"tu es le "+result.getNbPagesVues()+"eme visiteur de cette page!");

								}

							};
						
						temoignageService.incrementNbPagesVues(selectionModel.getSelectedObject().getId(), callback);
					}
				});
	}

	public SingleSelectionModel<Temoignage> getSelectionModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
