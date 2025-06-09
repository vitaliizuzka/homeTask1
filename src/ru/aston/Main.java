package ru.aston;

import ru.aston.collection.MyHashMap;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        MyHashMap<Integer, String> map = new MyHashMap<>();

        System.out.println(map.get(1));

        map.put(1, "Goga");

        map.put(7, "Vita");
        map.put(15, "Grisha");
        map.put(17, "Inga");
        map.put(33, null);
        map.put(0, "Luda");
        System.out.println(map.get(0));
        map.put(16, "Kristi");
        map.put(32, "Anya");
        map.put(null, "Katya");
        System.out.println(map.put(1, "Katya"));
        map.put(1, "Goga");
        map.put(11, "Goga");
        map.put(111, "Goga");
        map.put(1111, "Goga");
        map.put(11111, "Goga");
        map.put(17, "Goga");
        map.put(119, "Goga");
        map.put(13, "Goga");
        map.put(119, "Goga");
        System.out.println(map.get(1));
        System.out.println(map.get(32));
        System.out.println(map.get(null));
        System.out.println(map.get(17));
        System.out.println(map.remove(null));
        System.out.println("-------------------------");
        System.out.println(map.remove(1));
        System.out.println(map.remove(100));

    }
}
