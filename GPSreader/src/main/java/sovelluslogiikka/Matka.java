package sovelluslogiikka;

import java.util.ArrayList;

public class Matka {

    private ArrayList<Double> latitudi;
    private ArrayList<Double> longitudi;
    private ArrayList<String> aikaleima;

    public ArrayList<Double> getLatitudi() {
        return latitudi;
    }

    public ArrayList<Double> getLongitudi() {
        return longitudi;
    }

    public ArrayList<String> getAikaleima() {
        return aikaleima;
    }

    public ArrayList<Double> getMittauksentarkkuus() {
        return mittauksentarkkuus;
    }
    private ArrayList<Double> mittauksentarkkuus;
    private String paiva;
    private Double kuljettumatka;

    public Matka(ArrayList<Double> latitudi, ArrayList<Double> longitudi, ArrayList<String> aikaleima, ArrayList<Double> tarkkuus) {
        this.latitudi = latitudi;
        this.longitudi = longitudi;
        this.aikaleima = aikaleima;
        this.mittauksentarkkuus=tarkkuus;
        asetapaiva();
        this.kuljettumatka = 0.0;
        laskeKuljettuMatka();
    }

    public void laskeKuljettuMatka() {
        kuljettumatka=0.0;


        for (int i = 0; i < latitudi.size() - 1; i++) {
            kuljettumatka += laskeEtaisyys(latitudi.get(i), longitudi.get(i), latitudi.get(i + 1), longitudi.get(i + 1));

        }
        

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

    public void poistaEpaTarkatMittaukset(int tarkkuus){
        for (int i = 0; i < latitudi.size(); i++) {
            double tark = mittauksentarkkuus.get(i);
            if(tark>tarkkuus){
                mittauksentarkkuus.remove(i);
                latitudi.remove(i);
                longitudi.remove(i);
                aikaleima.remove(i);
            }
            
        }
        
    }
    private void asetapaiva() {
        String aika = aikaleima.get(0);
        System.out.println("Ensimmäisen rivin aikaleima on: " + aika);

    }

    public Double getKuljettumatka() {
        return kuljettumatka;
    }

}
