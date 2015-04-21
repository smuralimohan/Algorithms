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
//        Long secondSP = findShortestOfShortestPaths("X:\\work\\AlgoPractice\\src\\main\\resources\\APSPg2.txt");
//        Long thirdSP = findShortestOfShortestPaths("X:\\work\\AlgoPractice\\src\\main\\resources\\APSPg3.txt");

        System.out.println("firstSP=" + firstSP);
//        System.out.println("secondSP=" + secondSP);
//        System.out.println("thirdSP=" + thirdSP);

    }

    public static void initialize(String filePath) {
        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();

            String line1 = linesIterator.next();
            vertexCount = Integer.parseInt(line1.split(" ")[0]);
          //  reverseGraph = new ReverseGraph(vertexCount, Integer.parseInt(line1.split(" ")[1])) ;

            System.out.printf("Integer.MAX_VALUE < " + (vertexCount * vertexCount * (vertexCount + 1)) + " = " + (Integer.MAX_VALUE < (vertexCount * vertexCount * (vertexCount + 1))));
            int[] dpT = new int[vertexCount*vertexCount*(vertexCount+1)];//[vertexCount][vertexCount + 1];

            for (int i = 0;i < vertexCount; i++)
                for (int j =0; j < vertexCount; j++)
                    if (i != j) {
                        dpTable[i][j][0] =  Long.MAX_VALUE;
                    }

            while (linesIterator.hasNext()) {
                String line = linesIterator.next();
                int tail   = Integer.parseInt(line.split(" ")[0]),
                    head   = Integer.parseInt(line.split(" ")[1]),
                    weight = Integer.parseInt(line.split(" ")[2]);

            //    reverseGraph.addReverseEdge(tail, head, weight);
                dpTable[tail-1][head-1][0] = weight;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long findShortestOfShortestPaths(String filePath) {
        Long ssp = Long.MAX_VALUE;
        boolean hasNegativeCycle = false;

        initialize(filePath);

        for (int k = 1; k <= vertexCount; k++)
            for (int i =0; i < vertexCount; i++)
                for (int j = 0; j < vertexCount; j++) {
                    long minInclK = Long.MAX_VALUE;
                    if (dpTable[i][k-1][k-1] == Long.MAX_VALUE || dpTable[k-1][j][k-1] == Long.MAX_VALUE) {
                        minInclK = Long.MAX_VALUE;
                    } else {
                        minInclK = dpTable[i][k-1][k-1] + dpTable[k-1][j][k-1];
                    }
                    dpTable[i][j][k] = Math.min(dpTable[i][j][k-1], minInclK);

                    if (k == vertexCount) {
                        if (dpTable[i][j][k] < ssp) {
                            ssp = dpTable[i][j][k];
                        }
                    }
                }

        for (int i = 0; i < vertexCount; i++)
            if (dpTable[i][i][vertexCount] < 0) {
                return null;
            }
        return ssp;
    }
}
