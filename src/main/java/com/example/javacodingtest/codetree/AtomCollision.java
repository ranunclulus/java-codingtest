package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

public class AtomCollision {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M, K;
	static int[][] deltas = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static List<Atom>[][] atoms;
	
	class Atom {
		int mass;
		int speed;
		int direction;
		
		Atom(int mass, int speed, int direction) {
			this.mass = mass;
			this.speed = speed;
			this.direction = direction;
		}
	}

	public void solution() throws IOException {
		init();
		while(K --> 0) {
			moveAtom();
			collision();
		}
		builder.append(getMassSum());
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void moveAtom() {
		List[][] newMap = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = new ArrayList<Atom>();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (atoms[i][j].size() == 0) continue;
				for (int k = 0; k < atoms[i][j].size(); k++) {
					Atom atom = atoms[i][j].get(k);
					int nextRow = (i + deltas[atom.direction][0] * atom.speed + 1000 * N) % N;
					int nextCol = (j + deltas[atom.direction][1] * atom.speed + 1000 * N) % N;
					newMap[nextRow][nextCol].add(atom);
				}
				atoms[i][j].clear();
			}
		}
		atoms = newMap;
	}

	public void collision() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (atoms[i][j].size() <= 1) continue;

				int sumMass = 0;
				int sumSpeed = 0;
				boolean[] directionType = new boolean[8];

				for (int k = 0; k < atoms[i][j].size(); k++) {
					sumMass += atoms[i][j].get(k).mass;
					sumSpeed += atoms[i][j].get(k).speed;
					directionType[atoms[i][j].get(k).direction] = true;
				}
				sumMass /= 5;
				sumSpeed /= atoms[i][j].size();
				atoms[i][j].clear();

				if (sumMass == 0) continue;

				boolean isLine = false;
				if (!directionType[1] && !directionType[3] && !directionType[5] && !directionType[7]) {
					isLine = true;
				}
				if (!directionType[0] && !directionType[2] && !directionType[4] && !directionType[6]) {
					isLine = true;
				}

				if (isLine) {
					for(int k : new int[] {0, 2, 4, 6}) {
						atoms[i][j].add(new Atom(sumMass, sumSpeed, k));
					}
				} else {
					for(int k : new int[] {1, 3, 5, 7}) {
						atoms[i][j].add(new Atom(sumMass, sumSpeed, k));
					}
				}
			}
		}
	}

	public int getMassSum() {
		int sumMass = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < atoms[i][j].size(); k++) {
					sumMass += atoms[i][j].get(k).mass;
				}
			}
		}
		return sumMass;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		atoms = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				atoms[i][j] = new ArrayList<Atom>();
			}
		}

		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;
			int mass = Integer.parseInt(tokenizer.nextToken());
			int speed = Integer.parseInt(tokenizer.nextToken());
			int direction = Integer.parseInt(tokenizer.nextToken());
			atoms[row][col].add(new Atom(mass, speed, direction));
		}
	}

	public static void main(String[] args) throws IOException {
		new AtomCollision().solution();
	}


}
