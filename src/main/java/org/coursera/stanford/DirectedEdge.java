package org.coursera.stanford;

/**
 * Created by murali on 19-04-2015.
 */
public class DirectedEdge  {
    // A directed edge is always from tail to head.
    int tail;
    int head;
    int weight;

    public DirectedEdge(int tail, int head, int weight) {
        this.tail = tail;
        this.head = head;
        this.weight = weight;
    }
}
