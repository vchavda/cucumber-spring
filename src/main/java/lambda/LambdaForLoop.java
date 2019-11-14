package lambda;

import java.util.stream.IntStream;

public class LambdaForLoop {

    public static void main(String[] args) {

        //**** FOR LOOP ***********
        //*** Imperative way ******
        int total = 0;
        for (int i = 0; i <= 100; i++) {
            total += i;
        }
        System.out.println("Total using imparative approach: " + total);


        //*** Declaritive way ******
        System.out.println("Total using declaritive approach: " + IntStream.rangeClosed(0,100).sum());

    }
}
