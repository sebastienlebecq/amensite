package fr.nations.amen.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Utilisateur du forum avec son pseudonyme, son mot de passe
 * et les droits sur le forum qui lui sont autorises.
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class UtilisateurAmen
{
  public static final String MODERATEUR  = "M";
  public static final String UTILISATEUR = "U";

  @PrimaryKey
  @Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
  private String pseudonyme;
  
  @Persistent
  private String motDePasse;
  
  @Persistent
  private String autorisation;

  public UtilisateurAmen (String pseudonyme, String motDePasse,
                      String autorisation)
  {
    this.pseudonyme = pseudonyme;
    this.motDePasse = motDePasse;
    this.autorisation = autorisation;
  }

  public String getPseudonyme()
  {
    return this.pseudonyme;
  }

  public void setPseudonyme(String pseudonyme)
  {
    this.pseudonyme = pseudonyme;
  }

  public String getMotDePasse()
  {
    return this.motDePasse;
  }

  public void setMotDePasse(String motDePasse)
  {
    this.motDePasse = motDePasse;
  }

  public String getAutorisation()
  {
    return this.autorisation;
  }

  public boolean isModerateur ()
  {
    return MODERATEUR.equals(this.autorisation);
  }

  public void setAutorisation(String autorisation)
  {
    this.autorisation = autorisation;
  }

  public boolean equals (Object obj)
  {
    if (this.pseudonyme != null)
      if (obj instanceof UtilisateurAmen)
      {
        UtilisateurAmen utilisateur = (UtilisateurAmen)obj;
        return this.pseudonyme.equals(utilisateur.pseudonyme);
      }
    return false;
  }

  public int hashCode ()
  {
    if (this.pseudonyme == null)
      return super.hashCode();
    else
      return this.pseudonyme.hashCode();
  }

  public String toString ()
  {
    if (isModerateur())
      return this.pseudonyme + " (Mod\u00e9rateur)";
    else if (this.pseudonyme != null)
      return this.pseudonyme + " (Utilisateur)";
    else
      return "Utilisateur inconnnu";
  }
}
