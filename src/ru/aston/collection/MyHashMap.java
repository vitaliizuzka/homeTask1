package ru.aston.collection;

public class MyHashMap<K, V> {

    private final int DEFAULT_HASH_TABLE_SIZE = 16;
    private final double DEFAULT_LOAD_FACTOR = 0.75;
    private final int SIZE_INCREASE_MULTIPLIER = 2;


    private Node<K, V>[] hashTable;
    private int hashTableSize;
    private int currentSize;
    private double loadFactor;

    public MyHashMap() {
        hashTableSize = DEFAULT_HASH_TABLE_SIZE;
        loadFactor = DEFAULT_LOAD_FACTOR;
        hashTable = new Node[hashTableSize];
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public V put(K key, V value) {
        Node<K, V> addNode = new Node<>(key, value);
        currentSize++;

        if (loadFactor * hashTableSize < currentSize) {
            rebuildHashTable();
        }

        if (key == null) {
            hashTable[0] = addNode;
            return null;
        }

        int insertPosition = findPosition(key);

        if (hashTable[insertPosition] == null)
            hashTable[insertPosition] = addNode;
        else {
            Node<K, V> currentNode = hashTable[insertPosition];
            Node<K, V> prev = null;
            do {
                if (currentNode.key.equals(key)) {
                    V oldValue = currentNode.value;
                    currentNode.value = value;
                    return oldValue;
                }
                prev = currentNode;
            } while ((currentNode = currentNode.next) != null);
            prev.next = addNode;
        }
        return null;
    }

    public V get(K key) {
        if (key == null) {
            if (hashTable[0] == null) {
                return null;
            } else {
                return hashTable[0].value;
            }
        }

        int position = findPosition(key);
        Node<K, V> node = hashTable[position];
        while (node != null) {
            if (key.equals(node.key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }


    public V remove(K key) {
        V delValue = null;

        if (key == null) {
            if (hashTable[0] == null) {
                return null;
            } else {
                delValue = hashTable[0].value;
                hashTable[0] = null;
                return delValue;
            }
        }

        int deletePosition = findPosition(key);

        if (hashTable[deletePosition] == null)
            return null;
        else {
            if (hashTable[deletePosition].key.equals(key)) {
                delValue = hashTable[deletePosition].value;
                hashTable[deletePosition] = hashTable[deletePosition].next;
                return delValue;
            } else {
                Node<K, V> currentNode = hashTable[deletePosition];
                do {
                    if (currentNode.next != null && currentNode.next.key.equals(key)) {
                        delValue = currentNode.next.value;
                        currentNode.next = currentNode.next.next;
                        return delValue;
                    }
                } while ((currentNode = currentNode.next) != null);
            }
        }
        return null;
    }


    private void rebuildHashTable() {
        hashTableSize = hashTableSize * SIZE_INCREASE_MULTIPLIER;
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[hashTableSize];
        for (int i = 0; i < oldHashTable.length; i++) {
            Node<K, V> node = oldHashTable[i];
            if (node != null) {
                do {
                    put(node.key, node.value);
                    currentSize--;
                } while ((node = node.next) != null);
            }
        }
    }


    private int findPosition(K key) {
        int position = key.hashCode() % (hashTableSize - 1) + 1;
        return position;
    }

}
