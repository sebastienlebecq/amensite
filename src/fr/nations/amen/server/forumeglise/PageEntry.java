package fr.nations.amen.server.forumeglise;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class PageEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8742418297744726107L;

	public PageEntry() {
	}
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
	private int nbVisitePage;
	
	public int getNbVisitePage() {
		return nbVisitePage;
	}

	public long getHireDate() {
		return hireDate;
	}

	public void setHireDate(long hireDate) {
		this.hireDate = hireDate;
	}

	public void setNbVisitePage(int i) {
		this.nbVisitePage = i;
		
	}
}
