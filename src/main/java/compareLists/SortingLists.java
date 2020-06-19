package compareLists;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class SortingLists {

    public static void main(String[] args) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // sorting a list of integers
        List<Integer>  MyIntegers = Arrays.asList(3, 2, 1);
        MyIntegers.sort(Ordering.natural());
        assertEquals(Arrays.asList(1,2,3), MyIntegers);

        // if there are null values then use this
        List<Integer>  MyIntegerswithNulls = Arrays.asList(3, 2, null, 1);
        MyIntegerswithNulls.sort(Ordering.natural().nullsFirst());
        assertEquals(Arrays.asList(null, 1,2,3), MyIntegerswithNulls);

        // reversing the ordering
        List<Integer>  MyIntegersReverse = Arrays.asList(2, 3, 1);
        MyIntegersReverse.sort(Ordering.natural().reversed());
        assertEquals(Arrays.asList(3,2,1), MyIntegersReverse);

        // sorting a list of integers and removing duplicates
        List<Integer>  MyIntegersDuplicates = Arrays.asList(3, 2, 1, 3, 3, 3);
        List<Integer> MyIntegersWithoutDuplicates = new ArrayList<>(new HashSet<>(MyIntegersDuplicates));
        MyIntegersWithoutDuplicates.sort(Ordering.natural());
        assertEquals(Arrays.asList(1,2,3), MyIntegersWithoutDuplicates);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        // sorting a list of Strings
        List<String>  MyStrings = Arrays.asList("bob", "Apple", "zebra");
        // check if list is ordered
        if (Ordering.natural().isOrdered(MyStrings))
        {
            System.out.println("List is Ordered");
        }
        else
        {
            System.out.println("list is not ordered");
        }

        MyStrings.sort(Ordering.natural());
        assertEquals(Arrays.asList("Apple","bob","zebra"), MyStrings);

        // reversing a list of Strings
        List<String>  MyStringsRev = Arrays.asList("bob", "Apple", "zebra");

        // check if list is ordered
        if (Ordering.natural().reverse().isOrdered(MyStringsRev))
        {
            System.out.println("List is reversed Ordered");
        }
        else
        {
            System.out.println("list is not revered ordered");
        }


        MyStringsRev.sort(Ordering.natural().reversed());

        if (Ordering.natural().reverse().isOrdered(MyStringsRev))
        {
            System.out.println("List is reversed Ordered");
        }
        else
        {
            System.out.println("list is not revered ordered");
        }



        assertEquals(Arrays.asList("zebra", "bob", "Apple"), MyStringsRev);

        // sorting a list of Strings eleminating duplicates
        List<String>  MyStringsDup = Arrays.asList("bob", "Apple", "zebra", "bob", "bob");
        List<String>  MyStringsNoDup = new ArrayList<>(new HashSet<>(MyStringsDup));
        MyStringsNoDup.sort(Ordering.natural());
        assertEquals(Arrays.asList("Apple","bob","zebra"), MyStringsNoDup);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // dates test

        // sorting a list of Strings
        List<String>  MyStringsDates = Arrays.asList("2019-04-22T10:40:50.102Z", "2020-04-22T10:40:50.102Z", "2017-04-22T10:40:50.102Z");
        // check if list is ordered
        if (Ordering.natural().isOrdered(MyStringsDates))
        {
            System.out.println("List is Ordered");
        }
        else
        {
            System.out.println("list is not ordered");
        }

        MyStringsDates.sort(Ordering.natural());
        assertEquals(Arrays.asList("2017-04-22T10:40:50.102Z","2019-04-22T10:40:50.102Z","2020-04-22T10:40:50.102Z"), MyStringsDates);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        //PLEASE NOTE that the test passes if only 1 item or no items which is not useful eg

        List<String>  MyStringsNoData = Arrays.asList("");
        assertTrue(Ordering.natural().isOrdered(MyStringsNoData));

        List<String>  MyStringsOnly1Item = Arrays.asList("Apple");
        assertTrue(Ordering.natural().isOrdered(MyStringsOnly1Item));

        // therfore we need to add some validation
        List<String>  MyStringsNoDataWithVal = Arrays.asList("");
        assertFalse(Ordering.natural().isOrdered(MyStringsNoData) & MyStringsNoDataWithVal.size() > 1);

        List<String>  MyStringsOnly1ItemWithVal = Arrays.asList("Apple");
        assertFalse(Ordering.natural().isOrdered(MyStringsOnly1Item) & MyStringsOnly1ItemWithVal.size() > 1);



    }

}
