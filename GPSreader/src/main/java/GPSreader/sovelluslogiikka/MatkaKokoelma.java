package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTTallentaja;
import java.util.ArrayList;
import java.util.Collection;

/**
 * MatkaKokoelma joka sisältää useita matkoja
 */
public class MatkaKokoelma {

    private ArrayList<Matka> matkat;
    private double matkojenpituus;
    private TXTTallentaja tallentaja;
    private double matkojenkesto;

    /**
     * Poistaa Matkan kokoelmasta nimen perusteella
     *
     * @param nimi Etsittävä nimi
     */
    public boolean poistaMatkaNimella(String nimi) {
        int pois = -1;
        for (int i = 0; i < matkat.size(); i++) {
            if (nimi.equals(matkat.get(i).getMatkanNimi())) {
                pois = i;
            }
        }

        if (pois != -1) {
            matkat.remove(pois);
            return true;
        }

        return false;
    }

    /**
     * Palauttaa Matkan kokoelmasta nimen perusteella
     *
     * @param nimi Etsittävä nimi
     */
    public Matka getMatkaNimella(String nimi) {
        for (Matka m : matkat) {
            if (m.getMatkanNimi().equals(nimi)) {
                return m;
            }
        }
        return null;
    }

    public MatkaKokoelma() {
        tallentaja = new TXTTallentaja();
        matkat = new ArrayList<Matka>();
        matkojenkesto = 0.0;
    }

    public double getMatkojenpituus() {
        if (matkat.size() == 0) {
            return 0.0;
        }

        laskeMatkojenPituus();
        return matkojenpituus;
    }

    public ArrayList<Matka> getMatkat() {
        return matkat;
    }

    /**
     * Poistaa mittaukset joiden tarkkuus on huonompi kuin käyttäjän
     * määrittelemä minimitarkkuus
     *
     * @param tarkkuus tarkkuus jota suuremmat poistetaan
     */
    public void PoistaEpaTarkatMittaukset(int tarkkuus) {
        for (Matka m : matkat) {
            m.poistaEpaTarkatMittaukset(tarkkuus);
            tallentaja.kirjoitaMatkaTiedostoon(m);
        }
    }

    private void laskeMatkojenKesto() {
        matkojenkesto = 0.0;

        for (Matka m : matkat) {
            matkojenkesto += m.getKesto();
        }
    }

    /**
     * Palauttaa Matkakokoelmassa olevien matkojen keskinopeuden laskemalla sen
     * matkojen yhteispituudesta ja yhteiskestosta
     *
     */
    public double getMatkojenKeskinopeus() {
        return getMatkojenpituus() / (getMatkojenkesto() / 60);
    }

    private void laskeMatkojenPituus() {
        matkojenpituus = 0.0;

        for (Matka m : matkat) {
            matkojenpituus += m.getKuljettumatka();
        }

    }

    /**
     * Lisää Matka-olion kokoelmaan
     *
     */
    public void lisaaMatka(Matka matka) {
        matkat.add(matka);
    }

    public double getMatkojenkesto() {
        if (matkat.size() == 0) {
            return 0.0;
        }
        laskeMatkojenKesto();
        return matkojenkesto;
    }

}
