package test;



import bacheca22.GenericErrorException;
import bacheca22.Utente;
import bacheca22.Utenti;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtentiTest {
	
	@Before
	public void creazione() {
		Utenti.crea();
	}
	
	@Test
	public void testAggiungi() throws GenericErrorException {
		
		Utente u1=new Utente("Anto","anto@gmail.com");
		Utenti.aggiungiUtente(u1);
		Utente u2=new Utente("Anto","anto@gmail.com");
		Utenti.aggiungiUtente(u2);
		
		assertEquals(Utenti.numEl(),1);
	}
	
	@Test
	public void rimuoviUtentetest() throws GenericErrorException {
		
		assertEquals(Utenti.numEl(),0);
		Utente u1=new Utente("Anto","anto@gmail.com");
		Utenti.aggiungiUtente(u1);
		assertEquals(Utenti.numEl(),1);
		Utenti.RimuoviUtente("anto@gmail.com");
		assertEquals(Utenti.numEl(),0);
		
	}
	
	@Test
	public void verificaUtentetest() throws GenericErrorException {
		
		Utente u1=new Utente("Anto","anto@gmail.com");
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u1);
		Utenti.aggiungiUtente(u2);
		Utente u3=new Utente("Luca","luca@gmail.com");
		assertTrue(Utenti.containsUtente(u2.getEmail()));
		assertFalse(Utenti.containsUtente(u3.getEmail()));
		
	}
	@Test
	public void ElencoUtentiTest() throws GenericErrorException {
		
		Utente u1=new Utente("Anto","anto@gmail.com");
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u1);
		Utenti.aggiungiUtente(u2);
		assertEquals(Utenti.elencoUtenti(), """
				anto@gmail.com
				bob@gmail.com
				""");
		
	}
	@Test
	public void GetUtenteTest() throws GenericErrorException{
		Utente u1=new Utente("Anto","anto@gmail.com");
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u1);
		Utenti.aggiungiUtente(u2);
		assertEquals(Utenti.getUtente("anto@gmail.com").getEmail(),u1.getEmail());
		assertEquals(Utenti.getUtente("anto@gmail.com").getNome(),u1.getNome());

	}


}