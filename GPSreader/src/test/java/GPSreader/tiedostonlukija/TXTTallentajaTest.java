package GPSreader.tiedostonlukija;

import GPSreader.sovelluslogiikka.Matka;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TXTTallentajaTest {

    Matka m;

    public TXTTallentajaTest() {

    }

    @Before
    public void setUp() {
        TXTRaakaLukija luk = new TXTRaakaLukija();
        m = luk.lue("20150118162507.txt");
    }
    
    @Test
    public void TallentajaPalauttaaTrueKunTiedostoKirjoitettu(){
        TXTTallentaja tal = new TXTTallentaja();
        boolean onnistuiko =  tal.kirjoitaMatkaTiedostoon(m);
        
        
        File testitiedosto = new File("C:\\Users\\Jesse\\Documents\\Javalabra\\GPSreader\\GPSreader\\matkat\\2014_1_18_14.txt");
        testitiedosto.delete();
        
        
        assertTrue(onnistuiko);
        
    }

}
