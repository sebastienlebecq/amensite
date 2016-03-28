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

package fr.nations.amen.server.conf;


import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION,
					detachable="true")
public class ConfigEntry {

	private static boolean firsttime = true;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String appellation;

	@Persistent
	private Text description;
	
	@Persistent
	private Long dateActu;
	
	@Persistent
	private String imgPresentationUrl;
	@Persistent
	private Text adresseHtml;
	@Persistent
	private Text emailHtml;
	@Persistent
	private String photoBatiment;
	@Persistent
	private String integCode;
	@Persistent
	private String prefsVideo;
	@Persistent
	private String prefsChanson;
//	@Persistent
//	private String prefsTexte;
	@Persistent
	private String downloadBlobKeyDocs;
	@Persistent
	private String downloadEmails;
	@Persistent
	private String idCle;
	@Persistent
	private String imgPresentNousTrouverUrl;
	@Persistent
	private String dirPhotoPicasa;
	@Persistent
	private Integer dirPhotoPicasaNb;
	@Persistent
	private String imgPresentationWidth;

	public String getImgPresentationWidth() {
		return imgPresentationWidth;
	}

	public void setImgPresentationWidth(String imgPresentationWidth) {
		this.imgPresentationWidth = imgPresentationWidth;
	}

	public Integer getDirPhotoPicasaNb() {
		return dirPhotoPicasaNb;
	}

	public void setDirPhotoPicasaNb(Integer dirPhotoPicasaNb) {
		this.dirPhotoPicasaNb = dirPhotoPicasaNb;
	}

	public void setDirPhotoPicasa(String dirPhotoPicasa) {
		this.dirPhotoPicasa = dirPhotoPicasa;
	}

	public void setImgPresentNousTrouverUrl(String imgPresentNousTrouverUrl) {
		this.imgPresentNousTrouverUrl = imgPresentNousTrouverUrl;
	}

	public void setIdCle(String idCle) {
		this.idCle = idCle;
	}

	public String getDownloadEmails() {
		return downloadEmails;
	}

	public void setDownloadEmails(String downloadEmails) {
		this.downloadEmails = downloadEmails;
	}

	public String getDownloadBlobKeyDocs() {
		return downloadBlobKeyDocs;
	}

	public void setDownloadBlobKeyDocs(String downloadBlobKeyDocs) {
		this.downloadBlobKeyDocs = downloadBlobKeyDocs;
	}

//	public String getPrefsTexte() {
//		return prefsTexte;
//	}
//
//	public void setPrefsTexte(String prefsTexte) {
//		this.prefsTexte = prefsTexte;
//	}

	public String getPrefsVideo() {
		return prefsVideo;
	}

	public void setPrefsVideo(String prefsVideo) {
		this.prefsVideo = prefsVideo;
	}

	public void setIntegCode(String integCode) {
		this.integCode = integCode;
	}

	public void setPhotoBatiment(String photoBatiment) {
		this.photoBatiment = photoBatiment;
	}

	public void setEmailHtml(String emailHtml) {
		this.emailHtml = new Text(emailHtml);
	}

	public void setAdresseHtml(String adresseHtml) {
		this.adresseHtml = new Text(adresseHtml);
	}

	public ConfigEntry(long ldate, String appellation, String description, String imgPresUrl,
			String imgPresNsContacterUrl, 
			String emailHtml, String adresseHtml, String photoBatiment, String integCode, 
			String prefsvideo, String prefsChant, /*String prefsTexte,*/ String downloadBlobKeyDocs,
			String downloadEmails, String idCle, String dirPhotoPicasa, Integer dirPhotoPicasaNb,
			String imgPresentationWidth) {
    	
		this.description = new Text(description);
		this.appellation = appellation;
		this.photoBatiment = photoBatiment;
		this.dateActu = ldate;
		this.imgPresentationUrl=imgPresUrl;
		this.imgPresentNousTrouverUrl= imgPresNsContacterUrl;
		this.adresseHtml = new Text(adresseHtml);
		this.emailHtml = new Text(emailHtml);
		this.integCode = integCode;
		this.prefsVideo = prefsvideo;
		this.prefsChanson = prefsChant;
		//this.prefsTexte = prefsTexte;
		this.downloadBlobKeyDocs = downloadBlobKeyDocs;
		this.downloadEmails = downloadEmails;
		this.idCle = idCle;
		this.dirPhotoPicasa = dirPhotoPicasa;
		this.dirPhotoPicasaNb = dirPhotoPicasaNb;
		this.imgPresentationWidth = imgPresentationWidth;
	}
	
//	public void setTime(long time) {
//		this.dateActu = time;
//
//	}
	
	public Long getDateActu() {
		return dateActu;
	}

	public void setDateActu(Long dateActu) {
		this.dateActu = dateActu;
	}

	public String getAppellation() {
		return appellation;
	}

	public Long getId() {
		return id;
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

	public String getImgPresentationUrl() {
		return this.imgPresentationUrl;
	}
	

	public void setImgPresentationUrl(String imgPresentationUrl) {
		this.imgPresentationUrl = imgPresentationUrl;
	}

	public String getAdresseHtml() {
		return this.adresseHtml.getValue();
	}

	public String getEmailHtml() {
		return this.emailHtml.getValue();
	}

	public String getPhotoBatiment() {
		return this.photoBatiment;
	}

	public String getIntegCode() {
		return this.integCode;
	}

	public String getPrefsChanson() {
		// TODO Auto-generated method stub
		return prefsChanson;
	}

	public void setPrefsChanson(String prefsChanson) {
		this.prefsChanson = prefsChanson;
	}

	public String getIdCle() {
		return idCle;
	}

	public String getImgPresentNousTrouverUrl() {
		return this.imgPresentNousTrouverUrl;
	}

	public String getDirPhotoPicasa() {
		return this.dirPhotoPicasa;
	}


}
