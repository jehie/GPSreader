package tiedostonlukija;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sovelluslogiikka.Matka;

public class TXTLukija {

    private String tiedostopolku;
    BufferedReader bufr;
    String erotin;

    public TXTLukija(String tiedostopolku) {
        this.tiedostopolku = tiedostopolku;
        this.erotin = ",";

    }

    public Matka lue() {
        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        ArrayList<String> time = new ArrayList<String>();
        ArrayList<Double> accuracy = new ArrayList<Double>();

        try {
            bufr = new BufferedReader(new FileReader(tiedostopolku));

            String rivi = bufr.readLine();
            rivi = bufr.readLine();

            while (rivi != null) {

                String[] rivitaulukko = rivi.split(erotin);

                Double latitudi = Double.parseDouble(rivitaulukko[1]);
                lat.add(latitudi);
                Double longitudi = Double.parseDouble(rivitaulukko[2]);
                lon.add(longitudi);

                time.add(rivitaulukko[0]);
                Double acc = Double.parseDouble(rivitaulukko[4]);
                accuracy.add(acc);

                rivi = bufr.readLine();
            }

        } catch (FileNotFoundException exp) {
            System.out.println("Tiedostoa ei löydy");
        } catch (IOException ex) {
            Logger.getLogger(TXTLukija.class.getName()).log(Level.SEVERE, null, ex);
        }

        Matka uusimatka = new Matka(lat, lon, time, accuracy);

        return uusimatka;

    }

}
