package bacheca22;

import java.util.ArrayList;


public class Utenti {
	
	private static ArrayList<Utente> utenti= new ArrayList<>();
	
	public static void crea() { 			
		utenti= new ArrayList<>();
	}
	
	//Aggiunge un utente se l'e-mail non è già presente nella lista utenti
	 public static void aggiungiUtente(Utente u) throws GenericErrorException{
		
		if (containsUtente(u.getEmail())){
		}else{
			utenti.add(u);
		}
	}

	//Rimuove un singolo utente dalla lista identificato tramite Email di registrazione
	public static void RimuoviUtente(String email)throws GenericErrorException{
		utenti.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
	}
	
		
	//Restituisce una stringa contenente tutti gli identificativi (Email) degli utenti registrati
	public static String elencoUtenti(){
		
		StringBuilder elenco = new StringBuilder();
						
		for (Utente tmp : utenti) {
			elenco.append(tmp.getEmail()).append("\n");
		}
		return elenco.toString();
	}
	
	//Controlla se un utente è presente nella lista tramite identificativo (Email)
	public static boolean containsUtente(String email)	{
		
		for (Utente u : utenti){
			if(u.getEmail().equals(email)) {
				return true;
			}	
		}
		return false;
	}
	
	
	//Restituisce il numero di utenti in lista
	public static int numEl() {
		return utenti.size();
	}
	
	//Restituisce un utente tramite ricerca per identificativo (Email)
	public static Utente getUtente(String email)	throws GenericErrorException{
		
		for (Utente u : utenti) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}
		throw new GenericErrorException("Utente non trovato");
		}
}
