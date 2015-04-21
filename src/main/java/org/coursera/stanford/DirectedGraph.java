package org.coursera.stanford;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murali on 19-04-2015.
 */
public class DirectedGraph {

    ArrayList<ArrayList<DirectedEdge>>  adjList;
    int vertexCount;
    int edgeCount;

    public DirectedGraph(int vertexCount, int edgeCount) {

        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.adjList = new ArrayList<>();
        for (int j = 0; j <= vertexCount; j++) {
            adjList.add(new ArrayList<DirectedEdge>());
        }
    }

    public void addDirectedEdge(int tail, int head, int weight) {
        adjList.get(tail).add(new DirectedEdge(tail, head, weight));
    }

    public DirectedEdge getDirectedEdge(int tail, int head) {
        for (DirectedEdge edge : adjList.get(tail)) {
            if (edge.head == head) return edge;
        }
        return null;
    }

    public List<DirectedEdge> getOutgoingEdges(int tail) {

        return adjList.get(tail);
    }

    public List<DirectedEdge> getIncomingEdges(int head) {
        return adjList.get(head);
    }

    public List<Integer> getAdjVerices(int tail) {
        List<Integer> adjVertices = new ArrayList<>();
        for (DirectedEdge edge : adjList.get(tail)) {
            adjVertices.add(edge.head);
        }
        return adjVertices;
    }
}

