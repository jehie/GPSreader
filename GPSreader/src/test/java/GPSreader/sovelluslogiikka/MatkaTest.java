package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTRaakaLukija;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Jesse
 */
public class MatkaTest {
    
    public MatkaTest() {
    }
    
       TXTRaakaLukija lukija = new TXTRaakaLukija();
    Matka m;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        m = lukija.lue("20150118162507.txt");
    }

    @After
    public void tearDown() {
    }



    @Test
    public void PoistaEpatarkatMittauksetToimii() {
        int mittauksiaEnnenPoistoa = m.getLatitudi().size();

        //Aina mittauksia jotka huonompia kuin 10
        m.poistaEpaTarkatMittaukset(10);

        int mittauksiaPoistonJalkeen = m.getLatitudi().size();

        assertTrue(mittauksiaEnnenPoistoa > mittauksiaPoistonJalkeen);
    }

    @Test
    public void matkaLasketaanUudelleenMittaustenPoistonJalkeen() {
        double matkaAluksi = m.getKuljettumatka();
        m.poistaEpaTarkatMittaukset(10);
        double matkaLopuksi = m.getKuljettumatka();

        assertTrue(matkaAluksi > matkaLopuksi);
    }
    @Test
    public void muutaMatkanAikaaToimii(){
       int vuosi =  m.getAikaleima().get(0).getYear();
       m.muutaAika("2004", "1", "25");
        int vuosi_muutettu = m.getAikaleima().get(0).getYear();
        
        assertTrue(vuosi_muutettu<vuosi);
    }
    
}
