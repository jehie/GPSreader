package kayttoliittyma;

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

public class GUI extends javax.swing.JFrame {

    MatkaKokoelma matkakokoelma;
    MatkaLaskin matkalaskin;
    DefaultListModel<String> malli;
    GoogleMapsOsoitteenRakentaja gmor;
    TXTTallennettuLukija tl;
    Validoija validoija;
    Muuntaja muuntaja;
    Ilmoittaja ilmoittaja = new Ilmoittaja();

    /**
     * Creates new form GUI
     */
    public GUI() {
        luolista();
        initComponents();
        paivitaMatkojenTiedot();

    }

    private void luolista() {
        malli = new DefaultListModel<String>();
        matkalaskin = new MatkaLaskin();
        validoija = new Validoija();
        muuntaja = new Muuntaja();
        gmor = new GoogleMapsOsoitteenRakentaja();
        tl = new TXTTallennettuLukija();
        matkakokoelma = tl.lueKaikkiTallennetutTiedostot();

        for (Matka m : matkakokoelma.getMatkat()) {
            System.out.println(m.getMatkanNimi());
            malli.addElement(m.getMatkanNimi());
        }

    }

    private void paivitalista() {
        malli = new DefaultListModel<String>();

        for (Matka m : matkakokoelma.getMatkat()) {

            System.out.println(m.getMatkanNimi());
            malli.addElement(m.getMatkanNimi());

        }
        jList2.setModel(malli);
    }

