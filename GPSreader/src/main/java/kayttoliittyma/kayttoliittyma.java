package kayttoliittyma;

import sovelluslogiikka.Matka;
import tiedostonlukija.TXTLukija;

public class kayttoliittyma {

    public static void main(String[] args) throws Exception {
        TXTLukija lukija = new TXTLukija("C:\\Users\\Jesse\\Documents\\Javalabra\\GPSreader\\GPSreader\\20150118162507.txt", ",");
        Matka m = lukija.lue();
        m.laskeKuljettuMatka();
        
      

    }

    



}
