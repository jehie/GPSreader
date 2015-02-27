package GPSreader.sovelluslogiikka;

import java.io.IOException;
import java.util.Date;

/**
 * Luokka joka muuntaa numeerisia arvoja suomenkielisiksi
 */
public class Muuntaja {

    /**
     * Muuntaa numeerisen kuukauden suomenkieliseksi kuukaudeksi
     *
     * @param kuukausi muunnettava kuukausi
     * @return Kuukausi kirjoitettuna
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

    /**
     * Muodostaa annetusta String-muotoisesta aikaleimasta Date-olion.
     *
     * @param aikaleima Muutettava aikaleima
     * @return Aikaleimasta muodostettu Date-olio
     *
     */
    public Date stringToDate(String aikaleima) {
        String vuosi = aikaleima.substring(0, 4);
        String kuukausi = aikaleima.substring(5, 7);
        String paiva = aikaleima.substring(8, 10);
        String tunnit = aikaleima.substring(11, 13);
        String minuutit = aikaleima.substring(14, 16);
        String sekuntit = aikaleima.substring(17, 19);

        Date date = new Date();
        date.setYear(Integer.parseInt(vuosi));
        date.setMonth(Integer.parseInt(kuukausi));
        date.setDate(Integer.parseInt(paiva));
        date.setHours(Integer.parseInt(tunnit));
        date.setMinutes(Integer.parseInt(minuutit));
        date.setSeconds(Integer.parseInt(sekuntit));
        return date;

    }

}