    private void paivitaMatkojenTiedot() {
        String pituus = String.format("%.2f", matkakokoelma.getMatkojenpituus());
        jLabel12.setText("Pituus: " + pituus + " km");

        String keskinopeus = String.format("%.2f", matkakokoelma.getMatkojenKeskinopeus());
        jLabel14.setText("Keskinopeus: " + keskinopeus + " km/h");

        String kesto = String.format("%.2f", matkakokoelma.getMatkojenkesto());
        jLabel11.setText("Kesto: " + kesto + " min");

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
        jButton1 = new javax.swing.JButton();
        PoistaEpaTarkatButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);
        jTextArea1.getAccessibleContext().setAccessibleName("tiedot");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("Anna uusi tiedosto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        PoistaEpaTarkatButton.setText("Minimitarkkuus");
        PoistaEpaTarkatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PoistaEpaTarkatButtonActionPerformed(evt);
            }
        });

        jList2.setBorder(javax.swing.BorderFactory.createTitledBorder("Matkat"));
        jList2.setModel(malli);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jButton3.setText("Poista");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Matkan tiedot"));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 80));

        jLabel3.setText("Kuukausi:");

        jLabel4.setText("Päivä: ");

        jLabel5.setText("Kesto: ");

        jLabel6.setText("Pituus:          ");

        jLabel7.setText("Vuosi:       ");

        jLabel8.setText("Keskinopeus: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)))
        );

        jButton4.setText("Muokkaa");
        jButton4.setToolTipText("");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Näytä kartalla");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Alku");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Loppu");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Kaikkien matkojen tiedot"));
        jPanel3.setMaximumSize(new java.awt.Dimension(400, 80));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 80));

        jLabel11.setText("Kesto: ");

        jLabel12.setText("Pituus:");

        jLabel14.setText("Keskinopeus: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
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
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(PoistaEpaTarkatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton6, jButton7});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PoistaEpaTarkatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton6, jButton7});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jButton4});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {PoistaEpaTarkatButton, jButton1});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

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
            if (uusimatka != null) {
                matkakokoelma.lisaaMatka(uusimatka);
                TXTTallentaja tlt = new TXTTallentaja();
                tlt.kirjoitaMatkaTiedostoon(uusimatka);
                paivitalista();
                JOptionPane.showMessageDialog(parent, "Tiedosto luettu ja" + " tallennettu ohjelmaan!");
                paivitaMatkojenTiedot();
            } else {
                JOptionPane.showMessageDialog(parent, "Tiedosto on viallinen!");

            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void PoistaEpaTarkatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoistaEpaTarkatButtonActionPerformed
        Component parent = null;

        String tarkkuus = JOptionPane.showInputDialog("Anna minimi tarkkuus", parent);
        if (!validoija.validoiOnkoInputInteger(tarkkuus)) {
            ilmoittaja.ilmoita("Antamasi syöte (" + tarkkuus + ") on virheellinen");
        } else {
            int tarkkuusint = Integer.valueOf(tarkkuus);
            matkakokoelma.PoistaEpaTarkatMittaukset(tarkkuusint);

            ilmoittaja.ilmoita("Epätarkat mittaukset poistettu!");
            //JOptionPane.showMessageDialog(parent, "Epätarkat mittaukset poistettu!");
            System.out.println(tarkkuus);

        }


    }//GEN-LAST:event_PoistaEpaTarkatButtonActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged

    }//GEN-LAST:event_jList2ValueChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nimi = (String) jList2.getSelectedValue();
        System.out.println(nimi+" aaaaaa");
        Component parent = null;
        System.out.println(nimi);
        if (nimi == null) {
            JOptionPane.showMessageDialog(parent, "Valitse poistettava matka!");
        } else {
            matkakokoelma.poistaMatkaNimella(nimi);

            paivitalista();
            JOptionPane.showMessageDialog(parent, "Matka poistettu!");
            paivitaMatkojenTiedot();
            
        }
        
        try{
 
    		File file = new File("c:\\logfile20100131.log");
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        String valittu = (String) jList2.getSelectedValue();

        jLabel7.setText("Vuosi: " + matkakokoelma.getMatkaNimella(valittu).getVuosi());
        String kuukausi = muuntaja.matkanKuukausiTekstina(matkakokoelma.getMatkaNimella(valittu).getKuukausi());
        jLabel3.setText("Kuukausi: " + kuukausi);
        jLabel4.setText("Päivä: " + matkakokoelma.getMatkaNimella(valittu).getPaiva());

        String pituus = String.format("%.2f", matkakokoelma.getMatkaNimella(valittu).getKuljettumatka());
        jLabel6.setText("Pituus: " + pituus + " km");

        String kesto = String.format("%.1f", matkakokoelma.getMatkaNimella(valittu).getKesto());
        jLabel5.setText("Kesto: " + kesto + " min");

        String keskinopeus = String.format("%.1f", matkakokoelma.getMatkaNimella(valittu).getKeskinopeus());
        jLabel8.setText("Keskinopeus: " + keskinopeus + "km/h");
    }//GEN-LAST:event_jList2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String valittu = (String) jList2.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse muokattava matka!");
        } else {

            JTextField vuosi = new JTextField(matkakokoelma.getMatkaNimella(valittu).getVuosi());

            JTextField kuukausi = new JTextField(matkakokoelma.getMatkaNimella(valittu).getKuukausi());

            JTextField paiva = new JTextField(matkakokoelma.getMatkaNimella(valittu).getPaiva());

            Component parent = null;
            JPanel paneeli = new JPanel();
            paneeli.add(new JLabel("Vuosi:"));
            paneeli.add(vuosi);

            paneeli.add(new JLabel("Kuukausi: "));
            paneeli.add(kuukausi);

            paneeli.add(new JLabel("Paiva: "));
            paneeli.add(paiva);
            paneeli.add(Box.createVerticalStrut(15));

            paneeli.add(new JLabel("Anna kommentti: "));

            String kommentti = JOptionPane.showInputDialog(parent, paneeli, matkakokoelma.getMatkaNimella(valittu).getKommentti());
            String uusivuosi = vuosi.getText();
            String uusikuukausi = kuukausi.getText();
            String uusipaiva = paiva.getText();
            matkakokoelma.getMatkaNimella(valittu).setKommentti(kommentti);
            matkakokoelma.getMatkaNimella(valittu).muutaAika(uusivuosi, uusikuukausi, uusipaiva);

            paivitalista();
            jList2.setModel(malli);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String valittu = (String) jList2.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse näytettävä matka!");
        } else {

            int koko = matkakokoelma.getMatkaNimella(valittu).getLatitudi().size();
            String osoite = gmor.rakennaOsoitePolulla(matkakokoelma.getMatkaNimella(valittu).getLatitudi(), matkakokoelma.getMatkaNimella(valittu).getLongitudi(), "roadmap");

            GoogleMaps gm = new GoogleMaps();
            try {
                gm.avaaKartta(osoite);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String valittu = (String) jList2.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse näytettävä matka!");
        } else {
            double lat = matkakokoelma.getMatkaNimella(valittu).getLatitudi().get(0);
            double lon = matkakokoelma.getMatkaNimella(valittu).getLongitudi().get(0);
            String osoite = gmor.rakennaOsoiteMarkereilla(lat, lon, "roadmap", 18);

            GoogleMaps gm = new GoogleMaps();
            try {
                gm.avaaKartta(osoite);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String valittu = (String) jList2.getSelectedValue();
        if (valittu == null) {
            JOptionPane.showMessageDialog(null, "Valitse näytettävä matka!");
        } else {
            int koko = matkakokoelma.getMatkaNimella(valittu).getLatitudi().size() - 1;
            double lat = matkakokoelma.getMatkaNimella(valittu).getLatitudi().get(koko);
            double lon = matkakokoelma.getMatkaNimella(valittu).getLongitudi().get(koko);
            String osoite = gmor.rakennaOsoiteMarkereilla(lat, lon, "roadmap", 18);

            GoogleMaps gm = new GoogleMaps();
            try {
                gm.avaaKartta(osoite);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PoistaEpaTarkatButton;
    private java.awt.Choice choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
