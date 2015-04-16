package org.coursera.stanford;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class PrimsMinSpanTree {

    public static void main(String[] args) {
        System.out.println("Cost of Spanning Tree:" + findCostOfMinSpanningTree(buildGraph("X:\\work\\AlgoPractice\\src\\main\\resources\\prim_mst_input_edges.txt")));
    }
    public static Graph buildGraph(String filePath) {
        Graph graph = null;

        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();
            String line = linesIterator.next();
            graph = new Graph(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));

            while (linesIterator.hasNext()) {
                line = linesIterator.next();
                graph.addEdge(Integer.parseInt(line.split(" ")[0]),
                        Integer.parseInt(line.split(" ")[1]),
                        Integer.parseInt(line.split(" ")[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static Edge findMin(Set<Edge> crossingEdges) {
        int min = Integer.MAX_VALUE;
        Edge minEdge = null;

        for (Edge edge: crossingEdges) {
            if (edge.weight < min) {
                min = edge.weight;
                minEdge = edge;
            }
        }
        return minEdge;
    }

    public static int updateCrossingEdge(Set<Edge> crossingEdges, Set<Integer> processedNodes, Graph graph) {
        Edge minEdge = findMin(crossingEdges);
        List<Edge> adjVertices = graph.adjList.get(minEdge.v);

        //crossingEdges.remove(minEdge);
        for (Edge edge: adjVertices) {
            if (processedNodes.contains(edge.v)) {
                crossingEdges.remove(graph.getEdge(edge.v, edge.u));
            }
            else {
                crossingEdges.add(edge);
            }
        }
        processedNodes.add(minEdge.v);
        return minEdge.weight;
    }

    public static Long findCostOfMinSpanningTree(Graph graph) {
        Long minCost = 0L;

        if (graph != null) {

            Set<Edge> crossingEdges = new HashSet<>();
            Set<Integer> processedNodes = new HashSet<>();

            crossingEdges.addAll(graph.adjList.get(1));
            processedNodes.add(1);

            for (int i = 2; i <= graph.vertexCount; i++) {
                minCost += updateCrossingEdge(crossingEdges, processedNodes, graph);
            }
        }
        return minCost;
    }
}

class Graph {
    ArrayList<ArrayList<Edge>>  adjList;
    int vertexCount;
    int edgeCount;

    public Graph(int vertexCount, int edgeCount) {
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.adjList = new ArrayList<>();
        for (int j = 0; j <= vertexCount; j++) {
            adjList.add(new ArrayList<Edge>());
        }
    }

    public void addEdge(int u, int v, int weight) {

        adjList.get(u).add(new Edge(u, v, weight));
        adjList.get(v).add(new Edge(v, u, weight));
    }

    public Edge getEdge(int u, int v) {

        for (Edge edge : adjList.get(u)) {
            if (edge.v == v) return edge;
        }
        return null;
    }
}


class Edge  {
    int u;
    int v;
    int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}