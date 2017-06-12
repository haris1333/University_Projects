package algoritmproject1;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Prodromos Polichroniadis
 * AEM : 2513
 * e-mail: prodpoly@csd.auth.gr
 * 
 * The algorithm used to implement the first operation of the project is QuickHull.
 * The code I used to implement QuickHull is from http://www.sanfoundry.com/java-program-implement-quick-hull-algorithm-find-convex-hull/ 
 * along with some adjustments of my own to cover the operation's functionality.
 * I used QuickHull to solve the problem because our agent is allowed to move close to the outer mines-points, something that QuickHull takes advantage of.
 * Also, QuickHull's complexity is O(n*logn) as asked.
 * 
 */

public class QuickHull

{

    /**
     * This function implements the logic of the QuickHull algorithm.
     * More details as inline comments.
     * @param points An ArrayList consisted of all the points - mines we have in our project. 
     * @return The convexHull, which is the perimeter of the polygon formed from the outter points - mines.
     */
    public ArrayList<Point> quickHull(ArrayList<Point> points)

    {

        ArrayList<Point> convexHull = new ArrayList<Point>(); //Creates the first empty convexHull where the points of the perimeter will be stored.

        if (points.size() < 3) 

            return (ArrayList) points.clone(); //If only 3 points in our project then the shortest path are these 3 points themselves so the function returns a clone of the parameter ArrayList

 

        int minPoint = -1, maxPoint = -1; //minPoint - the starting point of the convexHull
                                          //maxPoint - the final point of the convexHull
        int minX = Integer.MAX_VALUE;

        int maxX = Integer.MIN_VALUE;

        for (int i = 0; i < points.size(); i++) //for-loop used to identify the starting and the final point of the convexHull from the ArrayList containing all the available points

        {

            if (points.get(i).x < minX)

            {

                minX = points.get(i).x;

                minPoint = i;

            }

            if (points.get(i).x > maxX)

            {

                maxX = points.get(i).x;

                maxPoint = i;

            }

        }

        Point A = points.get(minPoint); //The starting point passed in a ,seperate from list, point to be added in the convexHull

        Point B = points.get(maxPoint);//The final point passed in a ,seperate from list, point to be added in the convexHull

        convexHull.add(A);

        convexHull.add(B);
        
        //points.remove(A);

        //points.remove(B);


        ArrayList<Point> leftSet = new ArrayList<Point>(); //The set of points that are left of the Point A - Point B line

        ArrayList<Point> rightSet = new ArrayList<Point>(); //The set of points that are right of the Point A - Point B line

        //reminder that the points A and B are the starting and the final point respectively

        for (int i = 0; i < points.size(); i++) //for-loop that determines if the points in the parameter Arraylist are left or right from the A - B line
                                                //those points are added to the one of the two sets (leftSet, rightSet) according to their position
        {

            Point p = points.get(i);

            if (pointLocation(A, B, p) == -1)

                leftSet.add(p);

            else if (pointLocation(A, B, p) == 1)

                rightSet.add(p);

        }

        hullSet(A, B, rightSet, convexHull); 

        hullSet(B, A, leftSet, convexHull);

 

        return convexHull;

    }

    /**
     * Calculates the distance between of a point from the A - B line
     * @param A  First point
     * @param B  Second point
     * @param C  Third point
     * @return The distance of the point C from the A - B line
     */

    public int distance(Point A, Point B, Point C)

    {

        int ABx = B.x - A.x;

        int ABy = B.y - A.y;

        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x); //The mathematical equation to calculate C's distance from the A - B line.

        if (num < 0) //The result might come in negative so we need to change it to positive

            num = -num; 

        return num;

    }

    /**
     * Determines the exact points - mines the convexHull will be consisted of from both sets (leftSet, rightSet) through repetition and adds them to the convexHull.
     * This method ,along the quickhull method, implements and completes the QuickHull algorithm and allows the convexHull to be formed.
     * The quickhull method calls this one once with the leftSet as the set parameter, and once with the rightSet as the set parameter.
     * @param A The starting point of the convexHull
     * @param B The final point of the convexHull
     * @param set The left or the right set of points, depends on the calling method
     * @param hull The convexHull 
     */

    public void hullSet(Point A, Point B, ArrayList<Point> set,

            ArrayList<Point> hull)

    {

        int insertPosition = hull.indexOf(B);

        if (set.size() == 0) //if there are no points to the right or the left set, there is no need to go any further in the method

            return;

        if (set.size() == 1)

        {

            Point p = set.get(0);

            set.remove(p);

            hull.add(insertPosition, p);

            return;

        }

        int dist = Integer.MIN_VALUE;

        int furthestPoint = -1;

        for (int i = 0; i < set.size(); i++) //for-loop that identifies the furthest point of the set from the A - B line 

        {

            Point p = set.get(i);

            int distance = distance(A, B, p);

            if (distance > dist)

            {

                dist = distance;

                furthestPoint = i;

            }

        }

        Point P = set.get(furthestPoint);//The furthest point passed in a ,seperate from list, point to be added in the convexHull

        set.remove(furthestPoint);

        hull.add(insertPosition, P);//Addition of the furthest point to the convexHull

 

        // Determine who's to the left of AP

        ArrayList<Point> leftSetAP = new ArrayList<Point>();

        for (int i = 0; i < set.size(); i++)

        {

            Point M = set.get(i);

            if (pointLocation(A, P, M) == 1)

            {

                leftSetAP.add(M);

            }

        }

 

        // Determine who's to the left of PB

        ArrayList<Point> leftSetPB = new ArrayList<Point>();

        for (int i = 0; i < set.size(); i++)

        {

            Point M = set.get(i);

            if (pointLocation(P, B, M) == 1)

            {

                leftSetPB.add(M);

            }

        }

        hullSet(A, P, leftSetAP, hull);
                                       //Calls of repetition in order to identify the rest of the points needed to complete the convexHull
        hullSet(P, B, leftSetPB, hull);

 

    }

