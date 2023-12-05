package bacheca22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class Bacheca implements Iterable<Annuncio>{
	
	final int MAX_DIM=3;											//Numero massimo degli annunci	
	private final ArrayList<Annuncio> annunci = new ArrayList<>();	//Lista annunci di tipo annuncio
	
	protected ArrayList<String> parole = new ArrayList<>(List.of(        //Lista di parole chiave
			"Abbigliamento",
			"Altro",
			"Auto",
			"Bellezza",
			"Casa",
			"Elettronica",
			"Film",
			"Giardinaggio",
			"Giochi",
			"Gioielli",
			"Informatica",
			"Libri",
			"Musica",
			"Sport"));
	
	
	public Iterator<Annuncio> iterator(){

		return new IteratoreBacheca();
	}
	
	private class IteratoreBacheca implements Iterator<Annuncio>{
		
		private int cursor;

		public IteratoreBacheca(){
			cursor = 0;
		}

		public boolean hasNext()
		{
			if (annunci.size()>MAX_DIM) return false;
			return (cursor<annunci.size());
		}
		public Annuncio next()
		{
			if (!this.hasNext())
				return null;
			return annunci.get(cursor++);
		}
	}

	//Aggiunge annuncio se la dimensione massima non è ancora stata raggiunta
	public void aggiungiAnnuncio(Annuncio annuncio)throws GenericErrorException	{

		ArrayList<String> match  = new ArrayList<>();
		
		if(!(Utenti.containsUtente(annuncio.getEmailUtente())))
			throw new GenericErrorException("Utente non presente");
			
		
		for (Annuncio tmp : this.annunci){
			if (annuncio.getID() == tmp.getID())
				throw new GenericErrorException("Annuncio gia presente");
		}
		for(String s: parole){
			if(annuncio.getParoleChiave().contains(s)){
				match.add(s);
			}
		}

		if(match.size()==0){
			throw new GenericErrorException("Nessuna corrispondenza nelle parole chiave");
		}
		else{
			if (this.annunci.size()>=MAX_DIM) {
				throw new GenericErrorException("Dimensione massima raggiunta");
			}
			annuncio.setParoleChiave(match);
			this.annunci.add(annuncio);
		}
	}
		
	
	//Cerca annunci tramite l'identificativo dell'utente
	public ArrayList<Annuncio> trovaAnnunci(Utente u) {
		ArrayList<Annuncio> risultato = new ArrayList<>();
		
		for (Annuncio tmp : this.annunci)
			if (tmp.getEmailUtente().equals(u.getEmail()))
				risultato.add(tmp);
		return risultato;
	}
	
	//Restituisce gi annunci dell'utente loggato
	public String listaPropriAnnunci(Utente u){
		
		ArrayList <Annuncio> lista = trovaAnnunci(u);
		StringBuilder s = new StringBuilder();
		
		for (Annuncio tmp : lista){
			s.append(tmp.toString()).append("\n");
		}
		return s.substring(0, s.length()-1);
	}

	//Rimuove l'annuncio creato dall'utente stesso (un utente X non può eliminare annunci dell'utente Y)
	public void rimuoviAnnuncio(int identificatore, Utente u)throws GenericErrorException {
		
		for(int i=0;i<annunci.size();i++){
			Annuncio c=annunci.get(i);
			
			if(c.getID()==identificatore && Objects.equals(u.getEmail(), c.getEmailUtente())){
				annunci.remove(c);
				return;
			}
		}
		throw new GenericErrorException("Nessun annuncio trovato");
	}
	

	//Restituisce elenco totale degli annunci presenti
	public String elencoAnnunci()
	{
		StringBuilder s = new StringBuilder();
		
		for(Annuncio tmp : annunci)
			s.append(tmp.toString()).append("\n");
		return s.substring(0, s.length()-1);
	}
	
	
	//Leggi annunci da file.txt - salva annunci su file.txt
	public	void leggiBacheca(String nomeFile) throws GenericErrorException{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(nomeFile));
			String linea = in.readLine();
			String[] dati;
			
			while (linea != null) {
				dati = linea.split(",");
				
				if (dati.length >= 8) {

					char T = dati[0].charAt(0);
					String nomeOgg = dati[1].trim();
					String nomeU= dati[2].trim();
					String emailU= dati[3].trim();
					int  qnt = Integer.parseInt(dati[4]);
					int  prezzo= Integer.parseInt(dati[5]);
					int  ID = Integer.parseInt(dati[6]);
					String scadenza = dati[7].trim();
					String [] parCh = dati[8].split("-");
					Utente u = new Utente(nomeU,emailU);
					
					if (!(Utenti.containsUtente(u.getEmail())))
						Utenti.aggiungiUtente(u);
					this.aggiungiAnnuncio(new Annuncio(T,nomeOgg,u, qnt, prezzo, ID, scadenza, parCh));
					
				}
				linea = in.readLine();
			}
			in.close();	
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	//Crea un file.txt nel quale salvare gli annunci inseriti da console
	public void scriviBacheca(String nomeFile) throws FileNotFoundException  {
			PrintWriter out = new PrintWriter(nomeFile);
			out.printf(this.elencoAnnunci());
			out.close();
	}
	
	//Esegue un cleanup degli annunci scaduti
	public void cleanupAnnunciScaduti() 
	{
		annunci.removeIf(a -> a.getScadenza().isBefore(LocalDate.now()));
	}
	
	
	//Restituisce numero degli annunci
	public int numEl(){
		return this.annunci.size();
	}
	
}