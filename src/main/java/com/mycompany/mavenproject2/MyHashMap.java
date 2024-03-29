
package com.mycompany.mavenproject2;

//Key , Value

import java.util.Iterator;

public class MyHashMap<K, V> implements Iterable<MyHashMap.Entry<K, V>> {

    private static final int DEFAULT_CAPACITY = 16;
    private MyLinkedList<Entry<K, V>>[] table;

    public MyHashMap() {
        this.table = new MyLinkedList[DEFAULT_CAPACITY];
    }

    
    public MyLinkedList<Entry<K, V>>[] getTable() {
        return table;
    }

    public void ekle(K anahtar, V deger) {
        int hash = hash(anahtar);
        int indeks = (hash % table.length + table.length) % table.length;


        if (table[indeks] == null) {
            table[indeks] = new MyLinkedList<>();
        }

        MyLinkedList<Entry<K, V>> bucket  = table[indeks];
        for (Entry<K, V> entry : bucket ) {
            if (entry.getAnahtar().equals(anahtar)) {
                entry.setDeger(deger);
                return;
            }
        }

        bucket .ekle(new Entry<>(anahtar, deger));
    }

    //Statik değer türü V 
    public V getir(K anahtar) {
        int hash = hash(anahtar);
        int indeks = (hash % table.length + table.length) % table.length;


        MyLinkedList<Entry<K, V>> kova = table[indeks];
        if (kova != null) {
            for (Entry<K, V> entry : kova) {
                if (entry.getAnahtar().equals(anahtar)) {
                    return entry.getDeger();
                }
            }
        }

        return null;
    }


    private int hash(K anahtar) {
        
        return anahtar.hashCode();
    }

    

    public static class Entry<K, V> {
        private K anahtar;
        private V deger;
        private Entry<K, V> next;

        public Entry(K anahtar, V deger) {
            this.anahtar = anahtar;
            this.deger = deger;
        }

        public K getAnahtar() {
            return anahtar;
        }

        public V getDeger() {
            return deger;
        }

        public void setDeger(V deger) {
            this.deger = deger;
        }
    }
  @Override
public Iterator<Entry<K, V>> iterator() {
    return new Iterator<Entry<K, V>>() {
        private int currentIndex = 0;
        private Iterator<Entry<K, V>> currentBucketIterator;

        @Override
        public boolean hasNext() {
            while (currentIndex < table.length && (table[currentIndex] == null || !table[currentIndex].iterator().hasNext())) {
                currentIndex++;
            }
            return currentIndex < table.length;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            if (currentBucketIterator == null || !currentBucketIterator.hasNext()) {
                currentBucketIterator = (table[currentIndex] != null) ? table[currentIndex].iterator() : null;
                currentIndex++;
            }

            return currentBucketIterator.next();
        }
    };
    
    
    
}
}
    

