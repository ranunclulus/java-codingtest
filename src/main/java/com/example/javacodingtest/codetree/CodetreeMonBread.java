package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.10
 @link https://www.codetree.ai/ko/frequent-problems/problems/codetree-mon-bread/description
 @timecomplex
 @performance
 @category
 @note
 */
public class CodetreeMonBread {
	class Person {
		boolean onBaseCamp;
		boolean onStore;
		int row;
		int col;
		int storeRow;
		int storeCol;

		Person(int storeRow, int storeCol) {
			this.onBaseCamp = false;
			this.onStore = false;
			this.row = -1;
			this.col = -1;
			this.storeRow = storeRow;
			this.storeCol = storeCol;
		}
	}

	class BaseCamp {
		int row;
		int col;
		boolean isUsed;

		BaseCamp(int row, int col) {
			this.row = row;
			this.col = col;
			this.isUsed = false;
		}
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, K, time, clearNum;
	static int[][] map;
	static Person[] people;
	static List<BaseCamp> baseCamps;
	static int[][] deltas = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

	public void solution() throws IOException{
		init();

		while(clearNum != M) {
			time++;
			for(Person person : people) {
				if (person.onStore) continue; // 이동 끝난 애면 패스
				if (!person.onBaseCamp) continue; // 베이스캠프 배치 안 받았으면 패스
				movePerson(person);
			}
			for(Person person : people) {
				if (person.onStore) continue; // 이동 끝난 애면 패스
				if (!person.onBaseCamp) continue; // 베이스캠프 배치 안 받았으면 패스
				placeStore(person);
			}
			if (time <= M) {
				placeBaseCamp(people[time - 1]);
			}
		}

		builder.append(time);
		writer.write(builder.toString());
		writer.flush();
	}

	public void movePerson(Person person) {
		int[][] distance = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}

		Deque<int[]> toVisit = new ArrayDeque<>();
		toVisit.offer(new int[]{person.storeRow, person.storeCol, 0});
		distance[person.storeRow][person.storeCol] = 0;

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (distance[nextRow][nextCol] != Integer.MAX_VALUE) continue;
				if (map[nextRow][nextCol] == 2) continue;

				toVisit.offer(new int[] {nextRow, nextCol, now[2] + 1});
				distance[nextRow][nextCol] = now[2] + 1;
			}
		}

		int minDistance = Integer.MAX_VALUE;
		int minRow = -1;
		int minCol = -1;

		for(int[] delta : deltas) {
			int nextRow = person.row + delta[0];
			int nextCol = person.col + delta[1];

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
			if (map[nextRow][nextCol] == 2) continue;

			if (distance[nextRow][nextCol] < minDistance) {
				minDistance = distance[nextRow][nextCol];
				minRow = nextRow;
				minCol = nextCol;
			}
		}
		person.row = minRow;
		person.col = minCol;
	}

	public void placeStore(Person person) {
		if (person.row == person.storeRow && person.col == person.storeCol) { // 편의점에 도착했다면
			person.onStore = true;
			map[person.row][person.col] = 2;
			clearNum++;
		}
	}

	public void placeBaseCamp(Person person) {
		int[][] distance = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}

		Deque<int[]> toVisit = new ArrayDeque<>();
		toVisit.offer(new int[]{person.storeRow, person.storeCol, 0});
		distance[person.storeRow][person.storeCol] = 0;

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (distance[nextRow][nextCol] != Integer.MAX_VALUE) continue;
				if (map[nextRow][nextCol] == 2) continue;

				toVisit.offer(new int[] {nextRow, nextCol, now[2] + 1});
				distance[nextRow][nextCol] = now[2] + 1;
			}
		}

		int minIndex = -1;
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < K; i++) {
			if (baseCamps.get(i).isUsed) continue;

			if (distance[baseCamps.get(i).row][baseCamps.get(i).col] < minDistance) {
				minIndex = i;
				minDistance = distance[baseCamps.get(i).row][baseCamps.get(i).col];
			}
		}
		BaseCamp target = baseCamps.get(minIndex);
		target.isUsed = true;
		person.row = target.row;
		person.col = target.col;
		person.onBaseCamp = true;
		map[person.row][person.col] = 2;
	}

	public void init() throws IOException{
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		baseCamps = new ArrayList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				if (map[i][j] == 1) {
					baseCamps.add(new BaseCamp(i, j));
				}
			}
		}

		people = new Person[M];
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			people[i] = new Person(Integer.parseInt(tokenizer.nextToken()) - 1, Integer.parseInt(tokenizer.nextToken()) - 1);
		}

		K = baseCamps.size();
	}

	public static void main(String[] args) throws IOException {
		new CodetreeMonBread().solution();
	}
}
