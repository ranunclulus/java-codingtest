package com.example.javacodingtest.boj.silver;

import java.util.*;
import java.io.*;

/*
 @author ranunclulus
 @since 2025.08.27
 @link https://www.acmicpc.net/problem/2516
 @timecomplex
 @performance 14308KB 104MS
 @category
 @note
 */
public class one2516 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int[][] map = new int[19][19];
	static int[][] deltas = new int[][] {{1, 0}, {0, 1}, {1, 1}, {-1, 1}};

	public void solution() throws IOException {
		for(int i = 0; i < 19; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if (map[i][j] == 1 || map[i][j] == 2) {
					for(int d = 0; d < 4; d++) {
						int row = i;
						int col = j;
						int count = 1;

						while(true) {
							row += deltas[d][0];
							col += deltas[d][1];

							if (row >= 0 && row < 19 && col >= 0 && col < 19) {
								if (map[i][j] == map[row][col]) count++;
								else break;
							} else break;
						}

						if (count == 5) {
							row = i + deltas[d][0] * 5;
							col = j + deltas[d][1] * 5;
							if (row >= 0 && row < 19 && col >= 0 && col < 19) {
								if (map[i][j] == map[row][col]) continue;
							}

							row = i - deltas[d][0];
							col = j - deltas[d][1];
							if (row >= 0 && row < 19 && col >= 0 && col < 19) {
								if (map[i][j] == map[row][col]) continue;
							}

							builder.append(map[i][j]).append('\n').append(i + 1).append(' ').append(j + 1);
							return;
						}
					}
				}
			}
		}
		builder.append(0);
		return;
	}


	public static void main(String[] args) throws IOException {
		new one2516().solution();
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

}

