package algoritmproject1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Prodromos Polichroniadis
 * AEM : 2513
 * e-mail: prodpoly@csd.auth.gr
 */
public class AlgoritmProject1 {

    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Program Functions:");
        System.out.println("1. Treasure Finder");
        System.out.println("2. Diamond Scale");
        System.out.println("Type in the number of the function you need:");
        
        int i = scanner.nextInt();
        if(i==1){
            QuickHull  q = new QuickHull();
            q.start();
        }else if(i==2){
            DiamondScale d = new DiamondScale();
            d.start();
        }
    }
    
}
