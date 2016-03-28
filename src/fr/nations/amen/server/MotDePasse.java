/*
 * Fichier com/eteks/outils/MotDePasse.java
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

public class MotDePasse
{
  public static final int    LONGUEUR_MIN = 6;
  public static final String CARACTERES_ACCEPTES = "abcdefghijklmnopqrstuvwxyz0123456789";

  /**
   * Cree un nouveau de passe genere aleatoirement.
   */
  public static String creer ()
  {
    char [] motDePasse = new char [LONGUEUR_MIN];
    for (int i = 0; i < motDePasse.length; i++)
    {
      int nombreAleatoire = (int)(Math.random() * CARACTERES_ACCEPTES.length ());
      motDePasse [i] = CARACTERES_ACCEPTES.charAt (nombreAleatoire);
    }
    return new String (motDePasse);
  }
}