/**
 * Calculates the determinant of three points.
 * 
 * @param A First point
 * @param B Second point
 * @param P Third point
 * @return Whether the the determinant of A, B and P is positive (>0), negative (<0) or equal to zero (==0)  
 */

    public int pointLocation(Point A, Point B, Point P)

    {

        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x); //The mathematical equation to calculate the determinant of the three points.

        if (cp1 > 0)

            return 1;

        else if (cp1 == 0)

            return 0;

        else

            return -1;

    }

 
    /**
     * This is the "main" method of the QuickHull class which initiates the operation.
     * It reads the points from the file and puts them in an ArrayList<Point>.
     * Eventually it calculates the shortest path for our agent.
     * @throws FileNotFoundException 
     */
     public void start() throws FileNotFoundException

    {

        File file = new File("xy.txt"); //Here is the reading of the file
        Scanner scanner = new Scanner(file);
        
        int counter=0;
        
        while(scanner.hasNextInt()){ //while-loop as to avoid the third line of the file that is about the second operation
            int i=scanner.nextInt();
            counter++; //also calculates the number of points - mines
        
        }
        
        int N = counter/2+1;

        
        ArrayList<Point> points = new ArrayList<Point>();
        counter=0;
        
        scanner.close();
        Scanner sc = new Scanner(file);
        for (int i = 0; i < N; i++) //for-loop to add each point - mine into an ArrayList of Points
        {
            int x = sc.nextInt();

              
            
            if(i!=2){
            
            int y = sc.nextInt(); 
            Point e = new Point(x, y);
            
            points.add(counter, e); 
            counter++;}
            
        }

        

        QuickHull qh = new QuickHull();

        ArrayList<Point> p = qh.quickHull(points);
     

        Point k=new Point(0,0);
        double sum1=0, sum2=0; //sum1 = the distance of the UpperHull path, sum2 = the distance of the LowerHull path
        int y1=0,y2=p.get(p.size()-1).y , ymax=0; //y1 = the y coordinate of the starting point, y2= the y coordinate of the final point, ymax= max(y1,y2)
        for(Point point:points){ //for-loop to find the starting point in the p ArrayList in case it isn't stored first, Reminder that the p ArrayList is the convexHull containing the points of the perimeter of the polygon formed by the outter points
            
            for (int i = 0; i < p.size(); i++){
                if(p.get(i)==point){

                    
                    y1=p.get(i).y;
                    k=p.get(i); //k is now the starting point
                    break;


                }
            
            }
               break; 
        }
        if(y1<y2){ //determines whether the starting or the final point has the bigger y
           ymax=y2; 
        }else{
            ymax=y1;
        }
        
        ArrayList<Point> UpperHull = new ArrayList<Point>();
        ArrayList<Point> LowerHull = new ArrayList<Point>();
        
        for (int i = 0; i < p.size(); i++){ //for-loop in order to create the UpperHull and the LowerHull from the convexHull(p)
            if(p.get(i).y>ymax ){
                UpperHull.add(p.get(i));
            }else if(p.get(i).y<ymax && p.get(i).y!=y1 && p.get(i).y!=y2){
                LowerHull.add(p.get(i));
            }else if(p.get(i).y==y1 || p.get(i).y==y2){
                
                UpperHull.add(p.get(i));
                LowerHull.add(p.get(i));
            }
        }
        
        for(int i=0;i<UpperHull.size()-1;i++){ //for-loop to calculate the distance of the UpperHull path
            sum1=sum1+UpperHull.get(i).distance(UpperHull.get(i+1));
        }
        
        int position=0;//position of the starting point in the LowerHull ArrayList as it can happen that it is not stored first
        for(int i=0;i<LowerHull.size()-1;i++){
            if(LowerHull.get(i)==k){
                position=i;
                break;
            }
                
        }
        
        
        for(int i=0;i<LowerHull.size()-1;i++){ //for-loop to calculate the distance of the LowerHull path
            if(i==0){
                sum2=k.distance(LowerHull.get(i));
            }else if(i==position){
                sum2=sum2+LowerHull.get(i-1).distance(LowerHull.get(i+1));
            }else{
                sum2=sum2+LowerHull.get(i).distance(LowerHull.get(i+1));
            }
                
        }
        
        
        if(sum1>sum2){ //determines which path will be used by comparing the two distances
            System.out.print("The shortest path is " + "("+ k.x+ ", "+k.y+")");
            for(Point l:LowerHull){
                if(l!=k)
                    System.out.print("--> (" + l.x + ", " + l.y + ")");
            }
            System.out.println("");
            System.out.println("The shortest distance is " + sum2);
            
        }else if(sum1<sum2){
            
           System.out.print("The shortest path is " + "("+ k.x+ ", "+k.y+")");
           for(Point u:UpperHull){
               
               if(u!=k)
                    System.out.print("--> (" + u.x + ", " + u.y + ")");
            }
            System.out.println("");
            System.out.println("The shortest distance is " + sum2); 
            }
            

        sc.close();

    }

}
