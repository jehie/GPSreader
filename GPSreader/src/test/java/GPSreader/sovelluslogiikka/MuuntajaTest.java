package GPSreader.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MuuntajaTest {

    Muuntaja muuntaja;

    public MuuntajaTest() {

    }

    @Before
    public void setUp() {
        muuntaja = new Muuntaja();
    }

    @Test
    public void matkanKuukausiNimenaToimiiOikein() {
        String tammikuu = muuntaja.matkanKuukausiTekstina("1");
        String helmikuu = muuntaja.matkanKuukausiTekstina("2");
        String maaliskuu = muuntaja.matkanKuukausiTekstina("3");
        String huhtikuu = muuntaja.matkanKuukausiTekstina("4");
        String toukokuu = muuntaja.matkanKuukausiTekstina("5");
        String kesakuu = muuntaja.matkanKuukausiTekstina("6");
        String heinakuu = muuntaja.matkanKuukausiTekstina("7");
        String elokuu = muuntaja.matkanKuukausiTekstina("8");
        String syyskuu = muuntaja.matkanKuukausiTekstina("9");
        String lokakuu = muuntaja.matkanKuukausiTekstina("10");
        String marraskuu = muuntaja.matkanKuukausiTekstina("11");
        String joulukuu = muuntaja.matkanKuukausiTekstina("12");
        
        
        assertTrue(tammikuu.equals("Tammikuu"));
        assertTrue(helmikuu.equals("Helmikuu"));
        assertTrue(maaliskuu.equals("Maaliskuu"));
        assertTrue(huhtikuu.equals("Huhtikuu"));
        assertTrue(toukokuu.equals("Toukokuu"));
        assertTrue(kesakuu.equals("Kesäkuu"));
        assertTrue(heinakuu.equals("Heinäkuu"));
        assertTrue(elokuu.equals("Elokuu"));
        assertTrue(syyskuu.equals("Syyskuu"));
        assertTrue(lokakuu.equals("Lokakuu"));
        assertTrue(marraskuu.equals("Marraskuu"));
        assertTrue(joulukuu.equals("Joulukuu"));
        
        
    }
    
    
    @Test
    public void matkanKuukausiNimenaToimiiOikeinJosVirheellinenInput() {
        String testi1 = muuntaja.matkanKuukausiTekstina("512");
        String testi2 = muuntaja.matkanKuukausiTekstina("50");
        String testi3 = muuntaja.matkanKuukausiTekstina("13");
        
        assertTrue(testi1.equals("Virhe"));
        assertTrue(testi2.equals("Virhe"));
        assertTrue(testi3.equals("Virhe"));
        
    }
    
        
    @Test
    public void matkanKuukausiNimenaToimiiOikeinJosInputKirjaimia() {
        String testi1 = muuntaja.matkanKuukausiTekstina("asdasdasd");
        String testi2 = muuntaja.matkanKuukausiTekstina("123adasd");
        String testi3 = muuntaja.matkanKuukausiTekstina("asdxq2ads");
        
        assertTrue(testi1.equals("Virhe"));
        assertTrue(testi2.equals("Virhe"));
        assertTrue(testi3.equals("Virhe"));
        
    }

}
