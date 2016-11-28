import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// indxxd test
public class Solution {
    // define Interval class
    public static class Interval {
        int start;
        int end;
        Interval(int s, int e) {start = s; end = e;}
    }
    
    // define a comparator
    private class IntervalComparator implements Comparator <Interval> {
        @Override
        public int compare (Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    }
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        
        // sort intervals
        Collections.sort(intervals, new IntervalComparator());
        
        // initialize cur 
        Interval cur = null;
        
        // maintain current interval range
        for(Interval inputItv: intervals) {
            if(cur == null || cur.end < inputItv.start) {
                cur = inputItv;
                res.add(cur);
            } 
            else {
                cur.end = Math.max(inputItv.end, cur.end);
            }
        }
        return res;
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        // create object mi
        Solution mi = new Solution();
        
        // implement standard scan function to get input intervals
        Scanner sc = new Scanner(System.in);
       
        // scan integer N as the total number of input intervals 
        int N = sc.nextInt();
        
        // scan Enter to proceed to next step - scan N input intervals
        String enter = sc.nextLine();
        
        // define an object to store the input intervals
        List<Interval> intervals = new ArrayList<Interval>();
        for (int i = 0; i < N; i++) {
            String[] curr = sc.nextLine().split(" ");
            intervals.add(new Interval(Integer.parseInt(curr[0]), 
                                       Integer.parseInt(curr[1])));
        }
        
        // close input stream
        sc.close();
        mi.merge(intervals);
        List<Interval> res = mi.merge(intervals);
        
        // initialize sum of the interval union as 0                  
        int sum = 0;
        
        // calculate the sum of integer covered by the union of intervals                  
        for(Interval gap: res) {
            sum += gap.end - gap.start + 1;
        }
       
        // print out the total num of integers
        System.out.println(sum);                  
    }
}