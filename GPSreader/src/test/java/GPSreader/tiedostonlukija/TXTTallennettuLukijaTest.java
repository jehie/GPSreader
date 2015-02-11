package GPSreader.tiedostonlukija;

import GPSreader.sovelluslogiikka.Matka;
import GPSreader.sovelluslogiikka.MatkaKokoelma;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TXTTallennettuLukijaTest {

    TXTTallennettuLukija tl;

    public TXTTallennettuLukijaTest() {
    }

    @Before
    public void setUp() {
        tl = new TXTTallennettuLukija();
    }

    @Test
    public void TiedostoLuetaanOikeinSisaanOlemassaOlevallaTiedostolla() {

        Matka m = tl.lue("C:\\Users\\Jesse\\Documents\\Javalabra\\GPSreader\\GPSreader\\testimatkat\\2015_1_18_14.txt");
        assertNotNull(m);

    }

    @Test
    public void LuettuTiedostoSisältääRiveja() {

        Matka m = tl.lue("C:\\Users\\Jesse\\Documents\\Javalabra\\GPSreader\\GPSreader\\testimatkat\\2015_1_18_14.txt");
        assertTrue(m.getAikaleima().size()>5);
        
    }
    
//    @Test
//    public void LukeeKaikkiKansiossaOlevatTiedostot() {
//
//        MatkaKokoelma mk = tl.lueKaikkiTallennetutTiedostot();
//        assertTrue(mk.getMatkat().size()==2);
//        
//
//    }

}
