package com.example.javacodingtest.boj.gold;
import java.util.*;
import java.io.*;
/*
 @author ranunclulus
 @since 2025.08.26
 @link https://www.acmicpc.net/problem/17089
 @timecomplex
 @performance 35736KB 364MS
 @category
 @note
 */
public class five17089_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M, minFriend = Integer.MAX_VALUE;
	static boolean[][] isFriend;
	static int[] friendNum;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		isFriend = new boolean[N][N];
		friendNum = new int[N];

		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int A = Integer.parseInt(tokenizer.nextToken()) - 1;
			int B = Integer.parseInt(tokenizer.nextToken()) - 1;
			isFriend[A][B] = true;
			isFriend[B][A] = true;
			friendNum[A]++;
			friendNum[B]++;
		}

		minFriend = Integer.MAX_VALUE;

		for (int i = 0; i < N - 2; i++) {
			if (friendNum[i] < 2) continue;
			for (int j = i + 1; j < N - 1; j++) {
				if (friendNum[j] < 2) continue;
				if (!isFriend[i][j] || !isFriend[j][i]) continue;
				for (int k = j + 1; k < N; k++) {
					if (friendNum[k] < 2) continue;
					if (!isFriend[i][k] || !isFriend[k][i]) continue;
					if (!isFriend[j][k] || !isFriend[k][j]) continue;

					minFriend = Math.min(minFriend, friendNum[i] + friendNum[j] + friendNum[k] - 6);
				}
			}
		}

		builder.append(minFriend != Integer.MAX_VALUE ? minFriend : -1);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		new five17089_2().solution();
	}

}
