package lambda;

/*
    This program will take an array of strings and filter the array using streams to just filter the ones starting with "A"
*/



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class LambdaStreamFilter1 {
    public static void main(String[] args) {

        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        // now we are going to filter this result to only display the ones which start with "A"
        memberNames.stream().filter(a -> a.startsWith("A")).forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------");

        // now we are going to filter this result to only display the ones which contain "man"
        memberNames.stream().filter(a -> a.contains("man")).forEach(System.out::println);

        // using a map we can now convert each element to another object
        memberNames.stream().filter(a -> a.contains("man")).map(a -> a.toUpperCase()).forEach(System.out::println);
        //or
        memberNames.stream().filter(a -> a.contains("man")).map(String::toUpperCase).forEach(System.out::println);

        //here we are going to sort the list in alphabetical order
        memberNames.stream().sorted().forEach(System.out::println);

        //here we are going to sort the list in descending order
        memberNames.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}