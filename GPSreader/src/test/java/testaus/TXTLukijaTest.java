package testaus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.Matka;
import tiedostonlukija.TXTLukija;


public class TXTLukijaTest {
    
    public TXTLukijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


     
     @Test
     public void TiedostoLuetaanOikeinSisaanOlemassaOlevallaTiedostolla(){
         TXTLukija lukija = new TXTLukija("20150118162507.txt");
         Matka m = lukija.lue();
         assertNotNull(m);
        
     }
     @Test
     public void TiedostoaEiOlemassa(){
         TXTLukija lukija = new TXTLukija("asdasd.txt");
         fail("Tiedostoa ei löydy");
     }
     
     @Test
     public void MuuttujatEiOleNull(){
         TXTLukija lukija = new TXTLukija("20150118162507.txt");
         Matka m = lukija.lue();
         m.getKuljettumatka();
         
         assertNotNull(m.getAikaleima());
         assertNotNull(m.getLatitudi());
         assertNotNull(m.getLatitudi());
         assertNotNull(m.getMittauksentarkkuus());
         
     }
     
}
