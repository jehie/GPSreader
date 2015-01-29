package GPSreader.tiedostonlukija;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import GPSreader.sovelluslogiikka.Matka;
import java.sql.Time;
import java.util.Date;

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
    
    //Muuttaa String-muotoisen tekstin Date-olioksi
    public Date stringToTime(String time){
        String vuosi = time.substring(0,4);
        String kuukausi = time.substring(5,7);
        String paiva = time.substring(8,10);
        String tunnit = time.substring(11,13);
        String minuutit = time.substring(14,16);
        String sekuntit = time.substring(17,19);
        
        Date date = new Date();
        date.setYear(Integer.parseInt(vuosi));
        date.setMonth(Integer.parseInt(kuukausi));
        date.setDate(Integer.parseInt(paiva));
        date.setHours(Integer.parseInt(tunnit));
        date.setMinutes(Integer.parseInt(minuutit));
        date.setSeconds(Integer.parseInt(sekuntit));
        
 
        return date;
        
    }

    //Lukee tiedoston sisään ja luo Matka olion ja palauttaa sen
    public Matka lue(String tiedostopolku) {

        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        ArrayList<Date> time = new ArrayList<Date>();
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

                time.add(stringToTime(rivitaulukko[0]));
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
