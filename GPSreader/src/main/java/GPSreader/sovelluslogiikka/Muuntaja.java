package GPSreader.sovelluslogiikka;

import java.io.IOException;

/**
 * Luokka joka muuntaa numeerisia arvoja suomenkielisiksi
 */
public class Muuntaja {

    /**
     * Muuntaa numeerisen kuukauden suomenkieliseksi kuukaudeksi
     *
     * @param kuukausi muunnettava kuukausi
     */
    public String matkanKuukausiTekstina(String kuukausi) {
        try {
            int kk = Integer.valueOf(kuukausi);

            if (kk == 1) {
                return "Tammikuu";
            } else if (kk == 2) {
                return "Helmikuu";
            } else if (kk == 3) {
                return "Maaliskuu";
            } else if (kk == 4) {
                return "Huhtikuu";
            } else if (kk == 5) {
                return "Toukokuu";
            } else if (kk == 6) {
                return "Kesäkuu";
            } else if (kk == 7) {
                return "Heinäkuu";
            } else if (kk == 8) {
                return "Elokuu";
            } else if (kk == 9) {
                return "Syyskuu";
            } else if (kk == 10) {
                return "Lokakuu";
            } else if (kk == 11) {
                return "Marraskuu";
            } else if (kk == 12) {
                return "Joulukuu";
            }

            return "Virhe";

        } catch (NumberFormatException ex) {
            return "Virhe";
        }

    }

}
