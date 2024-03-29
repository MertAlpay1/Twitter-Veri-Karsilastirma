
package com.mycompany.mavenproject2;



import java.util.Iterator;

public class MyGraf<T> implements Iterable<T> {

    private MyHashMap<T, MyLinkedList<T>> adjacencyList;

    public MyGraf() {
        adjacencyList = new MyHashMap<>();
    }

    public void addNode(T node) {
        adjacencyList.ekle(node, new MyLinkedList<>());
    }

  public void addEdge(T source, T destination) {
        MyLinkedList<T> list = adjacencyList.getir(source);

        if (list == null) {
            list = new MyLinkedList<>();
            adjacencyList.ekle(source, list);
        }

        list.ekle(destination);
    }

    public MyLinkedList<T> getNeighbors(T node) {
        return adjacencyList.getir(node);
    }
    public boolean containsNode(T node) {
    return adjacencyList.getir(node) != null;
}
    public static <T> void printGraph(MyGraf<T> graph) {
    for (T node : graph) {
        System.out.print(node + " -> (");

        MyLinkedList<T> neighbors = graph.getNeighbors(node);
        if (neighbors != null) {
            int count = 0;
            for (T neighbor : neighbors) {
                if (count > 0) {
                    System.out.print(", ");
                }
                System.out.print(neighbor);
                count++;
            }
        }

        System.out.println(")");
    }
}
    
    
    
public int size() {
        int count = 0;
        for (T node : this) {
            count++;
        }
        return count;
    }

   @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            private Iterator<MyHashMap.Entry<T, MyLinkedList<T>>> currentBucketIterator;

            @Override
            public boolean hasNext() {
                while (currentIndex < adjacencyList.getTable().length) {
                    if (adjacencyList.getTable()[currentIndex] != null &&
                            adjacencyList.getTable()[currentIndex].iterator().hasNext()) {
                        return true;
                    }
                    currentIndex++;
                }
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }

                if (currentBucketIterator == null || !currentBucketIterator.hasNext()) {
                    currentBucketIterator = (adjacencyList.getTable()[currentIndex] != null) ?
                            adjacencyList.getTable()[currentIndex].iterator() : null;
                    currentIndex++;
                }

                return currentBucketIterator.next().getAnahtar();

            }
        };
    }
}