package java8;

import java.util.HashMap;
import java.util.Map;

public class SumofHashMapValues {

    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("a",1);
        myMap.put("b",2);
        myMap.put("c",3);
        myMap.put("d",4);
        myMap.put("e",5);

        int sum = myMap.values().stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
