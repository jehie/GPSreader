package GPSreader.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Date;

/**
 * Luokka jolla lasketaan Matka-oliohin liittyviä muuttujia sekä muutetaan
 * Matka-olion arvoja.
 */
public class MatkaLaskin {

    Validoija validoija;

    /**
     * Muuttaa Matka-olion aikaleimojen tietoja. Metodilla voidaan muuttaa
     * vuosi, kuukausi ja päivä. Metodi tarkistaa ovat arvot järkeviä
     *
     * @param vuosi Käyttäjän valitsema uusi Vuosi
     * @param kuukausi Käyttäjän valitsema uusi Kuukausi
     * @param paiva Käyttäjän valitsema uusi Päivä
     * @param aikaleimat Matka-olion aikaleimat listana
     * @return Palauttaa muokatun listan aikaleimoista
     */
    public ArrayList<Date> muutaAika(String vuosi, String kuukausi, String paiva, ArrayList<Date> aikaleimat) {
        validoija = new Validoija();

        boolean vuosiOnInteger = validoija.validoiOnkoInputInteger(vuosi);
        boolean kuukausiOnInteger = validoija.validoiOnkoInputInteger(kuukausi);
        boolean paivaOnInteger = validoija.validoiOnkoInputInteger(paiva);

        if (vuosiOnInteger && kuukausiOnInteger && paivaOnInteger) {

            int vuosiInt = Integer.valueOf(vuosi);
            int kuukausiInt = Integer.valueOf(kuukausi);
            int paivaInt = Integer.valueOf(paiva);

            boolean vuosiOnJarkeva = validoija.tarkistaOnkoVuosiJarkeva(vuosiInt);
            boolean paivaOnJarkeva = validoija.tarkistaOnkoPaivaJarkeva(paivaInt);
            boolean kuukausiOnJarkeva = validoija.tarkistaOnkoKuukausiJarkeva(kuukausiInt);

            if (vuosiOnJarkeva && paivaOnJarkeva && kuukausiOnJarkeva) {
                for (Date a : aikaleimat) {
                    a.setYear(vuosiInt);
                    a.setMonth(kuukausiInt);
                    a.setDate(paivaInt);
                }
                return aikaleimat;

            }

        }

        return aikaleimat;

    }

    /**
     * Laskee etäisyyden kahden koordinaatin välillä, käyttäen haversinen
     * kaavaa.
     *
     * @param alkuLat ensimmäisen koordinaatin Latitudi
     * @param alkuLon ensimmäisen koordinaatin Longitudi
     * @param kohdeLat toisen koordinaatin Latitudi
     * @param kohdeLon toisen koordinaatin Longitudi
     * @return Kahden koordinaattien välinen etäisyys kilometreissä
     */
    public double laskeEtaisyys(double alkuLat, double alkuLon,
            double kohdeLat, double kohdeLon) {
        double maanHalkaisija = 6371;

        double latEtaisyys = Math.toRadians(alkuLat - kohdeLat);
        double lonEtaisyys = Math.toRadians(alkuLon - kohdeLon);

        double a = Math.sin(latEtaisyys / 2) * Math.sin(latEtaisyys / 2)
                + Math.cos(Math.toRadians(alkuLat)) * Math.cos(Math.toRadians(kohdeLat))
                * Math.sin(lonEtaisyys / 2) * Math.sin(lonEtaisyys / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (maanHalkaisija * c);
    }

    /**
     * Laskee yhden matkan keston tunteina.
     *
     * @param aikaleimat Lista joka sisältää aikaleimat
     * @return Matkan kesto tunteina
     */
    public double laskeMatkanKesto(ArrayList<Date> aikaleimat) {
        if (aikaleimat == null) {
            return 0.0;
        }

        Double aika = 0.0;
        for (int i = 0; i < aikaleimat.size() - 1; i++) {
            aika += aikaleimat.get(i + 1).getTime() - aikaleimat.get(i).getTime();
        }
        aika = aika / 1000 / 60;
        return aika;
    }

    /**
     * Laskee mikä oli kahden edellisen mittauksen välinen nopeus
     *
     * @param aika1 ensimmäisen mittaus
     * @param aika2 toinen mittaus
     * @param matka kuljettu matka
     * @return kahden mittauksen välinen nopeus tunteina
     *
     */
    public double laskeNopeus(Date aika1, Date aika2, double matka) {
        double erotus = aika2.getTime() - aika1.getTime();
        erotus = erotus / 1000 / 60 / 60;

        return matka / erotus;
    }

}
