package GPSreader.kayttoliittyma;

import javax.swing.JOptionPane;

/**
 * Luokka muodostaa popup-ikkunan jossa ilmoitetaan jotakin käyttäjälle.
 *
 */
public class Ilmoittaja {

    /**
     * Metodi muodostaa popup-ikkunan jossa ilmoitetaan jotakin käyttäjälle.
     * @param ilmoitus Merkkijono, joka näytetään käyttäjälle popup-ikkunassa.
     *
     */
    public void ilmoita(String ilmoitus) {
        JOptionPane.showMessageDialog(null, ilmoitus);
    }

}
