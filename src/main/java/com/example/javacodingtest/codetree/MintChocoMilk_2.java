package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2026.01.01
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/mint-choco-milk
 @timecomplex
 @performance
 @category
 @note
 */
public class MintChocoMilk_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, T;
	static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Student[][] map;
	static boolean[][] visited;
	static List<Student> leaders;
	static boolean[][] isSpread;

	class Student {
		/*
		001 --> 1   민트
		010 --> 2   초코
		100 --> 4   우유
		011 --> 3   민트초코
		101 --> 5   민트우유
		110 --> 6   초코우유
		111 --> 7   민트초코우유
		 */
		int row;
		int col;
		int faith;
		int taste;

		public Student(int row, int col, int taste) {
			this.row = row;
			this.col = col;
			this.taste = taste;
		}
	}

	public void solution() throws IOException {
		init();
		while(T --> 0) {
			morning();
			afternoon();
			evening();
			print();
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	
	public void morning() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].faith++;
			}
		}
	}

	public void afternoon() {
		leaders = new ArrayList<>();
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) continue;
				List<Student> candidate = makeCandidate(i, j);
				leaders.add(candidate.get(0));
			}
		}

	}

	public List<Student> makeCandidate(int row, int col) {
		List<Student> answer = new ArrayList<>();
		Queue<int[]> toVisit = new ArrayDeque<>();
		int taste = map[row][col].taste;
		answer.add(map[row][col]);
		toVisit.add(new int[] {row, col});
		visited[row][col] = true;

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (visited[nextRow][nextCol]) continue;
				if (map[nextRow][nextCol].taste != taste) continue;

				answer.add(map[nextRow][nextCol]);
				toVisit.add(new int[] {nextRow, nextCol});
				visited[nextRow][nextCol] = true;
			}
		}
		answer.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.faith == o2.faith) {
					if (o1.row == o2.row) return Integer.compare(o1.col, o2.col);
					return Integer.compare(o1.row, o2.row);
				}
				return -Integer.compare(o1.faith, o2.faith);
			}
		});
		for (int i = 1; i < answer.size(); i++) {
			answer.get(i).faith--;
			answer.get(0).faith++;
		}
		return answer;
	}

	public void evening() {
		isSpread = new boolean[N][N];
		leaders.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				int one = 0;
				int two = 0;

				if (o1.taste == 1 || o1.taste == 2 || o1.taste == 4) {
					one = 1;
				} else if (o1.taste == 3 || o1.taste == 5 || o1.taste == 6) {
					one = 2;
				} else one = 3;

				if (o2.taste == 1 || o2.taste == 2 || o2.taste == 4) {
					two = 1;
				} else if (o2.taste == 3 || o2.taste == 5 || o2.taste == 6) {
					two = 2;
				} else two = 3;

				if (one == two) {
					if (o1.faith == o2.faith) {
						if (o1.row == o2.row) return Integer.compare(o1.col, o2.col);
						return Integer.compare(o1.row, o2.row);
					}
					return -Integer.compare(o1.faith, o2.faith);
				}
				return Integer.compare(one, two);
			}
		});
		for (int i = 0; i < leaders.size(); i++) {
			spread(leaders.get(i));
		}
	}

	public void spread(Student leader) {
		if (isSpread[leader.row][leader.col]) return;
		int x = leader.faith - 1;
		int direction = (leader.faith % 4);
		leader.faith = 1;

		int row = leader.row;
		int col = leader.col;
		int nextRow = row;
		int nextCol = col;

		while (true) {
			nextRow += deltas[direction][0];
			nextCol += deltas[direction][1];
			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) break;
			if (x == 0) break;

			if (map[nextRow][nextCol].taste != leader.taste) {
				int y = map[nextRow][nextCol].faith;
				isSpread[nextRow][nextCol] = true;
				if (x > y) {
					x -= (y + 1);
					map[nextRow][nextCol].faith++;
					map[nextRow][nextCol].taste = leader.taste;
					if (x == 0) break;
				} else {
					map[nextRow][nextCol].taste = (map[nextRow][nextCol].taste | leader.taste);
					map[nextRow][nextCol].faith += x;
					break;
				}
			}
		}
	}

	public void print() {
		int T = 0;
		int C = 0;
		int M = 0;
		int TC = 0;
		int TM = 0;
		int CM = 0;
		int TCM = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].taste == 1) T += map[i][j].faith;
				else if (map[i][j].taste == 2) C += map[i][j].faith;
				else if (map[i][j].taste == 4) M += map[i][j].faith;
				else if (map[i][j].taste == 3) TC += map[i][j].faith;
				else if (map[i][j].taste == 5) TM += map[i][j].faith;
				else if (map[i][j].taste == 6) CM += map[i][j].faith;
				else if (map[i][j].taste == 7) TCM += map[i][j].faith;
			}
		}

		builder.append(TCM).append(' ')
			.append(TC).append(' ')
			.append(TM).append(' ')
			.append(CM).append(' ')
			.append(M).append(' ')
			.append(C).append(' ')
			.append(T).append(' ').append('\n');
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		T = Integer.parseInt(tokenizer.nextToken());

		map = new Student[N][N];
		for (int i = 0; i < N; i++) {
			String input = reader.readLine();
			for (int j = 0; j < N; j++) {
				char type = input.charAt(j);
				int intType = -1;
				if (type == 'T') intType = 1;
				else if (type == 'C') intType = 2;
				else intType = 4;
				map[i][j] = new Student(i, j, intType);
			}
		}

		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j].faith = Integer.parseInt(tokenizer.nextToken());
			}
		}

	}


	public static void main(String[] args) throws IOException {
		new MintChocoMilk_2().solution();
	}
}
