
package com.mycompany.mavenproject2;


public class MyQueue<T> {
    private MyLinkedList<T> elements = new MyLinkedList<>();

    public void enqueue(T element) {
        elements.ekle(element);
    }

     public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T front = elements.getir(0);
        elements.sil(front);
        return front;
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}