package fr.nations.amen.server.messagesaudio;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class CommentEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7584502034485827065L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Persistent
	private String signataire;

	@Persistent
	private String comment;
	
	@Persistent
	private Long gpSouvenir;

	public Long getGpSouvenir() {
		return gpSouvenir;
	}

	public void setGpSouvenir(Long gpSouvenir) {
		this.gpSouvenir = gpSouvenir;
	}

	@Persistent
	private long hireDate;

	@Persistent
	private String appellation;
	
	  public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public CommentEntry() {
		  }

//	public String getKey() {
//		return new Long(key.getId()).toString();
//	}
//
//	public void setKey(Key key) {
//		this.key = key;
//	}

	public String getSignataire() {
		return signataire;
	}

	public void setSignataire(String signataire) {
		this.signataire = signataire;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getHireDate() {
		return hireDate;
	}

	public void setHireDate(long hireDate) {
		this.hireDate = hireDate;
	}

}
