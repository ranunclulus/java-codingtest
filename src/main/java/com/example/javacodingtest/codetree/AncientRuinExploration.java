package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

/*
 @author ranuinclulus
 @since 2025.04.05
 @link https://www.codetree.ai/ko/frequent-problems/problems/ancient-ruin-exploration/description?introductionSetId=&bookmarkId=
 @timecomplex
 @performance
 @category
 @note
 */
public class AncientRuinExploration {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer tokenizer;
	StringBuilder builder = new StringBuilder();

	class Exploration implements Comparable<Exploration> {
		int row;
		int col;
		int angle;
		int[][] explorationMap;
		int value;
		List<List<int[]>> ancientPieces;

		Exploration(int row, int col, int angle) {
			this.row = row;
			this.col = col;
			this.angle = angle;
			this.explorationMap = new int[5][5];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					this.explorationMap[i][j] = map[i][j];
				}
			}
			this.value = 0;
			this.ancientPieces = new ArrayList<>();
		}

		@Override
		public int compareTo(Exploration o) {
			if (this.value == o.value) {
				if (this.angle == o.angle) {
					if (this.col == o.col) {
						return Integer.compare(this.row, o.row); // 4 회전 중심 행 최소
					}
					return Integer.compare(this.col, o.col); // 3 회전 중심 열 최소
				}
				return Integer.compare(this.angle, o.angle); // 2 회전 각도 최소
			}
			return -Integer.compare(this.value, o.value); // 1 유물의 1차 가치 최대
		}

		public void rotate() {
			for (int a = 0; a < this.angle; a++) {
				int[][] temp = new int[5][5];
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						temp[i][j] = this.explorationMap[i][j];
					}
				}

				temp[this.row - 1][this.col - 1] = this.explorationMap[this.row + 1][this.col -1];
				temp[this.row - 1][this.col] = this.explorationMap[this.row][this.col - 1];
				temp[this.row - 1][this.col + 1] = this.explorationMap[this.row - 1][this.col - 1];

				temp[this.row][this.col - 1] = this.explorationMap[this.row + 1][this.col];
				temp[this.row][this.col + 1] = this.explorationMap[this.row - 1][this.col];

				temp[this.row + 1][this.col - 1] = this.explorationMap[this.row + 1][this.col +1];
				temp[this.row + 1][this.col] = this.explorationMap[this.row][this.col + 1];
				temp[this.row + 1][this.col + 1] = this.explorationMap[this.row - 1][this.col + 1];

				this.explorationMap = temp;
			}
		}

		public void updateValue() {
			this.ancientPieces = new ArrayList<>();
			this.value = 0;
			boolean[][] visited = new boolean[5][5];

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (visited[i][j]) continue;
					int value = this.explorationMap[i][j];
					List<int[]> points = new ArrayList<>();

					Deque<int[]> toVisit = new ArrayDeque<>();
					toVisit.add(new int[] {i, j});
					points.add(new int[] {i, j});
					visited[i][j] = true;

					while (!toVisit.isEmpty()) {
						int[] now = toVisit.poll();

						for(int[] delta : deltas) {
							int nextRow = now[0] + delta[0];
							int nextCol = now[1] + delta[1];

							if (nextRow < 0 || nextRow >= 5 || nextCol < 0 || nextCol >= 5) continue;
							if (visited[nextRow][nextCol]) continue;
							if (this.explorationMap[nextRow][nextCol] != value) continue;

							toVisit.add(new int[] {nextRow, nextCol});
							visited[nextRow][nextCol] = true;
							points.add(new int[] {nextRow, nextCol});
						}
					}

					if (points.size() >= 3) {
						this.value += points.size();
						this.ancientPieces.add(points);
					}
				}
			}
		}

		public void refreshMap() {
			List<int[]> targets = new ArrayList<>();
			for(List<int[]> points : this.ancientPieces) {
				targets.addAll(points);
			}
			pieceCount += targets.size();

			targets.sort(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1]) {
						return -Integer.compare(o1[0], o2[0]);
					}
					return Integer.compare(o1[1], o2[1]);
				}
			});

			for(int[] target : targets) {
				if (wallIndex >= M) this.explorationMap[target[0]][target[1]] = 0;
				else this.explorationMap[target[0]][target[1]] = wall[wallIndex++];
			}
		}
	}

	static int K, M, wallIndex, pieceCount;
	static int[][] map = new int[5][5];
	static int[] wall;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public void solution() throws IOException {
		init();

		while (K --> 0) {
			pieceCount = 0;

			PriorityQueue<Exploration> explorations = new PriorityQueue<>();
			for(int i = 1; i <= 3; i++) {
				for(int j = 1; j <= 3; j++) {
					for (int d = 1; d <= 3; d++) {
						Exploration exploration = new Exploration(i, j, d);
						exploration.rotate();
						exploration.updateValue();
						explorations.add(exploration);
					}
				}
			}
			Exploration target = explorations.poll();

			while (target.value != 0) {
				target.refreshMap();
				target.updateValue();
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					map[i][j] = target.explorationMap[i][j];
				}
			}

			if (pieceCount == 0) break;
			builder.append(pieceCount).append(' ');
		}

		writer.write(builder.toString());
		writer.flush();
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		K = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		for (int i = 0; i < 5; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		wall = new int[M];
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < M; i++) {
			wall[i] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	public static void main(String[] args) throws IOException {
		new AncientRuinExploration().solution();
	}
}
