
package com.mycompany.mavenproject2;


public class MinimumSpanningTree<T> {

    private MyGraf<T> graph;
    private MyHashMap<T, T> parentMap;

    public MinimumSpanningTree(MyGraf<T> graph) {
        this.graph = graph;
        this.parentMap = new MyHashMap<>();
        initializeParentMap();
    }

    public MyGraf<T> findMinimumSpanningTree() {
        MyGraf<T> minimumSpanningTree = new MyGraf<>();

        MyPriorityQueue<Edge<T>> edgeQueue = new MyPriorityQueue<>();
        for (T node : graph) {
            MyLinkedList<T> neighbors = graph.getNeighbors(node);
            if (neighbors != null) {
                for (T neighbor : neighbors) {
                    int weight = 2;
                    edgeQueue.add(new Edge<>(node, neighbor, weight));
                }
            }
        }

        while (!edgeQueue.isEmpty() && minimumSpanningTree.size() < graph.size() - 1) {
    Edge<T> edge = edgeQueue.poll();
    T sourceParent = findParent(edge.source);
    T destParent = findParent(edge.destination);

    if (sourceParent != null && destParent != null && !sourceParent.equals(destParent)) {
        minimumSpanningTree.addEdge(edge.source, edge.destination);
        union(sourceParent, destParent);
    }
}

        return minimumSpanningTree;
    }

    private void initializeParentMap() {
        for (T node : graph) {
            parentMap.ekle(node, node);
        }
    }

    private T findParent(T node) {
    T parent = parentMap.getir(node);
    if (parent != null && !parent.equals(node)) {
        parent = findParent(parent);
        parentMap.ekle(node, parent);
    }
    return parent;
}

    private void union(T source, T dest) {
        parentMap.ekle(source, dest);
    }

    private static class Edge<T> implements Comparable<Edge<T>> {
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
    
}