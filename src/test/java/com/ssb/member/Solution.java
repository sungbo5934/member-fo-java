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
	public String replaceAll (String ori,String rex) {
		String replace = ori.replaceAll(rex, ",").replaceAll("[,]{2,}", ",").replaceAll("^[,]", "").replaceAll("[,]$", "");
		return replace;
	}
	
	public static int solution(int numInt) {
        int answer = 0;
        long num = (long)numInt;
        while(true) {
        	
        	if(num == 1) {
        		break;
        	}else if(answer == 500) {
        		answer = -1;
        		break;
    		}
        	
        	if(num % 2 == 0 ) {
        		num /= 2;
        		answer++;
            }else {
            	num = num * 3 + 1;
            	answer++;
            }
        }
        return answer;
    }
	 
	public static void main(String[] phone_book) {
		int[] stages1 = {46, 33, 33 ,22, 31, 50};
		int[] stages2 = {27 ,56, 19, 14, 14, 10};
		System.out.println(solution(626331));
		//solution(6, stages1, stages2);
        
	}
	

	
	


}
