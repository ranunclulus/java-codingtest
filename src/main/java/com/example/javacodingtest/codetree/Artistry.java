package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Artistry {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N;
	static int[][] map;
	static int[][] newMap;
	static int score;
	static List<Group> groups;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	class Group {
		int num;
		int count;
		List<int[]> points;

		Group(int num, int count) {
			this.num = num;
			this.count = count;
			this.points = new ArrayList<int[]>();
		}
	}

	public void solution() throws IOException {
		init();
		for(int i = 0; i <= 3; i++) {
			makeGroups();
			calculateScore();
			resetMap();
		}
		builder.append(score);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void makeGroups() {
		boolean[][] visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (visited[i][j]) continue;

				int num = map[i][j];
				int count = 0;
				Group group = new Group(num, count);

				Deque<int[]> toVisit = new ArrayDeque<int[]>();
				toVisit.add(new int[] {i, j});
				visited[i][j] = true;


				while(!toVisit.isEmpty()) {
					int[] now = toVisit.poll();
					count++;
					group.points.add(now);

					for(int[] delta : deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];

						if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
						if (visited[nextRow][nextCol]) continue;
						if (map[nextRow][nextCol] != num) continue;

						toVisit.add(new int[] {nextRow, nextCol});
						visited[nextRow][nextCol] = true;

					}
				}

				group.count = count;
				groups.add(group);
			}
		}
	}

	public void calculateScore() {
		for(int i = 0; i < groups.size(); i++) {
			for(int j = i + 1; j < groups.size(); j++) {
				score += getScore(groups.get(i), groups.get(j));
			}
		}
	}

	public int getScore(Group a, Group b) {
		int score = 0;
		int adj = 0;

		for(int i = 0; i < a.count; i++) {
			int[] aPoint = a.points.get(i);
			for(int j = 0; j < b.count; j++) {
				int[] bPoint = b.points.get(j);

				int diff = Math.abs(aPoint[0] - bPoint[0]) + Math.abs(aPoint[1] - bPoint[1]);
				if (diff == 1) adj++;
			}
		}
		score = (a.count + b.count) * a.num * b.num * adj;
		return score;
	}

	public void resetMap() {
		newMap = new int[N][N];
		rotateRight(0, 0, N / 2 - 1, N / 2 - 1);
		rotateRight(N / 2 + 1, 0, N - 1, N / 2 - 1);
		rotateRight(0, N / 2 + 1, N / 2 - 1, N - 1);
		rotateRight(N / 2 + 1, N / 2 + 1, N - 1, N - 1);
		rotateLeft();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = newMap[i][j];
			}
		}

		groups.clear();
	}

	public void rotateRight(int startRow, int startCol, int endRow, int endCol) {
		for(int i = startRow; i <= endRow; i++) {
			for(int j = startCol; j <= endCol; j++) {
				newMap[startRow + (j - startCol)][endCol - (i - startRow)] = map[i][j];
			}
		}
	}

	public void rotateLeft() {
		for(int i = N / 2; i >= 0; i--) {
			newMap[i][N / 2] = map[N / 2][N / 2 + (N / 2 - i)];
		}

		for(int i = N / 2; i < N; i++) {
			newMap[N / 2][i] = map[i][N / 2];
		}

		for(int i = N / 2; i < N; i++) {
			newMap[i][N / 2] = map[N / 2][N / 2 + (N / 2 - i)];
		}

		for(int i = N / 2; i >= 0; i--) {
			newMap[N / 2][i] = map[i][N / 2];
		}
	}

	public void init() throws IOException {
		N = Integer.parseInt(reader.readLine());
		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		groups = new ArrayList<Group>();
	}


	public static void main(String[] args) throws IOException {
		new Artistry().solution();
	}

}