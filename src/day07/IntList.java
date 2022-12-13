package day07;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class IntList {
    
    public static void main(String[] args) {
        Integer max = 200;
        Integer range = 100;
        // Random rand = new SecureRandom();
        Random rand = new Random();
        List<Integer> randomList = new LinkedList<Integer>();
        
        // create list of 100 random numbers
        for (int i = 0; i < max; i++) {
            int randomInt = rand.nextInt(range);
            randomList.add(randomInt);
        }
        // view current randomlist 
        System.out.println(randomList);
        
        // convert list to stream and filter numbers divisible by 3, distinct values, sorted, top 5 (filter + map)
        List<Integer> resultList = randomList.stream()
            .filter((n) -> (n % 3 == 0))
            .distinct()
            .sorted()
            .limit(5) // limit size to first 5 value
            .toList();

        // view result list
        System.out.printf("The resultant list is: %s\n", resultList);

        // duplicate numbers in array or stream to single string (map)
        List<Integer> intList = resultList.stream() // converts string list to stream
            .map(v -> "%d%d".formatted(v, v)) // dublicates strings
            // .map(i -> Integer.parseInt(i))
            .map(Integer::parseInt) // converts string to integer  
            .toList();

        System.out.printf("The resultant list is: %s\n", intList);


        // Concatenate items in list (reduce)
        String reducedString = intList.stream().map((i) -> (Integer.toString(i)))
            .collect(Collectors.joining(""));


        // Accumulator: add all elements in  intList to total


        
    }
    // method to filter stream

    // method to 
}
