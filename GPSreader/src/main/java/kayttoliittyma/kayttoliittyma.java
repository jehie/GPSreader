package kayttoliittyma;

import GPSreader.sovelluslogiikka.Matka;
import GPSreader.sovelluslogiikka.MatkaKokoelma;
import GPSreader.sovelluslogiikka.MatkaLaskin;
import GPSreader.tiedostonlukija.TXTRaakaLukija;
import GPSreader.tiedostonlukija.TXTTallennettuLukija;
import java.util.*;
import GPSreader.tiedostonlukija.TXTTallentaja;
import java.io.File;

public class kayttoliittyma {

    public static void main(String[] args) throws Exception {
        Scanner lukija = new Scanner(System.in);

        kayttoliittyma(lukija);

    }

    public static void kayttoliittyma(Scanner lukija) {

        while (true) {
            System.out.println("Käytettävissä olevat komennot: " + "1. Lue uusi tiedosto, 2. Kokoelmassa olevat matkat, 3. LueVanhaTiedosto, 4. lopeta,");
            String komento = lukija.nextLine();

            if (komento.equals("4")) {
                break;
            }
            SuoritaKomento(komento, lukija);

        }

    }

    public static void SuoritaKomento(String komento, Scanner lukija) {

        if (komento.equals("2")) {
            TXTRaakaLukija txtl = new TXTRaakaLukija();
            Matka mallimatka = txtl.lue("20150118162507.txt");
            System.out.println("Kuljetun matkan pituus on: " + mallimatka.getKuljettumatka());
//            mallimatka.laskeMinimiJaMaksimiNopeudet();

         
                System.out.println("Karsitaan epätarkimmat mittaukset, anna minimitarkkuus metreissä: ");
                int tarkkuus = Integer.parseInt(lukija.nextLine());
                mallimatka.poistaEpaTarkatMittaukset(tarkkuus);
                System.out.println("Kuljetun matkan pituus karsinnan jälkeen: " + mallimatka.getKuljettumatka());
                System.out.println("");
                System.out.println("Matkan kesto: "+mallimatka.getKesto());
                System.out.println("Matkan keskinopeus: " +mallimatka.getKeskinopeus());
                System.out.println("Matkan pituus: "+mallimatka.getKuljettumatka());
                TXTTallentaja txtt = new TXTTallentaja();
                txtt.kirjoitaMatkaTiedostoon(mallimatka);


        } else if (komento.equals("3")) {
            MatkaLaskin ml = new MatkaLaskin();
            TXTTallennettuLukija luk = new TXTTallennettuLukija();
            MatkaKokoelma mko = luk.lueKaikkiTallennetutTiedostot();
            System.out.println("Matkojen yhteispituus on: " +mko.getMatkojenpituus());
            
            System.out.println("Poistetaan epätarkimmat mittaukset - Anna tarkkuus metreissä:");
            int tarkkuus = Integer.parseInt(lukija.nextLine());
            mko.PoistaEpaTarkatMittaukset(tarkkuus);
            System.out.println("Matkojen yhteispituus on poiston jälkeen: " +mko.getMatkojenpituus());
            System.out.println("");
            System.out.println("Matkojen keskinopeus on: " +mko.getMatkojenKeskinopeus());
            System.out.println("Matkojen yhteenlaskettu kesto on: " +mko.getMatkojenkesto());
            System.out.println("Matkojen yhteenlaskettu pituus on " +mko.getMatkojenpituus());
            
            
        } else if(komento.equals("1")){
            TXTRaakaLukija txtl = new TXTRaakaLukija();
            System.out.println("Anna tiedostonpolku ja nimi: ");
            String polku = lukija.nextLine();
            
            Matka m = txtl.lue(polku);
            System.out.println(m.getMatkanNimi());
            System.out.println(m.getAikaleima().get(0).getMonth() +"   Kuukausi on");
            TXTTallentaja txtatl = new TXTTallentaja();
            txtatl.kirjoitaMatkaTiedostoon(m); 
            System.out.println("Matka tallennettu ohjelmaan!");
            
            
        }

    }

}
