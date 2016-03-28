package fr.nations.amen.client.actupublic.cellwidget.common;

import java.util.ArrayList;
import java.util.List;

import fr.nations.amen.shared.actus.Actu;



public class Days implements Comparable<Days>{
	int day;
	ArrayList<Actu> photos = new ArrayList<Actu>();
	
	public int getDay(){
		return day;
	}
	
	public List<Actu> getPhotos(){
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
