package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.03.21
 @link https://www.acmicpc.net/problem/7682
 @timecomplex
 @performance 14096KB 100MS
 @category
 @note
 */
public class five7682 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;

	public void solution() throws IOException {
		while (true) {
			String input = reader.readLine();
			if (input.equals("end")) {
				break;
			}

			int xCount = 0;
			int oCount = 0;
			char[][] map = new char[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = input.charAt(3 * i + j);
					if (map[i][j] == 'X') xCount++;
					if (map[i][j] == 'O') oCount++;
				}
			}

			if (xCount + oCount == 9 && xCount - oCount == 1) { // 말이 전부 채워졌을 때
				if (tiktato(map, 'X') && tiktato(map, 'O')) builder.append("invalid\n");
				else if (tiktato(map, 'O')) builder.append("invalid\n");
				else builder.append("valid\n");
			} else {
				if (tiktato(map, 'X') && tiktato(map, 'O')) builder.append("invalid\n");
				else if (tiktato(map, 'O') && xCount == oCount) builder.append("valid\n");
				else if (tiktato(map, 'X') && xCount - oCount == 1) builder.append("valid\n");
				else builder.append("invalid\n");
			}
		}
		writer.write(builder.toString());
		writer.flush();
	}

	private boolean tiktato(char[][] map, char target) {
		for (int i = 0; i < 3; i++) {
			int count = 0;
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == target) count++;
			}
			if (count == 3) return true;
		}

		for (int i = 0; i < 3; i++) {
			int count = 0;
			for (int j = 0; j < 3; j++) {
				if (map[j][i] == target) count++;
			}
			if (count == 3) return true;
		}

		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (map[i][i] == target) count++;
		}
		if (count == 3) return true;

		count = 0;
		for (int i = 0; i < 3; i++) {
			if (map[i][2 - i] == target) count++;
		}
		if (count == 3) return true;

		return false;
	}

	public static void main(String[] args) throws IOException {
		new five7682().solution();
	}
}
