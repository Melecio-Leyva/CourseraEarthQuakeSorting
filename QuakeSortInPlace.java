
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int lrg = from;
        for(int i = from+1;i<quakes.size();i++){
            if(quakes.get(lrg).getDepth()<quakes.get(i).getDepth()){
                lrg = i;
            }
        }
        return lrg;
    }
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i=0; i<50; i++) {
            int lrg = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qLrg = in.get(lrg);
            in.set(i,qLrg);
            in.set(lrg,qi);
        }    
    }
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }        
    }
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData,int numSorted){
        int min = 0;        
        
        for(int i =1;i<numSorted;i++){
                QuakeEntry qI = quakeData.get(i);
                QuakeEntry qMin = quakeData.get(min);
                if(!(qMin.getMagnitude()<qI.getMagnitude())){
                quakeData.set(i,qMin);
                quakeData.set(min,qI);             
               }
               min=i;
            }
        
          
    }
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        
        for(int i =0;i<in.size()-1;i++){
            for(QuakeEntry qe :in){
                System.out.println(qe);
            }
            onePassBubbleSort(in,in.size());
            System.out.println("Printing quakes after pass "  + i);
        }
    }
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        boolean temp=true;
        int min = 0;
        for(int i = 1;i<quakes.size();i++){
            if(quakes.get(min).getMagnitude()>quakes.get(i).getMagnitude()){
                temp = false;
            }
            
            min = i;
        }
        return temp;
    }
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for(int i =0;i<in.size()-1;i++){
            
            for(QuakeEntry qe :in){
                System.out.println(qe);
            }
            if(checkInSortedOrder(in)){
               break; 
            }
            else{
                onePassBubbleSort(in,in.size());
                System.out.println("Printing quakes after pass "  + i);
            }
            
        }
    
    }
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
            for(int i =0; i < in.size();i++){              
                if(!checkInSortedOrder(in)){
                    int minIdx = getSmallestMagnitude(in,i);
                    QuakeEntry qi = in.get(i);
                    QuakeEntry qmin = in.get(minIdx);
                    in.set(i,qmin);
                    in.set(minIdx,qi);
                }
                else{
                    System.out.println("Number of passes " + i);
                    break;
                    
                }
           }
    }
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println(" ");
        System.out.println("The list after being sorted through bubble sort algo");
        // for (int i =0;i<50;i++) {
            // QuakeEntry qe =list.get(i);
            // System.out.println(qe + " " + i);
        // }
        // for(QuakeEntry qe : list) {
            // System.out.println(qe);
        // }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }        
    }
}
