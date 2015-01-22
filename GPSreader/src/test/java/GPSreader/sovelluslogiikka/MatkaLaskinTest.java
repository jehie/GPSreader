/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTRaakaLukija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesse
 */
public class MatkaLaskinTest {

    TXTRaakaLukija lukija = new TXTRaakaLukija();
    Matka m;

    public MatkaLaskinTest() {
    }

    @Before
    public void setUp() {
        m = lukija.lue("20150118162507.txt");
    }

    @Test
    public void matkaLasketaan() {
        m.laskeKuljettuMatka();
        assertTrue(m.getKuljettumatka() > 0);
    }

    @Test
    public void laskeEtaisyysPalauttaaJotain() {

    }

}
