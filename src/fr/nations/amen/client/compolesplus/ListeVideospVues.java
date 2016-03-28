package fr.nations.amen.client.compolesplus;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import fr.nations.amen.client.mvp.activities.SouvenirDetailsActivity;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.shared.messagesaudio.MessageAudio;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.souvenirs.SouvenirsService;
import fr.nations.amen.shared.souvenirs.SouvenirsServiceAsync;

public class ListeVideospVues  extends Composite{
	 
	 SouvenirsServiceAsync souvenirService = GWT.create(SouvenirsService.class);

		private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	 
	public ListeVideospVues() {
		super();
	    // Create a CellTable.
	    final CellTable<Souvenir> table = new CellTable<Souvenir>();
	    // Display 3 rows in one page
	    table.setPageSize(UniformDim.SIZE_ARRAY_FIRST_PAGE);

	    // Add a text column to show the name.
	    TextColumn<Souvenir> nameColumn = new TextColumn<Souvenir>() {
	      @Override
	      public String getValue(Souvenir object) {
	    	  //index du tableau
		    	 String indexStr = object.getCategory().substring(3);
		    	
		        return  UniformDim.categoriesSouvenirs[Integer.valueOf(indexStr)];
	       
	      }
	    };
	    table.addColumn(nameColumn, "Categorie");

	    // Add a date column to show the birthday.
	    TextColumn<Souvenir> catColumn = new TextColumn<Souvenir>() {
		      @Override
		      public String getValue(Souvenir object) {
		    	  
		    	  return object.getAppellation();
		      }
		    };
	    table.addColumn(catColumn, "Titre");

	    // Add a text column to show the address.
	    TextColumn<Souvenir> addressColumn = new TextColumn<Souvenir>() {
	      @Override
	      public String getValue(Souvenir object) {
	    	  if(object.getNbPagesVues()!=null){
	        return object.getNbPagesVues().toString();
	    	  }
	    	  else {
	    		  return "null";
	    	  }
	      }
	    };
	    
	    
	    // Add a text column to show the address.
	    TextColumn<Souvenir> vColumn = new TextColumn<Souvenir>() {
	      @Override
	      public String getValue(Souvenir object) {
	    	  if(object.getNbPagesVues()!=null){
		  	        return object.getNbPagesVues().toString();
		  	    	  }
		  	    	  else {
		  	    		  return "null";
		  	    	  }
	      }
	    };
	    table.addColumn(vColumn, "Nb de Visites");
	    //
		Column<Souvenir, String> detailsColumn = new Column<Souvenir, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Souvenir object) {
				return "Go!!";
			}
		};
		table.addColumn(detailsColumn, "Voir");

		detailsColumn.setFieldUpdater(new FieldUpdater<Souvenir, String>() {
			@Override
			public void update(int index, Souvenir object, String value) {
				
				//Window.alert("index:"+index+", value:"+value+", objet categorie:"+object.getCategory());
				SouvenirDetailsActivity.initSouvenir();
				goTo(new SouvenirDetailsPlace(object.getId()+"&"+object.getIndexInCells()+"&"+object.getCategory()));
	
			}
		});
	    
	    
	    
//		 // Associate an async data provider to the table
	    final AsyncDataProvider<Souvenir> provider = new AsyncDataProvider<Souvenir>() {
	      @Override
	      protected void onRangeChanged(HasData<Souvenir> display) {
	        final int start = display.getVisibleRange().getStart();
	        int length = display.getVisibleRange().getLength();
	        AsyncCallback<List<Souvenir>> callback = new AsyncCallback<List<Souvenir>>() {
	          @Override
	          public void onFailure(Throwable caught) {
	            Window.alert(caught.getMessage());
	          }
	          @Override
	          public void onSuccess(List<Souvenir> result) {
	            updateRowData(start, result);
	          }
	        };
	        // The remote service that should be implemented
	        souvenirService.fetchPage(start, start + UniformDim.SIZE_ARRAY_FIRST_PAGE, callback);
	      }
	    };
	    
	    
	    
	    provider.addDataDisplay(table);
	    
	    souvenirService.getCountMax(new AsyncCallback<Integer>() {

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
	    
	    TitleListeVideo tlaudio = new TitleListeVideo();
	    
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
