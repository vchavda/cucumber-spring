package lambda;

/*
This example of streaming will take an array of integer and then will stream that array
It will then use filter to take the first element and using a&2 will detemrine if the integer is even or odd
even will be 0 ie no remainder. Then it will map that integer (which is even) to do a calculation by multiplying it by 4.
The next thing it will do is it will limit the result to 2 and then finally add it to a list.
*/



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LambdaStream2 {
    public static void main(String[] args) {
        Integer[] arrayInt = new Integer[]{1,2,3,4,5,6,8,9,10};

        List<Integer> collect = Arrays.stream(arrayInt)
                .filter(a -> a % 2 == 0)
                .map(b -> b * 4)
                .limit(2)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

    }
}