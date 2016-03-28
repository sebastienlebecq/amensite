package fr.nations.amen.client.temoignagespublic.cellwidget.common;

import java.util.ArrayList;
import java.util.List;

import fr.nations.amen.shared.temoignages.Temoignage;



public class Days implements Comparable<Days>{
	int day;
	ArrayList<Temoignage> photos = new ArrayList<Temoignage>();
	
	public int getDay(){
		return day;
	}
	
	public List<Temoignage> getPhotos(){
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
