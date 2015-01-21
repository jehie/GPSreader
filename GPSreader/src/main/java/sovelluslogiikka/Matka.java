package sovelluslogiikka;

import java.util.ArrayList;

public class Matka {

    private ArrayList<Double> latitudi;
    private ArrayList<Double> longitudi;
    private ArrayList<String> aikaleima;
    private String paiva;
    private Double kuljettumatka;

    public Matka(ArrayList<Double> latitudi, ArrayList<Double> longitudi, ArrayList<String> aikaleima) {
        this.latitudi = latitudi;
        this.longitudi = longitudi;
        this.aikaleima = aikaleima;
        asetapaiva();
        this.kuljettumatka=0.0;
    }
    public void laskeKuljettuMatka(){
        
        
        System.out.println(latitudi.get(1));
        System.out.println(latitudi.get(2));
        System.out.println(longitudi.get(1));
        System.out.println(longitudi.get(2));
        System.out.println(laskeEtaisyys(latitudi.get(1), longitudi.get(1), latitudi.get(2), longitudi.get(2)));
        
        
        for (int i = 0; i < latitudi.size()-1; i++) {
           kuljettumatka+= laskeEtaisyys(latitudi.get(i), longitudi.get(i), latitudi.get(i+1), longitudi.get(i+1));
            
        }
        System.out.println(kuljettumatka);

    }

    public void luekoordinaatit() {
        for (int i = 0; i < latitudi.size(); i++) {
            System.out.println("Latitudi " + latitudi.get(i) + ", Longitudi " + longitudi.get(i));
        }

    }

    public static double laskeEtaisyys(double alkuLat, double alkuLon,
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
//
    private void asetapaiva() {
        String aika = aikaleima.get(0);
        System.out.println("Ensimmäisen rivin aikaleima on: " + aika);

    }

}
