package GPSreader.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Luokka johon tulee kaikki tarpeellinen laskenta mitä Matka-luokalle tehdään
 */
public class MatkaLaskin {

    public ArrayList<Date> muutaVuosi(String vuosi, String kuukausi, String paiva, ArrayList<Date> aikaleimat) {

        for (Date a : aikaleimat) {
            a.setYear(Integer.valueOf(vuosi));
            a.setMonth(Integer.valueOf(kuukausi));
            a.setDate(Integer.valueOf(paiva));

        }

        return aikaleimat;
    }

    /**
     * Laskee etäisyyden kahden koordinaatin välillä, käyttäen harersinen
     * kaavaa.
     *
     * @param alkuLat ensimmäisen koordinaatin Latitudi
     * @param alkuLon ensimmäisen koordinaatin Longitudi
     * @param kohdeLat toisen koordinaatin Latitudi
     * @param kohdeLon toisen koordinaatin Longitudi
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
        //palautus kilometreinä
        return (maanHalkaisija * c);
    }

    /**
     * Laskee kuinka kauan yksi matka kestää
     *
     * @param aikaleimat lista joka sisältää aikaleimat
     *
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
     * Laskee mikä oli kahden edellisen mittauksen nopeus
     *
     * @param aika1 ensimmäisen mittaus
     * @param aika2 toinen mittaus
     * @param matka kuljettu matka
     *
     */
    public double laskeNopeus(Date aika1, Date aika2, double matka) {
        double erotus = aika2.getTime() - aika1.getTime();
        erotus = erotus / 1000 / 60 / 60;

        System.out.println("Nopeus on: " + matka / erotus + "km/min");
        return matka / erotus;
    }

}
