/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPSreader.tiedostonlukija;

import GPSreader.sovelluslogiikka.Matka;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesse
 */
public class TXTRaakaLukijaTest {
    TXTRaakaLukija lukija;
    public TXTRaakaLukijaTest() {
    }

    @Before
    public void setUp() {
        lukija = new TXTRaakaLukija();
    }

    @Test
    public void TiedostoLuetaanOikeinSisaanOlemassaOlevallaTiedostolla() {
        
        Matka m = lukija.lue("20150118162507.txt");
        assertNotNull(m);

    }

//    @Test
//    public void TiedostoaEiOlemassa() {
//         
//        Matka m = lukija.lue("asdasd.txt");
//        fail("Tiedostoa ei löydy");
//    }
    
    @Test
    public void MetodiPalauttaaNullJosHeaderVaarin(){
        Matka m = lukija.lue("HeaderVaarin.txt");
        assertNull(m);
        
    }
    
      @Test
    public void MetodiPalauttaaNullJosErotinVaarin(){
        Matka m = lukija.lue("HeaderVaarin.txt");
        assertNull(m);
        
    }

    @Test
    public void MuuttujatEiOleNull() {
       
        Matka m = lukija.lue("20150118162507.txt");
        m.getKuljettumatka();

        assertNotNull(m.getAikaleima());
        assertNotNull(m.getLatitudi());
        assertNotNull(m.getLatitudi());
        assertNotNull(m.getMittauksentarkkuus());

    }

}
