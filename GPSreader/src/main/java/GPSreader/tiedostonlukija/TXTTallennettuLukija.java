package GPSreader.tiedostonlukija;

import GPSreader.sovelluslogiikka.Matka;
import GPSreader.sovelluslogiikka.MatkaKokoelma;
import GPSreader.sovelluslogiikka.Validoija;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import GPSreader.kayttoliittyma.Ilmoittaja;

/**
 * Lukee ohjelman avulla tallennetun tiedoston sisään ohjelmaan uudelleen.
 *
 */
public class TXTTallennettuLukija {

    Ilmoittaja ilmoittaja = new Ilmoittaja();
    Validoija validoija = new Validoija();

    /**
     * Lukee kaikki kansiossa olevat TXTTallentajalla tallennetut tiedostot.
     *
     * @param tallennuskansio Kansio josta matkoja yritetään lukea
     * @return Palautettava MatkaKokoelma-sisältää kaikki kansiosta luetut matkat
     */
    public MatkaKokoelma lueKaikkiTallennetutTiedostot(String tallennuskansio) {
        File kansio = new File(tallennuskansio);
        MatkaKokoelma mko = new MatkaKokoelma();
        for (File tiedosto : kansio.listFiles()) {

            if (tiedosto.isFile() && tiedosto.getName().endsWith(".txt")) {
                Matka m = lueTallennettuTiedosto(kansio + "/" + tiedosto.getName());
                if (m != null) {
                    mko.lisaaMatka(m);
                }

            }

        }
        return mko;
    }

    /**
     * Lukee jo ohjelmalla tallennetun tekstimuotoisen tiedoston sisään ja luo
     * Matka olion ja palauttaa sen.
     *
     * @param tiedostopolku Tiedostopolku josta tiedostoa yritetään lukea
     * @return Matka-olio, joka muodostettu luetusta tiedostosta
     */
    public Matka lueTallennettuTiedosto(String tiedostopolku) {
        
        boolean onkoOlemassa = new File(tiedostopolku).exists();
        boolean onkoTiedosto =  new File(tiedostopolku).isFile();
        boolean onkoTXT =  new File(tiedostopolku).getName().endsWith("txt");
        if(!onkoOlemassa || !onkoTiedosto || !onkoTXT){
            return null;
        }
        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        ArrayList<Date> time = new ArrayList<Date>();
        ArrayList<Double> accuracy = new ArrayList<Double>();

        try {
            BufferedReader bufferoitulukija = new BufferedReader(new FileReader(tiedostopolku));

            String rivi = bufferoitulukija.readLine();

            while (rivi != null) {
                if (!validoija.tarkistaErotin(rivi) || !validoija.TallennetunMatkanRiviSisaltaaOikeatMerkit(rivi)) {
                    return null;
                }

                String[] rivitaulukko = rivi.split(",");

                Double latitudi = Double.parseDouble(rivitaulukko[0]);
                Double longitudi = Double.parseDouble(rivitaulukko[1]);

                lat.add(latitudi);
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
            bufferoitulukija.close();
        } catch (FileNotFoundException exp) {
            ilmoittaja.ilmoita("Tiedostoa ei löydy");

        } catch (IOException ex) {
            ilmoittaja.ilmoita("Tiedostoa ei voida lukea");
        }

        Matka uusimatka = new Matka(lat, lon, time, accuracy);

        return uusimatka;

    }

}
