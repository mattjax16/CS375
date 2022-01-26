import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
public class Assignment6 {
    /**
     * This program takes as input an ArrayList of Double[] objects. Each Double[] object is expected to be of the form 
     * {Double x, Double y} (eg. new Double[] {(double) 1, (double) 2}). The constructor calculates the minimum distance 
     * between the tuples interpreted as coordinates, using computeMinDist (written by the student) and bruteForceMinDist
     * (which I have supplied). If written correctly, computeMinDist should have a O(nlog n) runtime - the brute force
     * solution has O(n^2) runtime. After computing the minimum distance, the program prints the time taken to compute the
     * results with both methods. 
     */

    public Assignment6(ArrayList<Double[]> input){
        // Do not edit this
        Instant i1 = Instant.now();
        Double minDist = computeMinDist(input);
        Instant i2 = Instant.now();
        Double minDist2 = bruteForceMinDist(input);
        Instant i3 = Instant.now();
        if(minDist.equals(minDist2)){
            System.out.println("The minimum distance is: " + minDist);
            System.out.println("It took " + Duration.between(i1, i2).toString().substring(2) + " to compute with computeMinDist, and " 
                + Duration.between(i2, i3).toString().substring(2) + " to compute with brute force.");
        } else {
            
            System.out.println("Your dist calc is " + minDist + " correct should be " + minDist2);
            
            System.out.println("There appears to be an error in the code for computeMinDist");
        }

    }

    public double min(double dist1,double dist2){
        if(dist1 < dist2){
            return dist1;
        }
        else{
            return dist2;
        }
    }

    public double computeMinDist(ArrayList<Double[]> input){
        /**
         * Your job is to implement this using the Divide and Conquer algorithm from class
         * After completion, test it out with some big inputs and compare it to how long 
         * bruteForceMinDist takes.
         */
        
                    // System.out.println("Size of Input: " + input.size());
                    // System.out.println("Non-Sorted Input: ");
                    // printInput(input);
         //  first want to sort the points\
        input = mergeSortPoints(input, false);
                    // System.out.println("\nSorted Input: ");
                    // printInput(input);

        //recursive helper function doing the real computing 
        computeMinDistanceHelper(input);
        
        double min_distance = computeMinDistanceHelper(input);

        return min_distance;
    }

    public double computeMinDistanceHelper(ArrayList<Double[]> input){
        
        if(input.size() == 1){
            return Double.POSITIVE_INFINITY;
        }
        
        double minDist = dist(input.get(0), input.get(1));
        
        
        if(input.size() == 2){
            return minDist;
        }
        
        // taking in the input and splitting it.
        ArrayList<ArrayList<Double[]>> split_input =  splitInput(input);


        ArrayList<Double[]> first_array = split_input.get(0);
        ArrayList<Double[]> second_array = split_input.get(1);

        
        
        
        
        double first_min_dist = computeMinDistanceHelper(first_array);
        double second_min_dist = computeMinDistanceHelper(second_array);

        
        
        double d = min(first_min_dist, second_min_dist);
        
        //work for compairing points across the 2 halves

        // get middle x getween 2 halves
        double mid_point = first_array.get(first_array.size()-1)[0]/second_array.get(0)[0];

        
        ArrayList<Double[]> mid_array = new ArrayList<Double[]>();
        // get all points within d (x range) of the center.
        // would use while loop to work from middle forward on first array
        for(int i = 0; i < first_array.size(); i++){
            
            Double x_cord = first_array.get(i)[0];

            if(x_cord >= mid_point - d){
                mid_array.add(first_array.get(i));
            }
        }
        for(int i = 0; i < second_array.size(); i++){
            
            Double x_cord = second_array.get(i)[0];

            if(x_cord <= mid_point + d){
                mid_array.add(second_array.get(i));
            }
            else{
                break;
            }
        }

        // sort mid array by y
        mid_array = mergeSortPoints(mid_array, true);

        for(int i = 0; i < mid_array.size(); i++){
            Double[] cord_1 = mid_array.get(i);
            for(int j = i+1; j < i+15; j++){
                
                if(j+1 > mid_array.size()){
                    break;
                }
                
                Double[] cord_2 = mid_array.get(j);

                double mid_dist = dist(cord_1, cord_2);

                d = min(mid_dist,d);
            }
        }
        return d;
    }

    


    public ArrayList<ArrayList<Double[]>> splitInput(ArrayList<Double[]> input){

        int first_array_size = input.size()/2;

        
        ArrayList<ArrayList<Double[]>> return_array = new ArrayList<ArrayList<Double[]>>();
        ArrayList<Double[]> first_array = new ArrayList<Double[]>();
        ArrayList<Double[]> second_array = new ArrayList<Double[]>();

        for(int i = 0; i < input.size(); i++){

            if(i <= first_array_size-1){
                first_array.add(i,input.get(i));
            }
            else{
                second_array.add(input.get(i));
            }
        }

        return_array.add(0,first_array);
        return_array.add(1,second_array);

        return return_array;
    }



