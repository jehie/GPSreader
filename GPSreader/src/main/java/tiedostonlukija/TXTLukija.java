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

    public TXTLukija(String tiedostopolku, String erotin) {
        this.tiedostopolku = tiedostopolku;
        this.erotin = erotin;

    }

    public Matka lue() {
        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        ArrayList<String> time = new ArrayList<String>();

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

                System.out.println(rivitaulukko[0] + ", " + rivitaulukko[1] + ", " + rivitaulukko[2] + ", " + rivitaulukko[3]
                        + ", " + rivitaulukko[4] + ", " + rivitaulukko[5] + ", " + rivitaulukko[6]);
                rivi = bufr.readLine();
            }

        } catch (FileNotFoundException exp) {
            System.out.println("Tiedostoa ei löydy");
        } catch (IOException ex) {
            Logger.getLogger(TXTLukija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Matka uusimatka = new Matka(lat, lon, time);
        
        return uusimatka;

    }

}
