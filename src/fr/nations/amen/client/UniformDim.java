package fr.nations.amen.client;

import fr.nations.amen.client.mvp.Tokens;

public interface UniformDim {
	
	final int PAGE_PUBLIC_SIZE = 8;
	
	final int PAGE_ADMIN_SIZE = 16;
	final int SIZE_ARRAY_FIRST_PAGE = 8;
	
	public enum MODE { PRODUCTION, DEBUG}
	
    public MODE mode = MODE.PRODUCTION;
    
    public enum urlMode { NOHISTORY, A_et_P};
    
    public int a_et_p = urlMode.A_et_P.ordinal();

	public static int hauteurBanniere = 120;
	public static int hauteurMenu = 35;
	public static String labelWait = "Chargement en cours...";
	
	String [] categoriesTextes = {"Temoignages","Enseignements"};
	String [] categoriesMessagesAudio = {"Edification", "Exhortation", "Famille"};
	String [] categoriesLouanges = {"Adorations", "Louanges"};
	String [] categoriesSouvenirs = {"Evenements", "Cultes"};
	
//	String[][] idMenuEglise = {
//			{Tokens.VISION,"93"}, 
//			{Tokens.APROPOS,"90"},
//			{Tokens.CONFESSIONFOI,"92"},
//			{Tokens.ORGANISATION,"91"},
//			{Tokens.HISTORIQUE,"89"},
//			};
	
//	String[][] idMenuEglise = {
//			{Tokens.VISION,"5700735861784576"}, 
//			{Tokens.APROPOS,"5766466041282560"},
//			{Tokens.CONFESSIONFOI,"5634387206995968"},
//			{Tokens.ORGANISATION,"5203516087861248"},
//			{Tokens.HISTORIQUE,"5693417237512192"},
//			};

	final int SIZE_DIALOG = 8;

	final int OUTOFLIST = 100;

	public enum Auteur {INTERNAUTE, EGLISE};
}
