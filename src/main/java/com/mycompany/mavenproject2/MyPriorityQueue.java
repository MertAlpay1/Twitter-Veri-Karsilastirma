
package com.mycompany.mavenproject2;

public class MyPriorityQueue<T extends Comparable<T>> {

     private MyLinkedList<T> elements;

    public MyPriorityQueue() {
        elements = new MyLinkedList<>();
    }

     public void add(T element) {
        if (isEmpty() || element.compareTo(elements.getir(elements.size() - 1)) <= 0) {
            elements.addLast(element);
        } else {
            int index = 0;
            while (index < elements.size() && element.compareTo(elements.getir(index)) > 0) {
                index++;
            }
            elements.ekle( element);
        }
    }

    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Boştur");
        }
        return elements.removeFirst();
    }

    

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}