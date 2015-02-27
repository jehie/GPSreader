# GPSreader
Ohjelma suoritetaan k‰ynnist‰m‰ll‰ jar-tiedosto. Ensimm‰iseksi valitaan hakemisto, johon matkat tallennetaan ja josta ohjelma yritt‰‰ lukea jo tallennettuja matkoja sis‰‰n. Kansio voi myˆs sis‰lt‰‰ muitakin tiedostoja, ohjelman ei tulisi lukea niit‰ sis‰‰n.  Ohjelma ei p‰‰st‰ k‰ytt‰j‰‰ eteenp‰in jos kansiota ei valita. Kansio tulee valita aina kun ohjelma k‰ynnistet‰‰n. N‰in ohjelmaa voi halutessaan k‰ytt‰‰ useampi k‰ytt‰j‰ samalla tietokoneella. Valittuaan kansion avautuu ohjelman p‰‰ikkuna. 
 
P‰‰n‰kym‰st‰ lˆytyy useampia toimintoja. Vasemmalla ylh‰‰ll‰ on listattuna ohjelmaan luetut Matkat, jotka kuvataan: vuosi_kuukausi_p‰iv‰_aloituskellonaika, nime‰misperusteella. Eli esimerkiksi 2015_1_19_20-niminen matka on tehty vuonna 19. Tammikuuta 2015 Kello 20 alkaen. 
Kaikkien matkojen tiedot kohdasta k‰ytt‰j‰ voi n‰hd‰ kaikkien ohjelmaan luettujen matkojen tiedot.
Matkan tiedot kohdasta k‰ytt‰j‰ voi tarkastella yksitt‰isen matkojen tietoja, valitsemalla listalta aina yhden kerrallaan. Valittuaan matkan ohjelma p‰ivitt‰‰ tiedot Matkan tiedot-kohtaan. 
Muokkaa-napilla k‰ytt‰j‰ voi vaihtaa valitun matkan vuoden, kuukauden tai p‰iv‰n jos n‰in haluaa. Ohjelma poistaa vanhan matkan ja tallentaa uuden tilalle. Muutoksen voi viel‰ perua painamalla Muokkaa n‰pp‰int‰ uudelleen, jolloin ohjelma ehdottaa alkuper‰isi‰ arvoja. 
Poista-napilla valittu matka voidaan poistaa ohjelmasta. Toiminto poistaa matkan myˆs kansiosta, toiminto on siis pysyv‰. 
Anna uusi tiedosto-n‰pp‰imell‰ voidaan ohjelmaan lukea uusi tiedosto. Tiedoston tulee olla tekstimuotoinen ja tuotu suoraan GPSLogger Android-ohjelmasta. Jos tiedosto on virheellinen, ilmoittaa ohjelma t‰st‰. Ohjelmassa on useita varmistuksia tiedoston oikeellisuudelle. Jos tiedosto luetaan oikein, ohjelma ilmoittaa t‰st‰ ja p‰ivitt‰‰ Matkan n‰kyviin listalle.
Minimitarkkuus-napilla voidaan poistaa ohjelmaan luetuista tiedostoista ep‰tarkkoja mittauksia. Toiminnolle annetaan minimitarkkuus metreiss‰, jota ep‰tarkemmat mittaukset poistetaan. Jos k‰ytt‰j‰ antaa syˆtteeksi 100 metri‰, poistaa ohjelma Matka-oliosta rivit, joiden tarkkuus on t‰t‰ huonompia. Muutos on pysyv‰. 
K‰ytt‰j‰ voi myˆs tarkastella tekemi‰‰n matkoja Google Maps-karttojen avulla:
Alku-nappia painamalla k‰ytt‰j‰ n‰kee Google Maps-kartan ja merkinn‰n mist‰ ensimm‰inen mittaus on tehty.
Loppu-nappia painamalla n‰hd‰‰n taas viimeisen mittauksen sijainti kartalla.
N‰yt‰ kartalla nappi n‰ytt‰‰ taas koko matkan reitin, k‰ytt‰en osaa mittauksista visualisointiin. Vain joka viides merkint‰ otetaan mukaan, jotta v‰ltyt‰‰n liian pitk‰lt‰ pyynnˆlt‰(maksimirajoitus). Lyhyill‰ matkoilla t‰m‰ voi johtaa îoikaisuunî kartalla. 
Ohjelma sulkeutuu X-napista oikeassa yl‰reunassa. 
v
