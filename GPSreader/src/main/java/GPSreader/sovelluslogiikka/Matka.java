package GPSreader.sovelluslogiikka;

import java.util.ArrayList;

public class Matka {

    //Matkaan liittyvät muuttujat
    private ArrayList<Double> latitudit;
    private ArrayList<Double> longitudit;
    private ArrayList<String> aikaleimat;
    private ArrayList<Double> mittauksientarkkuudet;
    private String mittauspaiva;

    public Matka(ArrayList<Double> latitudi, ArrayList<Double> longitudi,
            ArrayList<String> aikaleima, ArrayList<Double> tarkkuus) {
        this.latitudit = latitudi;
        this.longitudit = longitudi;
        this.aikaleimat = aikaleima;
        this.mittauksientarkkuudet = tarkkuus;

        laskeKuljettuMatka();
        
        //Vielä selvittämättä miten String -> Date muotoon javassa
        this.mittauspaiva = "TestiPaiva";

    }

    //Laskee kuljetun matkan, hyödynten MatkaLaskin-luokkaa.
    public void laskeKuljettuMatka() {
        MatkaLaskin matkalaskin = new MatkaLaskin();
        this.kuljettumatka = 0.0;
        
        for (int i = 0; i < latitudit.size() - 1; i++) {
            kuljettumatka += matkalaskin.laskeEtaisyys(latitudit.get(i), longitudit.get(i), latitudit.get(i + 1), longitudit.get(i + 1));

        }

    }

    //Poistaa mittaukste joiden tarkkuus on huonompi kuin käyttäjän määrittelemä minimitarkkuus
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
        laskeKuljettuMatka();

    }

    public Double getKuljettumatka() {
        return kuljettumatka;
    }

    public ArrayList<Double> getLatitudi() {
        return latitudit;
    }

    public ArrayList<Double> getLongitudi() {
        return longitudit;
    }

    public ArrayList<String> getAikaleima() {
        return aikaleimat;
    }

    public String getPaiva() {
        return mittauspaiva;
    }
    private Double kuljettumatka;

    public ArrayList<Double> getMittauksentarkkuus() {
        return mittauksientarkkuudet;
    }
}
