package com.ssb.member;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	
	public int solution(String[][] clothes) {
		Set<String> set = new HashSet<String>();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for(int i =0; i < clothes.length; i++) {
			List<String> list = Optional.ofNullable(map.get(clothes[i][1])).orElseGet(() -> {return new ArrayList<String>();});
			list.add(clothes[i][0]);
			map.put(clothes[i][1], list);
		}
		for(int i = 1 ; i < map.keySet().size() + 1 ; i ++) {
			List<String> clothList = new ArrayList<String>();
			int index = 0;
			String intKey = "";
			for(int j = 0 ; j < map.keySet().size()  ; j ++) {
				String key = (String) map.keySet().toArray()[j];
				for(String clothe : map.get(key)) {
					if(index < i && !intKey.equals(key)) {
						clothList.add(clothe);
						intKey = key;
						index++;
					}
					
					if(index == i) {
						if(set.contains(clothList.toString())) {
							clothList.remove(clothe);
							index--;
						}else {
							intKey = "";
							set.add(clothList.toString());
							clothList = new ArrayList<String>();
							index = 0;
							j=-1;
						}
					}
				}
			}
		}
        int answer = set.size();
        return answer;
    }
	
	
	
	public boolean solution2(String[] phone_book) {
		
		boolean chk = true;
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();

		for(String str : phone_book) {
			List<String> list = Optional.ofNullable(map.get(str.length())).orElseGet(() -> {return new ArrayList<String>();});
			list.add(str);
			map.put(str.length(), list);
		}

		loog1:
		for( int key1 : map.keySet()) {
			for( int key2 : map.keySet()) {
				if(key1 < key2) {
					for(String phone1 : map.get(key1)) {
						for(String phone2 : map.get(key2)) {
							if(phone2.startsWith(phone1)) {
								chk = false;
			             		break loog1;
							}
						}
					}
				}
			}
		}
		
        
        return chk;
    }
	
	public static void main(String[] phone_book) {
		List<String> clothList = new ArrayList<String>();
		clothList.add("test");
		clothList.add("test2");

		System.out.println(clothList.toString());
	}
	
	


}
