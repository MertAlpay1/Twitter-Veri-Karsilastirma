
package com.mycompany.mavenproject2;

public class Edge<T> implements Comparable<Edge<T>> {
    T source;
    T destination;
    int weight;

    public Edge(T source, T destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge<T> other) {
        return Integer.compare(this.weight, other.weight);
    }
}