package fr.nations.amen.client.compolesplus;

import java.util.Arrays;
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
import fr.nations.amen.client.mvp.activities.TemoignageActivity;
import fr.nations.amen.shared.temoignages.Temoignage;
import fr.nations.amen.shared.temoignages.TemoignagesService;
import fr.nations.amen.shared.temoignages.TemoignagesServiceAsync;
import fr.nations.amen.client.mvp.places.TemoignagePlace;


public class ListeArticlespLus  extends Composite{

	 TemoignagesServiceAsync temoignagesService = GWT.create(TemoignagesService.class);
	 private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	 
	public ListeArticlespLus() {
		super();
	    // Create a CellTable.
	    final CellTable<Temoignage> table = new CellTable<Temoignage>();
	    // Display 3 rows in one page
	    table.setPageSize(UniformDim.SIZE_ARRAY_FIRST_PAGE);

	    // Add a text column to show the name.
	    TextColumn<Temoignage> nameColumn = new TextColumn<Temoignage>() {
	      @Override
	      public String getValue(Temoignage object) {
		   String indexStr = object.getCategory().substring(3);
			    	
		   return  UniformDim.categoriesTextes[Integer.valueOf(indexStr)];
	      }
	    };
	    table.addColumn(nameColumn, "Categorie");

	    // Add a date column to show the birthday.
	    TextColumn<Temoignage> catColumn = new TextColumn<Temoignage>() {
		      @Override
		      public String getValue(Temoignage object) {
		        return object.getAppellation();
		      }
		    };
	    table.addColumn(catColumn, "Titre");

	    // Add a text column to show the address.
	    TextColumn<Temoignage> addressColumn = new TextColumn<Temoignage>() {
	      @Override
	      public String getValue(Temoignage object) {
	        return object.getNbPagesVues().toString();
	      }
	    };
	    table.addColumn(addressColumn, "Nb de Visites");

	    //
  		Column<Temoignage, String> detailsColumn = new Column<Temoignage, String>(
  				new ButtonCell()) {
  			@Override
  			public String getValue(Temoignage object) {
  				return "Go!!";
  			}
  		};
  		table.addColumn(detailsColumn, "Voir");

  		detailsColumn.setFieldUpdater(new FieldUpdater<Temoignage, String>() {
  			@Override
  			public void update(int index, Temoignage object, String value) {
  				
  				//Window.alert("index:"+index+", value:"+value+", objet categorie:"+object.getCategory());
  				TemoignageActivity.initLouange();
  				
  				if (object.getIndexInCells()==null){
  					
  					Window.alert("la mise à jour des index n'a pas été effectuée : sélectionnez chaque article!!");
  				}
  				else {
  					goTo(new TemoignagePlace(object.getId()+"&"+object.getIndexInCells()+"&"+object.getCategory()));
  				}
  				

  			}
  		});
  	    
	    
//		 // Associate an async data provider to the table
	    final AsyncDataProvider<Temoignage> provider = new AsyncDataProvider<Temoignage>() {
	      @Override
	      protected void onRangeChanged(HasData<Temoignage> display) {
	        final int start = display.getVisibleRange().getStart();
	        int length = display.getVisibleRange().getLength();
	        AsyncCallback<List<Temoignage>> callback = new AsyncCallback<List<Temoignage>>() {
	          @Override
	          public void onFailure(Throwable caught) {
	            Window.alert(caught.getMessage());
	          }
	          @Override
	          public void onSuccess(List<Temoignage> result) {
	            updateRowData(start, result);
	          }
	        };
	        // The remote service that should be implemented
	        temoignagesService.fetchPage(start, start + UniformDim.SIZE_ARRAY_FIRST_PAGE, callback);
	      }
	    };
	    
	    
	    provider.addDataDisplay(table);
	    
	    temoignagesService.getCountMax(new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			public void onSuccess(Integer countMax) {
				
				provider.updateRowCount(countMax, true);

			}
		});
	    
	    //provider.updateRowCount(table.getVisibleRange().getLength(), true);

	    SimplePager pager = new SimplePager();
	    pager.setDisplay(table);
	    
	    TitleListeArticle tlaudio = new TitleListeArticle();
	    
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
