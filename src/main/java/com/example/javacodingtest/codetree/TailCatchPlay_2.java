package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranunclulus
 @since 2026.01.29
 @link https://www.codetree.ai/ko/frequent-problems/problems/tail-catch-play/description
 @timecomplex
 @performance
 @category
 @note
 */
public class TailCatchPlay_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, K;
	static int[][] map;
	static List<Point>[] teams;
	static int[][] deltas = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static Point[] throwNodes;
	static int[] scores;

	class Point {
		int row;
		int col;
		int type;
		Point(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}

	public void solution() throws IOException {
		init();
		for(int i = 0; i < K; i++) {
			movePeople();
			throwBall(i % (4 * N));
		}

		builder.append(getScore());
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public int getScore() {
		int score = 0;

		for(int s : scores) {
			score += s;
		}

		return score;
	}

	public void throwBall(int throwIndex) {
		Point throwNode = throwNodes[throwIndex];

		int row = throwNode.row;
		int col = throwNode.col;
		boolean meetPerson = false;

		for(int i = 0; i < N; i++) {
			if (map[row][col] == 1 || map[row][col] == 2 || map[row][col] == 3) {
				meetPerson = true;
				break;
			}

			row += deltas[throwNode.type][0];
			col += deltas[throwNode.type][1];
		}

		if (!meetPerson) return;

		int teamIndex = -1;
		int k = -1;

		Point originalHead = null;
		Point originalTail = null;
		for(int i = 0; i < M; i++) {
			List<Point> team = teams[i];
			for (int j = 0; j < team.size(); j++) {
				Point point = team.get(j);

				if (point.row == row && point.col == col) {
					teamIndex = i;
					k = j + 1;
				}
				if (point.type == 1)
					originalHead = point;
				if (point.type == 3)
					originalTail = point;
			}
			if (teamIndex != -1)
				break;
		}
		scores[teamIndex] += (k * k);
		map[originalHead.row][originalHead.col] = 3;
		map[originalTail.row][originalTail.col] = 1;

		teams[teamIndex].clear();
		makeTeam(originalTail.row, originalTail.col, teamIndex, new boolean[N][N]);

	}

	public void movePeople() {
		for(int i = 0; i < M; i++) {
			List<Point> team = teams[i];
			int size = team.size() - 1;
			Point newHead = team.get(size);

			List<Point> movedTeam = new ArrayList<>();
			for(int j = size; j >= 0; j--) {
				Point now = team.get(j);
				Point next = team.get((j - 1) < 0 ? size : j - 1);

				movedTeam.add(new Point(next.row, next.col, now.type));
			}

			for(int j = 0; j < movedTeam.size(); j++) {
				Point point = movedTeam.get(j);
				map[point.row][point.col] = point.type;
			}
			team.clear();
			makeTeam(newHead.row, newHead.col, i, new boolean[N][N]);
		}
	}

	public void makeTeam(int row, int col, int teamIndex, boolean[][] visited) {
		Deque<int[]> toVisit = new ArrayDeque<int[]>();
		toVisit.add(new int[] {row, col});
		visited[row][col] = true;

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			teams[teamIndex].add(new Point(now[0], now[1], map[now[0]][now[1]]));

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (visited[nextRow][nextCol]) continue;
				if (map[nextRow][nextCol] == 0) continue;
				if (map[nextRow][nextCol] < map[now[0]][now[1]]) continue;
				if ((map[nextRow][nextCol] - map[now[0]][now[1]]) > 1) continue;

				toVisit.add(new int[] {nextRow, nextCol});
				visited[nextRow][nextCol] = true;
			}
		}
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		scores = new int[M];
		teams = new List[M];
		for(int i = 0; i < M; i++) {
			teams[i] = new ArrayList<>();
		}

		int teamIndex = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] != 1) continue;
				makeTeam(i, j, teamIndex, new boolean[N][N]);
				teamIndex++;
			}
		}

		throwNodes = new Point[N * 4];
		int throwIndex = 0;
		for(int i = 0; i < N; i++) {
			throwNodes[throwIndex] = new Point(i, 0, 0);
			throwIndex++;
		}
		for(int i = 0; i < N; i++) {
			throwNodes[throwIndex] = new Point(N - 1, i, 1);
			throwIndex++;
		}
		for(int i = N - 1; i >= 0; i--) {
			throwNodes[throwIndex] = new Point(i, N - 1, 2);
			throwIndex++;
		}
		for(int i = N - 1; i >= 0; i--) {
			throwNodes[throwIndex] = new Point(0, i, 3);
			throwIndex++;
		}

	}

	public static void main(String[] args) throws IOException {
		new TailCatchPlay_2().solution();
	}
}
