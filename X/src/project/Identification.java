package project;

import javax.servlet.http.Cookie;

public class Identification {
	public static String chercheNom (Cookie [] cookies) {
	 // cherche dans les cookies la valeur de celui qui se nomme "nom"
	 // retourne la valeur de ce nom au lieu de inconnu
		if (cookies != null) {
		    for (int i = 0; i < cookies.length; i++) {
		         Cookie cookie = cookies[i];
		        if (InscriptionClient.nomCookie.equals(cookie.getName())) {
		            return cookie.getValue();
		        }
		    }}
	 return "inconnu";
	 }

	

	}