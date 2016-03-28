package fr.nations.amen.client.souvenirspublic.celllist;

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
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;
import fr.nations.amen.client.mvp.views.SouvenirsView;
import fr.nations.amen.client.souvenirspublic.celllist.celltypes.custom.SouvenirCellWithUiBinder;
import fr.nations.amen.client.souvenirspublic.celllist.dataproviders.AllDataAsyncDataProvider;
import fr.nations.amen.client.souvenirspublic.celllist.dataproviders.CatDataAsyncDataProvider;
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

	interface CellTypesUiBinder extends UiBinder<Widget, CellListSouvenirManager> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);
	

	AsyncDataProvider<Souvenir> dataProviderAsync;

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

	@UiField
	ListBox categories;
	
	SouvenirsServiceAsync souvenirService = GWT.create(SouvenirsService.class);


	private SouvenirsView souvenirView;


	private SouvenirCellWithUiBinder scwb;


	private ChangeHandler chg;


	private SingleSelectionModel<Souvenir> selectionModel;
	 
	public SingleSelectionModel<Souvenir> getSelectionModel() {
		return selectionModel;
	}

	public SouvenirCellWithUiBinder getScwb() {
		return scwb;
	}

	public CellListSouvenirManager(SouvenirsView souvenirView) {
		
		this.souvenirView = souvenirView;

		scwb = new SouvenirCellWithUiBinder();
		souvenirList = new CellList<Souvenir>(scwb);
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
		
		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(souvenirList);

		handleUpdates();

		createWithAsyncDataProvider();
		
		//souvenirList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		souvenirList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		souvenirList.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
		
		for (String cat : UniformDim.categoriesSouvenirs){
			categories.addItem(cat);
		}
		
		chg = new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				//TODO: mettre en activité
				//remise à 0 et sélection du numéro zero
				//TODO : modif uibinder pour mettre le combobox ds la colonne de gauche dans ub docklayout
				
				scwb.setIndexSelected(UniformDim.OUTOFLIST);
				//selectionModel.getSelectedSet().clear();
				//CellListSouvenirManager.this.getScwb().setIndexSelected(0);

				dataProviderAsync.removeDataDisplay(souvenirList);
				souvenirList.redraw();

				//mise à jour du modèle
				dataProviderAsync = new CatDataAsyncDataProvider("cat"+categories.getSelectedIndex());
				dataProviderAsync.addDataDisplay(souvenirList);
				dataProviderAsync.updateRowCount(10, true);

				//rafraichissement de la vue
				souvenirManagerForm.setDisplay(souvenirList);
				//souvenirList.getVisibleItem(0);
				
				
				SouvenirManagerForm details = CellListSouvenirManager.this.getSouvenirManagerForm();
				details.setSouvenir(null);
				
				souvenirList.redraw();
				
				//selectionModel.getSelectedSet().clear();
				//souvenirList.getVisibleItem(0);
			}

		};
		
		categories.addChangeHandler(chg);

	}

	public CellList<Souvenir> getSouvenirList() {
		return souvenirList;
	}

	public void setSouvenirList(CellList<Souvenir> souvenirList) {
		this.souvenirList = souvenirList;
	}

	public ChangeHandler getChg() {
		return chg;
	}

	private void applyOptions() {
		souvenirList.setPageSize(UniformDim.PAGE_PUBLIC_SIZE);
		
	}

	private void createWithAsyncDataProvider() {
		//dataProviderAsync = new CatDataAsyncDataProvider("cat"+categories.getSelectedIndex());
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
				dataProviderAsync.removeDataDisplay(souvenirList);
				souvenirList.redraw();
			}
		});
	}
	private Range range;
	
	
	private void setUpSelectionModel() {
		selectionModel = new SingleSelectionModel<Souvenir>();
		souvenirList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						
						 AsyncCallback<Souvenir> callback  = new AsyncCallback<Souvenir>() {
								public void onFailure(Throwable error) {
									//do something, when fail
								}

								public void onSuccess(Souvenir result) {

									range = CellListSouvenirManager.this.getSouvenirList().getVisibleRange();

									SouvenirsPresenter pres = souvenirView.getPresenter();
									
									
									
									AsyncCallback<Souvenir> callback2  = new AsyncCallback<Souvenir>() {
										public void onFailure(Throwable error) {
											Window.alert("erreur : index non mis à jour!!");
										}

										@Override
										public void onSuccess(Souvenir result) {
											
											//Window.alert("index: "+result.getIndexInCells());
										}

									};
									
									
									souvenirService.registerIndexCellTable(selectionModel.getSelectedObject().getId(), 
											new Integer(range.getStart()).toString(), callback2);
									
									pres.souvenirSelected(result, CellListSouvenirManager.this, range, "cat"+categories.getSelectedIndex());
									
									//Window.alert("Sois beni Heureux Internaute, " +
									//		"tu es le "+result.getNbPagesVues()+"eme visiteur de cette page!");

								}

							};
						
						souvenirService.incrementNbPagesVues(selectionModel.getSelectedObject().getId(), callback);
						
						
						
					}


				});
		

	}

	public SouvenirManagerForm getSouvenirManagerForm() {
		return souvenirManagerForm;
	}

	public void setSouvenirManagerForm(SouvenirManagerForm souvenirManagerForm) {
		this.souvenirManagerForm = souvenirManagerForm;
	}

}
