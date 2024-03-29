
package com.mycompany.mavenproject2;

import java.util.Iterator;


//T olması jenerik yapar ve int,string olabilir
public class  MyLinkedList<T> implements Iterable<T> {
    
    private Node<T> bas;
 private Node<T> head;
 
    private static class Node<T> {
        private T veri;
        private Node<T> sonraki;

        public Node(T veri) {
            this.veri = veri;
            this.sonraki = null;
        }
    }
    
    public MyLinkedList(){
        
         this.bas = null;   
    }
    
    public void ekle(T eleman) {
        Node<T> yeniDugum = new Node<>(eleman);
        if (bas == null) {
            bas = yeniDugum;
        } else {
            Node<T> temp = bas;
            while (temp.sonraki != null) {
                temp = temp.sonraki;
            }
            temp.sonraki = yeniDugum;
        }
    }
    public T getir(int indeks) {
        Node<T> temp = bas;
        for (int i = 0; i < indeks; i++) {
            if (temp == null) {
                return null; // Eleman bulunamadı
            }
            temp = temp.sonraki;
        }
        return temp != null ? temp.veri : null;
    }
     public boolean isEmpty() {
        return bas == null;
    }
    
    public void sil(T eleman) {
        if (bas == null) {
            return;
        }

        if (bas.veri.equals(eleman)) {
            bas = bas.sonraki;
            return;
        }

        Node<T> temp = bas;
        while (temp.sonraki != null && !temp.sonraki.veri.equals(eleman)) {
            temp = temp.sonraki;
        }

        if (temp.sonraki != null) {
            temp.sonraki = temp.sonraki.sonraki;
        }
    }
     public void yazdir() {
        Node<T> temp = bas;
        while (temp != null) {
            System.out.print(temp.veri + " ");
            temp = temp.sonraki;
        }
        System.out.println();
    }
    public boolean contains(T eleman) {
    Node<T> temp = bas;
    while (temp != null) {
        if (eleman.equals(temp.veri)) {
            return true;
        }
        temp = temp.sonraki;
    }
    return false;
}
     public int size() {
    int count = 0;
    Node<T> temp = bas;
    
    while (temp != null) {
        count++;
        temp = temp.sonraki;
    }
    
    return count;
}
     
     public void addLast(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.sonraki != null) {
                temp = temp.sonraki;
            }
            temp.sonraki = newNode;
        }
    }

    // removeFirst metodu örneği
    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        T data = head.veri;
        head = head.sonraki;
        return data;
    }
     //Foreach döngüsü içinde gezinmek için
     @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = bas;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.veri;
                current = current.sonraki;
                return data;
            }
        };
}
 
}