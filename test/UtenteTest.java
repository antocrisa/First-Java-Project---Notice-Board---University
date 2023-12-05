package test;


import bacheca22.Utente;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class  UtenteTest{
		
		Utente u;
			
			@Test
			public void testCostruttore() {
				
				u=new Utente("Anto","anto@gmail.com");
				
				assertEquals("Anto",u.getNome());
				assertEquals("anto@gmail.com", u.getEmail());
			}			
	}