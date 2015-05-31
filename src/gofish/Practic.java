/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Joseph
 */
public class Practic {

    public static void main(String[] args) {
        int max = -1;
        int a[] = {1, 1, 1,1, 2,2,2,2, 3, 4, 5, 6, 7, 8, 9};
        final Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : a) {
            int count = 0;
            if (countMap.containsKey(num)) {
                count = countMap.get(num) + 1;
            } else {
                count = 1;
            }
            countMap.put(num, count);
            if (count > max) {
                max = count;
            }
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(value==4){
            System.out.print("Key: "+ key);
            System.out.println(" value "+ value);
            }
        }
        
        System.out.println("a".endsWith("A"));
        System.out.println("a".contains("A"));
        System.out.println("a".contains("a"));
//        System.out.println("a".endsWith("A"));
//        countMap.entrySet()
//        Set<Integer> test = countMap.keySet();
//        for (Integer count : test) {
//            System.out.println(count);
//        }
    }
}
