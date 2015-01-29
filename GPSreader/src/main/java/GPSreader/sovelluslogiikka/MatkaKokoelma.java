package GPSreader.sovelluslogiikka;

import GPSreader.tiedostonlukija.TXTTallentaja;
import java.util.ArrayList;
import java.util.Collection;

public class MatkaKokoelma {

    private ArrayList<Matka> matkat;
    private double matkojenpituus;
    private TXTTallentaja tallentaja;
    private double matkojenkesto;

    public double getMatkojenkesto() {
        if (matkat.size() == 0) {
            return 0.0;
        }
        laskeMatkojenKesto();
        return matkojenkesto;
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

    public double getMatkojenKeskinopeus() {
        return getMatkojenpituus() / (getMatkojenkesto() / 60);
    }

    private void laskeMatkojenPituus() {
        matkojenpituus = 0.0;

        for (Matka m : matkat) {
            matkojenpituus += m.getKuljettumatka();
        }

    }

    public void lisaaMatka(Matka matka) {
        matkat.add(matka);
    }

    public Collection<Matka> haeMatkatPaivanPerusteella(String paiva) {
        return null;
    }

}
