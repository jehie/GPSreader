package GPSreader.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Date;

/**
 * Luokka sisältää kaikki yhteen Matkaan liittyvät muuttujat ja metodit Matkan
 * ominaisuuksien laskemiseen. Matkan listatyyppisiä muuttujia ovat:
 * koordinaatit (Latiduti ja Longitudit), aikaleimat sekä mittauksientarkkuudet.
 * Muita muuttujia ovat nimi, vuosi, kuukausi, päivä, kesto, nopeus ja
 * kuljettumatka.
 */
public class Matka {

    //Matkaan GPS-mittaukset
    private ArrayList<Double> latitudit;
    private ArrayList<Double> longitudit;
    private ArrayList<Date> aikaleimat;
    private ArrayList<Double> mittauksientarkkuudet;

    //Lasketut muuttujat
    private String matkannimi;
    private String vuosi;
    private String kuukausi;
    private String paiva;

    private Double kuljettumatka;
    private double kesto;
    private double keskinopeus;

    //Luokat
    private MatkaLaskin matkalaskin;

    /**
     * Konstruktori Matka-luokalle. Laskee matkalle nimen.
     *
     * @param latitudi Jokaisen mittauksen latitudit listana
     * @param longitudi Jokaisen mittauksen longitudit listana
     * @param aikaleima Jokaisen mittauksen aikaleimat listana
     * @param tarkkuus GPS:n mittaustarkkuus listana
     * @see laskeMatkanNimi();
     */
    public Matka(ArrayList<Double> latitudi, ArrayList<Double> longitudi,
            ArrayList<Date> aikaleima, ArrayList<Double> tarkkuus) {
        this.latitudit = latitudi;
        this.longitudit = longitudi;
        this.aikaleimat = aikaleima;
        this.mittauksientarkkuudet = tarkkuus;
        laskeMatkanNimi();

        this.vuosi = String.valueOf(aikaleima.get(0).getYear());
        this.kuukausi = String.valueOf(aikaleima.get(0).getMonth());
        this.paiva = String.valueOf(aikaleima.get(0).getDate());

        this.kesto = 0.0;
        this.keskinopeus = 0.0;

        this.matkalaskin = new MatkaLaskin();

    }

    /**
     * Muodostaa matkalle nimen aikaleiman ensimmäisen merkinnän perusteella.
     *
     */
    public void laskeMatkanNimi() {
        this.matkannimi = String.valueOf(aikaleimat.get(0).getYear()) + "_"
                + String.valueOf(aikaleimat.get(0).getMonth() + "_"
                        + String.valueOf(aikaleimat.get(0).getDate() + "_"
                                + String.valueOf(aikaleimat.get(0).getHours())));

    }

    /**
     * Muuttaa matkan aikaleimat-muuttujan aikoja ja laskee nimen uudelleen
     *
     * @param vuosi uusivuosi
     * @param kuukausi uusikuukausi
     * @param paiva uusipaiva
     * @see laskeMatkanNimi();
     *
     */
    public void muutaAika(String vuosi, String kuukausi, String paiva) {
        this.aikaleimat = matkalaskin.muutaAika(vuosi, kuukausi, paiva, aikaleimat);
        laskeMatkanNimi();
    }

    /**
     * Laskee kuljetun matkan, hyödynten MatkaLaskin-luokkaa.
     *
     * @see MatkaLaskin.laskeEtaisyys()
     *
     */
    private void laskeKuljettuMatka() {

        this.kuljettumatka = 0.0;

        for (int i = 0; i < latitudit.size() - 1; i++) {
            kuljettumatka += matkalaskin.laskeEtaisyys(latitudit.get(i), longitudit.get(i), latitudit.get(i + 1), longitudit.get(i + 1));
        }

    }

    /**
     * Poistaa mittaukset joiden tarkkuus on huonompi kuin käyttäjän
     * määrittelemä minimitarkkuus
     *
     * @param tarkkuus tarkkuus jota suuremmat poistetaan
     */
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

    /**
     * Palauttaa kuljetun matkan. Laskee sen käyttäen laskeKuljettuMatka-metodia
     *
     * @return Matkan aikana kuljettumatka kilometreinä
     */
    public Double getKuljettumatka() {
        laskeKuljettuMatka();
        return kuljettumatka;
    }

    /**
     * Palauttaa matkan keston. Laskee käyttäen MatkaLaskin luokan
     * laskeMatkanKesto-metodia.
     *
     * @see matkalaskin.laskeMatkanKesto(aikaleimat)
     * @return Matkan aika tunteina
     */
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

    /**
     * Laskee ja palauttaa Matkan keskinopeuden
     *
     * @return Matkan keskinopeus
     */
    public double getKeskinopeus() {
        return getKuljettumatka() / (getKesto() / 60);
    }

    public ArrayList<Double> getMittauksentarkkuus() {
        return mittauksientarkkuudet;
    }

    public String getVuosi() {
        return vuosi;
    }

    public void setVuosi(String vuosi) {
        this.vuosi = vuosi;
    }

    public String getKuukausi() {
        return kuukausi;
    }

    public void setKuukausi(String kuukausi) {
        this.kuukausi = kuukausi;
    }

    public String getPaiva() {
        return paiva;
    }

    public void setPaiva(String paiva) {
        this.paiva = paiva;
    }
}
