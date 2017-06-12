package algorithmproject2;

/**
 * A.E.M. : 2513
 * Name : Prodromos Polichroniadis
 * e-mail: prodpoly@csd.auth.gr
 * 
 * This class is created to keep hold of all the clients read from the file.
 * We need to know the total amount of VMs that they want and the price they are willing to pay for them at all times.
 * In order to do so we create an object of this class for each one of them.
 * 
 */


public class VMClient {
    
    private int total; //total amount of VMs wanted by a client
    private float price; //price offer for the number of VMs wanted by a client
    
    public VMClient(int x, float y){
        total=x;
        price=y;
    }
    
    public int getTotal(){
        return total;
    }
    
    public float getPrice(){
        return price;
    }
    
}
