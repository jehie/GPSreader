package kayttoliittyma;

import sovelluslogiikka.Matka;
import tiedostonlukija.TXTLukija;
import java.util.*;

public class kayttoliittyma {

    public static void main(String[] args) throws Exception {
        Scanner lukija = new Scanner(System.in);

        kayttoliittyma(lukija);

    }

    public static void kayttoliittyma(Scanner lukija) {

        while (true) {
            System.out.println("Käytettävissä olevat komennot: " + "1. Anna tiedosto, 2. Jatka, 3. Mallitiedosto");
            System.out.println("");
            System.out.println("Lopeta ohjelma kirjoittamalla: lopeta");
            System.out.println("");
            System.out.println("Anna komento: ");
            String komento = lukija.nextLine();

            if (komento.equals("lopeta")) {
                break;
            }
            SuoritaKomento(komento, lukija);

        }

    }

    public static void SuoritaKomento(String komento, Scanner lukija) {

        if (komento.equals("3")) {
            TXTLukija txtl = new TXTLukija("20150118162507.txt");
            Matka mallimatka = txtl.lue();

            while (true) {
                System.out.println("Kuljetun matkan pituus on: " + mallimatka.getKuljettumatka());
                System.out.println("");
                System.out.println("Karsitaan epätarkimmat mittaukset, anna minimitarkkuus metreissä: ");
                int tarkkuus = Integer.parseInt(lukija.nextLine());
                mallimatka.poistaEpaTarkatMittaukset(tarkkuus);
                mallimatka.laskeKuljettuMatka();
                System.out.println("Kuljetun matkan pituus karsinnan jälkeen: " + mallimatka.getKuljettumatka());

            }

        }

    }

}
