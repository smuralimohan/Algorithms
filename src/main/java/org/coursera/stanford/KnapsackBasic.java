package org.coursera.stanford;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by murali on 18-04-2015.
 */
public class KnapsackBasic {

    static long[][] dpTable;
    static int capacity, itemCount = 0;
    static ArrayList<Integer> weights = new ArrayList<>();
    static ArrayList<Integer> values = new ArrayList<>();

    public static void main(String[] args) {
            initialize("X:\\work\\AlgoPractice\\src\\main\\resources\\knapsack1.txt");
            findOptimalSolution();
        System.out.println("Optimal value=" + dpTable[capacity][itemCount]);

    }

    public  static void findOptimalSolution() {

        for (int j = 0; j <= itemCount; j++)
            dpTable[0][j] = 0;
        for (int j = 0; j <= capacity; j++)
            dpTable[j][0] = 0;

        for (int w = 1; w <= capacity; w++) {
            for (int i = 1; i <= itemCount; i++) {
                long currValue = 0L;

                int wi = weights.get(i-1);
                if (w-wi >= 0) {
                    currValue = dpTable[w-wi][i-1] + values.get(i-1) + 0L;
                } else {
                    currValue = 0L;
                }

                long maxValue = Math.max(dpTable[w][i-1], currValue);
                dpTable[w][i] = maxValue;
            }
        }
    };

    public static void initialize(String filePath) {
        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();

            String line1 = linesIterator.next();
            capacity = Integer.parseInt(line1.split(" ")[0]);
            itemCount = Integer.parseInt(line1.split(" ")[1]);
            dpTable = new long[capacity + 1][itemCount + 1];

            while (linesIterator.hasNext()) {
                String line = linesIterator.next();
                values.add(Integer.parseInt(line.split(" ")[0]));
                weights.add(Integer.parseInt(line.split(" ")[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
