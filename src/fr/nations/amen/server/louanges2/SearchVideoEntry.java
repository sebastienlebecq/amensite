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

package fr.nations.amen.server.louanges2;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class SearchVideoEntry {

	private static boolean firsttime = true;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String appellation;

	@Persistent
	Blob image;

	@Persistent
	Text description;

	@Persistent
	private long dateActu;

	@Persistent
	private String albumRefPicasa;
	
	@Persistent
	private String refYoutube;

	public String getRefYoutube() {
		return refYoutube;
	}

	public void setRefYoutube(String refYoutube) {
		this.refYoutube = refYoutube;
	}

	
	public SearchVideoEntry(String appelation, String description, long date, 
			String albumRefPicasa2, String refYoutube2) {

		this.description = new Text(description);
		this.appellation = appelation;
		this.dateActu = date;
		this.albumRefPicasa = albumRefPicasa2;
		this.refYoutube = refYoutube2;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
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

}
