package GPSreader.sovelluslogiikka;

/**
 * Muodostaa oikeanlaisia staattisia Google Maps osoitteita, joilla Google Maps
 * rajapinnasta voidaan noutaa karttoja
 */
public class GoogleMapsOsoitteenRakentaja {

    final private String osoitteenalku = "https://maps.googleapis.com/maps/api/staticmap?center=";
    final private String apikey = "&key=AIzaSyDqqBJ4sBUY34znGoJA9IXQ3e-n4iEKzuU";

    /**
     * Muodostaa osoitteen jolla haetaan kartta tietyltä alueelta. Ei muodosta reittiä vaan pelkän kartan alueelta ilman merkkejä.
     *
     * @param latitudi Kartan keskipisteen latitudi
     * @param longitudi Kartan keskipisteen longitudi
     * @param kuvatyyppi Kartan tyyppi: terrain, satellite, roadmap tai hybrid
     * @param zoom Zoomauksen taso: Minimi: 1 ja maksimi: 20
     */
    public String rakennaOsoite(double latitudi, double longitudi, String kuvatyyppi, int zoom) {
        Validoija vd = new Validoija();
        boolean validointiok = vd.validoiGoogleMapsOsoiteTiedot(latitudi, longitudi, kuvatyyppi, zoom);
        System.out.println("Validointi on : " + validointiok);
        if (!validointiok) {
            return "virhe";
        }

        String osoite = osoitteenalku;
        osoite = osoite + latitudi + "," + longitudi + "&maptype=" + kuvatyyppi + "&zoom=" + zoom + "&size=600x600" + apikey;
        return osoite;

    }

}
