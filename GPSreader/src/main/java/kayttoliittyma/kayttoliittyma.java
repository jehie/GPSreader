package kayttoliittyma;



import GPSreader.sovelluslogiikka.Matka;
import GPSreader.tiedostonlukija.TXTRaakaLukija;
import java.util.*;
import GPSreader.tiedostonlukija.TXTTallentaja;

public class kayttoliittyma {

    public static void main(String[] args) throws Exception {
        Scanner lukija = new Scanner(System.in);

        kayttoliittyma(lukija);

    }

    public static void kayttoliittyma(Scanner lukija) {

        while (true) {
            System.out.println("Käytettävissä olevat komennot: " + "1. Anna tiedosto, 2. Jatka, 3. Mallitiedosto, 4. lopeta");
            String komento = lukija.nextLine();

            if (komento.equals("4")) {
                break;
            }
            SuoritaKomento(komento, lukija);

        }

    }

    public static void SuoritaKomento(String komento, Scanner lukija) {

        if (komento.equals("3")) {
            TXTRaakaLukija txtl = new TXTRaakaLukija();
            Matka mallimatka = txtl.lue("20150118162507.txt");
            System.out.println("Kuljetun matkan pituus on: " + mallimatka.getKuljettumatka());
            while (true) {
                
                System.out.println("Karsitaan epätarkimmat mittaukset, anna minimitarkkuus metreissä: ");
                int tarkkuus = Integer.parseInt(lukija.nextLine());
                mallimatka.poistaEpaTarkatMittaukset(tarkkuus);
                mallimatka.laskeKuljettuMatka();
                System.out.println("Kuljetun matkan pituus karsinnan jälkeen: " + mallimatka.getKuljettumatka());
                System.out.println("");
                TXTTallentaja txtt = new TXTTallentaja();
                txtt.kirjoitaMatkaTiedostoon(mallimatka);
                break;
                
            }

        }

    }

}
