package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaStream1 {

    //*** This test demonstrates how we can make a list unique using Lambda *****
    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1,2,2,2,3,4,4,4,5);

        List<Integer> unIntegerList = integerList.stream().distinct().collect(Collectors.toList());
        System.out.println(unIntegerList);

    }
}
