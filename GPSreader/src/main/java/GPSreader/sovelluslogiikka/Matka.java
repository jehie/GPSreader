package GPSreader.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Date;

public class Matka {
    //Matkaan GPS-mittaukset
    private ArrayList<Double> latitudit;
    private ArrayList<Double> longitudit;
    private ArrayList<Date> aikaleimat;
    private ArrayList<Double> mittauksientarkkuudet;

    //Lasketut muuttujat
    private String matkannimi;
    private Double kuljettumatka;
    private double kesto;
    private double keskinopeus;

    //Luokat
    private MatkaLaskin matkalaskin;

    public Matka(ArrayList<Double> latitudi, ArrayList<Double> longitudi,
            ArrayList<Date> aikaleima, ArrayList<Double> tarkkuus) {
        this.latitudit = latitudi;
        this.longitudit = longitudi;
        this.aikaleimat = aikaleima;
        this.mittauksientarkkuudet = tarkkuus;
        this.matkannimi = String.valueOf(aikaleima.get(0).getYear()) + "_"
                + String.valueOf(aikaleima.get(0).getMonth() + "_"
                        + String.valueOf(aikaleima.get(0).getDate() + "_"
                                + String.valueOf(aikaleima.get(0).getHours())));
        this.kesto = 0.0;
        this.keskinopeus = 0.0;

        this.matkalaskin = new MatkaLaskin();

    }

    
    //Kesken
//    public void laskeMinimiJaMaksimiNopeudet() {
//        for (int i = 0; i < latitudit.size() - 1; i++) {
//            Double k = matkalaskin.laskeNopeus(aikaleimat.get(i), aikaleimat.get(i + 1),
//                    matkalaskin.laskeEtaisyys(latitudit.get(i), longitudit.get(i), latitudit.get(i + 1), longitudit.get(i + 1)));
//            System.out.println("Laskettu " + k);
//        }
//
//    }

    //Laskee kuljetun matkan, hyödynten MatkaLaskin-luokkaa.
    private void laskeKuljettuMatka() {

        this.kuljettumatka = 0.0;

        for (int i = 0; i < latitudit.size() - 1; i++) {
            kuljettumatka += matkalaskin.laskeEtaisyys(latitudit.get(i), longitudit.get(i), latitudit.get(i + 1), longitudit.get(i + 1));

        }

    }

    //Poistaa mittaukset joiden tarkkuus on huonompi kuin käyttäjän määrittelemä minimitarkkuus
    public void poistaEpaTarkatMittaukset(int tarkkuus) {
        for (int i = latitudit.size() - 1; i >= 0; i--) {
            double tark = mittauksientarkkuudet.get(i);
            if (tark > (double) tarkkuus) {
                mittauksientarkkuudet.remove(i);
                latitudit.remove(i);
                longitudit.remove(i);
                aikaleimat.remove(i);
            }
        }
    }

    public Double getKuljettumatka() {
        laskeKuljettuMatka();
        return kuljettumatka;
    }

    public double getKesto() {
        kesto = matkalaskin.laskeMatkanKesto(aikaleimat);
        return kesto;
    }

    public ArrayList<Double> getLatitudi() {
        return latitudit;
    }

    public ArrayList<Double> getLongitudi() {
        return longitudit;
    }

    public ArrayList<Date> getAikaleima() {
        return aikaleimat;
    }

    public String getMatkanNimi() {
        return matkannimi;
    }

    public double getKeskinopeus() {
        return getKuljettumatka() / (getKesto()/60);
    }

    public ArrayList<Double> getMittauksentarkkuus() {
        return mittauksientarkkuudet;
    }
}