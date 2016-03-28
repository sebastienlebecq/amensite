package fr.nations.amen.client.compolesplus;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
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
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;
import fr.nations.amen.shared.messagesaudio.MessageAudio;
import fr.nations.amen.shared.messagesaudio.MessagesAudioService;
import fr.nations.amen.shared.messagesaudio.MessagesAudioServiceAsync;

public class ListeAudiopEcoutes extends Composite {
	
	MessagesAudioServiceAsync souvenirService = GWT.create(MessagesAudioService.class);
	
//	 private static class Audio {
//		    private final String categorie;
//		    private final String titre;
//		    private final String nbconsult;
//
//		    public Audio( String cat, String titre, String nbconsult) {
//		      this.nbconsult = nbconsult;
//		      this.categorie = cat;
//		      this.titre = titre;
//		    }
//		  }
//
//	 private static final List<Audio> AUDIOS = Arrays.asList(
//		      new Audio("Foi", "oreigteroigjweodgjwdoighjgodg", "100"), 
//		      new Audio("Mariage", "oreigteroigjweodgjwdoighjgodg", "87"), 
//		      new Audio("Famille", "oreigteroigjweodgjwdoighjgodg", "60"), 
//		      new Audio("Revelation", "oreigteroigjweodgjwdoighjgodg", "55"), 
//		      new Audio("Peches", "oreigteroigjweodgjwdoighjgodg", "66"), 
//		      new Audio("Repentence", "oreigteroigjweodgjwdoighjgodg","77n"));
	 
	 
	 
	public ListeAudiopEcoutes() {
		super();
	    // Create a CellTable.
	    final CellTable<MessageAudio> table = new CellTable<MessageAudio>();
	    // Display 3 rows in one page
	    table.setPageSize(UniformDim.SIZE_ARRAY_FIRST_PAGE);

	    // Add a text column to show the name.
	    TextColumn<MessageAudio> nameColumn = new TextColumn<MessageAudio>() {
	      @Override
	      public String getValue(MessageAudio object) {
	       	  String indexStr = object.getCategory().substring(3);
		    	
		        return  UniformDim.categoriesMessagesAudio[Integer.valueOf(indexStr)];
	      }
	    };
	    table.addColumn(nameColumn, "Categorie");

	    // Add a date column to show the birthday.
	    TextColumn<MessageAudio> catColumn = new TextColumn<MessageAudio>() {
		      @Override
		      public String getValue(MessageAudio object) {
		        return object.getAppellation();
		      }
		    };
	    table.addColumn(catColumn, "Titre");

	    // Add a text column to show the address.
	    TextColumn<MessageAudio> addressColumn = new TextColumn<MessageAudio>() {
	      @Override
	      public String getValue(MessageAudio object) {
	    	  if(object.getNbPagesVues()!=null){
		  	        return object.getNbPagesVues().toString();
		  	    	  }
		  	    	  else {
		  	    		  return "null";
		  	    	  }
	      }
	    };
	    table.addColumn(addressColumn, "Nb de Visites");

	    // Associate an async data provider to the table
	    // XXX: Use AsyncCallback in the method onRangeChanged
	    // to actaully get the data from the server side
	    AsyncDataProvider<MessageAudio> provider = new AsyncDataProvider<MessageAudio>() {
		      @Override
		      protected void onRangeChanged(HasData<MessageAudio> display) {
		        final int start = display.getVisibleRange().getStart();
		        int length = display.getVisibleRange().getLength();
		        AsyncCallback<List<MessageAudio>> callback = new AsyncCallback<List<MessageAudio>>() {
		          @Override
		          public void onFailure(Throwable caught) {
		            Window.alert(caught.getMessage());
		          }
		          @Override
		          public void onSuccess(List<MessageAudio> result) {
		            updateRowData(start, result);
		          }
		        };
		        // The remote service that should be implemented
		        souvenirService.fetchPage(start, start + UniformDim.SIZE_ARRAY_FIRST_PAGE, callback);
		      }
		    };
	    provider.addDataDisplay(table);
	    provider.updateRowCount(table.getVisibleRange().getLength(), true);

	    SimplePager pager = new SimplePager();
	    pager.setDisplay(table);
	    
	    TitleListeAudi tlaudio = new TitleListeAudi();
	    
	    VerticalPanel vp = new VerticalPanel();
	    vp.add(tlaudio);
	    vp.add(table);
	    vp.add(pager);
	    
	    DecoratorPanel decoratorPanel = new DecoratorPanel();
	      decoratorPanel.add(vp);
	      decoratorPanel.setStyleName("tableLesPlus");
	    
	    initWidget(decoratorPanel);
	    
	    
//	 // Associate an async data provider to the table
//	    AsyncDataProvider<Contact> provider = new AsyncDataProvider<Contact>() {
//	      @Override
//	      protected void onRangeChanged(HasData<Contact> display) {
//	        final int start = display.getVisibleRange().getStart();
//	        int length = display.getVisibleRange().getLength();
//	        AsyncCallback<List<Contact>> callback = new AsyncCallback<List<Contact>>() {
//	          @Override
//	          public void onFailure(Throwable caught) {
//	            Window.alert(caught.getMessage());
//	          }
//	          @Override
//	          public void onSuccess(List<Contact> result) {
//	            updateRowData(start, result);
//	          }
//	        };
//	        // The remote service that should be implemented
//	        remoteService.fetchPage(start, length, callback);
//	      }
//	    };
	}


}
