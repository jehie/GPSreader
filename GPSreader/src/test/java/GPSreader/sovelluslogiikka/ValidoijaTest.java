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
    public void validoiLongitudiJaLatitudiToimiiKunnollisellaArvoilla() {
        assertTrue(validoija.validoiLongitudiJaLatitudi(12, 12));
        assertTrue(validoija.validoiLongitudiJaLatitudi(0, 0));
        assertTrue(validoija.validoiLongitudiJaLatitudi(90, 180));
        assertTrue(validoija.validoiLongitudiJaLatitudi(-90, -180));
        assertTrue(validoija.validoiLongitudiJaLatitudi(-90, 0));
    }

    @Test
    public void validoiLongitudiJaLatitudiToimiiViallisillaArvoilla() {
        assertTrue(!validoija.validoiLongitudiJaLatitudi(122, 122));
        assertTrue(!validoija.validoiLongitudiJaLatitudi(123, -25888));
        assertTrue(!validoija.validoiLongitudiJaLatitudi(902112, 18000));
        assertTrue(!validoija.validoiLongitudiJaLatitudi(-90, -1800));
        assertTrue(!validoija.validoiLongitudiJaLatitudi(-9, 500));
    }

    @Test
    public void validoiInputinToimii() {
        assertTrue(!validoija.validoiOnkoInputInteger("asdasda"));
        assertTrue(!validoija.validoiOnkoInputInteger("124312412asdasd"));
        assertTrue(validoija.validoiOnkoInputInteger("12431241"));
        assertTrue(validoija.validoiOnkoInputInteger("55512"));
        assertTrue(validoija.validoiOnkoInputInteger("-1"));
    }

    @Test
    public void TallennetunMatkanRiviSisaltaaOikeatMerkitToimii() {
        assertTrue(validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit("60.224515,25.021293,61382406308468,6.0") == true);
        assertTrue(validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit("60.224515,25.021293,61382406asdasd308468,6.0") == false);
        assertTrue(validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit("60.224515[][][]XASD,25.021293,61382406asdasd308468,6.0") == false);
        assertTrue(validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit("asdASDASDX") == false);
        assertTrue(validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit("123123123") == false);
        assertTrue(validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit(".........") == false);
        assertTrue(validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit("60.224515,25.021293,,,61382406308468,6.0") == false);

    }

    @Test
    public void RaakaMatkanRiviSisaltaaOikeatMerkitToimii() {

        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("2014-01-18T15:02:07Z,60.228310,25.022108,110.800003,19.000000,298.100006,0.707107") == true);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("2014-01-18T15:02:07Z,60.228310,25.022108,110.800003,,,19.000000,298.100006,0.707107") == false);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("60.224515,25.021293,61382406308468,6.0") == false);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("60.224515,25.021293,61382406asdasd308468,6.0") == false);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("60.224515[][][]XASD,25.021293,61382406asdasd308468,6.0") == false);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("asdASDASDX") == false);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("123123123") == false);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit(".........") == false);
        assertTrue(validoija.RaakaMatkanRiviSisaltaaOikeatMerkit("60.224515,25.021293,,,61382406308468,6.0") == false);
    }

    @Test
    public void tarkistaOnkoPaivaJarkevaToimiiOikein() {
        assertTrue(validoija.tarkistaOnkoPaivaJarkeva(12) == true);
        assertTrue(validoija.tarkistaOnkoPaivaJarkeva(1) == true);
        assertTrue(validoija.tarkistaOnkoPaivaJarkeva(31) == true);
        assertTrue(validoija.tarkistaOnkoPaivaJarkeva(0) == false);
        assertTrue(validoija.tarkistaOnkoPaivaJarkeva(32) == false);
        assertTrue(validoija.tarkistaOnkoPaivaJarkeva(-1) == false);

    }

    @Test
    public void tarkistaOnkoVuosiJarkevaToimiiOikein() {
        assertTrue(validoija.tarkistaOnkoVuosiJarkeva(2011) == true);
        assertTrue(validoija.tarkistaOnkoVuosiJarkeva(2015) == true);
        assertTrue(validoija.tarkistaOnkoVuosiJarkeva(2019) == true);
        assertTrue(validoija.tarkistaOnkoVuosiJarkeva(0) == false);
        assertTrue(validoija.tarkistaOnkoVuosiJarkeva(2009) == false);
        assertTrue(validoija.tarkistaOnkoVuosiJarkeva(-1) == false);

    }

    @Test
    public void tarkistaOnkoKuukausiJarkevaToimiiOikein() {
        assertTrue(validoija.tarkistaOnkoKuukausiJarkeva(12) == true);
        assertTrue(validoija.tarkistaOnkoKuukausiJarkeva(1) == true);
        assertTrue(validoija.tarkistaOnkoKuukausiJarkeva(6) == true);
        assertTrue(validoija.tarkistaOnkoKuukausiJarkeva(0) == false);
        assertTrue(validoija.tarkistaOnkoKuukausiJarkeva(13) == false);
        assertTrue(validoija.tarkistaOnkoKuukausiJarkeva(-1) == false);

    }
}
