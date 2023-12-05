package bacheca22;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Annuncio {
	
	//Caratteristiche annuncio
	private final String nomeOggetto;
	private final Utente utente;
	private final int quantita;
	private final int prezzo;
	private ArrayList<String> paroleChiave;
	private final int ID;
	private final char type;
	private final LocalDate scadenza;
	
	//Costruttore annuncio
	public Annuncio(char T, String nomeOggetto, Utente u, int qnt, int prezzo, int ID, String scadenza, String... paroleChiave) throws GenericErrorException{
		this.type = T;
		this.nomeOggetto = nomeOggetto;
		this.utente = u;
		this.quantita = qnt;
		this.prezzo = prezzo;
		this.ID = ID;		
		this.paroleChiave = new ArrayList<>();
		this.scadenza = !Objects.equals(scadenza, "") ? LocalDate.parse(scadenza, DateTimeFormatter.ofPattern("uuuu-M-d")) : LocalDate.now().plusMonths(1);

		this.paroleChiave.addAll(Arrays.asList(paroleChiave));
		}

	
	//Restituisce il tipo di annuncio (Compro o vendo?)
	public char getT() {
		return type;
	}
	
	//Restituisce lista di parole chiave
	public ArrayList<String> getParoleChiave() {
		return paroleChiave;
	}

	//Restituisce codice identificativo annuncio
	public int getID() {
		return this.ID;
	}
	
	//Restituisce nome oggetto dell'annuncio
	public String getNome() {
		return this.nomeOggetto;
	}
		
	//Restituisce l'Email identificativa di chi ha inserito l'annuncio
	public String getEmailUtente() {
		return this.utente.getEmail();
	}
	
	//Restituisce la data di scadenza dell'annuncio
	public LocalDate getScadenza() {
		return this.scadenza;
	}
	
	//Setta le parole chiave
	public void setParoleChiave(ArrayList<String> match) {
		this.paroleChiave = match;
	}
	
	//Restituisce stringa di parole chiave (esempio: Musica-Abbigliamento-Auto)
	public String listaParole() {
		
		StringBuilder s = new StringBuilder();
			
		for(String tmp :this.paroleChiave)
			s.append(tmp).append("-");
		return s.substring(0, s.length()-1);
	}
	
	//Restituisce annuncio sotto forma di stringa
	public String toString() {
		return ( type+","+nomeOggetto+","+utente.getNome()+","+utente.getEmail()+","+quantita+","+prezzo+","+ID+"," +scadenza.toString()+ ","+this.listaParole());
	}

}