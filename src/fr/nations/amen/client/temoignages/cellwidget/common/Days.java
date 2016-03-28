package fr.nations.amen.client.temoignages.cellwidget.common;

import java.util.ArrayList;
import java.util.List;

import fr.nations.amen.shared.souvenirs.Souvenir;



public class Days implements Comparable<Days>{
	int day;
	ArrayList<Souvenir> photos = new ArrayList<Souvenir>();
	
	public int getDay(){
		return day;
	}
	
	public List<Souvenir> getPhotos(){
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
