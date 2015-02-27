package GPSreader.sovelluslogiikka;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Luokka validoi erilaisia Matkaan ja GoogleMaps osoitteeseen liittyviä
 * muuttujia
 */
public class Validoija {

    /**
     * Tarkistaa että annetut parametrit ovat sopivia Google Maps rajapinnalle
     *
     * @param latitudi Kartan keskipisteen latitudi
     * @param longitudi Kartan keskipisteen longitudi
     * @param kuvatyyppi Kartan tyyppi: terrain, satellite, roadmap tai hybrid
     * @param zoom Zoomauksen taso: Minimi: 1 ja maksimi: 20
     * @return Onnistuiko osoitteen muodostaminen
     */
    public boolean validoiGoogleMapsOsoiteTiedot(double latitudi, double longitudi, String kuvatyyppi, int zoom) {
        boolean latlonOK = validoiLongitudiJaLatitudi(latitudi, longitudi);
        boolean kuvatyyppiOK = validoiKuvatyyppi(kuvatyyppi);
        boolean zoomOK = validoiZOOM(zoom);
        if (latlonOK && kuvatyyppiOK && zoomOK) {
            return true;
        }
        return false;
    }

    /**
     * Tarkistaa Zoom parametrin olevan sallituissa rajoissa
     *
     * @param zoom Zoomauksen taso: Minimi: 1 ja maksimi: 20
     * @return Onko zoom-muuttujan arvo ok
     */
    public boolean validoiZOOM(int zoom) {
        if (zoom <= 20 && zoom > 0) {
            return true;
        }
        return false;
    }

    /**
     * Tarkistaa Kuvatyyppi parametrin olevan jokin sallituista
     *
     * @param kuvatyyppi Kartan tyyppi: terrain, satellite, roadmap tai hybrid
     * @return Onko kuvatyyppi hyväksytty
     */
    public boolean validoiKuvatyyppi(String kuvatyyppi) {
        if (kuvatyyppi.equals("satellite")) {
            return true;
        } else if (kuvatyyppi.equals("terrain")) {
            return true;
        } else if (kuvatyyppi.equals("roadmap")) {
            return true;
        } else if (kuvatyyppi.equals("hybrid")) {
            return true;
        }

        return false;

    }

    /**
     * Tarkistaa Latitudi ja Longitudi-arvojen olevan sallituilla väleillä
     *
     *
     * @param latitudi Kartan keskipisteen latitudi
     * @param longitudi Kartan keskipisteen longitudi
     * @return onko Longitudi ja Latitudi-arvot järkeviä
     */
    public boolean validoiLongitudiJaLatitudi(double latitudi, double longitudi) {
        boolean latitudiOK = false;
        if (latitudi >= -90.0 && latitudi <= 90) {
            latitudiOK = true;
        }
        boolean longitudiOK = false;

        if (longitudi >= -180 && longitudi <= 180) {
            longitudiOK = true;
        }

        if (latitudiOK && longitudiOK) {
            return true;
        }
        return false;
    }

    /**
     * Validoi onko luettava input Integer-tyyppinen
     *
     * @param input Tarkistettava merkkijono
     *
     * @return Onko input integer
     *
     */
    public boolean validoiOnkoInputInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Tarkistaa sisältääkö rivi tarpeelliset merkit eikä kiellettyjä merkkejä.
     *
     * @param rivi Rivi jolta tarkistetaan merkit
     *
     * @return Sisältääkö rivi vain sallittuja merkkejä
     *
     */
    public boolean tarkistaErotin(String rivi) {
        if (rivi.contains(",") && rivi.contains(".") && !rivi.contains("@") && !rivi.contains("€")) {
            System.out.println("Erotin ok");
            return true;
        }

        return false;
    }

    /**
     * Tarkistaa onko tiedoston Header eli ensimmäinen rivi kunnollinen.
     *
     * @param ensimmainenrivi Luettavan tiedoston ensimmäinen rivi, jonka tulisi
     * sisältää sarakkeiden nimet
     * @return Onko header kunnossa
     *
     */
    public boolean tarkistaHeaderRaakaMatkalta(String ensimmainenrivi) {

        if (ensimmainenrivi.equals("time,lat,lon,elevation,accuracy,bearing,speed")) {
            System.out.println("Header ok");
            return true;
        }
        return false;
    }

    /**
     * Tarkistaa sisältääkö rivi mitään kirjaimia.
     *
     * @param rivi tarkistettava rivi(String)
     *
     * @return Sisältääkö rivi kirjaimia
     *
     */
    public boolean TallennetunMatkanRiviSisaltaaOikeatMerkit(String rivi) {
        int pist = rivi.length() - rivi.replace(".", "").length();
        int pilk = rivi.length() - rivi.replace(",", "").length();
        if (pist != 3 || pilk != 3) {
            return false;
        }
        return rivi.matches("^[0-9,.]+$");

    }

    public boolean RaakaMatkanRiviSisaltaaOikeatMerkit(String rivi) {
        int pist = rivi.length() - rivi.replace(".", "").length();
        int pilk = rivi.length() - rivi.replace(",", "").length();
        int T = rivi.length() - rivi.replace("T", "").length(); 
        if (pist != 6 || pilk != 6 || T!=1) {
            System.out.println(rivi);
            System.out.println("Merkkejä väärä määr");
            return false;
        }
        System.out.println("Merkit ok");
        return rivi.matches("^[0-9,.T:Z-]+$");

    }



}
