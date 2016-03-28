package fr.nations.amen.shared.temoignages;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Temoignage implements IsSerializable, Comparable<Temoignage> {

	Date date;

	String id;
	
	public String getIndexInCells() {
		return indexInCells;
	}

	String appellation;

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	String description;
	String tags;
	String[] tagsList;

	String thubnailUrl;

	String title;

	private String albumRefPicasa;
	private String category;

	private Integer nbPagesVues;

	private String indexInCells;

	public void setNbPagesVues(int nbPagesVues) {
		this.nbPagesVues = nbPagesVues;
	}


	public String getCategory() {
		return category;
	}


	public Temoignage() {
	}

	public Temoignage(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(Temoignage o) {
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
	
	public String getAlbumRefPicasa() {
		return albumRefPicasa;
	}

	public void setAlbumRefPicasa(String albumRefPicasa) {
		this.albumRefPicasa = albumRefPicasa;
	}

	public void setCategory(String cat) {
		this.category=cat;
		
	}


	public Integer getNbPagesVues() {
		return nbPagesVues;
	}


	public void setIndexInCells(String index) {
		this.indexInCells = index;
		
	}
}
