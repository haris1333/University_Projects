/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmproject2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A.E.M. : 2513
 Name : Prodromos Polichroniadis
 e-mail: prodpoly@csd.auth.gr
 
 The code for the following algorithm is from: http://introcs.cs.princeton.edu/java/96optimization/KnapsackSorter.java.html along with some adjustments of my own to better serve the purpose of our project.
 * 
 * This class is used to implement the second function of our project. The most suitable algorithm for this function is the one that solves the knapsack problem.
 * This algorithm finds the most suitable clients to be accepted according to the money that they will spend without using brute force.
 */
public class KnapsackSorter {
    
    /**
     * Method that initiates the knapsack algorithm logic.
     * @throws FileNotFoundException 
     */
    public void start() throws FileNotFoundException {
        int N =0;   // number of clients
        int W =0;   // maximum number of VMs available

        File file = new File("VirtualMachines.txt"); //Reading the file given with the VMs available along with each client's offer.
        Scanner sc = new Scanner(file);
        int counter=0; //counts the number of clients through the file
        int i=0; //holds the number of the VMs wanted by each client
        float j=0; //holds the price offer made by each client for the VMs that he wants
        
        ArrayList<VMClient> clients = new ArrayList<>(); //ArrayList consisted of all the clients that we read from the file
        
        while(sc.hasNextInt()|| sc.hasNextFloat()){ //The file is consisted of integer(number of VMs) and float(price offers) values and we want to read both of them in one reading process
            if(counter==0){ //if this is true then it means that we are on the first line of the file where maximum number of VMs available is written
               W=sc.nextInt();
            }
            
            i=sc.nextInt(); //reads the number of VMs wanted by a client
            j=sc.nextFloat(); //reads the price offer of the client
            
            VMClient c = new VMClient(i,j); //creates a new client object to be put in the ArrayList mentioned above
            clients.add(c);
            counter++;
            
        } //I want to note here that the scanner couldn't read the floats from the file when they were in the form of "x.y" but only when they were in the form of "x,y"
         //so I had to change the form of all the floats in the file using "." with a form using "," in order for this reading process to be completed without errors.
        
        
        N=counter-1; //number of clients
        
        
        float[] profit = new float[N+1]; //array that holds the total amount that each client is willing to pay to buy the VMs that he needs
        int[] weight = new int[N+1]; //array that holds the total amount of VMs that each client wants to buy

        
        
        int k=0;
        for (VMClient client: clients) { //for-loop to fill the two arrays mentioned above
            float x = client.getPrice();
            int y= client.getTotal();
            profit[k]=x*y; //calculates the amount to be paid by each client and puts it in the array
            weight[k] = y;
            k++;
        }
        
        
        // opt[n][w] = max profit of packing items 1..n with weight limit w
        // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];

        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= W; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w) option2 = (int) (profit[n] + opt[n-1][w-weight[n]]);

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }

        // determine which items to take
        boolean[] take = new boolean[N+1];
        for (int n = N, w = W; n > 0; n--) {
            if (sol[n][w]) { take[n] = true;  w = w - weight[n]; }
            else           { take[n] = false;                    }
        }

        // print results
        float sum=0; //the total amount to be paid by the clients accepted combined
        boolean firstClient=false; //miscellaneous boolean to help me in the printing process
        
        for (int n = 0; n <= N; n++) { //for-loop to print which clients are accepted and to calculate the sum
            if(take[n]==true && firstClient== false){
                System.out.print("Clients accepted: "+n+"  ");
                firstClient=true;
                sum=profit[n];
            }else if(take[n] == true && firstClient==true ){
                System.out.print(n+"  ");
                sum=sum+profit[n];
            }
        }
        System.out.println("");
        System.out.println("Total amount: " + sum);
        
    }
}
