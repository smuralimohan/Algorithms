package org.coursera.stanford;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by murali on 12-04-2015.
 */
public class SingleLinkClusteringHammingDistance {

    private static List<Vertex> vertexList;;
    private static UnionFindBasic unionFindBasic;

    public static void main(String[] args) {

        initalize("X:\\work\\AlgoPractice\\src\\main\\resources\\clustering_big.txt");
        int k = findMaxSpacing();
        System.out.println("k = " + k);
    }

    public static int findMaxSpacing() {
        Collections.sort(vertexList, new CompareVertexBitStrings());

        for (int i = 0; i < vertexList.size() - 1; i++) {
            for (int j = i + 1; j < vertexList.size(); j++) {
                Vertex v1 = vertexList.get(i);
                Vertex v2 = vertexList.get(j);
                if (v2.populationCount - v1.populationCount > 2) {
                    break;
                }
                if (findHammingDistance(v1, v2) < 3) {
                    if (!unionFindBasic.find(v1, v2)) {
                        unionFindBasic.union(v1, v2);
                    } else {
                        //System.out.println(e.u + " and " + e.v + " in the same partition");
                    }
                }
            }
        }
        return unionFindBasic.getPartitionCount();
    }

    public static int findHammingDistance(Vertex v1, Vertex v2){
        int hammingDistance = 0;
        char[] c1 = v1.bitString.toCharArray();
        char[] c2 = v2.bitString.toCharArray();

        for (int i=0; i < 24; i++) {
            if (c1[i] != c2[i]) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    public static void initalize(String filePath) {

        vertexList = new ArrayList<>();
        unionFindBasic = new UnionFindBasic();

        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();
            //Skip the first line;
            linesIterator.next();

            int vertexCounter = 1;

            while (linesIterator.hasNext()) {
                String line = linesIterator.next();
                Vertex vertex = new Vertex(vertexCounter++, line.replaceAll(" ", ""));
                vertexList.add(vertex);
                unionFindBasic.addNewPartition(vertex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CompareVertexBitStrings implements Comparator<Vertex>
{
    public int compare(Vertex v1, Vertex v2)
    {
        return ((Integer) v1.populationCount).compareTo(v2.populationCount);
    }
}
