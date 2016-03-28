/* Copyright (c) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.nations.amen.server.louanges;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class LouangesEntry {

	private static boolean firsttime = true;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String appellation;
	
	@Persistent
	private String indexInCells;

	public String getIndexInCells() {
		return indexInCells;
	}

	public void setIndexInCells(String indexInCells) {
		this.indexInCells = indexInCells;
	}

	@Persistent
	String image;
	
	@Persistent
	String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Persistent
	Text description;

	@Persistent
	private long dateActu;

	@Persistent
	private String albumRefPicasa;
	
	@Persistent
	private String refYoutube;
	@Persistent
	private String blobKey;
	@Persistent
	private String blobKeyogg;
	@Persistent
	private String blobKeywav;
	@Persistent
	private String category;
	@Persistent
	private Integer nbPagesVues;

	public Integer getNbPagesVues() {
		return nbPagesVues;
	}

	public void setNbPagesVues(Integer nbPagesVues) {
		this.nbPagesVues = nbPagesVues;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setBlobKeywav(String blobKeywav) {
		this.blobKeywav = blobKeywav;
	}

	public void setBlobKeyogg(String blobKeyogg) {
		this.blobKeyogg = blobKeyogg;
	}

	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}

	public String getRefYoutube() {
		return refYoutube;
	}

	public void setRefYoutube(String refYoutube) {
		this.refYoutube = refYoutube;
	}

	
	public LouangesEntry(String category, String appelation, String description, long date, 
			String albumRefPicasa2, String refYoutube2, String blobKey, String blobKeyogg2, String blobKeywav2, 
			String numero,
			String image, String indexInCells) {

		this.category = category;
		this.description = new Text(description);
		this.appellation = appelation;
		this.dateActu = date;
		this.albumRefPicasa = albumRefPicasa2;
		this.refYoutube = refYoutube2;
		this.blobKey = blobKey;
		this.blobKeyogg=blobKeyogg2;
		this.blobKeywav=blobKeywav2;
		this.numero=numero;
		this.image = image;
		this.indexInCells = indexInCells;
		
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAppellation() {
		return appellation;
	}

	public Long getId() {
		return id;
	}

	public Long getDateActu() {
		return dateActu;
	}

	public String getDescription() {
		return description.getValue();
	}

	public void setAppellation(String appellation2) {
		this.appellation = appellation2;

	}

	public void setDescription(String description2) {
		this.description = new Text(description2);

	}

	public void setTime(long time) {
		this.dateActu = time;

	}

	public String getAlbumRefPicasa() {
		return albumRefPicasa;
	}

	public void setAlbumRefPicasa(String albumRefPicasa) {
		this.albumRefPicasa = albumRefPicasa;
	}

	public String getBlobKey() {
		return this.blobKey;
	}

	public String getBlobKeyogg() {
		return this.blobKeyogg;
	}

	public String getBlobKeywav() {
		return this.blobKeywav;
	}

}