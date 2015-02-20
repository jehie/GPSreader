package GPSreader.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidoijaTest {

    Validoija validoija;

    public ValidoijaTest() {
    }

    @Before
    public void setUp() {
        validoija = new Validoija();

    }

    @Test
    public void validoiKuvaTyypinOikein() {
        boolean test1 = validoija.validoiKuvatyyppi("roadmap");
        boolean test2 = validoija.validoiKuvatyyppi("satellite");
        boolean test3 = validoija.validoiKuvatyyppi("hybrid");
        boolean test4 = validoija.validoiKuvatyyppi("terrain");

        assertTrue(test1);
        assertTrue(test2);
        assertTrue(test3);
        assertTrue(test3);

    }

    @Test
    public void validoiKuvaTyypinPalauttaaFalsejosVaara() {
        boolean test1 = validoija.validoiKuvatyyppi("rasdasd");
        boolean test2 = validoija.validoiKuvatyyppi("sateq213llite");
        boolean test3 = validoija.validoiKuvatyyppi("hybrasd123id");
        boolean test4 = validoija.validoiKuvatyyppi("123123");

        assertTrue(!test1);
        assertTrue(!test2);
        assertTrue(!test3);
        assertTrue(!test3);

    }

    @Test
    public void validoiZoomToimiiOikeinKunnollisellaArvolla() {
        assertTrue(validoija.validoiZOOM(15));
        assertTrue(validoija.validoiZOOM(1));
        assertTrue(validoija.validoiZOOM(20));
    }

    @Test
    public void validoiZoomToimiiOikeinViallisellaArvolla() {
        assertTrue(!validoija.validoiZOOM(-20000));
        assertTrue(!validoija.validoiZOOM(0));
        assertTrue(!validoija.validoiZOOM(1255255));
    }
    
    @Test
    public void validoiLongitudiJaLatitudiToimiiKunnollisellaArvoilla(){
        assertTrue(validoija.validoiLongitudiJaLatitudi(12, 12));
        assertTrue(validoija.validoiLongitudiJaLatitudi(0, 0));
        assertTrue(validoija.validoiLongitudiJaLatitudi(90, 180));
        assertTrue(validoija.validoiLongitudiJaLatitudi(-90, -180));
         assertTrue(validoija.validoiLongitudiJaLatitudi(-90, 0));
    }
    
        @Test
    public void validoiLongitudiJaLatitudiToimiiViallisillaArvoilla(){
        assertTrue(!validoija.validoiLongitudiJaLatitudi(122, 122));
        assertTrue(!validoija.validoiLongitudiJaLatitudi(123, -25888));
        assertTrue(!validoija.validoiLongitudiJaLatitudi(902112, 18000));
        assertTrue(!validoija.validoiLongitudiJaLatitudi(-90, -1800));
         assertTrue(!validoija.validoiLongitudiJaLatitudi(-9, 500));
    }
    
    @Test
    public void validoiInputinToimii(){
        assertTrue(!validoija.validoiOnkoInputInteger("asdasda"));
        assertTrue(!validoija.validoiOnkoInputInteger("124312412asdasd"));
        assertTrue(validoija.validoiOnkoInputInteger("12431241"));
        assertTrue(validoija.validoiOnkoInputInteger("55512"));
        assertTrue(validoija.validoiOnkoInputInteger("-1"));
    }

}
