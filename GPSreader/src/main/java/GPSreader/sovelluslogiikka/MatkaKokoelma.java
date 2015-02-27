package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTTallentaja;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * MatkaKokoelma joka sisältää useita matkoja. Kokoelman matkoille voidaan
 * laskea kokonaismatka, kokonaisaika ja keskinopeus. Kokoelmaan voidaan lisätä
 * matkoja ja niitä voidaan poistaa.
 */
public class MatkaKokoelma {

    /**
     * Lista Matka-oliota
     */
    private ArrayList<Matka> matkat;
    /**
     * Matkojen yhteispituus
     */
    private double matkojenpituus;
    /**
     * TXTTallentaja-luokan olio ilmentymä
     */
    private TXTTallentaja tallentaja;
    /**
     * Matkojen yhteispituus
     */
    private double matkojenkesto;

    /**
     * MatkaKokoelman konstuktori, alustaa muuttujat ja asettaa matkojenkestoksi
     * 0.
     *
     */
    public MatkaKokoelma() {
        tallentaja = new TXTTallentaja();
        matkat = new ArrayList<Matka>();
        matkojenkesto = 0.0;
    }

    /**
     * Poistaa Matkan kokoelmasta nimen perusteella
     *
     * @param nimi Poistettavan matkan nimi
     * @param kansio Kansio josta tiedosto poistetaan
     * @return Onnistuiko matkan poisto
     */
    public boolean poistaMatkaNimella(String nimi, String kansio) {
        int pois = -1;
        for (int i = 0; i < matkat.size(); i++) {
            if (nimi.equals(matkat.get(i).getMatkanNimi())) {
                pois = i;
            }
        }

        if (pois != -1) {
            matkat.remove(pois);
            File poistettava = new File(kansio + "/" + nimi + ".txt");
            boolean ok = poistettava.delete();
            return true;
        }

        return false;
    }

    /**
     * Palauttaa Matkan kokoelmasta nimen perusteella.
     *
     * @param nimi Etsittävä nimi
     * @return Palautettava Matka-olio
     */
    public Matka getMatkaNimella(String nimi) {
        for (Matka m : matkat) {
            if (m.getMatkanNimi().equals(nimi)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Palauttaa MatkaKokoelmassa olevien Matkojen yhteispituuden kilometreinä.
     *
     * @return Matkojen yhteispituus kilometreinä
     */
    public double getMatkojenpituus() {
        if (matkat.size() == 0) {
            return 0.0;
        }

        laskeMatkojenPituus();
        return matkojenpituus;
    }

    /**
     * Palauttaa MatkaKokoelmassa olevat Matkat listana.
     *
     * @return Kokoelmassa olevat matkat
     */
    public ArrayList<Matka> getMatkat() {
        return matkat;
    }

    /**
     * Poistaa mittaukset joiden tarkkuus on huonompi kuin käyttäjän
     * määrittelemä minimitarkkuus
     *
     * @param tarkkuus tarkkuus jota suuremmat poistetaan
     * @param kansio Kansio josssa tiedostot sijaitsevat
     */
    public void PoistaEpaTarkatMittaukset(int tarkkuus, String kansio) {
        for (Matka m : matkat) {
            m.poistaEpaTarkatMittaukset(tarkkuus);
            tallentaja.kirjoitaMatkaTiedostoon(m, kansio);
        }
    }

    /**
     * Laskee kaikkien MatkaKokoelmassa olevien matkojen yhteiskeston
     */
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
     * @return Matkojen keskinopeus
     */
    public double getMatkojenKeskinopeus() {
        return getMatkojenpituus() / (getMatkojenkesto() / 60);
    }

    /**
     * Laskee kaikkien MatkaKokoelmassa olevien matkojen yhteispituuden
     */
    private void laskeMatkojenPituus() {
        matkojenpituus = 0.0;

        for (Matka m : matkat) {
            matkojenpituus += m.getKuljettumatka();
        }

    }

    /**
     * Lisää Matka-olion kokoelmaan
     *
     * @param matka Lisättävä matka
     *
     */
    public void lisaaMatka(Matka matka) {
        matkat.add(matka);
    }

    /**
     * Palauttaa matkojen yhteiskesto, jota ennen laskee sen käyttäen
     * laskeMatkojenKesto()-metodilla.
     *
     * @return Matkoihin käytetyn ajan yhteiskesto
     *
     */
    public double getMatkojenkesto() {
        if (matkat.size() == 0) {
            return 0.0;
        }
        laskeMatkojenKesto();
        return matkojenkesto;
    }

}
