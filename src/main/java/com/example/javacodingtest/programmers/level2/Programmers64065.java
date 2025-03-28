package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.28
 @link https://school.programmers.co.kr/learn/courses/30/lessons/64065
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers64065 {
    
    static String input;
    static int[] answer;
    static boolean[] visited = new boolean[100001];
    static List<List<Integer>> numbers = new ArrayList<>();
    
    public int[] solution(String s) {
        input = s.substring(2, s.length() - 2);
        input = input.replace("},{", "&");
        String[] tuples = input.split("&");
        
        for(int i = 0; i < tuples.length; i++) {
            String[] tuple = tuples[i].split(",");
            numbers.add(new ArrayList<>());
            
            for(String t : tuple) {
                numbers.get(i).add(Integer.parseInt(t));
            }
        }
        
        Collections.sort(numbers, (o1, o2) -> Integer.compare(o1.size(), o2.size()));
        
        answer = new int[numbers.size()];
        
        for(int i = 0; i < numbers.size(); i++) {
            for(int j = 0; j < numbers.get(i).size(); j++) {
                int num = numbers.get(i).get(j);
                if (visited[num]) continue;
                answer[i] = num;
                visited[num] = true;
            }
        }
        return answer;
    }
}
