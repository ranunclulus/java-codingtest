package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.08
 @link https://www.codetree.ai/ko/frequent-problems/problems/royal-knight-duel/submissions?page=1&page_size=20&introductionSetId=&bookmarkId=
 @timecomplex
 @performance 
 @category
 @note
 */
public class RoyalKnightDuel {
	class Night {
		int index;
		int row;
		int col;
		int height;
		int width;
		int k;
        int damage;
		boolean active;
		List<int[]> points;

		Night(int index, int row, int col, int height, int width, int k) {
			this.index = index;
			this.row = row;
			this.col = col;
			this.height = height;
			this.width = width;
			this.k = k;
            this.damage = 0;
			this.active = true;
			this.points = new ArrayList<>();

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					nightMap[row + i][col + j] = index;
					points.add(new int[] {row + i, col + j});
				}
			}
		}
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;

	static int L, N, Q, answer;
	static int[][] map;
	static int[][] nightMap;
	static Night[] nights;
	static int[][] queries;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public void solution() throws IOException {
		init();
		for (int[] query : queries) {
			boolean[] isMoved = moveNight(nights[query[0]], query[1]);
            for (int i = 0; i < N; i++) {
                if (!isMoved[i]) continue;
                if (!nights[i].active) continue;
                moving(nights[i], query[1]);
            }

            for (int i = 0; i < N; i++) {
                if (!isMoved[i]) continue;
                if (i == query[0]) continue;
                removeNight(nights[i]);
            }
		}

        for(Night night : nights) {
            if (!night.active) continue;
            answer += night.damage;
        }

		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
	}

    private void removeNight(Night night) {
        int attackPoint = 0;
        for(int[] point : night.points) {
            if (map[point[0]][point[1]] == 1) attackPoint++;
        }

        night.k -= attackPoint;
        night.damage += attackPoint;

        if (night.k <= 0) night.active = false;
    }

    private void moving(Night night, int dir) {
        List<int[]> newPoints = new ArrayList<>();
        for(int[] point : night.points) {
            if (nightMap[point[0]][point[1]] == night.index) nightMap[point[0]][point[1]] = 0;
        }

        for(int[] point : night.points) {
            point[0] += deltas[dir][0];
            point[1] += deltas[dir][1];
            nightMap[point[0]][point[1]] = night.index;
        }
    }

    public boolean[] moveNight(Night night, int dir) {
		Deque<Night> toVisit = new ArrayDeque<>();
		boolean[] isMoved = new boolean[N];
		boolean[] visited = new boolean[N];
		toVisit.offer(night);
		visited[night.index - 1] = true;
        
        if (!night.active) return isMoved; // 이미 제거된 나이트에 대한 명령이면 이동 불가능 리턴

		boolean totalMoved = true;
		while (!toVisit.isEmpty()) {
			Night now = toVisit.poll();
            if (!now.active) continue; // 이미 제거된 나이트 제외

			boolean canMove = true;

			for (int[] point : now.points) {
				int nextRow = point[0] + deltas[dir][0];
				int nextCol = point[1] + deltas[dir][1];

				if (nextRow < 0 || nextRow >= L || nextCol < 0 || nextCol >= L) {
					canMove = false;
					totalMoved = false;
					break;
				}

				if (map[nextRow][nextCol] == 2) {
					canMove = false;
					totalMoved = false;
					break;
				}

				if (nightMap[nextRow][nextCol] != 0 && nightMap[nextRow][nextCol] != now.index) {
					if (!visited[nightMap[nextRow][nextCol] - 1]) {
						toVisit.add(nights[nightMap[nextRow][nextCol] - 1]);
						visited[nightMap[nextRow][nextCol] - 1] = true;
					}
				}
			}
			isMoved[now.index - 1] = canMove;
		}

		if (!totalMoved)
			Arrays.fill(isMoved, false);

		return isMoved;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		L = Integer.parseInt(tokenizer.nextToken());
		N = Integer.parseInt(tokenizer.nextToken());
		Q = Integer.parseInt(tokenizer.nextToken());

		nightMap = new int[L][L];
		map = new int[L][L];
		for (int i = 0; i < L; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < L; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		nights = new Night[N];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			nights[i] = new Night(i + 1,
				Integer.parseInt(tokenizer.nextToken()) - 1,
				Integer.parseInt(tokenizer.nextToken()) - 1,
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()));
		}

		queries = new int[Q][2];
		for (int i = 0; i < Q; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			queries[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
			queries[i][1] = Integer.parseInt(tokenizer.nextToken());
		}

	}

	public static void main(String[] args) throws IOException {
		new RoyalKnightDuel().solution();
	}
}
