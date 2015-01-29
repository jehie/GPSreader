package GPSreader.sovelluslogiikka;
//Luokka johon tulee kaikki tarpeellinen laskenta mitä Matka-luokalle tehdään

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MatkaLaskin {

    //Laskee etäisyyden kahden koordinaatin välillä, käyttäen harersinen kaavaa.
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
    
    public double laskeMatkanKesto(ArrayList<Date> aikaleimat) {
        if(aikaleimat==null){
            return 0.0;
        }
        
        Double aika = 0.0;
        for (int i = 0; i < aikaleimat.size() - 1; i++) {
            aika += aikaleimat.get(i + 1).getTime() - aikaleimat.get(i).getTime();
        }
        aika = aika / 1000 / 60;
        return aika;
    }

    public double laskeNopeus(Date aika1, Date aika2, double matka) {
        double erotus = aika2.getTime() - aika1.getTime();
        erotus=erotus/1000/60/60;
        
        System.out.println("Nopeus on: " +matka/erotus+"km/min");
        return matka/erotus;
    }

}
