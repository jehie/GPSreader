package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTTallennettuLukija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
