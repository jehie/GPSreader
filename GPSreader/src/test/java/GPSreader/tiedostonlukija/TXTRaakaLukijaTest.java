package GPSreader.tiedostonlukija;

import GPSreader.sovelluslogiikka.Matka;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

        Matka m = lukija.lue("testimatkat/20150118162507.txt");
        assertNotNull(m);
    }

    @Test
    public void OlematonTiedostoaEiLuetaSisaan() {

        Matka m = lukija.lue("keksittytiedosto.txt");
        assertTrue(m == null);
    }

    @Test
    public void KansiotaEiLuetaSisaan() {

        Matka m = lukija.lue("testimatkat/");
        assertTrue(m == null);
    }

    @Test
    public void MetodiPalauttaaNullJosHeaderVaarin() {
        Matka m = lukija.lue("testimatkat/HeaderVaarin.txt");
        assertNull(m);

    }

    @Test
    public void MetodiPalauttaaNullJosErotinVaarin() {
        Matka m = lukija.lue("testimatkat/HeaderVaarin.txt");
        assertNull(m);

    }

    @Test
    public void MuuttujatEiOleNull() {

        Matka m = lukija.lue("testimatkat/20150118162507.txt");
        m.getKuljettumatka();

        assertNotNull(m.getAikaleima());
        assertNotNull(m.getLatitudi());
        assertNotNull(m.getLatitudi());
        assertNotNull(m.getMittauksentarkkuus());

    }

}
