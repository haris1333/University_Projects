package algorithmproject2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A.E.M. : 2513
 * Name : Prodromos Polichroniadis
 * e-mail: prodpoly@csd.auth.gr
 * 
 * The code for this algorithm is from: https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CoinChangingMinimumCoin.java along with some adjustments of my own.
 * 
 * This class is used to implement the first function of our project. The algorithm that I found is most suitable for this function is the Coin Changing Algorithm.
 * This algorithm is used in order to get the combination with the smallest amount of VMs possible for each client's needs without using brute force. 
 */

public class MinimumVMSorter {

    /**
     * Top down dynamic programing. Using map to store intermediate results.
     * Returns Integer.MAX_VALUE if total cannot be formed with given VMs
     * @param n the number of the client currently processed
     * @param total the total amount of VMs that the client wants to buy
     * @param vms array with the available VMs' values 
     * @param map map used in order to implement the algorithm's logic 
     */
    public int minimumVMTopDown(int n,int total, int[] vms, Map<Integer, Integer> map) {

        //if total is 0 then there is nothing to do. return 0.
        if ( total == 0 ) {
            return 0;
        }

        //if map contains the result means we calculated it before. Lets return that value.
        if ( map.containsKey(total) ) {
            return map.get(total);
        }

        //iterate through all vms and see which one gives best result.
        int min = Integer.MAX_VALUE;
        for ( int i=0; i < vms.length; i++ ) {
            //if value of VM is greater than total we are looking for just continue.
            if( vms[i] > total ) {
                continue;
            }
            //recurse with total - vms[i] as new total
            int val = minimumVMTopDown(n,total - vms[i], vms, map);

            //if val we get from picking vms[i] as first vm for current total is less
            // than value found so far make it minimum.
            if( val < min ) {
                min = val;
            }
        }

        //if min is MAX_VAL dont change it. Just result it as is. Otherwise add 1 to it.
        min =  (min == Integer.MAX_VALUE ? min : min + 1);

        //memoize the minimum for current total.
        map.put(total, min);
        return min;
    }

    /**
     * Bottom up way of solving this problem.
     * Keep input sorted. Otherwise temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which
     * can be very low negative number
     * Returns Integer.MAX_VALUE - 1 if solution is not possible.
     * 
     * @param n the number of the client currently processed
     * @param total the total amount of VMs that the client wants to buy
     * @param vms array with the available VMs' values 
     */
    public int minimumVMBottomUp(int n,int total, int[] vms){
        int T[] = new int[total + 1];
        int R[] = new int[total + 1];
        T[0] = 0;
        for(int i=1; i <= total; i++){
            T[i] = Integer.MAX_VALUE-1;
            R[i] = -1;
        }
        for(int j=0; j < vms.length; j++){
            for(int i=1; i <= total; i++){
                if(i >= vms[j]){
                    if (T[i - vms[j]] + 1 < T[i]) {
                        T[i] = 1 + T[i - vms[j]];
                        R[i] = j;
                    }
                }
            }
        }
        printVMCombination(n,R, vms);
        return T[total];
    }
    
    /**
     * Method that prints the best combination with the smallest amount of VMs for the client processed.
     * @param n the number of the client currently processed
     * @param vms array with the available VMs' values ms 
     * @param R array used in order to determine which VM's are needed to fulfill the client's needs. 
     */
    private void printVMCombination(int n,int R[], int[] vms) {
        if (R[R.length - 1] == -1) {
            System.out.print("No solution is possible");
            return;
        }
        int numofVM[]= {0, 0, 0, 0}; //keeps track of how many VMs of each VM category are needed (we have four categories (1,2,7 or 11-core))
        
        int start = R.length - 1;
        
        
        while ( start != 0 ) { //while-loop that fills the array mentioned above
            int j = R[start];
            if(vms[j]==1){
                numofVM[0] = numofVM[0]+1;
            }else if(vms[j]==2){
                numofVM[1] = numofVM[1]+1;
            }else if(vms[j]==7){
                numofVM[2] = numofVM[2]+1;
            }else if(vms[j]==11){
                numofVM[3] = numofVM[3]+1;
            }
            start = start - vms[j];
        }
        
        
            System.out.println("Client "+ n+": "+numofVM[0]+" 1-core, " +numofVM[1]+" 2-core, " +numofVM[2]+" 7-core and "+ numofVM[3]+" 11-core VMs.");
            
    }
    
    /**
     * Method that initiates the Coin Changing algorithm logic.
     * @throws FileNotFoundException 
     */
    public void start () throws FileNotFoundException {
        
        int[] vms = {1, 2, 7, 11}; //array that keeps the available VM values
        File file = new File("VirtualMachines.txt"); //reading of the file initiates in order to get each client's VM needs.
        Scanner sc = new Scanner(file);
        int counter=0; //counts the number of the clients given by the file
        int i=0; //holds the number of the VMs wanted by each client
        float j=0; //holds the price offer made by each client for the VMs that he wants
        int first=0; //keeps the first integer written in the file which shows the total amount of VMs available even though it is not used in this function
        
        ArrayList<VMClient> clients = new ArrayList<>();//ArrayList consisted of all the clients that we read from the file
        
        while(sc.hasNextInt()|| sc.hasNextFloat()){//The file is consisted of integer(number of VMs) and float(price offers) values and we want to read both of them in one reading process
            if(counter==0){//if this is true then it means that we are on the first line of the file where maximum number of VMs available is written
               first=sc.nextInt();
            }
            
            i=sc.nextInt();//reads the number of VMs wanted by a client
            j=sc.nextFloat(); //reads the price offer of the client
            
            VMClient c = new VMClient(i,j);//creates a new client object to be put in the ArrayList mentioned above
            clients.add(c);
            counter++;
            
        }//I want to note here that the scanner couldn't read the floats from the file when they were in the form of "x.y" but only when they were in the form of "x,y"
         //so I had to change the form of all the floats in the file using "." with a form using "," in order for this reading process to be completed without errors.
        
        
        
        int num_of_clients=1; //the number of the client processed
        for(VMClient client: clients){ //for-loop that processes all the clients from the ArrayList created above in order to print the best solution for each one of them
            MinimumVMSorter cc = new MinimumVMSorter();
            Map<Integer, Integer> map = new HashMap<>();
            int topDownValue = cc.minimumVMTopDown(num_of_clients ,client.getTotal(), vms, map);
            int bottomUpValue = cc.minimumVMBottomUp(num_of_clients ,client.getTotal(), vms);
            num_of_clients++; //update to the client's number
        }
         

    }
    
}
