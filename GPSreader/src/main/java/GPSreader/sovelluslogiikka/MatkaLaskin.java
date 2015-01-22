package GPSreader.sovelluslogiikka;
//Luokka johon tulee kaikki tarpeellinen laskenta mitä Matka-luokalle tehdään
public class MatkaLaskin {

    //Laskee etäisyyden kahden koordinaatin välillä, käyttäen harersinen kaavaa.
    public static double laskeEtaisyys(double alkuLat, double alkuLon,
            double kohdeLat, double kohdeLon) {
        double maanHalkaisija = 6371;

        double latEtaisyys = Math.toRadians(alkuLat - kohdeLat);
        double lonEtaisyys = Math.toRadians(alkuLon - kohdeLon);

        double a = Math.sin(latEtaisyys / 2) * Math.sin(latEtaisyys / 2)
                + Math.cos(Math.toRadians(alkuLat)) * Math.cos(Math.toRadians(kohdeLat))
                * Math.sin(lonEtaisyys / 2) * Math.sin(lonEtaisyys / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //palautus kilometreinä
        return (maanHalkaisija * c);
    }

}
