/**
 * Created by SophieGao on 10/17/16.
 */
import java.util.*;
public class Test01{
    public static String outAggree(String input){
        HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String,Integer>>();
        String[] lines = input.split("\n");
        int start=0;
        String rstart = null,rend = null;
        String res=new String();
        for(String line:lines){
            if(start==0){
                String[] parts = line.split(",");
                rstart=parts[0];
                rend=parts[1];
            }
            else if(start>1){
                String[] parts = line.split(",");
                String keystr=parts[0].substring(0, 7);
                // if not contains key
                if(!map.containsKey(keystr)){
                    map.put(keystr, new HashMap<String, Integer>());
                    map.get(keystr).put(parts[1],Integer.parseInt(parts[2]));
                }
                else{
                    // if not contains subkey
                    if(!map.get(keystr).containsKey(parts[1])){
                        map.get(keystr).put(parts[1],Integer.parseInt(parts[2]));
                    }
                    else{
                        //combine the values(clicks,1) (clicks,2)
                        map.get(keystr).put(parts[1],map.get(keystr).get(parts[1])+Integer.parseInt(parts[2]));
                    }
                }
            }
            start++;
        }
        //get the sorted keysets also get using treemap,in reverse order
        List<String> sortedKeys=new ArrayList<String>(map.keySet());
        Collections.sort(sortedKeys,Collections.reverseOrder());
        for(String key:sortedKeys){
            if(key.compareTo(rstart)>=0 && key.compareTo(rend)<=0){
                String vstr=new String();
                //get the subkey set
                List<String> subkeys=new ArrayList<String>(map.get(key).keySet());
                Collections.sort(subkeys);
                for(String subkey:subkeys){
                    vstr+=','+subkey+','+map.get(key).get(subkey);
                }
                res+=key+vstr+'\n';
            }
        }
        return res;
    }
    public static void main(String []args){
        String inputs= new String("2015-08,2016-04\n\n2015-08-15,clicks,635\n2016-03-24,app_installs,683\n"+
                "2015-04-05,favorites,763\n2016-01-22,favorites,788\n2015-12-26,clicks,525\n"+
                "2015-12-16,clicks,525\n2016-06-03,retweets,101\n2015-12-02,app_installs,982\n"+
                "2016-09-17,app_installs,770\n2015-11-07,impressions,245\n2016-10-16,app_installs,567\n");
        System.out.println(outAggree(inputs));
    }
}

