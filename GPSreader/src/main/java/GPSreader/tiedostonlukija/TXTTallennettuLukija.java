package GPSreader.tiedostonlukija;

import GPSreader.sovelluslogiikka.Matka;
import GPSreader.sovelluslogiikka.MatkaKokoelma;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.Ilmoittaja;

/**
 * Lukee ohjelman avulla tallennetun tiedoston sisään ohjelmaan uudelleen.
 *
 */
public class TXTTallennettuLukija {
    Ilmoittaja ilmoittaja = new Ilmoittaja();
    File kansio = new File("matkat\\");

    //Lukee kaikki kansiossa olevat TXTTallentajalla tallennetut tiedostot
    public MatkaKokoelma lueKaikkiTallennetutTiedostot() {
        MatkaKokoelma mko = new MatkaKokoelma();
        for (File fileEntry : kansio.listFiles()) {
            System.out.println(fileEntry.getName());
            Matka m = lue(kansio + "\\" + fileEntry.getName());
            mko.lisaaMatka(m);
        }
        System.out.println("");
        return mko;
    }

    //Lukee jo ohjelmalla tallennetun tekstimuotoisen tiedoston sisään ja luo Matka olion ja palauttaa sen
    public Matka lue(String tiedostopolku) {

        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        ArrayList<Date> time = new ArrayList<Date>();
        ArrayList<Double> accuracy = new ArrayList<Double>();

        try {
            BufferedReader bufferoitulukija = new BufferedReader(new FileReader(tiedostopolku));

            String rivi = bufferoitulukija.readLine();

            while (rivi != null) {

                String[] rivitaulukko = rivi.split(",");

                Double latitudi = Double.parseDouble(rivitaulukko[0]);
                lat.add(latitudi);
                Double longitudi = Double.parseDouble(rivitaulukko[1]);
                lon.add(longitudi);
                Date lisattavaAika = new Date();
                String aika = rivitaulukko[2];
                long luku = Long.valueOf(aika);
                lisattavaAika.setTime(luku);

                time.add(lisattavaAika);
                Double acc = Double.parseDouble(rivitaulukko[3]);
                accuracy.add(acc);

                rivi = bufferoitulukija.readLine();
            }

        } catch (FileNotFoundException exp) {
            ilmoittaja.ilmoita("Tiedostoa ei löydy");

        } catch (IOException ex) {
            ilmoittaja.ilmoita("Tiedostoa ei voida lukea");
        }

        Matka uusimatka = new Matka(lat, lon, time, accuracy);

        return uusimatka;

    }

}
