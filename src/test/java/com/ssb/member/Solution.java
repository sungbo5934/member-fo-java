package com.ssb.member;


import java.util.*;
import java.util.stream.Collectors;



public class Solution {
	
	static int[] distance;
	
	public static class Node{
		int x;
		int k;
		List<Integer> before = new ArrayList<Integer>();
		
		public Node(int x, int k) {
			this.x =x;
			this.k =k;
		}
		
		public Node(int x, int k, int before) {
			this.x =x;
			this.k =k;
			this.before.add(before);
		}
	}
	
	
	public static int solution(int N, int[][] road, int K) {
		int answer = 0 ;
		distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0;
        Set<Integer> set = new HashSet<Integer>();
        Map<Integer, List<Node>> map = new HashMap<Integer, List<Node>>();
        Queue<Node> que = new LinkedList<Node>();
        for(int[] arr : road) {
        	List<Node> list = map.getOrDefault(arr[0], new ArrayList<Node>());
        	list.add(new Node(arr[1], arr[2]));
    		map.put(arr[0], list);
    		
    		List<Node> deflist = map.getOrDefault(arr[1], new ArrayList<Node>());
    		deflist.add(new Node(arr[0], arr[2]));
    		map.put(arr[1], deflist);
    		
        }
        
        que.add(new Node(1, 0));
        set.add(1);
        
        while(!que.isEmpty()) {
        	Node node = que.poll();
        	List<Node> nodeList = map.get(node.x);
        	if(nodeList != null) {
        		for(Node list : nodeList) {
        			int x =list.x;
        			int weight = list.k + node.k;
            		if(distance[x] > weight) {
            			distance[x] = weight;
            			que.add(new Node(x, weight));
            		}
            	}
        	}
        	
        }
        
        for(int dis : distance) {
        	if(dis <= K) {
        		answer++;
        	}
        }
        
        return answer;
    }
	
	public static void main(String[] phone_book)throws Exception {
		//int[] stages1 = {1, 2, 3, 9, 10, 12};
		//int[] stages2 = {27 ,56, 19, 14, 14, 10};
		int[] stages ={1,10,};
		int[] stages1 ={2, 1, 3, 2};
		int[][] stages2 = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		String[] str1 = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] str2 = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		int[][] place = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
		double int1 = 9;
		double int2 = 18;
		//System.out.println(8,12);
		//System.out.println(Math.floor(57345.12891872515));
		System.out.println(solution(6, place, 4));
        
	}
	

	
	


}
