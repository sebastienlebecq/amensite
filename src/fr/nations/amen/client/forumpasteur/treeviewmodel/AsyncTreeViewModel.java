package fr.nations.amen.client.forumpasteur.treeviewmodel;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;

import fr.nations.amen.client.forumpasteur.celltypes.custom.PhotoCellWithUiBinder;
import fr.nations.amen.client.forumpasteur.dataproviders.DayAsyncDataProvider;
import fr.nations.amen.client.forumpasteur.dataproviders.MonthAsyncDataProvider;
import fr.nations.amen.client.forumpasteur.dataproviders.PhotoAsyncDataProvider;
import fr.nations.amen.client.forumpasteur.dataproviders.YearAsyncDataProvider;
import fr.nations.amen.shared.forumpasteur.AsyncDays;
import fr.nations.amen.shared.forumpasteur.AsyncMonths;
import fr.nations.amen.shared.forumpasteur.AsyncYears;
import fr.nations.amen.shared.forumpasteur.Messages;


public class AsyncTreeViewModel implements TreeViewModel {
	
	AsyncDataProvider<AsyncYears> yearDataAsyncProvider;
	AsyncDataProvider<AsyncMonths> monthDataAsyncProvider;
	AsyncDataProvider<AsyncDays> dayDataAsyncProvider;
	AsyncDataProvider<Messages> photoDataAsyncProvider;
		
	int currYear, currMonth, currDay;
	
		
	public AsyncTreeViewModel(){
	}

	/**
	 * Get the {@link NodeInfo} that provides the children of the specified
	 * value.
	 */
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value==null){
			// LEVEL 0 - so return the years
			yearDataAsyncProvider = new YearAsyncDataProvider();

	    	Cell<AsyncYears> cell = new AbstractCell<AsyncYears>() {
				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						AsyncYears value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.append(value.getYear());						
					}
				}
	    	};
	    	return new DefaultNodeInfo<AsyncYears>(yearDataAsyncProvider, cell);
	    } else if (value instanceof AsyncYears){
	    	// LEVEL 1 - so return the months
	    	currYear = ((AsyncYears)value).getYear();
			monthDataAsyncProvider = new MonthAsyncDataProvider(currYear);

	    	Cell<AsyncMonths> cell = new AbstractCell<AsyncMonths>() {
				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						AsyncMonths value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(LocaleInfo.getCurrentLocale().getDateTimeFormatInfo().monthsFull()[value.getMonth()-1]);
					}
				}
	    	};
	    	return new DefaultNodeInfo<AsyncMonths>(monthDataAsyncProvider, cell);
	    } else if (value instanceof AsyncMonths){
	    	// LEVEL 2 - so return the days
	    	currMonth = ((AsyncMonths)value).getMonth();
			dayDataAsyncProvider = new DayAsyncDataProvider(currYear, currMonth);
	    	Cell<AsyncDays> cell = new AbstractCell<AsyncDays>() {
				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						AsyncDays value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getDay() + "th ("+value.getPhotosNumber()+")");						
					}
				}
	    	};
	    	return new DefaultNodeInfo<AsyncDays>(dayDataAsyncProvider, cell);
	    } else if (value instanceof AsyncDays){
	    	// LEVEL 3 - so return the photo
	    	currDay = ((AsyncDays)value).getDay();
			photoDataAsyncProvider = new PhotoAsyncDataProvider(currYear, currMonth, currDay);
	    	return new DefaultNodeInfo<Messages>(photoDataAsyncProvider, new PhotoCellWithUiBinder());
	    }
	    return null;
	}

	/**
	 * Check if the specified value represents a leaf node. Leaf nodes
	 * cannot be opened.
	 */
	public boolean isLeaf(Object value) {
		return (value instanceof Messages);
	}
}
