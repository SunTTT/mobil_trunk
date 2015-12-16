package com.longhui.entity;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Sorter {

    public static Map sort(Map map) {
        Map<Object, Object> mapVK = new TreeMap<Object, Object>(
            new Comparator<Object>() {
                public int compare(Object obj1, Object obj2) {
                    String v1 = (String)obj1;
                    String v2 = (String)obj2;
                    int s = v2.compareTo(v1);
                    return s;
                }
            }
        );

        Set col = map.keySet();
        Iterator iter = col.iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            Integer value = (Integer) map.get(key);
            mapVK.put(key, value);
        }
        return mapVK;
    }
    
}
