/*
 * Fichier com/eteks/forum/UtilisateurForum.java
 *
 * Copyright (C) 2003-2006 Emmanuel PUYBARET / eTeks <info@eteks.com>. All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.nations.amen.server;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class UtilisateurForumAmen extends UtilisateurAmen
{
  public UtilisateurForumAmen(String pseudonyme, String motDePasse,
                        String autorisation)
  {
    super (pseudonyme, motDePasse, autorisation);
  }

  public UtilisateurForumAmen()
  {
    this (null, null, null);
  }

  public boolean rechercher ()
  {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(UtilisateurAmen.class,
				"pseudonyme == pseudonymeParam");
		query.declareParameters("String pseudonymeParam");
		//query.declareParameters("String motDePasse");

		Object result = query.execute(getPseudonyme());

		
		UtilisateurAmen resultAmen;
	  if(result==null){
		  return false;
	  }
	  else {
		  
		resultAmen = (UtilisateurAmen) result;  
		  
	  }
	  
	  
	  
//    PreparedStatement rechercheUtilisateur =
//      connecteur.getConnexion().prepareStatement (
//           "SELECT * FROM UTILISATEUR WHERE PSEUDONYME=?");
//    rechercheUtilisateur.setString (1, getPseudonyme());
//    ResultSet resultat = rechercheUtilisateur.executeQuery();
  //  boolean utilisateurExiste = result;
  //  if (utilisateurExiste)
  //  {
      setMotDePasse (resultAmen.getMotDePasse());
      setAutorisation (resultAmen.getAutorisation());
  //  }
//    resultat.close();
//    rechercheUtilisateur.close();
    return true;
  }

  public void ajouter ()
  {
//    PreparedStatement ajoutUtilisateur =
//      connecteur.getConnexion().prepareStatement (
//        "INSERT INTO UTILISATEUR"
//        + " (PSEUDONYME, MOTDEPASSE, AUTORISATION)"
//        + " VALUES (?, ?, ?)");
//    ajoutUtilisateur.setString(1, getPseudonyme());
//    ajoutUtilisateur.setString(2, getMotDePasse());
//    ajoutUtilisateur.setString(3, getAutorisation());
//    ajoutUtilisateur.executeUpdate ();
//    ajoutUtilisateur.close();
  }
}

