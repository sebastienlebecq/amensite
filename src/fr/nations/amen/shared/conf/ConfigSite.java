package fr.nations.amen.shared.conf;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;



public class ConfigSite implements IsSerializable, Comparable<ConfigSite>{

	private String logo;
	private String description;
	private String idNumber;
	private String imgPresentationUrl;
	private String emailHtml;
	private String adresseHtml;
	private String photoBatiment;
	private String urlIntegration;
	private List<String> prefsVideo;
	private List<String> prefsChanson;
	//private List<String> prefsTexte;
	private List<String> downloadBlobKeyDocs;
	private List<String> downloadEmails;
	private String idCle;
	private String imgPresentNsTrouverUrl;
	private String dirPhotoPicasa;
	private Integer dirPhotoPicasaNb;
	private String imgPresentationWidth;


	public void setDirPhotoPicasa(String dirPhotoPicasa) {
		this.dirPhotoPicasa = dirPhotoPicasa;
	}

	public String getImgPresentNsTrouverUrl() {
		return imgPresentNsTrouverUrl;
	}

	public void setImgPresentNsTrouverUrl(String imgPresentNsTrouverUrl) {
		this.imgPresentNsTrouverUrl = imgPresentNsTrouverUrl;
	}

	public void setIdCle(String idCle) {
		this.idCle = idCle;
	}

	public void setDownloadEmails(List<String> downloadEmails) {
		this.downloadEmails = downloadEmails;
	}

	public void setDownloadBlobKeyDocs(List<String> downloadBlobKeyDocs) {
		this.downloadBlobKeyDocs = downloadBlobKeyDocs;
	}

//	public List<String> getPrefsTexte() {
//		return prefsTexte;
//	}
//
//	public void setPrefsTexte(List<String> prefsTexte) {
//		this.prefsTexte = prefsTexte;
//	}

	public void setPrefsChanson(List<String> prefsChanson) {
		this.prefsChanson = prefsChanson;
	}

	public void setUrlIntegration(String urlIntegration) {
		this.urlIntegration = urlIntegration;
	}

	public void setPhotoBatiment(String photoBatiment) {
		this.photoBatiment = photoBatiment;
	}

	public void setAdresseHtml(String adresseHtml) {
		this.adresseHtml = adresseHtml;
	}

	public void setEmailHtml(String emailHtml) {
		this.emailHtml = emailHtml;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setLogo(String text) {
		this.logo = text;
		
	}

	public void setDescription(String html) {
		this.description=html;
		
	}

	public String getLogo() {
		return logo;
	}

	public String getDescription() {
		return description;
	}

	public void setIdNumber(String text) {
		this.idNumber = text;
	}

	@Override
	public int compareTo(ConfigSite o) {
		return this.idNumber.compareTo(o.idNumber);
	}

	public void setImgPresentationUrl(String trim) {
		this.imgPresentationUrl = trim;
		
	}	
	
	public String getImgPresentationUrl() {
		return this.imgPresentationUrl;
	}

	public String getEmailHtml() {
		return this.emailHtml;
	}

	public String getAdresseHtml() {
		return this.adresseHtml;
	}

	public String getPhotoBatiment() {
		return this.photoBatiment;
	}

	public String getUrlIntegration() {
		return this.urlIntegration;
	}

	public void setPrefsVideo(List<String> prefsVideo) {
		this.prefsVideo = prefsVideo;
		
	}

	public List<String> getPrefsVideo() {
		return prefsVideo;
	}

	public List<String> getPrefsChanson() {
		return prefsChanson;
	}

	public List<String> getDownloadBlobKeyDocs() {
		return downloadBlobKeyDocs;
	}

	public List<String> getDownloadEmails() {
		return downloadEmails;
	}

	public String getIdCle() {
		return this.idCle;
	}

	public String getDirPhotoPicasa() {
		return this.dirPhotoPicasa;
	}

	public Integer getDirPhotoPicasaNb() {
		return this.dirPhotoPicasaNb;
	}

	public void setDirPhotoPicasaNb(Integer dirPhotoPicasaNb) {
		this.dirPhotoPicasaNb = dirPhotoPicasaNb;
	}

	public String getImgPresentationWidth() {
		return this.imgPresentationWidth;
	}

	public void setImgPresentationWidth(String imgPresentationWidth) {
		this.imgPresentationWidth = imgPresentationWidth;
	}
	
	

}
