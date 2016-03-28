package fr.nations.amen.client.louanges2.cellwidget.common;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

public class CalendarFactory {

	static String DATE_FORMAT = "dd/MM/yyyy";
	
	public static DateTimeFormat getFormatter(){
		return DateTimeFormat.getFormat(DATE_FORMAT);
	}

	public static DateTimeFormat getDisplayFormatter() {
		return	DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
	}

	public static DateTimeFormat getClientSideFormatter() {
		return DateTimeFormat.getFormat(DATE_FORMAT);
	}
	
}
