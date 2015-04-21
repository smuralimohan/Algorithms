package org.coursera.stanford;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by murali on 19-04-2015.
 */
public class FloydWarshall {

  //  static ReverseGraph reverseGraph;
    static long[][][] dpTable;
    static int vertexCount;

    public static void main(String[] args) {
        Long firstSP = findShortestOfShortestPaths("X:\\work\\AlgoPractice\\src\\main\\resources\\APSPg1.txt");
        Long secondSP = findShortestOfShortestPaths("X:\\work\\AlgoPractice\\src\\main\\resources\\APSPg2.txt");
        Long thirdSP = findShortestOfShortestPaths("X:\\work\\AlgoPractice\\src\\main\\resources\\APSPg3.txt");

        System.out.println("firstSP=" + firstSP);
        System.out.println("secondSP=" + secondSP);
        System.out.println("thirdSP=" + thirdSP);

    }

    public static void initialize(String filePath) {
        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();

            String line1 = linesIterator.next();
            vertexCount = Integer.parseInt(line1.split(" ")[0]);

            //System.out.printf("Integer.MAX_VALUE < " + (vertexCount * vertexCount * (vertexCount + 1)) + " = " + (Integer.MAX_VALUE < (vertexCount * vertexCount * (vertexCount + 1))));
            dpTable = new long[2][vertexCount][vertexCount];

            for (int i = 0;i < vertexCount; i++)
                for (int j =0; j < vertexCount; j++)
                    if (i != j) {
                        dpTable[0][i][j] =  Long.MAX_VALUE;
                    }

            while (linesIterator.hasNext()) {
                String line = linesIterator.next();
                int tail   = Integer.parseInt(line.split(" ")[0]),
                    head   = Integer.parseInt(line.split(" ")[1]),
                    weight = Integer.parseInt(line.split(" ")[2]);

                dpTable[0][tail-1][head-1] = weight;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long findShortestOfShortestPaths(String filePath) {
        Long ssp = Long.MAX_VALUE;
        boolean hasNegativeCycle = false;
        int previous = 0, current = 1;
        initialize(filePath);

        for (int k = 1; k <= vertexCount; k++) {
            for (int i = 0; i < vertexCount; i++){
                for (int j = 0; j < vertexCount; j++) {
                    long minInclK = Long.MAX_VALUE;
                    if (dpTable[previous][i][k - 1] == Long.MAX_VALUE || dpTable[previous][k - 1][j] == Long.MAX_VALUE) {
                        minInclK = Long.MAX_VALUE;
                    } else {
                        minInclK = dpTable[previous][i][k - 1] + dpTable[previous][k - 1][j];
                    }
                    dpTable[current][i][j] = Math.min(dpTable[previous][i][j], minInclK);

                    if (k == vertexCount) {
                        if (dpTable[current][i][j] < ssp) {
                            ssp = dpTable[current][i][j];
                        }
                    }
                }
            }

            // Search for a negative cycle.
            for (int i = 0; i < vertexCount; i++) {
                if (dpTable[current][i][i] < 0) {
                    return null;
                }
            }
            previous = 1 - previous;
            current  = 1 - current;
        }
        return ssp;
    }
}
