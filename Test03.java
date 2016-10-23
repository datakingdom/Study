/**
 * Created by SophieGao on 10/22/16.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Test03 {
    public static class Node{
        int time;
        String year;
        String month;
        String action;
        String count;
        public Node(int t, String y, String m, String a, String c){
            time = t;
            year = y;
            month = m;
            action = a;
            count = c;
        }
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scan = new Scanner(System.in);
        String[] lineSplit = scan.nextLine().split(",");
        String[] startTime = lineSplit[0].split("-");
        String[] endTime = lineSplit[1].split("-");
        int startYear = Integer.parseInt(startTime[0].trim());
        int startMonth = Integer.parseInt(startTime[1].trim());
        int endYear = Integer.parseInt(endTime[0].trim());
        int endMonth = Integer.parseInt(endTime[1].trim());
        int begin = startYear * 12 + startMonth;
        int end = endYear * 12 + endMonth;

        String line = scan.nextLine();
        ArrayList<Node> data = new ArrayList<Node>();
        while(scan.hasNextLine()){
            line = scan.nextLine();
            if(line.length() == 0){ break;}
            String[] str = line.split(",");
            String[] date = str[0].split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int cur = year * 12 + month;
            if(begin <= cur && cur <= end){
                Node entry = new Node(cur, date[0], date[1], str[1], str[2]);
                data.add(entry);
            }
        }
        Collections.sort(data, new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return o2.time - o1.time;
            }}
        );

        Node pre = data.get(0);
        System.out.print(pre.year + "-" + pre.month + "," + pre.action + "," + pre.count);
        for(int i = 1; i < data.size(); i++){
            Node n = data.get(i);
            if(n.time != pre.time){
                System.out.print("\n" + n.year + "-" + n.month + "," + n.action + "," + n.count);
            }
            else{
                System.out.print("," + n.action + "," + n.count);
            }
            pre = n;
        }
        System.out.println();
    }
}

