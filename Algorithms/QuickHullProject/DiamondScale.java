package algoritmproject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Prodromos Polichroniadis
 * AEM: 2513
 * e-mail: prodpoly@csd.auth.gr
 * 
 * The algorithm  I used for this operation is a repetitive one and works like this: We take the number (n) of the given diamonds and divide it by 2.
 * Then, we take the 2 different (n/2) portions of diamonds and put them on a scale. The lighter one contains the fake diamond, so we take the lighter portion and divide it by 2 again.
 * We repeat the same exact process until we are down to two diamonds where we can see which one is the fake one. 
 * If the  number of  the remaining diamonds is a prime number, then we subtract one diamond from our available diamonds and divide again by 2. 
 * We scale the two portions and if their weight is the same, then the diamond we subtracted is the fake diamond and the algorithm completes.
 * If not, then we continue in the same way as before with the lighter portion of diamonds.
 * The worst case senario for this algorithm is when the fake diamond is the last one that is left to us. 
 * With this algorithm, each time we divide by 2 we have to solve a smaller problem than the previous one. So we face the same situation over and over again, but only with half the number of diamonds from what we had in the previous repetition.
 * A repetitive mathematical equation for an algorithm like this is W(worst)(n) = W(worst)(n/2) + 1 for every n > 1 and with knowing that W(worst)(1)=1. W comes from Weightings.
 * If we solve this equation backwards and with the change of n to 2^k we find that the worst case complexity of this algorithm is W(worst)(n) = logn. Interestingly enough, we see that the same one applies for the medium case scenario.
 * So, the algorithm's complexity is O(logn) as asked.
 * No free source code used.
 */
public class DiamondScale {

    /**
     * This is the "main" method for our operation to be initiated.
     * It reads from the file the number of diamonds and then creates an ArrayList containing all of them. One is ,of course , fake.
     * Calls the repetitive Scale method to begin the algorithm.
     * @throws FileNotFoundException 
     */
    public void start() throws FileNotFoundException { 
        
        File file = new File("xy.txt"); //Reading of the file 
        int num_of_weightings=0; //number of the weightings done to find the fake diamond
        Scanner sc = new Scanner(file);
        int N=0; //number of diamonds
        int counter=0; //variable used to skip the useless ,for this operation , first integers of the .txt file
        
        while(sc.hasNextInt()){
            int i=sc.nextInt();
            if(counter==3){
                N=sc.nextInt();
                break;
            }
                
            counter++;
        }
        
        sc.close();
        

        ArrayList<Diamonds> diamonds = new ArrayList<Diamonds>(); //ArrayList containing all the diamonds
        
        Diamonds false_d=new Diamonds(1); //fake diamond initialized and put into the ArrayList. The weight of the fake diamond is 1 whereas 10 is for the real diamonds
        diamonds.add(false_d);
        
        for (int i=1;i<N ; i++){ //The rest of the diamonds initialized and put into the ArrayList
            Diamonds d = new Diamonds(10);//10 is the weight of the real diamonds
            diamonds.add(d);
        }
        
        Collections.shuffle(diamonds); //Shuffles all the diamonds so that we don't have the fake one always in the same position
        
        
        
        num_of_weightings = Scale(diamonds,num_of_weightings); //Call of the repetitive method 
        
        System.out.println("The number of weightings is :" + num_of_weightings);
    }
    
    /**
     * This method is repetitive and implements the logic of the algorithm explained above.
     * @param diamonds The ArrayList containing all the diamonds
     * @param num_of_weightings The number of weighting done to find the fake diamond
     * @return the number of weightings
     */
    public static int Scale(ArrayList<Diamonds> diamonds , int num_of_weightings){
        
        int sum1=0; //The weight of the first portion of the diamonds
        int sum2=0; //The weight of the second one
        
        if(diamonds.size()%2!=0){ //Checks if the number of diamonds available is a prime number and removes one if true
            
                diamonds.remove(0);
            
        }
        /**
         * divide the number of diamonds to two different portions
         */
        ArrayList<Diamonds> h_diamonds = new ArrayList<Diamonds>(diamonds.subList(0, diamonds.size()/2  )); 
        ArrayList<Diamonds> h_diamonds2 = new ArrayList<Diamonds>(diamonds.subList(diamonds.size()/2 ,diamonds.size()  ));
        
        num_of_weightings++; 
        
        for(Diamonds diamond : h_diamonds){ //for-loop to calculate the weight of the first portion
            sum1=sum1+diamond.getWeight();
        }
        
        for(Diamonds diamond : h_diamonds2){ //for-loop to calculate the weight of the second portion
            sum2=sum2+diamond.getWeight();
        }
        
        
        if(sum1==1 || sum2==1){ 
            return num_of_weightings;
        }
        else if(sum1<sum2){
            num_of_weightings=Scale(h_diamonds,num_of_weightings);
        }
        else if (sum2<sum1){
            num_of_weightings=Scale(h_diamonds2,num_of_weightings);
        }
        else if(sum1==sum2){ //tjis can only be true when we subtracted one diamond from our available diamonds because their number was a prime one 
            return num_of_weightings; //it means that the diamond that we subtracted was the fake one, so the algorithm completes
        }
        
        return num_of_weightings;
    }
    
}
