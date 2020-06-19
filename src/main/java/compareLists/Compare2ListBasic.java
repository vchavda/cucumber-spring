package compareLists;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Compare2ListBasic {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("D", "C", "B", "A");
        List<String> list2 = Arrays.asList("A", "B", "C", "D");
        assertNotEquals(list1, list2);

        Collections.sort(list1);
        Collections.sort(list2);
        assertEquals(list1, list2);

        // Eleiminate duplicates
        List<String> list1Dup = Arrays.asList("D", "C", "B", "A", "B", "B");
        List<String> list2Dup = Arrays.asList("A", "B", "C", "D", "D", "D", "D");
        List<String> List1NoDup = new ArrayList<>(new HashSet<>(list1Dup));
        List<String> List2NoDup = new ArrayList<>(new HashSet<>(list2Dup));
        Collections.sort(List1NoDup);
        Collections.sort(List2NoDup);
        assertEquals(List1NoDup, List2NoDup);

    }



}
