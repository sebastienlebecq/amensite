package fr.nations.amen.client.eglise.visionpublic;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.activities.LouangeDetailsActivity;
import fr.nations.amen.client.mvp.places.EglisePlace;
import fr.nations.amen.client.mvp.places.LouangeDetailsPlace;
import fr.nations.amen.shared.eglise.MenuEglise;
import fr.nations.amen.shared.eglise.MenuEgliseService;
import fr.nations.amen.shared.eglise.MenuEgliseServiceAsync;



public class ListeEgliseSujet extends Composite {
	
	MenuEgliseServiceAsync egliseService = GWT.create(MenuEgliseService.class);
	
	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	 
	 
	public ListeEgliseSujet() {
		super();
	    // Create a CellTable.
	    final CellTable<MenuEglise> table = new CellTable<MenuEglise>();
	    // Display 3 rows in one page
	    table.setPageSize(UniformDim.SIZE_ARRAY_FIRST_PAGE+10);

//	    // Add a text column to show the name.
//	    TextColumn<MenuEglise> nameColumn = new TextColumn<MenuEglise>() {
//	      @Override
//	      public String getValue(MenuEglise object) {
//	    	  String indexStr = object.getCategory().substring(3);
//		    	
//		        return  UniformDim.categoriesLouanges[Integer.valueOf(indexStr)];
//	      }
//	    };
//	    table.addColumn(nameColumn, "Categorie");

	    // Add a date column to show the birthday.
	    TextColumn<MenuEglise> catColumn = new TextColumn<MenuEglise>() {
		      @Override
		      public String getValue(MenuEglise object) {
		        return object.getAppellation();
		      }
		    };
	    table.addColumn(catColumn, "Titre");

	    //
	  		Column<MenuEglise, String> detailsColumn = new Column<MenuEglise, String>(
	  				new ButtonCell()) {
	  			@Override
	  			public String getValue(MenuEglise object) {
	  				return "Go!!";
	  			}
	  		};
	  		table.addColumn(detailsColumn, "Voir");

	  		detailsColumn.setFieldUpdater(new FieldUpdater<MenuEglise, String>() {
	  			@Override
	  			public void update(int index, MenuEglise object, String value) {
	  				
	  				//Window.alert("index:"+index+", value:"+value+", objet categorie:"+object.getCategory());
	  				//LouangeDetailsActivity.initLouange();
	  				
//	  				if (object.getIndexInCells()==null){
//	  					
//	  					Window.alert("la mise à jour des index n'a pas été effectuée : sélectionnez chaque article!!");
//	  				}
//	  				else {
//	  					goTo(new LouangeDetailsPlace(object.getId()+"&"+object.getIndexInCells()+"&"+object.getCategory()));
//	  				}
	  				goTo(new EglisePlace(object.getId()));

	  			}
	  		});
	  	    
	    
	    
	    final AsyncDataProvider<MenuEglise> provider = new AsyncDataProvider<MenuEglise>() {
		      @Override
		      protected void onRangeChanged(HasData<MenuEglise> display) {
		        final int start = display.getVisibleRange().getStart();
		        int length = display.getVisibleRange().getLength();
		        AsyncCallback<List<MenuEglise>> callback = new AsyncCallback<List<MenuEglise>>() {
		          @Override
		          public void onFailure(Throwable caught) {
		            Window.alert(caught.getMessage());
		          }
		          @Override
		          public void onSuccess(List<MenuEglise> result) {
		            updateRowData(start, result);
		          }
		        };
		        // The remote service that should be implemented
		        egliseService.getMenuEgliseList(start, start + UniformDim.SIZE_ARRAY_FIRST_PAGE, callback);
		      }
		    };
	    provider.addDataDisplay(table);
	   // provider.updateRowCount(table.getVisibleRange().getLength(), true);
	    
		egliseService.getCountMax(new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			public void onSuccess(Integer countMax) {
				
				provider.updateRowCount(countMax, true);

			}
		});
	    
	   // provider.updateRowCount(10, true);

	    SimplePager pager = new SimplePager();
	    pager.setDisplay(table);
	    
	    TitleListEglise tlaudio = new TitleListEglise();
	    
	    VerticalPanel vp = new VerticalPanel();
	    vp.add(tlaudio);
	    vp.add(table);
	    vp.add(pager);
	    
	    DecoratorPanel decoratorPanel = new DecoratorPanel();
	      decoratorPanel.add(vp);
	      decoratorPanel.setStyleName("tableLesPlus");
	    
	    initWidget(decoratorPanel);
	    
	}

	 public void goTo(Place place) {
	        clientFactory.getPlaceController().goTo(place);
	    }

}
