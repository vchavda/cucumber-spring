/*

The program creates an array of integer and then does a for-next loop from 0 to 10
for each value of the for-loop we extract the item and then we add that item to our list we also print the calue of the item.
Tis program also demonstrates how we can use the foreach with single and multiple statements.

*/

package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LambdaStreamToArray {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        //IntStream.rangeClosed(0,10).forEach(value -> list.add(value));
        //IntStream.rangeClosed(0,10).forEach(System.out::println);

        IntStream.rangeClosed(0,10).forEach(item->{
            list.add(item);
            System.out.println(item);
        });

        list.forEach(System.out::println);

    }
}
