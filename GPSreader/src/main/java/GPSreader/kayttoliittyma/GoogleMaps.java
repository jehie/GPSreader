package GPSreader.kayttoliittyma;

import GPSreader.sovelluslogiikka.GoogleMapsOsoitteenRakentaja;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Luokka muodostaa JFrame-olion, joka sisältää Google Maps API-rajapinnasta
 * noudetun kartan.
 */
public class GoogleMaps {

    /**
     * Metodi avaa Google Maps kartan JFrame-ikkunaan. Kartta noudetaan Google
     * Maps API -rajapinnasta HTTP-GET pyynnöllä, käyttäen InputStream-metodia.
     * Osoite josta kartta noudetaan, muodostetaan
     * GoogleMapsOsoitteenRakentaja-luokalla. Muodostetun JFrame-ikkunan koko on
     * 600x600 pikseliä. Jos kuvan nouto epäonnistuu, ilmoittaa metodi
     * virheellisesti internet-yhteydestä.
     *
     * @param osoite URL-osoite josta kartta yritetään noutaa.
     * @see GoogleMapsOsoitteenRakentaja
     */
    public void avaaKartta(String osoite) throws IOException {
        JFrame freimi = new JFrame("Google Maps Kartta");

        try {
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
            JOptionPane.showMessageDialog(null, "Virhe kuvanlatauksessa, tarkista internet yhteys");
        }

        freimi.add(new JLabel(new ImageIcon((new ImageIcon("kartta.jpg")).getImage().getScaledInstance(630, 600,
                java.awt.Image.SCALE_SMOOTH))));

        freimi.setVisible(true);
        freimi.pack();
    }
}
