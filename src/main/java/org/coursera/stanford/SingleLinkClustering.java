package org.coursera.stanford;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by murali on 12-04-2015.
 */
public class SingleLinkClustering {

    private static int vertexCount;
    private static List<Vertex> vertexList;
    private static List<Edge> edgeList;
    private static UnionFindBasic unionFindBasic;
    private static int clusterCount;

    public static void main(String[] args) {

        for (int j= 4; j<=4; j++) {
            initalize("X:\\work\\AlgoPractice\\src\\main\\resources\\clustering1.txt");
            System.out.println("Spacing = " + findMaxSpacing(j));
        }
    }

    public static int findMaxSpacing(int k) {
        int spacing = 0;
        clusterCount = k;

        Collections.sort(edgeList, new CompareEdgeWeights());

        Iterator<Edge> iterator = edgeList.iterator();
        int edgeCounter = 1;

        while (iterator.hasNext()) {

            //System.out.println("edgeCounter=" + edgeCounter);

            if (unionFindBasic.getPartitionCount() <= clusterCount) {

                while (iterator.hasNext()) {
                    Edge e = iterator.next();
                    Vertex v1 = vertexList.get(e.u);
                    Vertex v2 = vertexList.get(e.v);
                    if (!unionFindBasic.find(v1, v2)) {
                        spacing = e.weight;
                        break;
                    }
                }
                break;
            }

            Edge e = iterator.next();
            Vertex v1 = vertexList.get(e.u);
            Vertex v2 = vertexList.get(e.v);

            if (!unionFindBasic.find(v1, v2)) {
                unionFindBasic.union(v1,v2);
            } else {
                //System.out.println(e.u + " and " + e.v + " in the same partition");
            }
            edgeCounter++;
        }
        return spacing;
    }

    public static void initalize(String filePath) {

        vertexCount = 0;
        vertexList = new ArrayList<>();
        edgeList = new ArrayList<>();
        unionFindBasic = new UnionFindBasic();

        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();
            String line = linesIterator.next();
            vertexCount = Integer.parseInt(line);

            vertexList.add(null);

            for (int i = 1; i <= vertexCount; i++) {
                Vertex vertex = new Vertex(i);
                vertexList.add(vertex);
                unionFindBasic.addNewPartition(vertex);
            }

            while (linesIterator.hasNext()) {
                line = linesIterator.next();
                Edge edge = new Edge(Integer.parseInt(line.split(" ")[0]),
                        Integer.parseInt(line.split(" ")[1]),
                        Integer.parseInt(line.split(" ")[2]));
                edgeList.add(edge);
            }
            //unionFindBasic.initialize((List<UnionFindBasicElement>)(List<?>)edgeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CompareEdgeWeights implements Comparator<Edge>
{
    public int compare(Edge e1, Edge e2)
    {
        return ((Integer) e1.weight).compareTo(e2.weight);
    }
}

class Vertex implements UnionFindBasicElement {
    int id;
    Object partition;
    String bitString;
    int populationCount = 0;
   // int hamCode = 0;

    public Vertex(int id) {
        this.id = id;
    }

    public Vertex(int id, String bitString) {
        this.id = id;
        this.bitString = bitString;
        char[] characters = bitString.toCharArray();

        for (int i=0; i < 24; i++) {
           // hamCode = hamCode << 1;
            if (characters[i] == '1') {
                populationCount++;
               // hamCode = hamCode | 1;
            }
        }
    }

    @Override
    public void setPartition(Object partition) {
        this.partition = partition;
    }

    @Override
    public Object getPartition() {
        return this.partition;
    }
}
