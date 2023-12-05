package test;

import bacheca22.Annuncio;
import bacheca22.GenericErrorException;
import bacheca22.Utente;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AnnuncioTest {

	Annuncio ann, ann1;
		
	@Before
	public void testCostruttore() throws GenericErrorException
	{
		ann = new Annuncio('C',"Chitarra",new Utente("Anto", "anto@gmail.com"),1,50,1,"2022-10-31","Musica");
		assertEquals(ann.getID(),1);
		assertEquals(ann.getEmailUtente(),"anto@gmail.com");
		assertEquals(ann.getT(),'C');
		assertEquals(ann.getParoleChiave().size(),1);
		assertEquals(ann.getNome(),"Chitarra");
		
		ann1 = new Annuncio('V',"Cavo",new Utente("Bob", "bob@gmail.com"),1,20,2,"2022-11-07","Elettronica");
		assertEquals(ann1.getID(),2);
		assertEquals(ann1.getEmailUtente(),"bob@gmail.com");
		assertEquals(ann1.getT(),'V');
		assertEquals(ann1.getParoleChiave().size(),1);
		assertEquals(ann1.getNome(),"Cavo");
		
	}
	

	@Test
	public void testtoString() 
	{
		assertEquals(ann.toString(),"C,Chitarra,Anto,anto@gmail.com,1,50,1,2022-10-31,Musica");
		assertEquals(ann1.toString(),"V,Cavo,Bob,bob@gmail.com,1,20,2,2022-11-07,Elettronica");
	}
		


}