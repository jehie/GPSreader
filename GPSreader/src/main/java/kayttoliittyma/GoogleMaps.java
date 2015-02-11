package kayttoliittyma;

import GPSreader.sovelluslogiikka.GoogleMapsOsoitteenRakentaja;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GoogleMaps {


    public void avaaKartta(double lat, double lon, String kuvatyyppi, int zoom) throws IOException {
        GoogleMapsOsoitteenRakentaja gmor = new GoogleMapsOsoitteenRakentaja();
        String osoite = gmor.rakennaOsoite(lat, lon, kuvatyyppi, zoom);
        System.out.println(osoite);

        JFrame freimi = new JFrame("Google Maps");

        try {
            //String osoite = "https://maps.googleapis.com/maps/api/staticmap?center=Helsinki,Finland&zoom=11&size=1024x800&key=AIzaSyDqqBJ4sBUY34znGoJA9IXQ3e-n4iEKzuU";
           
            String tiedosto = "kartta.jpg";
            URL url = new URL(osoite);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(tiedosto);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println("Virhe kuvanlatauksessa, tarkista internet yhteys");  
        }

        freimi.add(new JLabel(new ImageIcon((new ImageIcon("kartta.jpg")).getImage().getScaledInstance(630, 600,
                java.awt.Image.SCALE_SMOOTH))));

        freimi.setVisible(true);
        freimi.pack();
    }
}
