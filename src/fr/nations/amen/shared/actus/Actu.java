package fr.nations.amen.shared.actus;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Actu implements IsSerializable, Comparable<Actu> {

	Date date;

	String id;
	
	String appellation;

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	String description;
	String tags;
	String[] tagsList;

	String thubnailUrl;

	String title;

	public Actu() {
	}

	public Actu(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(Actu o) {
		return this.id.compareTo(o.id);
	}

	public Integer getComparableId() {
		return Integer.parseInt(id);
	}

	public Date getDate() {
		return date;
	}
	
	// Need to use Date as this is also used on client side
	@SuppressWarnings("deprecation")
	public int getYear() {
		return 	date.getYear();
	}
	
	@SuppressWarnings("deprecation")
	public int getMonth() {
		return date.getMonth();
	}
	
	@SuppressWarnings("deprecation")
	public int getDay() {
		return date.getDay();
		
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getTags() {
		String toReturn = "";
		toReturn += tagsList[0];
		for (int loop=1; loop <tagsList.length; loop++)
			toReturn += ", " +tagsList[loop];
		return toReturn;
	}

	public String[] getTagsList() {
		return tagsList;
	}

	public String getThubnailUrl() {
		return thubnailUrl;
	}

	public void setDate(Date l) {
		this.date = l;
	}

	public void setDescription(String largeUrl) {
		this.description = largeUrl;
	}

	public void setTags(String tags) {
		String[] theTags = tags.split(",");
		this.tagsList = theTags;
	}
	
	public void setTags(String[] tags){
		this.tagsList = tags;
	}

	public void setThubnailUrl(String thubnailUrl) {
		this.thubnailUrl = thubnailUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAppellation() {
		return appellation;
	}
}
