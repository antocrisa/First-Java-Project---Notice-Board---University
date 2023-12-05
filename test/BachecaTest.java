package test;

import java.util.Iterator;

import bacheca22.Annuncio;
import bacheca22.Bacheca;
import bacheca22.GenericErrorException;
import bacheca22.Utente;
import bacheca22.Utenti;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BachecaTest {
	
	Bacheca b;
	
	@Before
	public void testcreazione() {
		b=new Bacheca();
		assertEquals(b.numEl(),0);
		Utenti.crea();
		assertEquals(Utenti.numEl(),0);
	}
	
	@Test
	public void testAggiungiAnnuncio() throws GenericErrorException{
		
		Utente u=new Utente("Anto","anto@gmail.com");
		Utenti.aggiungiUtente(u);
		assertEquals(Utenti.numEl(),1);
		
		Annuncio a=new Annuncio('C',"Chitarra",u,1,50,1,"2022-10-31","Musica");
		b.aggiungiAnnuncio(a);
		
		Annuncio a1=new Annuncio('C',"Batteria",u,1,100,2,"2022-10-30","Elettronica");
		b.aggiungiAnnuncio(a1);
		
		Annuncio a2=new Annuncio('C',"Ferrari",u,1,2000,3,"2022-10-29","Auto");
		b.aggiungiAnnuncio(a2);
		
		assertEquals(b.numEl(),3);
		
	}
	
	
	@Test 
	public void testlistaPropriAnnunci() throws GenericErrorException
	{
		Utente u=new Utente("Anto","anto@gmail.com");
		Utenti.aggiungiUtente(u);
		Annuncio a1=new Annuncio('C',"Mouse",u,1,5,1,"2022-10-31","Elettronica");
		b.aggiungiAnnuncio(a1);
		
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u2);
		Annuncio a2=new Annuncio('V',"Computer",u2,1,500,2,"2022-10-31","Elettronica");
		b.aggiungiAnnuncio(a2);
		
		Annuncio a3=new Annuncio('C',"Chitarra",u,1,10,3,"2022-10-31","Musica");
		b.aggiungiAnnuncio(a3);
		
		assertEquals(b.listaPropriAnnunci(u),"C,Mouse,Anto,anto@gmail.com,1,5,1,2022-10-31,Elettronica\nC,Chitarra,Anto,anto@gmail.com,1,10,3,2022-10-31,Musica");
		assertEquals(b.listaPropriAnnunci(u2),"V,Computer,Bob,bob@gmail.com,1,500,2,2022-10-31,Elettronica");

	}
	@Test
	public void testTrovaAnnunci() throws GenericErrorException {
		
		Utente u=new Utente("Anto","anto@gmail.com");
		Utenti.aggiungiUtente(u);
		Annuncio a1=new Annuncio('C',"Chitarra",u,1,10,1,"2022-10-31","Musica");
		b.aggiungiAnnuncio(a1);
		
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u2);
		Annuncio a2=new Annuncio('C',"Flauto",u2,1,10,2,"2022-10-31","Musica");
		b.aggiungiAnnuncio(a2);
		
		Annuncio a3=new Annuncio('C',"Tromba",u,1,10,3,"2022-10-31","Musica");
		b.aggiungiAnnuncio(a3);
		
					
	}
	@Test
	public void testRimuoviAnnuncio() throws GenericErrorException {
		
		Utente u=new Utente("Anto","anto@gmail.com");
		Annuncio a1=new Annuncio('C',"Cavo",u,1,5,1,"2022-10-31","Elettronica");
		Utenti.aggiungiUtente(u);
		b.aggiungiAnnuncio(a1);
		
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u2);
		Annuncio a2=new Annuncio('C',"Computer",u2,1,2000,2,"2022-10-31","Elettronica");
		b.aggiungiAnnuncio(a2);
		
		Annuncio a3=new Annuncio('V',"Automobile",u,1,2000,3,"2022-10-31","Auto");
		b.aggiungiAnnuncio(a3);
		
		assertEquals(b.numEl(),3);
		
		b.rimuoviAnnuncio(2, u2);
		
		assertEquals(b.numEl(),2);
	
	}
	@Test
	public void testVisualizzaAnnuncio() throws GenericErrorException {
		
		Utente u=new Utente("Anto","anto@gmail.com");
		Annuncio a1=new Annuncio('C',"Tastiera",u,1,10,3,"2022-10-31","Elettronica");
		Utenti.aggiungiUtente(u);
		b.aggiungiAnnuncio(a1);
		
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u2);
		Annuncio a2=new Annuncio('V',"Computer",u2,1,2000,4,"2022-10-31","Elettronica");
		b.aggiungiAnnuncio(a2);
		
		Annuncio a3=new Annuncio('V',"Mercedes",u,1,2000,5,"2022-10-31","Auto");
		b.aggiungiAnnuncio(a3);
		
		assertEquals(b.numEl(),3);
	
	
	}
	@Test
	public void testElencoAnnunci() throws GenericErrorException {
		
		Utente u=new Utente("Anto","anto@gmail.com");
		Annuncio a1=new Annuncio('C',"Mouse",u,1,5,3,"2022-10-31","Elettronica");
		Utenti.aggiungiUtente(u);
		b.aggiungiAnnuncio(a1);
		
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u2);
		Annuncio a2=new Annuncio('V',"Computer",u2,1,500,4,"2022-10-31","Elettronica");
		b.aggiungiAnnuncio(a2);
		
		Annuncio a3=new Annuncio('V',"Mercedes",u,1,2000,5,"2022-10-31","Auto");
		b.aggiungiAnnuncio(a3);
		
		assertEquals(b.numEl(),3);
		assertEquals(b.elencoAnnunci(), """
				C,Mouse,Anto,anto@gmail.com,1,5,3,2022-10-31,Elettronica
				V,Computer,Bob,bob@gmail.com,1,500,4,2022-10-31,Elettronica
				V,Mercedes,Anto,anto@gmail.com,1,2000,5,2022-10-31,Auto""");
	
	}
	@Test
	public void testintersezione() throws GenericErrorException {
		
		Utente u=new Utente("Anto","anto@gmail.com");
		Annuncio a1=new Annuncio('C',"Mouse",u,1,50,3,"2022-10-31","Elettronica");
		Utenti.aggiungiUtente(u);
		b.aggiungiAnnuncio(a1);
		
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u2);
		Annuncio a2=new Annuncio('V',"Computer",u2,1,2000,4,"2022-10-31","Elettronica");
		b.aggiungiAnnuncio(a2);
		
		Annuncio a3=new Annuncio('V',"Mercedes",u,1,2000,5,"2022-10-31","Auto");
		b.aggiungiAnnuncio(a3);
		
		assertEquals(b.numEl(),3);
	
	}
	
	@Test
	public void testIterator() throws GenericErrorException
	{
		Iterator<Annuncio> it1 = b.iterator();
		
		Utente u=new Utente("Anto","anto@gmail.com");
		Annuncio a1=new Annuncio('C',"Mouse",u,1,2000,3,"2022-10-31","Elettronica");
		Utenti.aggiungiUtente(u);
		b.aggiungiAnnuncio(a1);
		
		Utente u2=new Utente("Bob","bob@gmail.com");
		Utenti.aggiungiUtente(u2);
		Annuncio a2=new Annuncio('V',"Computer",u2,1,2000,4,"2022-10-31","Elettronica");
		b.aggiungiAnnuncio(a2);
		
		Annuncio a3=new Annuncio('V',"Mercedes",u,1,2000,5,"2022-10-31","Auto");
		b.aggiungiAnnuncio(a3);
		
		assertEquals(b.numEl(),3);
		assertTrue(it1.hasNext());
		assertEquals(it1.next().getNome(),"Mouse");

		assertTrue(it1.hasNext());
		assertEquals(it1.next().getNome(),"Computer");
		assertTrue(it1.hasNext());

		assertEquals(it1.next().getNome(),"Mercedes");
		assertFalse(it1.hasNext());
		assertNull(it1.next());

	}
	@Test
	public void testRead() throws GenericErrorException
	{
		b.leggiBacheca("./src/bacheca");
		assertEquals(b.elencoAnnunci(), """
				C,Mouse,Anto,anto@gmail.com,1,2000,3,2022-10-31,Elettronica
				V,Mercedes,Anto,anto@gmail.com,1,2000,5,2022-10-31,Auto
				V,Computer,Bob,bob@gmail.com,1,2000,4,2022-10-31,Elettronica""");
	}
	
}