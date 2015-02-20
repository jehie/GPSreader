package GPSreader.sovelluslogiikka;

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
     *
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
     *
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
     *
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
     *
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
    
    public boolean validoiOnkoInputInteger(String input){
     try {
         Integer.parseInt(input);
         return true;
     } catch (NumberFormatException e){
         return false;
     }
    }

}
