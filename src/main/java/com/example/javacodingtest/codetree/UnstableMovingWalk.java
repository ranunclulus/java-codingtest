package com.example.javacodingtest.codetree;


import java.io.*;
import java.util.*;

public class UnstableMovingWalk {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, K, T;
	static List<Node> movingWalk;
	static int startIndex;

	class Node {
		int stability;
		boolean isFull;

		Node(int stability) {
			this.stability = stability;
			this.isFull = false;
		}
	}


	public void solution() throws IOException {
		init();
		while(true) {
			T++;
			moveNode();
			movePeople();
			addPerson();
			if (isEnd()) break;
		}

		builder.append(T);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}


	public void moveNode() {
		movingWalk.get((startIndex - 1 + N) % (2 * N)).isFull = false;
		if (startIndex == 0) startIndex = 2 * N - 1;
		else startIndex--;
	}

	public void movePeople() {
		movingWalk.get((startIndex - 1 + N) % (2 * N)).isFull = false;
		for(int i = 0; i < 2 * N; i++) {
			int now = (startIndex - i + 2 * N) % (2 * N);
			int next = (startIndex - i + 1 + 2 * N) % (2 * N);

			if (!movingWalk.get(now).isFull) continue;
			if (movingWalk.get(next).isFull) continue;
			if (movingWalk.get(next).stability == 0) continue;


			movingWalk.get(now).isFull = false;
			movingWalk.get(next).isFull = true;
			movingWalk.get(next).stability--;
		}
	}

	public boolean isEnd() {
		int zeroCount = 0;
		for(int i = 0; i < 2 * N; i++) {
			if (movingWalk.get(i).stability == 0) zeroCount++;
		}

		return (zeroCount >= K);
	}

	public void addPerson() {
		if (!movingWalk.get(startIndex).isFull && movingWalk.get(startIndex).stability != 0) {
			movingWalk.get(startIndex).isFull = true;
			movingWalk.get(startIndex).stability--;
		}
		movingWalk.get((startIndex - 1 + N) % (2 * N)).isFull = false;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		movingWalk = new ArrayList<>();
		tokenizer = new StringTokenizer(reader.readLine());
		for(int i = 0; i < 2 * N; i++) {
			movingWalk.add(new Node(Integer.parseInt(tokenizer.nextToken())));
		}

	}


	public static void main(String[] args) throws IOException {
		new UnstableMovingWalk().solution();
	}

}