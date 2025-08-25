package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

public class QueenAnt {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static final int MAX_COMMAND_COUNT = 20000;
	static final int MAX_ANTHILL_COUNT = 10000;
	static int commandCount, antHillCount;
	static ArrayList<Boolean> antHillDeleted = new ArrayList<>();
	static ArrayList<Integer> antHillPosition = new ArrayList<>();

	public void initialVillageConstruction() {
		int antHillCount = Integer.parseInt(tokenizer.nextToken());

		for(int i = 1; i <= antHillCount; i++) {
			int position = Integer.parseInt(tokenizer.nextToken());
			antHillPosition.add(position);
			antHillDeleted.add(false);
		}
	}

	public void addNewAntHill() {
		int newAntHillPosition = Integer.parseInt(tokenizer.nextToken());
		antHillPosition.add(newAntHillPosition);
		antHillDeleted.add(false);
	}

	public void removeAntHill() {
		int antHillNumber = Integer.parseInt(tokenizer.nextToken());
		antHillDeleted.set(antHillNumber, true);
	}

	public void processScoutQuery() {
		int numAnts = Integer.parseInt(tokenizer.nextToken());

		int lowerBound = 0;
		int upperBound = 1000000000;

		int minimumTime = 0;

		while (lowerBound <= upperBound) {
			int midTime = (lowerBound + upperBound) / 2;
			int intervalNeeded = 0; // midTime 내에 하나의 개미가 커버할 수 있는 영역
			int lastCoveredPosition = -1000000000; // 직전에 커버된 마지막 개미집의 위치


			for(int i = 1; i < antHillPosition.size(); i++) {
				if (antHillDeleted.get(i)) continue;

				int currentAntHillPosition = antHillPosition.get(i);

				if (currentAntHillPosition - lastCoveredPosition > midTime) {
					lastCoveredPosition = currentAntHillPosition;
					intervalNeeded++;
				}
			}

			if (intervalNeeded <= numAnts) {
				minimumTime = midTime;
				upperBound = midTime - 1;
			} else {
				lowerBound = midTime + 1;
			}
		}
		builder.append(minimumTime).append('\n');
	}

	public void init() throws IOException {
		commandCount = Integer.parseInt(reader.readLine());
		antHillPosition.add(0);
		antHillDeleted.add(false);
		while(commandCount --> 0) {
			tokenizer = new StringTokenizer(reader.readLine());
			int commandType = Integer.parseInt(tokenizer.nextToken());

			if (commandType == 100) {
				initialVillageConstruction();
			} else if (commandType == 200) {
				addNewAntHill();
			} else if (commandType == 300) {
				removeAntHill();
			} else {
				processScoutQuery();
			}
		}

		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void solution() throws IOException {
		init();
	}

	public static void main(String[] args) throws IOException {
		new QueenAnt().solution();
	}
}
