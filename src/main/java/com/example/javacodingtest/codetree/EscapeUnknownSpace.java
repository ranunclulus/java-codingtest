package com.example.javacodingtest.codetree;


import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.04.07
 @link https://www.codetree.ai/ko/frequent-problems/problems/escape-unknown-space/solutions?introductionSetId=&bookmarkId=
 @timecomplex
 @performance
 @category
 @note
 */
public class EscapeUnknownSpace {
	class Hurdle {
		int row;
		int col;
		int direction;
		int v;

		Hurdle(int row, int col, int direction, int v) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.v = v;
		}
	}

	class Node {
		int direction;
		int row;
		int col;

		Node(int direction, int row, int col) {
			this.direction = direction;
			this.row = row;
			this.col = col;
		}
	}


	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, F;
	static int[][] map;
	static int[][][] timeWall;
	static Hurdle[] hurdles;
	static Node threeStart, threeEnd, twoStart, twoEnd;
	static int[][] deltas = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동서남북
	static int answer;

	public void solution() throws IOException {
		init();
		find();
		if (threeEnd == null) {
			answer = -1;
		} else threeBFS();
		if (answer != -1) {
			spreadHurdles();
			twoBFS();
		}
		builder.append(answer == Integer.MAX_VALUE ? -1 : answer);
		writer.write(builder.toString());
		writer.flush();
	}

	public void find() {
		findThreeStart();
		findThreeEndAndTwoStart();
		findTwoEnd();
	}

	public void findThreeStart() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if (timeWall[4][i][j] == 2) {
					threeStart = new Node(4, i, j);
					return;
				}
			}
		}
	}

	public void findThreeEndAndTwoStart() {
		int baseRow = -1;
		int baseCol = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 3) {
					baseRow = i;
					baseCol = j;
					break;
				}
			}
			if (baseRow != -1) break;
		}

		for (int i = baseRow; i < baseRow + M; i++) {
			for (int j = baseCol; j < baseCol + M; j++) {
				if (map[i][j] != 3) continue;

				if (map[i][j + 1] == 0) { // 동쪽에 출구
					twoStart = new Node(5, i, j + 1);
					threeEnd = new Node(0, M - 1, (M - 1) - (i - baseRow));
					return;
				} else if (map[i][j - 1] == 0) { // 서쪽에 출구
					twoStart = new Node(5, i, j - 1);
					threeEnd = new Node(1, M - 1, i - baseRow);
					return;
				} else if (map[i + 1][j] == 0) { // 남쪽에 출구
					twoStart = new Node(5, i + 1, j);
					threeEnd = new Node(2, M - 1, j - baseCol);
					return;
				} else if (map[i - 1][j] == 0) { // 북쪽에 출구
					twoStart = new Node(5, i - 1, j);
					threeEnd = new Node(3, M - 1, (M - 1) - (j - baseCol));
					return;
				}
			}
		}
	}

	public void findTwoEnd() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 4) {
					twoEnd = new Node(5, i, j);
					return;
				}
			}
		}
	}

	public void threeBFS() {
		Deque<Node> toVisit = new ArrayDeque<>();
		int[][][] distance = new int[5][M][M];
		toVisit.offer(threeStart);
		distance[threeStart.direction][threeStart.row][threeStart.col] = 1;

		while (!toVisit.isEmpty()) {
			Node now = toVisit.poll();

			if (now.direction == threeEnd.direction && now.row == threeEnd.row && now.col == threeEnd.col) {
				answer = distance[threeEnd.direction][threeEnd.row][threeEnd.col];
				return;
			}

			for(int[] dleta : deltas) {
				int nextDirection = now.direction;
				int nextRow = now.row + dleta[0];
				int nextCol = now.col + dleta[1];

				if (nextRow < 0) { // 위쪽으로 이동
					if (now.direction == 0) {
						nextDirection = 4;
						nextRow = (M - 1) - now.col;
						nextCol = M - 1;
					} else if (now.direction == 1) {
						nextDirection = 4;
						nextRow = now.col;
						nextCol = 0;
					} else if (now.direction == 2) {
						nextDirection = 4;
						nextRow = M - 1;
						nextCol = now.col;
					} else if (now.direction == 3) {
						nextDirection = 4;
						nextRow = 0;
						nextCol = (M - 1) - now.col;
					} else if (now.direction == 4) {
						nextDirection = 3;
						nextRow = 0;
						nextCol = (M - 1) - now.col;
					}
				} else if (nextRow >= M) { // 아래쪽으로 이동
					if (now.direction == 4) {
						nextDirection = 2;
						nextRow = 0;
						nextCol = now.col;
					} else continue;
				} else if (nextCol < 0) { // 왼쪽으로 이동
					if (now.direction == 0) {
						nextDirection = 2;
						nextRow = now.row;
						nextCol = M - 1;
					} else if (now.direction == 1) {
						nextDirection = 3;
						nextRow = now.row;
						nextCol = M - 1;
					} else if (now.direction == 2) {
						nextDirection = 1;
						nextRow = now.row;
						nextCol = M - 1;
					} else if (now.direction == 3) {
						nextDirection = 0;
						nextRow = now.row;
						nextCol = M - 1;
					} else if (now.direction == 4) {
						nextDirection = 1;
						nextRow = 0;
						nextCol = now.row;
					}
				} else if (nextCol >= M) { // 오른쪽으로 이동
					if (now.direction == 0) {
						nextDirection = 3;
						nextRow = now.row;
						nextCol = 0;
					} else if (now.direction == 1) {
						nextDirection = 2;
						nextRow = now.row;
						nextCol = 0;
					} else if (now.direction == 2) {
						nextDirection = 0;
						nextRow = now.row;
						nextCol = 0;
					} else if (now.direction == 3) {
						nextDirection = 1;
						nextRow = now.row;
						nextCol = 0;
					} else if (now.direction == 4) {
						nextDirection = 0;
						nextRow = 0;
						nextCol = (M - 1) - now.row;
					}
				}

				if (distance[nextDirection][nextRow][nextCol] != 0) continue;
				if (timeWall[nextDirection][nextRow][nextCol] != 0) continue;

				toVisit.offer(new Node(nextDirection, nextRow, nextCol));
				distance[nextDirection][nextRow][nextCol] = distance[now.direction][now.row][now.col] + 1;
			}
		}
	}

	public void spreadHurdles() {
		for (Hurdle hurdle : hurdles) {
			int row = hurdle.row + deltas[hurdle.direction][0];
			int col = hurdle.col + deltas[hurdle.direction][1];
			int turn = 0;

			while (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] != 0) break;
				turn++;
				map[row][col] = hurdle.v * turn;
				row += deltas[hurdle.direction][0];
				col += deltas[hurdle.direction][1];
			}
		}
	}

	public void twoBFS() {
		Deque<int[]> toVisit = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		toVisit.add(new int[] {twoStart.row, twoStart.col, answer});
		visited[twoStart.row][twoStart.col] = true;

		answer = Integer.MAX_VALUE;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			if (now[0] == twoEnd.row && now[1] == twoEnd.col) {
				answer = Math.min(answer, now[2]);
			}

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (visited[nextRow][nextCol]) continue;
				if (map[nextRow][nextCol] == 1) continue;
				if (map[nextRow][nextCol] == 3) continue;
				if (map[nextRow][nextCol] != 0 && map[nextRow][nextCol] != 4 && map[nextRow][nextCol] <= now[2] + 1) continue;

				toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
				visited[nextRow][nextCol] = true;
			}
		}

	}

	public void init() throws IOException {
		answer = -1;
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		F = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		timeWall = new int[5][M][M]; //동서남북상
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < M; j++) {
				tokenizer = new StringTokenizer(reader.readLine());
				for (int k = 0; k < M; k++) {
					timeWall[i][j][k] = Integer.parseInt(tokenizer.nextToken());
				}
			}
		}

		hurdles = new Hurdle[F];
		for (int i = 0; i < F; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			hurdles[i] = new Hurdle(Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()));

			map[hurdles[i].row][hurdles[i].col] = 1;
		}
	}

	public static void main(String[] args) throws IOException {
		new EscapeUnknownSpace().solution();
	}
}


/*
8 3 2
4 0 0 0 0 0 0 0
0 1 1 1 1 1 0 0
0 1 3 3 3 1 0 1
0 1 3 3 3 1 0 1
0 1 3 3 3 0 0 0
0 1 1 1 1 1 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 1
1 1 1
0 0 0
0 1 1
1 1 1
1 0 1
1 1 1
0 0 1
1 0 0
1 0 1
0 0 0
1 0 0
1 1 1
2 0 0
0 1 0
0 0 0
0 7 1 14
6 3 3 2

28
 */
