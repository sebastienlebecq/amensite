/*
 * Fichier com/eteks/outils/OutilsChaine.java
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

public class OutilsChaine
{
  public static final String CHAINE_SUITE = " [...]";

  /**
   * Renvoie la chaine limitee a max caracteres.
   */
  public static String limiterLongueur (String chaine, int max)
  {
    if (chaine.length() <= max)
      return chaine;
    else
    {
      int indiceEspace = chaine.lastIndexOf(' ', max - CHAINE_SUITE.length ());
      // S'il n'y a pas d'espace avant max, la chaine est coupee a max caracteres
      if (indiceEspace == -1)
        return chaine.substring (0, max - CHAINE_SUITE.length ()) + CHAINE_SUITE;
      else
        return chaine.substring (0, indiceEspace) + CHAINE_SUITE;
    }
  }

  /**
   * Convertit les caracteres < ' " et & en leur equivalent HTML.
   */
  public static String convertirEnEntites(String chaine)
  {
    if (chaine != null)
    {
      // Remplacement des caracteres & par des entites &amp;
      chaine = remplacer (chaine, '&',  "&amp;");
      // Remplacement des caracteres < par des entites &lt;
      chaine = remplacer (chaine, '<',  "&lt;");
      // Remplacement des caracteres ' par des entites &apos;
      chaine = remplacer (chaine, '\'',  "&apos;");
      // Remplacement des caracteres " par des entites &quot;
      chaine = remplacer (chaine, '"',  "&quot;");
    }
    return chaine;
  }

  /**
   * Convertit les caracteres < ' " & et retour a la ligne de chaine en leur equivalent HTML.
   */
  public static String convertirEnHTML (String chaine)
  {
    if (chaine != null)
    {

      chaine = convertirEnEntites (chaine);
      // Remplacement des retours a la ligne par des balises <br>
      chaine = remplacer (chaine, '\n',  "<br>");
    }
    return chaine;
  }

  /**
   * Remplace les caracteres c dans chaine par la chaine t.
   */
  public static String remplacer(String chaine, char c, String t)
  {
    for (int indiceC = chaine.indexOf (c);
         indiceC != -1;
         indiceC = chaine.indexOf (c, indiceC + 1))
      chaine = chaine.substring(0, indiceC)
                 + t + chaine.substring(indiceC + 1);
    return chaine;
  }
}
