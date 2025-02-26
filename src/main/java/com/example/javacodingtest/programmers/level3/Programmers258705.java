package com.example.javacodingtest.programmers.level3;

/*
 @author ranuinclulus
 @since 2025.02.27
 @link https://school.programmers.co.kr/learn/courses/30/lessons/258705
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers258705 {
	public int solution(int n, int[] tops) {
		int mod = 10007;
		int floor = 2 * n + 1;
		int[] dp = new int[floor + 1];

		dp[0] = 1;
		dp[1] = 1;
		for(int i = 2; i <= floor; i++) {
			if (i % 2 == 0 && tops[(i - 1) / 2] == 1) {
				dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % mod;
			}
			else {
				dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
			}
		}
		return dp[floor];
	}
}
