/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTRaakaLukija;
import GPSreader.tiedostonlukija.TXTTallennettuLukija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesse
 */
public class MatkaLaskinTest {

    TXTRaakaLukija lukija = new TXTRaakaLukija();
    TXTTallennettuLukija tlukija = new TXTTallennettuLukija();
    MatkaKokoelma mk;
    MatkaLaskin ml;
    Matka m;
    Matka m2;

    public MatkaLaskinTest() {
    }

    @Before
    public void setUp() {
        ml=new MatkaLaskin();
        m = lukija.lue("testimatkat\\20150118162507.txt");
        m2= tlukija.lue("testimatkat\\2015_1_18_14.txt");
    }

    @Test
    public void matkaLasketaan() {
        m.getKuljettumatka();
        assertTrue(m.getKuljettumatka() > 0);
    }

    @Test
    public void laskeEtaisyysPalauttaaJotain() {
        double kesto = ml.laskeMatkanKesto(m2.getAikaleima());
        assertNotNull(kesto);
    }
    
    @Test
    public void LaskekestoToimii(){
        assertTrue(m.getKesto()>0);
    }
    
     @Test
    public void LaskeNopeusToimiiOikein(){
       
        double etaisyys = ml.laskeEtaisyys(m2.getLatitudi().get(0), m2.getLongitudi().get(0), m2.getLatitudi().get(1),m2.getLongitudi().get(1));
        double nopeus = ml.laskeNopeus(m2.getAikaleima().get(0), m2.getAikaleima().get(1), etaisyys);
         ;
        nopeus = Math.round(nopeus*100.0)/100.0;
        String vertaus = String.valueOf(nopeus);
        assertEquals("6.25", vertaus);
    }
    
    public void laskeMatkanKestoPalauttaaNollajosNull(){
        double kesto = ml.laskeMatkanKesto(null);
        assertEquals(0.0, kesto);
    }

}
