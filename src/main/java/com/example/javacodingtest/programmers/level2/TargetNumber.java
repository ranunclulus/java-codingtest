package com.example.javacodingtest.programmers.level2;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165

class Solution {
    public static int dfs(int[] numbers, int target, int sum, int current) {
        int answer = 0;

        // 끝까지 다 검사했을 때
        if (current == numbers.length) {
            // 타겟 넘버를 만족하면 +1
            if(sum == target) return 1;
            // 타겟 넘버를 만족하지 않으면 +0
            else return 0;
        }
        // 다음 숫자 더했을 경우
        answer += dfs(numbers, target, sum + numbers[current], current + 1);
        // 다음 숫자 뺐을 경우
        answer += dfs(numbers, target, sum - numbers[current], current + 1);
        return answer;
    }
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
}
public class TargetNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[] {1, 1, 1, 1}, 3);
    }
}
