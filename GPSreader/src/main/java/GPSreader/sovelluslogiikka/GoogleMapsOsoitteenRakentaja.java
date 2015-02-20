package GPSreader.sovelluslogiikka;

import java.util.ArrayList;

/**
 * Muodostaa oikeanlaisia staattisia Google Maps osoitteita, joilla Google Maps
 * rajapinnasta voidaan noutaa karttoja
 */
public class GoogleMapsOsoitteenRakentaja {

    final private String osoitteenalku = "https://maps.googleapis.com/maps/api/staticmap?center=";
    final private String apikey = "&key=AIzaSyDqqBJ4sBUY34znGoJA9IXQ3e-n4iEKzuU";

    /**
     * Muodostaa osoitteen jolla haetaan kartta tietyltä alueelta. Ei muodosta
     * reittiä vaan pelkän kartan alueelta ilman merkkejä.
     *
     * @param latitudi Kartan keskipisteen latitudi
     * @param longitudi Kartan keskipisteen longitudi
     * @param kuvatyyppi Kartan tyyppi: terrain, satellite, roadmap tai hybrid
     * @param zoom Zoomauksen taso: Minimi: 1 ja maksimi: 20
     */
    public String rakennaOsoite(double latitudi, double longitudi, String kuvatyyppi, int zoom) {
        Validoija vd = new Validoija();
        boolean validointiok = vd.validoiGoogleMapsOsoiteTiedot(latitudi, longitudi, kuvatyyppi, zoom);
        if (!validointiok) {
            return "virhe";
        }
        String osoite = osoitteenalku;
        osoite = osoite + latitudi + "," + longitudi + "&maptype=" + kuvatyyppi + "&zoom=" + zoom + "&size=600x600" + apikey;
        return osoite;
    }

    public String rakennaOsoiteMarkereilla(double alkuLat, double alkuLon, String kuvatyyppi, int zoom) {
        Validoija vd = new Validoija();
        boolean validointiok = vd.validoiGoogleMapsOsoiteTiedot(alkuLat, alkuLon, kuvatyyppi, zoom);
        if (!validointiok) {
            return "virhe";
        }
        String osoite = osoitteenalku;
        osoite = osoite + alkuLat + "," + alkuLon + "&maptype=" + kuvatyyppi + "&zoom=" + zoom + "&size=600x600" + "&markers=color:blue%7Clabel:A%7C" + alkuLat + "," + alkuLon + apikey;
        System.out.println("osoite");
        return osoite;

    }

    /**
     *
     * @param latitudit
     * @param longitudit
     * @param kuvatyyppi
     * @param zoom
     * @return
     */
    public String rakennaOsoitePolulla(ArrayList<Double> latitudit, ArrayList<Double> longitudit, String kuvatyyppi) {
        Validoija vd = new Validoija();
        boolean ok = true;
        String reitti = "&path=color:0x0000ff|weight:5";
        int joka20 = 0;
        for (int i = 0; i < latitudit.size(); i++) {
            ok = vd.validoiLongitudiJaLatitudi(latitudit.get(i), longitudit.get(i));

            if (!ok) {
                break;
            }
            if (joka20 == 0) {
                reitti = reitti + "|" + latitudit.get(i) + "," + longitudit.get(i);
            }

            joka20++;
            if (joka20 == 20) {
                joka20 = 0;
            }

            if (latitudit.size() - 1 == i) {
                reitti = reitti + "|" + latitudit.get(i) + "," + longitudit.get(i);
            }

        }

        if (!ok) {
            return "virhe";
        }

        String osoite = osoitteenalku;
        osoite = osoite + latitudit.get(0) + "," + longitudit.get(0) + "&maptype=" + kuvatyyppi  + "&size=600x600" + reitti + apikey;
        return osoite;

    }

}
