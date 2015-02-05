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
    
    
    public MatkaKokoelmaTest() {
    }
    
    @Before
    public void setUp() {
        TXTTallennettuLukija txtl = new TXTTallennettuLukija();
        mk = txtl.lueKaikkiTallennetutTiedostot();
    }

    @Test
    public void matkojenSummaOnSuurempiKuinYhdenMatkanSumma() {
        assertTrue(mk.getMatkojenpituus()>mk.getMatkat().get(1).getKuljettumatka());
    }
    
    @Test
    public void PoistaMatkaNimellaPoistaa(){
        int montaAlussa = mk.getMatkat().size();
        String poistettava = mk.getMatkat().get(1).getMatkanNimi();
        mk.poistaMatkaNimella(poistettava);
        assertTrue(montaAlussa> mk.getMatkat().size());
        
    }
    @Test
    public void PalauttaaOikeanMatkan(){
        Matka m = mk.getMatkat().get(1);
        String etsi = m.getMatkanNimi();
        
        Matka em = mk.getMatkaNimella(etsi);
        
        assertTrue(em.getMatkanNimi().equals(etsi));
    }
    @Test
    public void PoistaaEpatarkkatMittaukset(){
        mk.PoistaEpaTarkatMittaukset(20);
        
        boolean onkoyli20 = false;
        for(Matka m:mk.getMatkat()){
            for(Double i:m.getMittauksentarkkuus()){
                if(i>20.0){
                    onkoyli20=true;
                }
            }
        }
        
        assertEquals(false, onkoyli20);
    }
    
    @Test
    public void matkojenPituusNollajosTyhja(){
        MatkaKokoelma mk2 = new MatkaKokoelma();
        String tulos = String.valueOf(mk2.getMatkojenpituus());
        assertEquals(tulos, "0.0");
    }
    
    @Test
    public void matkojenNopeusNollajosTyhja(){
        MatkaKokoelma mk2 = new MatkaKokoelma();
        String tulos = String.valueOf(mk2.getMatkojenkesto());
        assertEquals(tulos, "0.0");
    }
    
}
