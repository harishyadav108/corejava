import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

interface interf{
    public void add(int a, Integer j);

}


public class TestQuestion {


    public static void main(String[] args) {

        interf i = (int a,Integer b)->System.out.println(a+b);
        i.add(5,6);

        List<Integer> list = Arrays.asList(9, 2, 2, 7, 6, 6, 5, 7);
        list.stream().filter(ii->ii%2==0).collect(Collectors.toList()).forEach(System.out::println);
    }
}
