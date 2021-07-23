package com.ssb.member;


import java.util.*;
import java.util.stream.Collectors;

public class Solution {
	
	public int[] 가장큰수(int[] array, int[][] commands) {
        int[] answer = {};
        answer =  new int[commands.length];
        for(int l = 0 ; l  < commands.length ; l++) {
        	int i = commands[l][0]; //2
        	int j = commands[l][1]; //5
        	int k = commands[l][2]; //3
        	int length = j - i + 1;
        	int[] arr = new int[length];
        	for(int h = 0 ; h < length ; h++) {
        		arr[h] = array[h + i - 1];
        	}
        	
        	Arrays.sort(arr);
        	answer[l] = arr[k -1];
        	
        }
        
        return answer;
    }
	public int gety(int number) {
		
		if(number >= 1 && number <= 3) {
			return 3;
		}else if(number >= 4 && number <= 6) {
			return 2;
		}else if(number >= 7 && number <= 9) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public int getx(int number) {
		
		if(number >= 1 && number <= 3) {
			return number - 1;
		}else if(number >= 4 && number <= 6) {
			return number - 4;
		}else if(number >= 7 && number <= 9) {
			return number - 7;
		}else {
			return 1;
		}
	}
	
	public String solution2(int[] numbers, String hand) {

        String answer = "";
        int leftx = 0;
        int lefty = 0;
        int rightx = 2;
        int righty = 0;
        

        
        Boolean moveHand = null;
        //true 왼손
        //flase 오른손
        for(int number : numbers) {
        	if(number == 1 || number == 4 || number == 7) {
        		moveHand = true;
        	}else if(number == 3 || number == 6 || number == 9) {
        		moveHand = false;
        	}else {
        		int curx = getx(number); 
        		int cury = gety(number); 
        		int totlaLeft = Math.abs(curx - leftx) + Math.abs(cury - lefty);
        		int totlaright = Math.abs(curx - rightx) + Math.abs(cury - righty);
        		if(totlaLeft > totlaright) {
        			moveHand = false;
        		}else if(totlaLeft < totlaright) {
        			moveHand = true;
        		}else {
        			if(hand.equals("right")) {
        				moveHand = false;
        			}else {
        				moveHand = true;
        			}
        		}
        		
        	}
        	
        	if(moveHand) {
        		answer += "L";
        		leftx = getx(number);
        		lefty = gety(number); 
        	}else {
        		answer += "R";
        		rightx = getx(number);
        		righty = gety(number); 
        	}
        }
        
        
        return answer;
    }

	
	public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        Map<Integer, Double> map = new HashMap<Integer,Double>();
        for(int i = 0 ; i < answer.length; i++) {
        	int j = i + 1;
        	int child = Arrays.stream(stages).filter((data) -> data == j ).toArray().length;
        	int parent = Arrays.stream(stages).filter((data) -> data >= j ).toArray().length;
        	double persent = (double) child / (double)parent ;
        	map.put(j, parent == 0 ? 0 :  persent);
        	
        }
    	List<Map.Entry<Integer, Double>> list = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());
    	for(int i = 0 ; i < answer.length ; i++) {
        	answer[i] = list.get(i).getKey();
        }
        return answer;
    }

	
	public static void main(String[] phone_book) {
		Integer[] lottos = {44, 1, 0, 0, 31, 25};
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		solution(11, stages);
		Map<Integer, Integer> map = new HashMap<>();
		map.put(2, 1020);
		map.put(5, 300);
		map.put(1, 100);
		map.put(3, 300);
        
	}
	

	
	


}
