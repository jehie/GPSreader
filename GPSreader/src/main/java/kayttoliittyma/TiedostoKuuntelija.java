package kayttoliittyma;

import GPSreader.sovelluslogiikka.Matka;
import GPSreader.tiedostonlukija.TXTRaakaLukija;
import GPSreader.tiedostonlukija.TXTTallentaja;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TiedostoKuuntelija implements ActionListener {

    JFileChooser fileChooser;

    @Override
    public void actionPerformed(ActionEvent ae) {
        fileChooser = new JFileChooser();

        JFileChooser tiedostonvalitsija = new JFileChooser();
        FileNameExtensionFilter filtteri = new FileNameExtensionFilter(".txt",
        "txt");
        tiedostonvalitsija.setFileFilter(filtteri);
        
        Component parent = null;
        int palautusarvo = tiedostonvalitsija.showOpenDialog(parent);
        if (palautusarvo == JFileChooser.APPROVE_OPTION) {
            System.out.println("Avasit tiedoston: "
                    + tiedostonvalitsija.getSelectedFile().getPath());
            TXTRaakaLukija txtrl = new TXTRaakaLukija();
            Matka uusimatka = txtrl.lue(tiedostonvalitsija.getSelectedFile().getPath());
            TXTTallentaja tlt = new TXTTallentaja();
            tlt.kirjoitaMatkaTiedostoon(uusimatka);
            JOptionPane.showMessageDialog(parent, "Tiedosto luettu ja"
                    + " tallennettu ohjelmaan!");
        }

    }

}
