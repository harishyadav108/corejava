package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectListElements {

    public static void main(String[] args) {
        List<String> l= Arrays.asList("harish,krishna");

        l.replaceAll(String::toUpperCase);
        List<String> l2 = l.stream().map(s->s.toUpperCase()).collect(Collectors.toList());

        System.out.println(l);
        System.out.println(l2);
    }
}
