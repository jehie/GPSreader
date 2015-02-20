package GPSreader.sovelluslogiikka;

import java.util.ArrayList;
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
    public void osoitteenRakentajaMarkereillaMuodostaaOsoitteenOikein() {
        String oikeaOsoite = "https://maps.googleapis.com/maps/api/staticmap?center=12.1,13.3&maptype=terrain&zoom=12&size=600x600&markers=color:blue%7Clabel:A%7C12.1,13.3&key=AIzaSyDqqBJ4sBUY34znGoJA9IXQ3e-n4iEKzuU";
        String osoite = gmor.rakennaOsoiteMarkereilla(12.1, 13.3, "terrain", 12);
        System.out.println(oikeaOsoite);
        System.out.println(osoite);
        assertEquals(oikeaOsoite, osoite);
    }

    @Test
    public void osoitteenRakentajaPoluillaMuodostaaOsoitteenOikein() {
        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();

        lat.add(12.1);
        lat.add(12.2);
        lat.add(12.3);
        lon.add(13.1);
        lon.add(13.2);
        lon.add(13.3);

        String oikeaOsoite = "https://maps.googleapis.com/maps/api/staticmap?center=12.1,13.1&maptype=terrain&size=600x600&path=color:0x0000ff|weight:5|12.1,13.1|12.3,13.3&key=AIzaSyDqqBJ4sBUY34znGoJA9IXQ3e-n4iEKzuU";
        String osoite = gmor.rakennaOsoitePolulla(lat, lon, "terrain");
        System.out.println(oikeaOsoite);
        System.out.println(osoite);
        assertEquals(oikeaOsoite, osoite);
    }

    @Test
    public void virheJosLatitudiJaLongitudiVaarin() {
        String osoite = gmor.rakennaOsoite(1000, 5000, "terrain", 12);
        String osoite2 = gmor.rakennaOsoiteMarkereilla(-125, 50000, "terrain", 15);
        assertEquals("virhe", osoite);
        assertEquals("virhe", osoite);
    }

    @Test
    public void virheJosZoomVaarin() {
        String osoite = gmor.rakennaOsoite(12.1, 50, "terrain", 500);
        String osoite2 = gmor.rakennaOsoiteMarkereilla(15, 15, "terrain", 500);
        assertEquals("virhe", osoite);
    }

    @Test
    public void virheJosKuvatyyppiVaarin() {
        String osoite = gmor.rakennaOsoite(12.1, 50, "testilol", 12);
        String osoite2 = gmor.rakennaOsoiteMarkereilla(15, 15, "terrainxxxx", 15);
        assertEquals("virhe", osoite);
        assertEquals("virhe", osoite2);
    }

    @Test
    public void muodostaOsoitePolullaEiToimiJosVirhe() {
        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        
        lat.add(5000.1);
        lon.add(5000.1);
        String osoite = gmor.rakennaOsoitePolulla(lat, lon, "terrain");
        assertEquals("virhe", osoite);
        
    }

}
