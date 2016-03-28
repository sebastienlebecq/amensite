package fr.nations.amen.shared.louanges;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Louange
	 implements IsSerializable, Comparable<Louange> {

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

			private String albumRefPicasa;

			private String refYoutube;

			private String blobKey;

			private String blobKeyogg;

			private String blobKeywav;

			private String category;

			private String numero;

			private Integer nbPagesVues;

			private String indexInCells;

			//private String img;



			public void setNbPagesVues(Integer nbPagesVues) {
				this.nbPagesVues = nbPagesVues;
			}

			public void setNumero(String numeroAmen) {
				this.numero = numeroAmen;
			}

			public String getBlobKey() {
				return blobKey;
			}

			public void setRefYoutube(String refYoutube) {
				this.refYoutube = refYoutube;
			}

			public Louange() {
			}

			public Louange(String id) {
				this.id = id;
			}

			@Override
			public int compareTo(Louange o) {
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

			public String getSujet() {
				return appellation;
			}
			
			public String getAlbumRefPicasa() {
				return albumRefPicasa;
			}

			public void setAlbumRefPicasa(String albumRefPicasa) {
				this.albumRefPicasa = albumRefPicasa;
			}

			public String getRefYoutube() {
				return refYoutube;
			}

			public void setBlobKey(String trim) {
				this.blobKey = trim;
				
			}

			public String getBlobKeyogg() {

				return this.blobKeyogg;
			}

			public void setBlobKeyogg(String blobKeyogg) {
				this.blobKeyogg = blobKeyogg;
			}

			public String getBlobKeywav() {
				return blobKeywav;
			}

			public void setBlobKeywav(String blobKeywav) {
				this.blobKeywav = blobKeywav;
			}

			public void setCategory(String category) {
				this.category =category;
			}

			public String getCategory() {
				return category;
			}

			public String getNumero() {
				return this.numero;
				
			}

			public Integer getNbPagesVues() {
				return this.nbPagesVues;
			}

			public void setIndexInCells(String indexInCells) {
				this.indexInCells = indexInCells;
				
			}

			public String getIndexInCells() {
				return indexInCells;
			}

//			public void setImg(String image) {
//				this.img = image;
//				
//			}
//
//			public String getImg() {
//				return img;
//			}


}
