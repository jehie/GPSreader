package testaus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.Matka;
import tiedostonlukija.TXTLukija;

public class MatkaTest {
    TXTLukija lukija = new TXTLukija("20150118162507.txt");
    Matka m;

    public MatkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        
        m = lukija.lue();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void matkaLasketaan(){
        m.laskeKuljettuMatka();
        assertTrue(m.getKuljettumatka()>0);  
    }
    
    @Test
    public void PoistaEpatarkatMittauksetToimii(){
        int mittauksiaEnnenPoistoa = m.getLatitudi().size();
        
        //Aina mittauksia jotka huonompia kuin 10
        m.poistaEpaTarkatMittaukset(10);
        
        int mittauksiaPoistonJalkeen = m.getLatitudi().size();
        
        assertTrue(mittauksiaEnnenPoistoa>mittauksiaPoistonJalkeen);
    }
    
    @Test
    public void matkaLasketaanUudelleenMittaustenPoistonJalkeen(){
        m.laskeKuljettuMatka();
        double matkaAluksi = m.getKuljettumatka();
        m.poistaEpaTarkatMittaukset(10);
        m.laskeKuljettuMatka();
        double matkaLopuksi = m.getKuljettumatka();
        
        assertTrue(matkaAluksi>matkaLopuksi);
    }
    
}
