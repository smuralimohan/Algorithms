package org.coursera.stanford;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by murali on 18-04-2015.
 */
public class KnapsackRecursive {
    static Map<String, Long> cache = new HashMap<>();
    static int capacity, itemCount = 0;
    static ArrayList<Integer> weights = new ArrayList<>();
    static ArrayList<Integer> values = new ArrayList<>();

    public static void main(String[] args) {
        initialize("X:\\work\\AlgoPractice\\src\\main\\resources\\knapsack_big.txt");
        System.out.println("Optimal value=" + findOptimalSolution(itemCount, capacity));
    }

    public  static Long findOptimalSolution(int itemIndex, int knapsackSize) {

        if (itemIndex <= 0 || knapsackSize <= 0) {
            cache.put(knapsackSize + ":" + itemIndex, 0L);
            return 0L;
        }

        Long optimalVal = cache.get(knapsackSize + ":" + itemIndex);
        if (optimalVal != null)
            return optimalVal;

        int wi = weights.get(itemIndex - 1);

        Long excludingCurrentElem, includingCurrentElem = 0L;
        excludingCurrentElem = findOptimalSolution(itemIndex - 1, knapsackSize);

        if (knapsackSize - wi >= 0) {
            includingCurrentElem = findOptimalSolution(itemIndex - 1, knapsackSize - wi) + values.get(itemIndex - 1);
        }

        optimalVal = Math.max(excludingCurrentElem, includingCurrentElem);
        cache.put(knapsackSize + ":" + itemIndex, optimalVal);
        return optimalVal;
    };

    public static void initialize(String filePath) {
        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();

            String line1 = linesIterator.next();
            capacity = Integer.parseInt(line1.split(" ")[0]);
            itemCount = Integer.parseInt(line1.split(" ")[1]);

            while (linesIterator.hasNext()) {
                String line = linesIterator.next();
                values.add(Integer.parseInt(line.split(" ")[0]));
                weights.add(Integer.parseInt(line.split(" ")[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (int j = 0; j <= itemCount; j++)
//            cache.put("0:" + j, 0L);
//        for (int j = 0; j <= capacity; j++)
//            cache.put(j + ":0", 0L);
    }
}
