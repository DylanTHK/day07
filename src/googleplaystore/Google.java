package googleplaystore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Google {

    public static Map<String, ArrayList<Double>> ratingsMap = new HashMap<String, ArrayList<Double>>();

    // Find average score per genre of app
    public static void main(String[] args) {
        // read file
        try {
            BufferedReader br = new BufferedReader(new FileReader("googleplaystore.csv"));
            // read header 
            String line = br.readLine();
            String[] headerArray = line.trim().split(",");
            List<String> headerList = new ArrayList<String>(Arrays.asList(headerArray));
            
            // get index of Genre and Rating (unnecessary, can just use index 0,1,2)
            Integer ratingIndex = headerList.indexOf("Rating");
            Integer catIndex = headerList.indexOf("Category");
            // System.out.println("Index of Rating: " + ratingIndex); // REMOVE
            // System.out.println("Index of Genres: " + catIndex); // REMOVE
            // System.out.println(headerList); // REMOVE
            
            // add genre(key) and rating(value - array) to hashmap
            while ((line = br.readLine()) != null) {
                String[] rowArray = line.trim().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // List<String> rowList = new ArrayList<String>(Arrays.asList(rowArray));
                // System.out.println(rowList);

                String genreName = rowArray[catIndex];
                // System.out.println("Genre Name: " + genreName); // REMOVE

                Double ratingValue = Double.parseDouble(rowArray[ratingIndex]); // convert String to double
                // System.out.println("Rating Value: " + ratingValue); // REMOVE

                if (!rowArray[ratingIndex].toLowerCase().equals("nan")) {
                    // // check if hashmap contains key (genreName)
                    if (ratingsMap.containsKey(genreName)) {
                        ArrayList<Double> ratingsArray = ratingsMap.get(genreName); //extract valuels
                        ratingsArray.add(ratingValue); // dit values
                        ratingsMap.put(genreName, ratingsArray); // update array in hashmap value
                        
                    } else { // if no key, push key to genre array
                        ArrayList<Double> ratingsArray = new ArrayList<Double>();
                        ratingsArray.add(ratingValue);
                        ratingsMap.put(genreName, ratingsArray);
                    }
                }
            } // while loop

            // System.out.println(ratingsMap); // REMOVE

            // calculate average of array, push to ratings array (Use accumulator)
            for (Map.Entry<String, ArrayList<Double>> set :ratingsMap.entrySet()) {
                String category = set.getKey();
                ArrayList<Double> ratings = set.getValue();
                Integer count = 0;
                Double totalRating = 0d;

                
                for (Double rating : ratings) {
                    totalRating += rating;
                    count++;
                }

                Double averageRating = totalRating / count;
                
                System.out.printf("%s - %.1f\n", set.getKey(), averageRating);
            }

            // print out average rating of each genre

            br.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
