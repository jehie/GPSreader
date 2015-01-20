
package sovelluslogiikka;

import java.util.ArrayList;



public class Matka {
    private ArrayList<Double> latitudi;
    private ArrayList<Double> longitudi;
    private ArrayList<String> aikaleima;
    private String paiva;

    public Matka(ArrayList<Double> latitudi, ArrayList<Double> longitudi, ArrayList<String> aikaleima) {
        this.latitudi = latitudi;
        this.longitudi = longitudi;
        this.aikaleima = aikaleima;
        asetapaiva();
    }

    private void asetapaiva() {
        String aika = aikaleima.get(0);
        System.out.println("Ensimmäisen rivin aikaleima on: "+aika);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        
    }
    
    
    
    
 
    
}
