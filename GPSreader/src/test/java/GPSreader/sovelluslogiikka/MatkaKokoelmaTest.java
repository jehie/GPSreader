package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTTallennettuLukija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka testaa MatkaKokoelmat luokkaa
 */
public class MatkaKokoelmaTest {

    MatkaKokoelma mk;
     TXTTallennettuLukija txtl = new TXTTallennettuLukija();

    public MatkaKokoelmaTest() {
    }

    @Before
    public void setUp() {
       
        mk = new MatkaKokoelma();

        mk.lisaaMatka(txtl.lueTallennettuTiedosto("testimatkat/kokoelmatesti/2015_1_18_14.txt"));
        mk.lisaaMatka(txtl.lueTallennettuTiedosto("testimatkat/kokoelmatesti/2015_1_19_10.txt"));
        System.out.println("Matkojen summa on : " +mk.getMatkat().size());
    }

    @Test
    public void matkojenSummaOnSuurempiKuinYhdenMatkanSumma() {
        assertTrue(mk.getMatkojenpituus() > mk.getMatkat().get(1).getKuljettumatka());
    }

    @Test
    public void PoistaMatkaNimellaPoistaaJosLöytyy() {
        int montaAlussa = mk.getMatkat().size();
        String poistettava = mk.getMatkat().get(0).getMatkanNimi();
        mk.poistaMatkaNimella(poistettava, "testimatkat");
        assertTrue(montaAlussa > mk.getMatkat().size());

    }

    @Test
    public void MatkaKokoelmaLukeeKaikkiMatkatSisaan() {
        MatkaKokoelma mk2 = txtl.lueKaikkiTallennetutTiedostot("testimatkat/kokoelmatesti");
        
  
        assertTrue(mk2.getMatkat().size()==2);

    }

    @Test
    public void PoistaMatkaNimellaPalauttaaFalseJosEiLöydy() {
        assertTrue(!mk.poistaMatkaNimella("testinimi", "testimatkat"));
    }

    @Test
    public void PoistaMatkaNimellaPalauttaaTrueJosLoytyy() {
        assertTrue(mk.poistaMatkaNimella(mk.getMatkat().get(0).getMatkanNimi(), "testimatkat"));
    }

    @Test
    public void PalauttaaOikeanMatkan() {
        Matka m = mk.getMatkat().get(0);
        String etsi = m.getMatkanNimi();

        Matka em = mk.getMatkaNimella(etsi);

        assertTrue(em.getMatkanNimi().equals(etsi));
    }

    @Test
    public void PalauttaaNullJosEiLöydyMatkaa() {
        Matka etsittava = mk.getMatkaNimella("EiLoydyTataNimea");

        assertTrue(etsittava == null);
    }

    @Test
    public void PoistaaEpatarkkatMittaukset() {
        mk.PoistaEpaTarkatMittaukset(20, "testimatkat/");

        boolean onkoyli20 = false;
        for (Matka m : mk.getMatkat()) {
            for (Double i : m.getMittauksentarkkuus()) {
                if (i > 20.0) {
                    onkoyli20 = true;
                }
            }
        }

        assertEquals(false, onkoyli20);
    }

    @Test
    public void matkojenPituusNollajosTyhja() {
        MatkaKokoelma mk2 = new MatkaKokoelma();
        String tulos = String.valueOf(mk2.getMatkojenpituus());
        assertEquals(tulos, "0.0");
    }

    @Test
    public void matkojenNopeusNollajosTyhja() {
        MatkaKokoelma mk2 = new MatkaKokoelma();
        String tulos = String.valueOf(mk2.getMatkojenkesto());
        assertEquals(tulos, "0.0");
    }

    @Test
    public void matkojenKeskiNopeusOnJarkeva() {
        double kn = mk.getMatkojenKeskinopeus();
        assertTrue(kn > 3.0);
    }

}
