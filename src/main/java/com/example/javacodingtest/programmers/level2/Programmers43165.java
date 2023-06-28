package com.example.javacodingtest.programmers.level2;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165

public class Programmers43165 {
    private int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        return answer;
    }

    // 재귀 함수 dfs
    public void dfs(
            // 내가 사용할 숫자들
            int[] numbers,
            // 내가 다음에 사용할 차례의 숫자
            // 이번 dfs 호출에서 더하거나 빼거나 할 숫자는 numbers[next]
            int next,
            // 목표값
            int target,
            // 이번 재귀까지 합한 값
            int sum
    ) {
        // 마지막 숫자를 쓴 시점에 next는 배열의 크기와 동일해진다
        if(next == numbers.length) {
            // target과 일치하는지 확인
            if(sum == target) this.answer++;
        }
        else {

            // 더한 다음 다음 숫자 결정
            dfs(numbers, next + 1, target, sum + numbers[next]);
            // 뺀 다음 다음 숫자 결정
            dfs(numbers, next + 1, target, sum - numbers[next]);
        }
    }

    public static void main(String[] args) {
        int answer = new Programmers43165().solution(new int[] {1, 1, 1, 1, 1}, 3);
        System.out.println("answer = " + answer);
    }
}

