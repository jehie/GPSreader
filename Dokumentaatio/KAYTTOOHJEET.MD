# GPSreader
Ohjelma suoritetaan käynnistämällä jar-tiedosto. Ensimmäiseksi valitaan hakemisto, johon matkat tallennetaan ja josta ohjelma yrittää lukea jo tallennettuja matkoja sisään. Kansio voi myös sisältää muitakin tiedostoja, ohjelman ei tulisi lukea niitä sisään.  Ohjelma ei päästä käyttäjää eteenpäin jos kansiota ei valita. Kansio tulee valita aina kun ohjelma käynnistetään. Näin ohjelmaa voi halutessaan käyttää useampi käyttäjä samalla tietokoneella. Valittuaan kansion avautuu ohjelman pääikkuna. 
 
Päänäkymästä löytyy useampia toimintoja. Vasemmalla ylhäällä on listattuna ohjelmaan luetut Matkat, jotka kuvataan: vuosi_kuukausi_päivä_aloituskellonaika, nimeämisperusteella. Eli esimerkiksi 2015_1_19_20-niminen matka on tehty vuonna 19. Tammikuuta 2015 Kello 20 alkaen. 

Kaikkien matkojen tiedot kohdasta käyttäjä voi nähdä kaikkien ohjelmaan luettujen matkojen tiedot.

Matkan tiedot kohdasta käyttäjä voi tarkastella yksittäisen matkojen tietoja, valitsemalla listalta aina yhden kerrallaan. Valittuaan matkan ohjelma päivittää tiedot Matkan tiedot-kohtaan. 

Muokkaa-napilla käyttäjä voi vaihtaa valitun matkan vuoden, kuukauden tai päivän jos näin haluaa. Ohjelma poistaa vanhan matkan ja tallentaa uuden tilalle. Muutoksen voi vielä perua painamalla Muokkaa näppäintä uudelleen, jolloin ohjelma ehdottaa alkuperäisiä arvoja. 

Poista-napilla valittu matka voidaan poistaa ohjelmasta. Toiminto poistaa matkan myös kansiosta, toiminto on siis pysyvä. 

Anna uusi tiedosto-näppäimellä voidaan ohjelmaan lukea uusi tiedosto. Tiedoston tulee olla tekstimuotoinen ja tuotu suoraan GPSLogger Android-ohjelmasta. Jos tiedosto on virheellinen, ilmoittaa ohjelma tästä. Ohjelmassa on useita varmistuksia tiedoston oikeellisuudelle. Jos tiedosto luetaan oikein, ohjelma ilmoittaa tästä ja päivittää Matkan näkyviin listalle.

Minimitarkkuus-napilla voidaan poistaa ohjelmaan luetuista tiedostoista epätarkkoja mittauksia. Toiminnolle annetaan minimitarkkuus metreissä, jota epätarkemmat mittaukset poistetaan. Jos käyttäjä antaa syötteeksi 100 metriä, poistaa ohjelma Matka-oliosta rivit, joiden tarkkuus on tätä huonompia. Muutos on pysyvä. 

Käyttäjä voi myös tarkastella tekemiään matkoja Google Maps-karttojen avulla:

Alku-nappia painamalla käyttäjä näkee Google Maps-kartan ja merkinnän mistä ensimmäinen mittaus on tehty.

Loppu-nappia painamalla nähdään taas viimeisen mittauksen sijainti kartalla.

Näytä kartalla nappi näyttää taas koko matkan reitin, käyttäen osaa mittauksista visualisointiin. Vain joka viides merkintä otetaan mukaan, jotta vältytään liian pitkältä pyynnöltä(maksimirajoitus). Lyhyillä matkoilla tämä voi johtaa ”oikaisuun” kartalla. 

Ohjelma sulkeutuu X-napista oikeassa yläreunassa. 

Mallitiedostoja löytyy Dokumentaatio/MalliMatkat hakemistosta, joilla käyttäjä voi testata ohjelman toimintaa. 
