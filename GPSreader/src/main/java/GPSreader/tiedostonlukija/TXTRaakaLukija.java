package GPSreader.tiedostonlukija;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import GPSreader.sovelluslogiikka.Matka;
import GPSreader.sovelluslogiikka.Muuntaja;
import GPSreader.sovelluslogiikka.Validoija;
import java.sql.Time;
import java.util.Date;
import GPSreader.kayttoliittyma.Ilmoittaja;

/**
 * Lukee GPS Logger -android sovelluksesta saatavan tekstimuotoisen GPS-mittaus
 * tiedoston ja muuntaa sen Matka-olioksi.
 *
 */
public class TXTRaakaLukija {

    private Ilmoittaja ilmoittaja = new Ilmoittaja();
    private Validoija validoija = new Validoija();
    private Muuntaja muuntaja = new Muuntaja();
    private BufferedReader bufferoitulukija;
    private String erotin = ",";

    /**
     * Lukee annetun tiedoston sisään, tarkistaa sen oikeellisuuden ja parsii
     * siitä Matka-olion. Metodi lukee rivi kerrallaan tiedoston sisään.
     *
     * @param tiedostopolku Tiedostopolku josta luettava tiedosto löytyy
     * @return Tiedostosta muodostettu Matka-olio
     *
     */
    public Matka lue(String tiedostopolku) {

        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        ArrayList<Date> time = new ArrayList<Date>();
        ArrayList<Double> accuracy = new ArrayList<Double>();

        try {
            bufferoitulukija = new BufferedReader(new FileReader(tiedostopolku));

            String rivi = bufferoitulukija.readLine();
            boolean headerok = validoija.tarkistaHeaderRaakaMatkalta(rivi);
            if (!headerok) {
                return null;
            }

            rivi = bufferoitulukija.readLine();

            while (rivi != null) {

                if (!validoija.tarkistaErotin(rivi) || !validoija.RaakaMatkanRiviSisaltaaOikeatMerkit(rivi)) {
                    return null;
                }

                String[] rivitaulukko = rivi.split(erotin);

                Double latitudi = Double.parseDouble(rivitaulukko[1]);
                Double longitudi = Double.parseDouble(rivitaulukko[2]);
                lat.add(latitudi);
                lon.add(longitudi);

                time.add(muuntaja.stringToDate(rivitaulukko[0]));
                Double acc = Double.parseDouble(rivitaulukko[4]);
                accuracy.add(acc);

                rivi = bufferoitulukija.readLine();
            }

        } catch (FileNotFoundException exp) {
            return null;
        } catch (IOException ex) {
            return null;
        }

        Matka uusimatka = new Matka(lat, lon, time, accuracy);

        return uusimatka;

    }

}
