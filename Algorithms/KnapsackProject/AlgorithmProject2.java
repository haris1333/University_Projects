package algorithmproject2;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A.E.M. : 2513
 * Name : Prodromos Polichroniadis
 * e-mail : prodpoly@csd.auth.gr
 * 
 * This is the main class of the project that initiates the whole program. 
 * Asks the user which function he wants to operate and initiates his choice. 
 * 
 */
public class AlgorithmProject2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        System.out.println("Which operation do you want to use ?");
        System.out.println("Press 1 for operation A or 2 for operation B: ");
        Scanner sc = new Scanner(System.in);
        
        int choice = sc.nextInt();
        if(choice==1){
            MinimumVMSorter m = new MinimumVMSorter();
            m.start();
        }else if(choice==2){
            KnapsackSorter ks = new KnapsackSorter();
            ks.start();
        }
    }
    
}
