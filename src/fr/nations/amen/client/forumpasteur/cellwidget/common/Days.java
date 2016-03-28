package fr.nations.amen.client.forumpasteur.cellwidget.common;

import java.util.ArrayList;
import java.util.List;

import fr.nations.amen.shared.forumpasteur.Messages;

public class Days implements Comparable<Days>{
	int day;
	ArrayList<Messages> photos = new ArrayList<Messages>();
	
	public int getDay(){
		return day;
	}
	
	public List<Messages> getPhotos(){
		return photos;
	}
	
	public int getPhotosNumber(){
		return photos.size();
	}
	
	
	public Days(int day){
		this.day = day;
	}
	
	@Override
	public int compareTo(Days otherDay) {
		return (day-otherDay.getDay());
	}
}
