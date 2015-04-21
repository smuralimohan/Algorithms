package org.coursera.stanford;

import java.util.ArrayList;

/**
 * Created by murali on 19-04-2015.
 */
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
