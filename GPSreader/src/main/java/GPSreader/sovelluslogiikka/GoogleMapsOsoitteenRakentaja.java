package GPSreader.sovelluslogiikka;

import java.util.ArrayList;

/**
 * Muodostaa staattisia Google Maps osoitteita, joilla Google Maps rajapinnasta
 * voidaan noutaa karttoja.
 * <p>
 * Luokalla mahdollista toteuttaa useanlaisia karttapyyntöjä.
 */
public class GoogleMapsOsoitteenRakentaja {

    final private String osoitteenalku = "https://maps.googleapis.com/maps/api/staticmap?center=";
    final private String apikey = "&key=AIzaSyDqqBJ4sBUY34znGoJA9IXQ3e-n4iEKzuU";
    Validoija validoija;

    public GoogleMapsOsoitteenRakentaja() {
        validoija = new Validoija();
    }

    /**
     * Muodostaa osoitteen jolla haetaan kartta tietyltä alueelta. Ei muodosta
     * reittiä vaan pelkän kartan alueelta ilman merkkejä.
     *
     * @param latitudi Kartan keskipisteen latitudi
     * @param longitudi Kartan keskipisteen longitudi
     * @param kuvatyyppi Kartan tyyppi: terrain, satellite, roadmap tai hybrid
     * @param zoom Zoomauksen taso: Minimi: 1 ja maksimi: 20
     * @return Osoite jolla kartta voidaan pyytää
     */
    public String rakennaOsoite(double latitudi, double longitudi, String kuvatyyppi, int zoom) {
        boolean validointiok = validoija.validoiGoogleMapsOsoiteTiedot(latitudi, longitudi, kuvatyyppi, zoom);
        if (!validointiok) {
            return "virhe";
        }
        return osoitteenalku + latitudi + "," + longitudi + "&maptype=" + kuvatyyppi + "&zoom=" + zoom + "&size=600x600" + apikey;

    }

    /**
     * Muodostaa osoitteen jolla haetaan kartta tietyltä alueelta,käyttäen
     * merkkejä kohteen osoittamiseksi. Ei muodosta reittiä.
     *
     * @param latitudi Kartan keskipisteen latitudi
     * @param longitudi Kartan keskipisteen longitudi
     * @param kuvatyyppi Kartan tyyppi: terrain, satellite, roadmap tai hybrid
     * @param zoom Zoomauksen taso: Minimi: 1 ja maksimi: 20
     * @return Osoite jolla kartta voidaan pyytää
     */
    public String rakennaOsoiteMarkereilla(double latitudi, double longitudi, String kuvatyyppi, int zoom) {
        boolean validointiok = validoija.validoiGoogleMapsOsoiteTiedot(latitudi, longitudi, kuvatyyppi, zoom);
        if (!validointiok) {
            return "virhe";
        }

        return osoitteenalku + latitudi + "," + longitudi + "&maptype=" + kuvatyyppi + "&zoom=" + zoom + "&size=600x600" + "&markers=color:blue%7Clabel:A%7C" + latitudi + "," + longitudi + apikey;

    }

    /**
     * Muodostaa osoitteen jolla haetaan kartta koko reitin alueelta, joka
     * näytetään viivana. Reitissä näytetään joka viidennet koordinaatit.
     * Muodostettavalla osoitteella on maksimikoko, joten koordinaattien
     * karsiminen on pakollista. Lyhyillä matkoilla tämä voi aiheuttaa
     * "oikaisua" kartalla. Metodi palauttaa virheen jos koordinatit tai
     * kuvatyyppi eivät ole järkeviä.
     *
     * @param latitudit Reitin latitudi-koordinaatit
     * @param longitudit Reitin longitudi-koordinaatit
     * @param kuvatyyppi Kartan tyyppi: terrain, satellite, roadmap tai hybrid
     * @return Osoite jolla kartta voidaan pyytää
     */
    public String rakennaOsoitePolulla(ArrayList<Double> latitudit, ArrayList<Double> longitudit, String kuvatyyppi) {

        boolean onkoKoordinaatitOK = true;
        String reitti = "&path=color:0x0000ff|weight:7";
        int monesko = 0;

        if (!validoija.validoiKuvatyyppi(kuvatyyppi)) {
            return "virhe";
        }

        for (int i = 0; i < latitudit.size(); i++) {
            onkoKoordinaatitOK = validoija.validoiLongitudiJaLatitudi(latitudit.get(i), longitudit.get(i));

            if (!onkoKoordinaatitOK) {
                break;
            }
            if (monesko == 0) {
                reitti = reitti + "|" + latitudit.get(i) + "," + longitudit.get(i);
            }

            monesko++;
            if (monesko == 5) {
                monesko = 0;
            }

            if (latitudit.size() - 1 == i) {
                reitti = reitti + "|" + latitudit.get(i) + "," + longitudit.get(i);
            }

        }

        if (!onkoKoordinaatitOK) {
            return "virhe";
        }

        return osoitteenalku + latitudit.get(0) + "," + longitudit.get(0) + "&maptype=" + kuvatyyppi + "&size=600x600" + reitti + apikey;

    }

}
