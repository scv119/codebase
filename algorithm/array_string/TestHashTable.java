import java.util.*;


public class TestHashTable{
    public static void main(String args[]){
        Map<Integer,String> map = new HashMap<Integer,String>();
        map.put(1,"test");
        map.put(2,"test1");
        for (Integer i:map.keySet()){
            System.out.println(i + "" + map.get(i));
        }
    }
}
