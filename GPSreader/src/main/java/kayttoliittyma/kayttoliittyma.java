
package kayttoliittyma;

import sovelluslogiikka.Matka;
import tiedostonlukija.TXTLukija;


public class kayttoliittyma {

    public static void main(String[] args) {
        TXTLukija lukija = new TXTLukija("C:\\Users\\Jes\\GPSreader\\GPSreader\\20150118162507.txt",",");
        Matka m = lukija.lue();
        
        
    }

}
