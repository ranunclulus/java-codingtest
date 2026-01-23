package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2026.01.22
 @link https://www.codetree.ai/ko/frequent-problems/problems/codetree-mon-bread/description
 @timecomplex
 @performance
 @category
 @note
 */
public class CodetreeMonBread_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M;
	static int[][] map;
	static boolean[][] canMove;
	static Shop[] shops;
	static Person[] people;
	static int time = 1;
	static int[][] deltas = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

	class Shop {
		int row;
		int col;

		Shop(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	class Person {
		int row;
		int col;

		Person(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}
	class BaseCamp implements Comparable<BaseCamp> {
		int row;
		int col;
		int distance;

		BaseCamp(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}

		@Override
		public int compareTo(BaseCamp o) {
			if (this.distance == o.distance) {
				if (this.row == o.row) return Integer.compare(this.col, o.col);
				return Integer.compare(this.row, o.row);
			}
			return Integer.compare(this.distance, o.distance);
		}
	}

	class Point {
		int row;
		int col;
		int distance;

		Point(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
	}

	public void solution() throws IOException {
		init();
		while(true) {
			movePeople();
			checkRoad();
			intoBaseCamp();
			if (isFinished()) break;
			time++;
		}
		builder.append(time);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}


	public void movePeople() {
		for(int i = 0; i < M; i++) {
			Shop shop = shops[i];
			Person person = people[i];

			if (person.row == -1 && person.col == -1) continue;
			if (person.row == shop.row && person.col == shop.col) continue;

			int[] nextMove = getRoot(shop, person);
			person.row = nextMove[0];
			person.col = nextMove[1];
		}
	}

	public int[] getRoot(Shop shop, Person person) {
		Point[][] rootMap = new Point[N][N];
		List<int[]> root = new ArrayList<>();
		Deque<int[]> toVisit = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				rootMap[i][j] = new Point(i, j, Integer.MAX_VALUE);
			}
		}


		toVisit.add(new int[] {person.row, person.col, 0});
		rootMap[person.row][person.col] = new Point(-1, -1, 0);

		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			if (now[0] == shop.row && now[1] == shop.col) {
				int iRow = shop.row;
				int iCol = shop.col;
				root.add(new int[] {iRow, iCol});
				while(true) {
					Point point = rootMap[iRow][iCol];
					if (point.row == person.row && point.col == person.col) break;

					root.add(new int[] {point.row, point.col});
					iRow = point.row;
					iCol = point.col;
				}

			}

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];
				int nextDistance = now[2] + 1;

				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if(!canMove[nextRow][nextCol]) continue;
				if(rootMap[nextRow][nextCol].distance <= nextDistance) continue;

				rootMap[nextRow][nextCol] = new Point(now[0], now[1], nextDistance);
				toVisit.add(new int[] {nextRow, nextCol, nextDistance});
			}
		}

		return root.get(root.size() - 1);
	}

	public void checkRoad() {
		for(int i = 0; i < M; i++) {
			Shop shop = shops[i];
			Person person = people[i];
			if (person.row == shop.row && person.col == shop.col) {
				canMove[person.row][person.col] = false;
			}
		}
	}

	public void intoBaseCamp() {
		if (time - 1 >= M) return;
		Shop shop = shops[time - 1];
		Person person = people[time - 1];

		PriorityQueue<BaseCamp> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				if (!canMove[i][j]) continue;

				Deque<int[]> toVisit = new ArrayDeque<>();
				int[][] distances = new int[N][N];
				for(int p = 0; p < N; p++) {
					for(int q = 0; q < N; q++) {
						distances[p][q] = Integer.MAX_VALUE;
					}
				}

				toVisit.add(new int[] {i, j, 0});
				distances[i][j] = 0;
				int distance = Integer.MAX_VALUE;

				while((!toVisit.isEmpty())) {
					int[] now = toVisit.poll();
					if (now[0] == shop.row && now[1] == shop.col) {
						distance = distances[shop.row][shop.col];
						break;
					}

					for(int[] delta : deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];
						int nextDistance = now[2] + 1;

						if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
						if(!canMove[nextRow][nextCol]) continue;
						if(distances[nextRow][nextCol] <= nextDistance) continue;

						toVisit.add(new int[] {nextRow, nextCol, nextDistance});
						distances[nextRow][nextCol] = nextDistance;
					}
				}

				pq.add(new BaseCamp(i, j, distance));
			}
		}

		BaseCamp baseCamp = pq.poll();
		person.row = baseCamp.row;
		person.col = baseCamp.col;
		canMove[baseCamp.row][baseCamp.col] = false;

	}

	public boolean isFinished() {
		for(int i = 0; i < M; i++) {
			Shop shop = shops[i];
			Person person = people[i];

			if (shop.row != person.row || shop.col != person.col) return false;
		}
		return true;
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		canMove = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				canMove[i][j] = true;
			}
		}

		shops = new Shop[M];
		people = new Person[M];
		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;
			shops[i] = new Shop(row, col);
			people[i] = new Person(-1, -1);
		}
	}

	public static void main(String[] args) throws IOException {
		new CodetreeMonBread_2().solution();
	}
}