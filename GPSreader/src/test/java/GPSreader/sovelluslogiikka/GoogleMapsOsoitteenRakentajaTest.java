
package GPSreader.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GoogleMapsOsoitteenRakentajaTest {

    GoogleMapsOsoitteenRakentaja gmor;

    public GoogleMapsOsoitteenRakentajaTest() {
    }

    @Before
    public void setUp() {
        gmor = new GoogleMapsOsoitteenRakentaja();
    }

    @Test
    public void osoitteenRakentajaMuodostaaOsoitteenOikein() {
        String oikeaOsoite = "https://maps.googleapis.com/maps/api/staticmap?center=12.1,13.3&maptype=terrain&zoom=12&size=600x600&key=AIzaSyDqqBJ4sBUY34znGoJA9IXQ3e-n4iEKzuU";
        String osoite = gmor.rakennaOsoite(12.1, 13.3, "terrain", 12);
        System.out.println(oikeaOsoite);
        System.out.println(osoite);
        assertEquals(oikeaOsoite, osoite);

    }

    @Test
    public void virheJosLatitudiJaLongitudiVaarin() {

        String osoite = gmor.rakennaOsoite(1000, 5000, "terrain", 12);

        assertEquals("virhe", osoite);

    }

    @Test
    public void virheJosZoomVaarin() {

        String osoite = gmor.rakennaOsoite(12.1, 50, "terrain", 500);

        assertEquals("virhe", osoite);

    }

    @Test
    public void virheJosKuvatyyppiVaarin() {

        String osoite = gmor.rakennaOsoite(12.1, 50, "testilol", 12);

        assertEquals("virhe", osoite);

    }

}
