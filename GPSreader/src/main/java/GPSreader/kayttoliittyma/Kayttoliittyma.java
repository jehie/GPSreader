package GPSreader.kayttoliittyma;

import GPSreader.sovelluslogiikka.GoogleMapsOsoitteenRakentaja;
import GPSreader.sovelluslogiikka.Matka;
import GPSreader.sovelluslogiikka.MatkaKokoelma;
import GPSreader.sovelluslogiikka.MatkaLaskin;
import GPSreader.sovelluslogiikka.Muuntaja;
import GPSreader.sovelluslogiikka.Validoija;
import GPSreader.tiedostonlukija.TXTRaakaLukija;
import GPSreader.tiedostonlukija.TXTTallennettuLukija;
import GPSreader.tiedostonlukija.TXTTallentaja;
import java.awt.Component;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Kayttoliittyma extends javax.swing.JFrame {

    MatkaKokoelma matkaKokoelma;
    MatkaLaskin matkalaskin;
    Ilmoittaja ilmoittaja;
    DefaultListModel<String> malli;
    GoogleMapsOsoitteenRakentaja googleMapsOsoitteenRakentaja;
    TXTTallennettuLukija txtTallennettuLukija;
    TXTTallentaja txtTallentaja;
    Validoija validoija;
    Muuntaja muuntaja;
    String kansio;

    /**
     * Graafisen käyttöliittymän konstruktori. Alustaa oliomuuttujat, käynnistää
     * luokat: annaHakemisto(), luoMatkalista(), initComponents() ja
     * paivitaMatkojenTiedot().
     *
     * @see annaHakemisto()
     * @see luoMatkalista()
     * @see initComponents()
     * @see paivitaMatkojenTiedot()
     */
    public Kayttoliittyma() {
        ilmoittaja = new Ilmoittaja();
        matkalaskin = new MatkaLaskin();
        validoija = new Validoija();
        muuntaja = new Muuntaja();
        googleMapsOsoitteenRakentaja = new GoogleMapsOsoitteenRakentaja();
        txtTallennettuLukija = new TXTTallennettuLukija();
        txtTallentaja = new TXTTallentaja();
        kansio = null;

        annaHakemisto();
        luoMatkalista();
        initComponents();
        paivitaMatkojenTiedot();

    }

    /**
     * Metodi pyytää käyttäjältä tallennuskansiota johon tulevat Matka-tiedostot
     * tallennetaan. Kansio voi sisältää myös jo aiemmin tallennettuja matkoja,
     * jotka ohjela lukee automaattisesti sisään. Jos kansiota ei anneta, pyytää
     * ohjelma sitä uudelleen.
     */
    private void annaHakemisto() {

        ilmoittaja.ilmoita("Anna sovellukselle tallennuskansio");
        while (true) {
            JFileChooser tiedostonvalitsija = new JFileChooser();
            tiedostonvalitsija.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int palautusarvo = tiedostonvalitsija.showOpenDialog(null);

            if (palautusarvo == JFileChooser.APPROVE_OPTION) {
                kansio = tiedostonvalitsija.getSelectedFile().getPath();

            }
            if (kansio != null) {
                break;
            }

        }

    }

    /**
     * Metodi luo DefaultListModel<String> muotoisen mallin-objektin, johon
     * tallenetaan sovelluksessa olevien matkojen nimi. Malli annetaan
     * JList-objektille, josta se hakee matkojen nimet.
     */
    private void luoMatkalista() {
        malli = new DefaultListModel<String>();

        matkaKokoelma = txtTallennettuLukija.lueKaikkiTallennetutTiedostot(kansio);

        for (Matka m : matkaKokoelma.getMatkat()) {
            malli.addElement(m.getMatkanNimi());
        }

    }

    /**
     * Metodi luo DefaultListModel<String> muotoisen mallin-objektin, johon
     * tallenetaan sovelluksessa olevien matkojen nimi. Metodi hakee matkat
     * Matkakokoelmasta, eikä tallennuskansiosta. Malli annetaan
     * JList-objektille, josta se hakee matkojen nimet.
     */
    private void paivitaMatkaLista() {
        malli = new DefaultListModel<String>();

        for (Matka m : matkaKokoelma.getMatkat()) {

            malli.addElement(m.getMatkanNimi());

        }
        matkatJList.setModel(malli);
    }

    /**
     * Metodi päivättää graafisessa käyttöliittymässä näytettävien kaikkien
     * matkojen tiedot. Jos sovellukseen lisätään uusimatka, käytetään metodia
     * tietojen päivittämiseen ruudulle. Tiedot asetetaan JLabel-objekteihin.
     */
    private void paivitaMatkojenTiedot() {
        String pituus = String.format("%.2f", matkaKokoelma.getMatkojenpituus());
        matkojenPituusJLabel.setText("Pituus: " + pituus + " km");

        String keskinopeus = String.format("%.2f", matkaKokoelma.getMatkojenKeskinopeus());
        matkojenKeskinopeusJLabel.setText("Keskinopeus: " + keskinopeus + " km/h");

        String kesto = String.format("%.2f", matkaKokoelma.getMatkojenkesto());
        matkojenKestoJLabel.setText("Kesto: " + kesto + " min");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        choice1 = new java.awt.Choice();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        AnnaTiedostoJButton = new javax.swing.JButton();
        PoistaEpaTarkatJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        matkatJList = new javax.swing.JList();
        PoistaMatkaJButton = new javax.swing.JButton();
        valitunMatkanTiedotJPanel = new javax.swing.JPanel();
        matkanKuukausiJLabel = new javax.swing.JLabel();
        matkanPaivaJlabel = new javax.swing.JLabel();
        matkanKestoJLabel = new javax.swing.JLabel();
        matkanPituusJLabel = new javax.swing.JLabel();
        matkanVuosiJLabel = new javax.swing.JLabel();
        matkanKeskinopeusJlabel = new javax.swing.JLabel();
        muokkaaMatkaaJButton = new javax.swing.JButton();
        NaytaKartallaJButton = new javax.swing.JButton();
        MatkanAlkuJButton = new javax.swing.JButton();
        MatkanLoppuJButton = new javax.swing.JButton();
        kaikkienMatkojenTiedotJPanel = new javax.swing.JPanel();
        matkojenKestoJLabel = new javax.swing.JLabel();
        matkojenPituusJLabel = new javax.swing.JLabel();
        matkojenKeskinopeusJLabel = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);
        jTextArea1.getAccessibleContext().setAccessibleName("tiedot");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        AnnaTiedostoJButton.setText("Anna uusi tiedosto");
        AnnaTiedostoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnaTiedostoJButtonActionPerformed(evt);
            }
        });

        PoistaEpaTarkatJButton.setText("Minimitarkkuus");
        PoistaEpaTarkatJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PoistaEpaTarkatJButtonActionPerformed(evt);
            }
        });

        matkatJList.setBorder(javax.swing.BorderFactory.createTitledBorder("Matkat"));
        matkatJList.setModel(malli);
        matkatJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                matkatJListMouseClicked(evt);
            }
        });
        matkatJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                matkatJListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(matkatJList);

        PoistaMatkaJButton.setText("Poista");
        PoistaMatkaJButton.setToolTipText("");
        PoistaMatkaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PoistaMatkaJButtonActionPerformed(evt);
            }
        });

        valitunMatkanTiedotJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Matkan tiedot"));
        valitunMatkanTiedotJPanel.setPreferredSize(new java.awt.Dimension(400, 80));

        matkanKuukausiJLabel.setText("Kuukausi:");

        matkanPaivaJlabel.setText("Päivä: ");

        matkanKestoJLabel.setText("Kesto: ");

        matkanPituusJLabel.setText("Pituus:          ");

        matkanVuosiJLabel.setText("Vuosi:       ");

        matkanKeskinopeusJlabel.setText("Keskinopeus: ");

        javax.swing.GroupLayout valitunMatkanTiedotJPanelLayout = new javax.swing.GroupLayout(valitunMatkanTiedotJPanel);
        valitunMatkanTiedotJPanel.setLayout(valitunMatkanTiedotJPanelLayout);
        valitunMatkanTiedotJPanelLayout.setHorizontalGroup(
            valitunMatkanTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valitunMatkanTiedotJPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(valitunMatkanTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matkanVuosiJLabel)
                    .addComponent(matkanPituusJLabel))
                .addGap(25, 25, 25)
                .addGroup(valitunMatkanTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matkanKuukausiJLabel)
                    .addComponent(matkanKeskinopeusJlabel))
                .addGroup(valitunMatkanTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(valitunMatkanTiedotJPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(matkanPaivaJlabel))
                    .addGroup(valitunMatkanTiedotJPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(matkanKestoJLabel)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        valitunMatkanTiedotJPanelLayout.setVerticalGroup(
            valitunMatkanTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valitunMatkanTiedotJPanelLayout.createSequentialGroup()
                .addGroup(valitunMatkanTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matkanKuukausiJLabel)
                    .addComponent(matkanPaivaJlabel)
                    .addComponent(matkanVuosiJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valitunMatkanTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matkanPituusJLabel)
                    .addComponent(matkanKestoJLabel)
                    .addComponent(matkanKeskinopeusJlabel)))
        );

        muokkaaMatkaaJButton.setText("Muokkaa");
        muokkaaMatkaaJButton.setToolTipText("");
        muokkaaMatkaaJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        muokkaaMatkaaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muokkaaMatkaaJButtonActionPerformed(evt);
            }
        });

        NaytaKartallaJButton.setText("Näytä kartalla");
        NaytaKartallaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NaytaKartallaJButtonActionPerformed(evt);
            }
        });

        MatkanAlkuJButton.setText("Alku");
        MatkanAlkuJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MatkanAlkuJButtonActionPerformed(evt);
            }
        });

        MatkanLoppuJButton.setText("Loppu");
        MatkanLoppuJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MatkanLoppuJButtonActionPerformed(evt);
            }
        });

        kaikkienMatkojenTiedotJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Kaikkien matkojen tiedot"));
        kaikkienMatkojenTiedotJPanel.setMaximumSize(new java.awt.Dimension(400, 80));
        kaikkienMatkojenTiedotJPanel.setPreferredSize(new java.awt.Dimension(400, 80));

        matkojenKestoJLabel.setText("Kesto: ");

        matkojenPituusJLabel.setText("Pituus:");

        matkojenKeskinopeusJLabel.setText("Keskinopeus: ");

        javax.swing.GroupLayout kaikkienMatkojenTiedotJPanelLayout = new javax.swing.GroupLayout(kaikkienMatkojenTiedotJPanel);
        kaikkienMatkojenTiedotJPanel.setLayout(kaikkienMatkojenTiedotJPanelLayout);
        kaikkienMatkojenTiedotJPanelLayout.setHorizontalGroup(
            kaikkienMatkojenTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kaikkienMatkojenTiedotJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(matkojenPituusJLabel)
                .addGap(18, 18, 18)
                .addComponent(matkojenKeskinopeusJLabel)
                .addGap(18, 18, 18)
                .addComponent(matkojenKestoJLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kaikkienMatkojenTiedotJPanelLayout.setVerticalGroup(
            kaikkienMatkojenTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kaikkienMatkojenTiedotJPanelLayout.createSequentialGroup()
                .addGroup(kaikkienMatkojenTiedotJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matkojenPituusJLabel)
                    .addComponent(matkojenKeskinopeusJLabel)
                    .addComponent(matkojenKestoJLabel))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(muokkaaMatkaaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PoistaMatkaJButton))
                    .addComponent(PoistaEpaTarkatJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AnnaTiedostoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MatkanLoppuJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MatkanAlkuJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(NaytaKartallaJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(kaikkienMatkojenTiedotJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valitunMatkanTiedotJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {MatkanAlkuJButton, MatkanLoppuJButton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {PoistaMatkaJButton, muokkaaMatkaaJButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MatkanAlkuJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MatkanLoppuJButton))
                            .addComponent(NaytaKartallaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(kaikkienMatkojenTiedotJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(AnnaTiedostoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PoistaEpaTarkatJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(muokkaaMatkaaJButton)
                            .addComponent(PoistaMatkaJButton)))
                    .addComponent(valitunMatkanTiedotJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {MatkanAlkuJButton, MatkanLoppuJButton});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {PoistaMatkaJButton, muokkaaMatkaaJButton});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {AnnaTiedostoJButton, PoistaEpaTarkatJButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Toiminnankuuntelija Anna uusi tiedosto-napille. Käyttäjän klikatessa
     * nappia, kuuntelija avaa tiedostonvalitsijan (JFileChooser).
     * Tiedostonvalitsija näyttää vain .txt päätteiset tiedostot. Tiedostopolku
     * annetaan TXTRaakaLukijalle, joka lukee tiedoston sisään ja palauttaa
     * Matka-olion. Olio tallennetaan TXTTallentajalla. Matkojen tiedot
     * päivitetään. Jos operaatio onnistuu, ilmoitetaan tästä käyttäjälle. Jos
     * operaatio epäonnistuu ilmoitetaan tästäkin.
     *
     * @see TXTRaakaLukija
     * @see TXTTallentaja
     * @see paivitaMatkaLista()
     * @see Ilmoittaja
     *
     */
    private void AnnaTiedostoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnaTiedostoJButtonActionPerformed

        JFileChooser tiedostonvalitsija = new JFileChooser();
        FileNameExtensionFilter filtteri = new FileNameExtensionFilter(".txt",
                "txt");
        tiedostonvalitsija.setFileFilter(filtteri);

        int palautusarvo = tiedostonvalitsija.showOpenDialog(null);
        if (palautusarvo == JFileChooser.APPROVE_OPTION) {

            TXTRaakaLukija txtrl = new TXTRaakaLukija();
            Matka uusimatka = txtrl.lue(tiedostonvalitsija.getSelectedFile().getPath());
            if (uusimatka != null) {
                matkaKokoelma.lisaaMatka(uusimatka);
                TXTTallentaja tlt = new TXTTallentaja();
                tlt.kirjoitaMatkaTiedostoon(uusimatka, kansio);
                paivitaMatkaLista();
                ilmoittaja.ilmoita("Tiedosto luettu ja" + " tallennettu ohjelmaan!");
                paivitaMatkojenTiedot();
            } else {
                ilmoittaja.ilmoita("Tiedosto on viallinen!");

            }

        }
    }//GEN-LAST:event_AnnaTiedostoJButtonActionPerformed

    /**
     * Toiminnankuuntelija Minimitarkkuus -napille. Käyttäjän klikatessa nappia,
     * kuuntelija avaa ikkunan jossa kysytään haluttua minimitarkkuutta. Metodi
     * validoi onko syöte Integer-tyyppinen ja ilmoittaa mahdollisesta
     * virheellisyydestä. Poistaa MatkaKokoelmasta epätarkat mittaukset käyttäen
     * PoistaEpaTarkatMittaukset-metodia. Päivittää tiedot näkymään.
     *
     *
     * @see Validoija.validoiOnkoInputInteger(tarkkuus)
     * @see MatkaKokoelma.PoistaEpaTarkatMittaukset()
     * @see paivitaMatkojenTiedot()
     * @see Ilmoittaja
     *
     */
    private void PoistaEpaTarkatJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoistaEpaTarkatJButtonActionPerformed
        Component parent = null;

        String tarkkuus = JOptionPane.showInputDialog("Anna minimi tarkkuus", parent);
        if (!validoija.validoiOnkoInputInteger(tarkkuus)) {
            ilmoittaja.ilmoita("Antamasi syöte (" + tarkkuus + ") on virheellinen");
        } else {
            int tarkkuusint = Integer.valueOf(tarkkuus);
            matkaKokoelma.PoistaEpaTarkatMittaukset(tarkkuusint, kansio);

            ilmoittaja.ilmoita("Epätarkat mittaukset poistettu!");
            //JOptionPane.showMessageDialog(parent, "Epätarkat mittaukset poistettu!");
            System.out.println(tarkkuus);
            paivitaMatkojenTiedot();
        }


    }//GEN-LAST:event_PoistaEpaTarkatJButtonActionPerformed

    private void matkatJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_matkatJListValueChanged

    }//GEN-LAST:event_matkatJListValueChanged

    private void PoistaMatkaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoistaMatkaJButtonActionPerformed
        String nimi = (String) matkatJList.getSelectedValue();

        if (nimi == null) {
            ilmoittaja.ilmoita("Valitse poistettava matka!");
        } else {
            matkaKokoelma.poistaMatkaNimella(nimi);
            paivitaMatkaLista();
            paivitaMatkojenTiedot();
            ilmoittaja.ilmoita("Matka poistettu!");

        }


    }//GEN-LAST:event_PoistaMatkaJButtonActionPerformed

    private void matkatJListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_matkatJListMouseClicked
        String valittu = (String) matkatJList.getSelectedValue();

        matkanVuosiJLabel.setText("Vuosi: " + matkaKokoelma.getMatkaNimella(valittu).getVuosi());
        String kuukausi = muuntaja.matkanKuukausiTekstina(matkaKokoelma.getMatkaNimella(valittu).getKuukausi());
        matkanKuukausiJLabel.setText("Kuukausi: " + kuukausi);
        matkanPaivaJlabel.setText("Päivä: " + matkaKokoelma.getMatkaNimella(valittu).getPaiva());
        System.out.println(matkaKokoelma.getMatkaNimella(valittu).getPaiva());

        String pituus = String.format("%.2f", matkaKokoelma.getMatkaNimella(valittu).getKuljettumatka());
        matkanPituusJLabel.setText("Pituus: " + pituus + " km");

        String kesto = String.format("%.1f", matkaKokoelma.getMatkaNimella(valittu).getKesto());
        matkanKestoJLabel.setText("Kesto: " + kesto + " min");

        String keskinopeus = String.format("%.1f", matkaKokoelma.getMatkaNimella(valittu).getKeskinopeus());
        matkanKeskinopeusJlabel.setText("Keskinopeus: " + keskinopeus + "km/h");
    }//GEN-LAST:event_matkatJListMouseClicked

    private void muokkaaMatkaaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muokkaaMatkaaJButtonActionPerformed
        String valittu = (String) matkatJList.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse muokattava matka!");
        } else {

            JTextField vuosi = new JTextField(matkaKokoelma.getMatkaNimella(valittu).getVuosi());
            JTextField kuukausi = new JTextField(matkaKokoelma.getMatkaNimella(valittu).getKuukausi());
            JTextField paiva = new JTextField(matkaKokoelma.getMatkaNimella(valittu).getPaiva());

            JPanel paneeli = new JPanel();
            paneeli.add(new JLabel("Vuosi:"));
            paneeli.add(vuosi);

            paneeli.add(new JLabel("Kuukausi: "));
            paneeli.add(kuukausi);

            paneeli.add(new JLabel("Paiva: "));
            paneeli.add(paiva);

            JOptionPane.showInputDialog(null, paneeli);
            String uusivuosi = vuosi.getText();
            String uusikuukausi = kuukausi.getText();
            String uusipaiva = paiva.getText();

            //Haetaan matka kokoelmasta
            Matka m = matkaKokoelma.getMatkaNimella(valittu);

            //Poistetaan matka kokoelmasta ja tallennetaan muokattu tilalle
            matkaKokoelma.poistaMatkaNimella(valittu);
            m.muutaAika(uusivuosi, uusikuukausi, uusipaiva);
            matkaKokoelma.lisaaMatka(m);
            txtTallentaja.kirjoitaMatkaTiedostoon(m, kansio);
            paivitaMatkaLista();
            matkatJList.setModel(malli);
        }

    }//GEN-LAST:event_muokkaaMatkaaJButtonActionPerformed

    private void NaytaKartallaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NaytaKartallaJButtonActionPerformed
        String valittu = (String) matkatJList.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse näytettävä matka!");
        } else {

            int koko = matkaKokoelma.getMatkaNimella(valittu).getLatitudi().size();
            String osoite = googleMapsOsoitteenRakentaja.rakennaOsoitePolulla(matkaKokoelma.getMatkaNimella(valittu).getLatitudi(), matkaKokoelma.getMatkaNimella(valittu).getLongitudi(), "roadmap");

            GoogleMaps gm = new GoogleMaps();
            try {
                gm.avaaKartta(osoite);
            } catch (IOException ex) {
                Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_NaytaKartallaJButtonActionPerformed

    private void MatkanAlkuJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MatkanAlkuJButtonActionPerformed
        String valittu = (String) matkatJList.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse näytettävä matka!");
        } else {
            double lat = matkaKokoelma.getMatkaNimella(valittu).getLatitudi().get(0);
            double lon = matkaKokoelma.getMatkaNimella(valittu).getLongitudi().get(0);
            String osoite = googleMapsOsoitteenRakentaja.rakennaOsoiteMarkereilla(lat, lon, "roadmap", 16);

            GoogleMaps gm = new GoogleMaps();
            try {
                gm.avaaKartta(osoite);
            } catch (IOException ex) {
                Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_MatkanAlkuJButtonActionPerformed

    private void MatkanLoppuJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MatkanLoppuJButtonActionPerformed
        String valittu = (String) matkatJList.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse näytettävä matka!");
        } else {
            int koko = matkaKokoelma.getMatkaNimella(valittu).getLatitudi().size() - 1;
            double lat = matkaKokoelma.getMatkaNimella(valittu).getLatitudi().get(koko);
            double lon = matkaKokoelma.getMatkaNimella(valittu).getLongitudi().get(koko);
            String osoite = googleMapsOsoitteenRakentaja.rakennaOsoiteMarkereilla(lat, lon, "roadmap", 16);

            GoogleMaps gm = new GoogleMaps();
            try {
                gm.avaaKartta(osoite);
            } catch (IOException ex) {
                Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_MatkanLoppuJButtonActionPerformed
    /**
     * Metodi käynnistää käyttöliittymä-luokan ja asettaa sen näkyviin. 
     *
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kayttoliittyma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnnaTiedostoJButton;
    private javax.swing.JButton MatkanAlkuJButton;
    private javax.swing.JButton MatkanLoppuJButton;
    private javax.swing.JButton NaytaKartallaJButton;
    private javax.swing.JButton PoistaEpaTarkatJButton;
    private javax.swing.JButton PoistaMatkaJButton;
    private java.awt.Choice choice1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel kaikkienMatkojenTiedotJPanel;
    private javax.swing.JLabel matkanKeskinopeusJlabel;
    private javax.swing.JLabel matkanKestoJLabel;
    private javax.swing.JLabel matkanKuukausiJLabel;
    private javax.swing.JLabel matkanPaivaJlabel;
    private javax.swing.JLabel matkanPituusJLabel;
    private javax.swing.JLabel matkanVuosiJLabel;
    private javax.swing.JList matkatJList;
    private javax.swing.JLabel matkojenKeskinopeusJLabel;
    private javax.swing.JLabel matkojenKestoJLabel;
    private javax.swing.JLabel matkojenPituusJLabel;
    private javax.swing.JButton muokkaaMatkaaJButton;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JPanel valitunMatkanTiedotJPanel;
    // End of variables declaration//GEN-END:variables
}
