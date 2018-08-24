package cn.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class SetTest {
	public static void main(String[] args) {
		TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
		tm.put("a", 3);
		tm.put("c", 5);
		tm.put("b", 3);
		tm.put("m", 9);
		tm.put("al",7);
		
		Set<String> set = tm.keySet();
		for (String k : set) {
			System.out.println(k);
		}
		
		Collection<Integer> set1 = tm.values();
		System.out.println("--------------------------");
		for (Integer i : set1) {
			System.out.println(i);
		}
		System.out.println("--------------------------");
		System.out.println(Collections.max(set1));
System.out.println("--------------------------------------");
		//获取所有键的集合
		Collection<String> setKey = tm.keySet();
		//获取所有键的长度，为了获取一共有多少个小时的数据
		int keyCount = setKey.size();
		int sum = 0;
		Set<Entry<String, Integer>> keyValue = tm.entrySet();
		
		for (Entry<String, Integer> entry : keyValue) {
			sum += entry.getValue();
		}
		System.out.println(sum/keyCount);
		
		
	}
}
