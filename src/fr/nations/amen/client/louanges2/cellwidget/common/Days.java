package fr.nations.amen.client.louanges2.cellwidget.common;

import java.util.ArrayList;
import java.util.List;

import fr.nations.amen.client.louanges2.SearchVideo;


public class Days implements Comparable<Days>{
	int day;
	List<SearchVideo> photos = new ArrayList<SearchVideo>();
	
	public int getDay(){
		return day;
	}
	
	public List<SearchVideo> getPhotos(){
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
