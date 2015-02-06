package GPSreader.tiedostonlukija;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import GPSreader.sovelluslogiikka.Matka;


/**
 * Tallentaa jo ohjelmaan tuodun Matka-olion tekstimuotoiseksi tiedostoksi.
 * 
 */
public class TXTTallentaja {
    
    

    public boolean kirjoitaMatkaTiedostoon(Matka talletettavaMatka) {
        String nimi = talletettavaMatka.getMatkanNimi();
        try {
            FileWriter kirjoittaja = new FileWriter("C:\\Users\\Jesse\\Documents\\Javalabra\\GPSreader\\GPSreader\\matkat\\"+nimi+".txt");

            for (int i = 0; i < talletettavaMatka.getLatitudi().size(); i++) {
                String lat = Double.toString(talletettavaMatka.getLatitudi().get(i));
                String lon = Double.toString(talletettavaMatka.getLongitudi().get(i));
                String aika = String.valueOf(talletettavaMatka.getAikaleima().get(i).getTime());
                String tark = Double.toString(talletettavaMatka.getMittauksentarkkuus().get(i));
                
                kirjoittaja.write(lat + "," + lon + "," + aika + "," + tark);
                kirjoittaja.write(System.lineSeparator());
                
            }
            kirjoittaja.close();
            return true;
            
        } catch (IOException ex) {
            return false;
            
        }
        
    }

}
