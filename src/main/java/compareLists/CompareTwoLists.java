package compareLists;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTwoLists {
//https://www.baeldung.com/java-remove-duplicates-from-list removing duplicates from a list.


    public static void main(String[] args) {

        List<String> listA = Arrays.asList("Hello htest", "World!", "How", "how", "Are", "You");
        List<String> listB = Arrays.asList("You", "Are", "How", "how", "World!", "Hello htest");
        List<String> listC = Arrays.asList("D", "C", "B", "A");
        List<String> listD = Arrays.asList("A", "B", "C", "D");

        //check if list is sorted
        boolean sortedA = Ordering.natural().isOrdered(listA);
        boolean sortedB = Ordering.natural().isOrdered(listB);
        // tjis reverses the ordering of a list
        Collections.sort(listD, Ordering.natural().reverse());
        Comparator<Comparable> reversedOrder = Ordering.natural().reversed();

        boolean reversedC = (Ordering.from(reversedOrder).isOrdered(listC));
        boolean reversedD = (Ordering.from(reversedOrder).isOrdered(listD));


        if (sortedA) {System.out.println(" A Sorted");}
        if (sortedB) {System.out.println(" B Sorted");}
        if (reversedC){System.out.println(" C is Sorted reversed");}
        if (reversedD){System.out.println(" D is Sorted reversed");}

        Collections.sort(listA);
        Collections.sort(listB);

        //check if list is sorted
        boolean sortedA1 = Ordering.natural().isOrdered(listA);
        boolean sortedB1 = Ordering.natural().isOrdered(listB);
        if (sortedA1) {System.out.println(" A Sorted");}
        if (sortedB1) {System.out.println(" B Sorted");}

        if (listA.equals(listB))
        {
            System.out.println("Match found");
        }
        else
            System.out.println("No Match");

    }
}
