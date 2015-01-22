package GPSreader.tiedostonlukija;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import GPSreader.sovelluslogiikka.Matka;

//Lukee GPS Logger -android sovelluksesta saatavan tekstimuotoisen GPS-mittaus tiedoston.
public class TXTRaakaLukija {

    BufferedReader bufferoitulukija;
    String erotin = ",";

    public boolean tarkistaErotin(String rivi) {
        if (rivi.contains(",") && rivi.contains(".") && !rivi.contains("@") && !rivi.contains("€")) {
            return true;
        }

        return false;
    }

    public boolean tarkistaHeader(String rivi) {

        if (rivi.equals("time,lat,lon,elevation,accuracy,bearing,speed")) {
            return true;
        }

        return false;

    }

    //Lukee tiedoston sisään ja luo Matka olion ja palauttaa sen
    public Matka lue(String tiedostopolku) {

        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        ArrayList<String> time = new ArrayList<String>();
        ArrayList<Double> accuracy = new ArrayList<Double>();

        try {
            bufferoitulukija = new BufferedReader(new FileReader(tiedostopolku));

            String rivi = bufferoitulukija.readLine();
            boolean headerok = tarkistaHeader(rivi);
            if (!headerok) {
                return null;
            }

            rivi = bufferoitulukija.readLine();

            boolean erotinok = tarkistaErotin(rivi);
            if (!erotinok) {
                return null;
            }

            while (rivi != null) {

                String[] rivitaulukko = rivi.split(erotin);

                Double latitudi = Double.parseDouble(rivitaulukko[1]);
                lat.add(latitudi);
                Double longitudi = Double.parseDouble(rivitaulukko[2]);
                lon.add(longitudi);

                time.add(rivitaulukko[0]);
                Double acc = Double.parseDouble(rivitaulukko[4]);
                accuracy.add(acc);

                rivi = bufferoitulukija.readLine();
            }

        } catch (FileNotFoundException exp) {
            System.out.println("Tiedostoa ei löydy");
        } catch (IOException ex) {
            Logger.getLogger(TXTRaakaLukija.class.getName()).log(Level.SEVERE, null, ex);
        }

        Matka uusimatka = new Matka(lat, lon, time, accuracy);

        return uusimatka;

    }

}
