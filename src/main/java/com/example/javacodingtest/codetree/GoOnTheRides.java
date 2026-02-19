package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GoOnTheRides {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, score;
	static Student[] students;
	static int[][] map;
	static int[][] deltas = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

	class Student {
		int num;
		boolean[] likes;
		int row;
		int col;

		Student(int num) {
			this.num = num;
			this.likes = new boolean[N * N + 1];
		}
	}

	class Node implements Comparable<Node> {
		int like;
		int empty;
		int row;
		int col;

		Node(int like, int empty, int row, int col) {
			this.like = like;
			this.empty = empty;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Node o) {
			if (this.like == o.like) {
				if (this.empty == o.empty) {
					if (this.row == o.row) return Integer.compare(this.col, o.col);
					return Integer.compare(this.row, o.row);
				}
				return -Integer.compare(this.empty, o.empty);
			}
			return -Integer.compare(this.like, o.like);
		}
	}

	public void solution() throws IOException {
		init();
		for(int i = 0; i < N * N; i++) {
			placeStudents(students[i]);
		}
		for(int i = 0; i < N * N; i++) {
			getScore(students[i]);
		}
		builder.append(score);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void placeStudents(Student student) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] != 0) continue;
				int like = 0;
				int empty = 0;
				int row = i;
				int col = j;

				for(int[] delta : deltas) {
					int nextRow = row + delta[0];
					int nextCol = col + delta[1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;

					if (map[nextRow][nextCol] == 0) empty++;
					if (student.likes[map[nextRow][nextCol]]) like++;
				}
				pq.add(new Node(like, empty, row, col));
			}
		}

		Node node = pq.poll();
		map[node.row][node.col] = student.num;
		student.row = node.row;
		student.col = node.col;
	}

	public void getScore(Student student) {
		int like = 0;
		for(int[] delta : deltas) {
			int nextRow = student.row + delta[0];
			int nextCol = student.col + delta[1];

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;

			if (student.likes[map[nextRow][nextCol]]) like++;
		}
		if (like == 1) score += 1;
		else if (like == 2) score += 10;
		else if (like == 3) score += 100;
		else if (like == 4) score += 1000;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		map = new int[N][N];

		students = new Student[N * N];
		for(int i = 0; i < N * N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int n0 = Integer.parseInt(tokenizer.nextToken());
			int n1 = Integer.parseInt(tokenizer.nextToken());
			int n2 = Integer.parseInt(tokenizer.nextToken());
			int n3 = Integer.parseInt(tokenizer.nextToken());
			int n4 = Integer.parseInt(tokenizer.nextToken());

			students[i] = new Student(n0);
			students[i].likes[n1] = true;
			students[i].likes[n2] = true;
			students[i].likes[n3] = true;
			students[i].likes[n4] = true;
		}

	}


	public static void main(String[] args) throws IOException {
		new GoOnTheRides().solution();
	}

}
