package fr.nations.amen.server.forumpasteur;

import java.text.SimpleDateFormat;

public class ServerCalendarFactory {

	static String DATE_FORMAT = "dd/MM/yyyy";
	
	public static SimpleDateFormat getFormatter(){
		return new SimpleDateFormat(DATE_FORMAT);
	}
}
