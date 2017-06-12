package algoritmproject1;

/**
 * @author Prodromos Polichroniadis
 * AEM 2513
 * e-mail: prodpoly@csd.auth.gr
 * 
 * Class used to create diamond objects.
 * Each diamond will have its weight (1 if it the fake one or 10 if it is real).
 */
public class Diamonds {
    
    private int weight;
    
    public Diamonds(int w ){
        weight=w;
    }
    
    public int getWeight(){
        return weight;
    }
    
}
