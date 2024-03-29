
package com.mycompany.mavenproject2;
import java.util.Iterator;
//T olması jenerik yapar ve int,string olabilir
public class MyArrayList<T> implements Iterable<T> {
    
    private Object[] elements;
    private int boyut;
    private static final int DEFAULT_CAPACITY = 16;
    
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.boyut = 0;
    }
    
    public void ekle(T eleman){
        if (boyut == elements.length) {
            genislet();
        }
        elements[boyut++]=eleman;
    }

     public void sil(T eleman) {
        for (int i = 0; i < boyut; i++) {
            if (eleman.equals(elements[i])) {
                kaydir(i);
                boyut--;
                return;
            }
        }
    }
     public int getBoyut() {
        return boyut;
    }
      public void yazdir() {
        for (int i = 0; i < boyut; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
     private void genislet() {
        int yeniBoyut = elements.length * 2;
        Object[] yeniDizi = new Object[yeniBoyut];
        System.arraycopy(elements, 0, yeniDizi, 0, boyut);
        elements = yeniDizi;
    }
    
    private void kaydir(int indeks) {
        System.arraycopy(elements, indeks + 1, elements, indeks, boyut - indeks - 1);
    }
    public boolean contains(T eleman) {
        for (int i = 0; i < boyut; i++) {
            if (eleman.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < boyut;
            }

            @Override
            public T next() {
                return (T) elements[currentIndex++];
            }
        };
    
    }
}
