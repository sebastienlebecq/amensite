package fr.nations.amen.shared.louanges;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;


public class Commentaire implements IsSerializable, Comparable<Commentaire>{
	
	
	
	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentaire(String id, String signataire, String comment,
			long hireDate, String appellation) {
		super();
		this.id = id;
		this.signataire = signataire;
		this.comment = comment;
		this.hireDate = hireDate;
		this.appellation = appellation;
	}

	private String id;
	
	
	private String signataire;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSignataire() {
		return signataire;
	}

	public void setSignataire(String signataire) {
		this.signataire = signataire;
	}

	public Long getHireDate() {
		return hireDate;
	}

	public void setHireDate(long hireDate) {
		this.hireDate = hireDate;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	private String comment;


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private Long hireDate;

	private String appellation;


	@Override
	public int compareTo(Commentaire o) {
		return this.id.compareTo(o.id);
	}
}