    public ArrayList<Double[]> mergeSortPoints(ArrayList<Double[]> input, 
    Boolean sort_by_y){

        if(sort_by_y){
            if(input.size() == 1){
                return input;
            }
    
            ArrayList<ArrayList<Double[]>> split_input =  splitInput(input);
    
    
            ArrayList<Double[]> first_array = split_input.get(0);
            ArrayList<Double[]> second_array = split_input.get(1);
    
            
            //do work on each one of the split inputs
            for(int i = 1; i < first_array.size(); i++){
               
                Double[] cord_one = first_array.get(i-1);
                Double[] cord_two = first_array.get(i);
    
                Double cord_one_y = cord_one[1];
                Double cord_two_y = cord_two[1];
    
                if(cord_two_y > cord_one_y){
                    Collections.swap(first_array, (i-1), i);
                }
            }
            first_array = mergeSortPoints(first_array,sort_by_y);
    
    
    
            for(int i = 1; i < second_array.size(); i++){
               
                Double[] cord_one = second_array.get(i-1);
                Double[] cord_two = second_array.get(i);
    
                Double cord_one_y = cord_one[1];
                Double cord_two_y = cord_two[1];
    
                if(cord_two_y > cord_one_y){
                    Collections.swap(second_array, (i-1), i);
                }
            }
            second_array = mergeSortPoints(second_array,sort_by_y);
    
    
    
            //Merging the two arrays 
            int first_array_amt = 0;
            int second_array_amt = 0;
    
            ArrayList<Double[]> sorted_array = new ArrayList<Double[]>();
            
            for(int i = 0; i < first_array.size()+ second_array.size(); i++){
    
    
                if(first_array_amt != first_array.size() && 
                second_array_amt != second_array.size()){
    
                    Double[] cord_one = first_array.get(first_array_amt);
                    Double[] cord_two = second_array.get(second_array_amt);
    
                    Double first_cord_y = cord_one[1];
                    Double second_cord_y = cord_two[1];
    
                    if(first_cord_y < second_cord_y){
                        sorted_array.add(first_array.get(first_array_amt));
                        
                        first_array_amt++;
                    }
                    else{
                        sorted_array.add(second_array.get(second_array_amt));
                        second_array_amt++;
                    }
                }
                else if(first_array_amt == first_array.size()){
                    sorted_array.add(second_array.get(second_array_amt));
                    second_array_amt++;
                }
                else{
                    sorted_array.add(first_array.get(first_array_amt));
                    first_array_amt++;
                }
            }   
            return sorted_array;
        }
        else{
            if(input.size() == 1){
                return input;
            }
    
            ArrayList<ArrayList<Double[]>> split_input =  splitInput(input);
    
    
            ArrayList<Double[]> first_array = split_input.get(0);
            ArrayList<Double[]> second_array = split_input.get(1);
    
            
            //do work on each one of the split inputs
            for(int i = 1; i < first_array.size(); i++){
               
                Double[] cord_one = first_array.get(i-1);
                Double[] cord_two = first_array.get(i);
    
                Double cord_one_x = cord_one[0];
                Double cord_two_x = cord_two[0];
    
                if(cord_two_x > cord_one_x){
                    Collections.swap(first_array, (i-1), i);
                }
            }
            first_array = mergeSortPoints(first_array, sort_by_y);
    
    
    
            for(int i = 1; i < second_array.size(); i++){
               
                Double[] cord_one = second_array.get(i-1);
                Double[] cord_two = second_array.get(i);
    
                Double cord_one_x = cord_one[1];
                Double cord_two_x = cord_two[1];
    
                if(cord_two_x > cord_one_x){
                    Collections.swap(second_array, (i-1), i);
                }
            }
            second_array = mergeSortPoints(second_array, sort_by_y);
    
    
    
            //Merging the two arrays 
            int first_array_amt = 0;
            int second_array_amt = 0;
    
            ArrayList<Double[]> sorted_array = new ArrayList<Double[]>();
            
            for(int i = 0; i < first_array.size()+ second_array.size(); i++){
    
    
                if(first_array_amt != first_array.size() && 
                second_array_amt != second_array.size()){
    
                    Double[] cord_one = first_array.get(first_array_amt);
                    Double[] cord_two = second_array.get(second_array_amt);
    
                    Double first_cord_x = cord_one[1];
                    Double second_cord_x = cord_two[1];
    
                    if(first_cord_x < second_cord_x){
                        sorted_array.add(first_array.get(first_array_amt));
                        
                        first_array_amt++;
                    }
                    else{
                        sorted_array.add(second_array.get(second_array_amt));
                        second_array_amt++;
                    }
                }
                else if(first_array_amt == first_array.size()){
                    sorted_array.add(second_array.get(second_array_amt));
                    second_array_amt++;
                }
                else{
                    sorted_array.add(first_array.get(first_array_amt));
                    first_array_amt++;
                }
            }   
            return sorted_array;
        }
    }
    
    
    public Double bruteForceMinDist(ArrayList<Double[]> input){
        // Do not edit this
        Double minDist = dist(input.get(0), input.get(1));
        for(int i = 0; i < input.size(); i++){
            for(int j = i+1; j < input.size(); j++){
                Double curDist = dist(input.get(i), input.get(j));
                if(curDist  < minDist){
                    minDist = curDist; 
                }
            }
        } return minDist;
    }

    public Double dist(Double[] p1, Double[] p2){
        // Do not edit this
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1],2));
    }



    public void printInput(ArrayList<Double[]> input){
        for(int i = 0; i<=input.size()-1; i++ ) {
            System.out.print("("+ input.get(i)[0] + " " + input.get(i)[1] + ")");
        }
    }


    public static void main(String[] args){
        // Feel free to use this to test out whatever you might like
        ArrayList<Double[]> input = new ArrayList<Double[]>();
        for(int i = 0; i<6000; i++){
            input.add(new Double[] {(double) i, (double) i+1});
        } new Assignment6(input);
    }
}