package bacheca22;

import jbook.util.Input;
import java.io.FileNotFoundException;

public class Menu {
	
	public static void main(String[] args) {
		
		Bacheca bach = new Bacheca();	//Creo oggetto di tipo bacheca
		int risp=0;						//Variabile per monitorare il tipo di risposta utente
		
		//Menù
		while(risp!=3){			
			try{
				switch(risp)
				{
				case 1:
					modUtente();
					risp=0;
					break;
				case 2:
					String email = Input.readString("Inserisci la tua email utente: ");
					
					if (Utenti.containsUtente(email))
							modBacheca(bach, Utenti.getUtente(email));
					else
						System.out.println("Inserisci una email valida");
					risp=0;
					break;
				case 3:
					break;
				default: 
					risp = Input.readInt("============\nScegli:\n1)Gestione Utenti\n2)Gestione Bacheca\n3)ESCI\n");
				}
			}
			catch (NumberFormatException e){
				System.out.println("\nDevi inserire un valore intero\n---------------");
			}
			catch (GenericErrorException e){
				System.out.println("\n"+e.getMessage()+"\n");
				risp = 0;
			}	
		}
	}
	
	
	
	//Metodo per gestire la modalità bacheca
	private static void modBacheca(Bacheca bach, Utente u) throws NumberFormatException,GenericErrorException	{
		int modb=0;
		while((modb>0)||(modb<8)) {
			try {
				System.out.println("====================\nGestione Bacheca\n");
				System.out.println("""
						1) Visualizza tutti i tuoi annunci
						2) Inserisci un annuncio nella bacheca
						3) Rimuovi un annuncio
						4) Visualizza tutti gli annunci in bacheca
						5) Leggi la bacheca da file
						6) Stampa su file
						7) Esegui cleanup annunci scaduti
						8) Logout
						""");
				
				modb = Input.readInt();
				switch(modb) {
				case 1:
					System.out.println("\nPropri annunci presenti in bacheca:\n"+bach.listaPropriAnnunci(u));
					break;
				case 2:
					int count = 0 ;
					String[] data = new String[7];
					data[count++]=Input.readString("Inserisci il tipo di annuncio(V/C): ");
					data[count++]=Input.readString("Inserisci il nome dell'oggetto: ");
					data[count++]=Input.readString("Inserisci la quantita': ");
					data[count++]=Input.readString("Inserisci il prezzo: ");
					data[count++]=Input.readString("Inserisci l'identificatore: ");
					data[count++]=Input.readString("Inserisci la data di scadenza nel formato yyyy-mm-dd (premere invio senza digitare nulla per scadenza tra un mese):");
					data[count++]=Input.readString("Inserisci delle parole chiave separate da un '-' senza spazi in mezzo: ");

					String[] listaChiavi = data[6].split("-");
					bach.aggiungiAnnuncio(new Annuncio(data[0].charAt(0),data[1],u,Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]), data[5], listaChiavi));
					break;
				case 3:	
					int id1= Input.readInt("Inserisci l'id dell'annuncio da eliminare: ");
					bach.rimuoviAnnuncio(id1, u);
					System.out.println(bach.elencoAnnunci());
					break;
				case 4:
					System.out.println("ELENCO ANNUNCI:\n"+bach.elencoAnnunci());
					break;
				case 5:
					String nomeFile= Input.readString("Inserisci il nome del file da cui importare la bacheca: ");
					bach.leggiBacheca(nomeFile);
					System.out.println("ELENCO ANNUNCI:\n"+bach.elencoAnnunci());
					break;
				case 6:
					nomeFile= Input.readString("Inserisci il nome del file su cui scrivere la bacheca: ");
					bach.scriviBacheca(nomeFile);
					System.out.println("Bacheca scritta con successo!\n");
					break;
				case 7:
					bach.cleanupAnnunciScaduti();
					System.out.println("ELENCO ANNUNCI DOPO CLEANUP:\n"+bach.elencoAnnunci());
					break;
				case 8: return;
				}
			}
			catch (GenericErrorException e)
			{
				System.out.println("\n"+e.getMessage()+"!!!\n---------------");
			}
			catch (StringIndexOutOfBoundsException e)
			{
				System.out.println("\nNon sono presenti annunci\n---------------");
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nDevi inserire un valore intero\n---------------");
			}
			catch(FileNotFoundException e)
			{
				System.out.println("\nFile non trovato o formato file non adeguato");
			}
		}	
	}
	
	
	//Metodo per gestire la modalità utente
	private static void modUtente ()
	{
		int modu=0;
		while((modu!=2)||(modu!=1)){
			try{
				System.out.println("Gestione utenti\n");
				System.out.println("1) Aggiungi utente");
				System.out.println("2) Rimuovi utente");
				System.out.println("3) TORNA INDIETRO");
				
				modu = Input.readInt();
				
				if(modu==1){
							String nome = Input.readString("Inserisci il nome utente: ");
							String email = Input.readString("Inserisci l'email dell'utente: ");
							Utente u = new Utente(nome,email);
							Utenti.aggiungiUtente(u);					
				}	
				else if(modu==2){
	
					if (Utenti.numEl()==0) System.out.println("Non ci sono utenti in bacheca\n");
					else{
							System.out.println("Lista degli Utenti:\n"+Utenti.elencoUtenti());
							String email= Input.readString("Inserisci l'email dell'utente da rimuovere: ");
							Utenti.RimuoviUtente(email);
						}	
				}
				else if (modu==3)
					return;
							
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nInserisci un valore intero\n---------------");
			}
			catch (GenericErrorException e)
			{
				System.out.println("\n"+e.getMessage()+"\n---------------");
			}
			}
	}
}