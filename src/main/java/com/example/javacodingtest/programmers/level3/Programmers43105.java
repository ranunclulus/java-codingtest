package com.example.javacodingtest.programmers.level3;

/*
 @author ranuinclulus
 @since 2025.02.06
 @link https://school.programmers.co.kr/learn/courses/30/lessons/43105?language=java
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers43105 {
	public int solution(int[][] triangle) {
		int answer = 0;

		int[][] dp = new int[triangle.length][];
		for(int i = 0; i < triangle.length; i++) {
			dp[i] = new int[i + 1];
		}

		dp[0][0] = triangle[0][0];

		for(int i = 1; i < triangle.length; i++) {
			dp[i][0] = dp[i - 1][0] + triangle[i][0];
			dp[i][i] = dp[i - 1][i - 1]+ triangle[i][i];
			for(int j = 1; j < i; j++) {
				dp[i][j] = (triangle[i][j] +
					Math.max(dp[i - 1][j - 1], dp[i - 1][j]));
			}
		}

		for(int i = 0; i < triangle.length; i++) {
			answer = Math.max(answer, dp[triangle.length - 1][i]);
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] triangle = {{1}, {4, 5}, {7, 8, 9}};
		new Programmers43105().solution(triangle);
	}
}
