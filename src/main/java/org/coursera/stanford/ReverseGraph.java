package org.coursera.stanford;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murali on 19-04-2015.
 */
public class ReverseGraph  {

    ArrayList<ArrayList<DirectedEdge>>  adjList;
    int vertexCount;
    int edgeCount;

    public ReverseGraph(int vertexCount, int edgeCount) {

        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.adjList = new ArrayList<>();
        for (int j = 0; j <= vertexCount; j++) {
            adjList.add(new ArrayList<DirectedEdge>());
        }
    }

    public void addReverseEdge(int tail, int head, int weight) {
        adjList.get(head).add(new DirectedEdge(tail, head, weight));

    }

    public DirectedEdge getReverseEdge(int tail, int head) {
        for (DirectedEdge edge : adjList.get(head)) {
            if (edge.tail == tail) return edge;
        }
        return null;
    }

    public List<Integer> getAdjVerices(int head) {
        List<Integer> adjVertices = new ArrayList<>();
        for (DirectedEdge edge : adjList.get(head)) {
            adjVertices.add(edge.tail);
        }
        return adjVertices;
    }
}

