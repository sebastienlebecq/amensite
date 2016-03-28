package fr.nations.amen.shared.messagesaudio;

import com.google.gwt.user.client.rpc.IsSerializable;



public class AsyncDays implements Comparable<AsyncDays>, IsSerializable{
	int day;
	
	public int getDay(){
		return day;
	}
	
	
	public int getPhotosNumber(){
		int num = 0;
		return num;
	}
	
	
	public AsyncDays(int day){
		this.day = day;
	}
	
	public AsyncDays(){}
	
	@Override
	public int compareTo(AsyncDays otherDay) {
		return (day-otherDay.getDay());
	}
}
