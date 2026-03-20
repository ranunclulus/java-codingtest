package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

public class Tetris2D {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int K;
	static int[][] map;
	static Block[] blocks;
	static int redPoint, yellowPoint;

	class Block {
		int type;
		int row;
		int col;
		List<int[]> deltas;

		Block(int type, int row, int col) {
			this.type = type;
			this.row = row;
			this.col = col;
			this.deltas = new ArrayList<>();
			this.setBlock();
		}

		public void setBlock() {
			if (this.type == 1) {
				deltas.add(new int[] {0, 0});
			}
			else if (this.type == 2) {
				deltas.add(new int[] {0, 0});
				deltas.add(new int[] {0, 1});
			}
			else if (this.type == 3) {
				deltas.add(new int[] {0, 0});
				deltas.add(new int[] {1, 0});
			}
		}
	}


	public void solution() throws IOException {
		init();
		for(int i = 0; i < K; i++) {
			placeBlock(blocks[i]);
			moveBlock(blocks[i]);
			removeBlocks();
			removeShallowPlace();
			resetMap();
		}

		builder.append(redPoint + yellowPoint + "\n" + getZero());
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}


	public void placeBlock(Block block) {
		int row = block.row;
		int col = block.col;

		for(int[] delta : block.deltas) {
			int nextRow = block.row + delta[0];
			int nextCol = block.col + delta[1];
			map[nextRow][nextCol] = 0;
		}
	}

	public void moveBlock(Block block) {
		moveRed(block);
		moveYellow(block);
	}

	public void moveRed(Block block) {
		int row = block.row;
		int col = block.col;
		boolean canMove = true;

		while(canMove) {
			int nextRow = row;
			int nextCol = col + 1;


			for(int[] delta : block.deltas) {
				int deltaRow = nextRow + delta[0];
				int deltaCol = nextCol + delta[1];

				if (deltaRow < 0 || deltaRow >= 10 || deltaCol < 0 || deltaCol >= 10) {
					canMove = false;
					break;
				}

				if (deltaCol >= 5 && map[deltaRow][deltaCol] == 0) {
					canMove = false;
					break;
				}
			}
			if (canMove) {
				row = nextRow;
				col = nextCol;
			}
		}

		for(int[] delta : block.deltas) {
			int nextRow = row + delta[0];
			int nextCol = col + delta[1];

			map[nextRow][nextCol] = 0;
		}
	}

	public void moveYellow(Block block) {
		int row = block.row;
		int col = block.col;
		boolean canMove = true;



		while(canMove) {
			int nextRow = row + 1;
			int nextCol = col;


			for(int[] delta : block.deltas) {
				int deltaRow = nextRow + delta[0];
				int deltaCol = nextCol + delta[1];

				if (deltaRow < 0 || deltaRow >= 10 || deltaCol < 0 || deltaCol >= 10) {
					canMove = false;
					break;
				}

				if (deltaRow >= 5 && map[deltaRow][deltaCol] == 0) {
					canMove = false;
					break;
				}
			}

			if (canMove) {
				row = nextRow;
				col = nextCol;
			}
		}

		for(int[] delta : block.deltas) {
			int nextRow = row + delta[0];
			int nextCol = col + delta[1];

			map[nextRow][nextCol] = 0;
		}
	}

	public void removeShallowPlace() {
		int redCount = 0;
		for(int i = 4; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				if (map[j][i] == 0) {
					redCount++;
					break;
				}
			}
		}

		if (redCount != 0) {
			for(int i = 0; i < redCount; i++) {
				for(int j = 0; j < 4; j++) {
					map[j][9 - i] = 5;
				}
			}

			int[][] temp = new int[4][4];

			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					temp[i][j] = map[i][6 - redCount + j] == 0 ? 0 : 5;
					map[i][6 - redCount + j] = 5;
				}
			}

			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					map[i][6 + j] = temp[i][j];
				}
			}
		}

		int yellowCount = 0;
		for(int i = 4; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				if (map[i][j] == 0) {
					yellowCount++;
					break;
				}
			}
		}

		if (yellowCount != 0) {
			for(int i = 0; i < yellowCount; i++) {
				for(int j = 0; j < 4; j++) {
					map[9 - i][j] = 4;
				}
			}

			int[][] temp = new int[4][4];

			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					temp[i][j] = map[6 - yellowCount + i][j] == 0 ? 0 : 4;
					map[6 - yellowCount + i][j] = 4;
				}
			}

			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					map[6 + i][j] = temp[i][j];
				}
			}
		}

	}

	public void removeBlocks() {
		removeRed();
		removeYellow();
	}

	public void removeRed() {
		int[][] temp = new int[4][4];
		for(int i = 0; i < 4; i++) {
			Arrays.fill(temp[i], 5);
		}

		int index = 3;
		for(int i = 9; i >= 4; i--) {
			boolean isFull = true;

			for(int j = 0; j < 4; j++) {
				if (map[j][i] != 0) {
					isFull = false;
					break;
				}
			}

			if (isFull) {
				for(int j = 0; j < 4; j++) {
					map[j][i] = 5;
				}
				yellowPoint++;
			}

			if (!isFull) {
				for(int j = 0; j < 4; j++) {
					if (map[j][i] == 0) {
						temp[j][index] = map[j][i];
						map[j][i] = 5;
					}
				}
				index--;
			}

			if (index < 0) break;
		}

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][6 + j] = temp[i][j];
			}
		}
	}

	public void removeYellow() {
		int[][] temp = new int[4][4];
		for(int i = 0; i < 4; i++) {
			Arrays.fill(temp[i], 4);
		}

		int index = 3;

		for(int i = 9; i >= 4; i--) {
			boolean isFull = true;

			for(int j = 0; j < 4; j++) {
				if (map[i][j] != 0) {
					isFull = false;
					break;
				}
			}

			if (isFull) {
				for(int j = 0; j < 4; j++) {
					map[i][j] = 4;
				}
				yellowPoint++;
			}

			if (!isFull) {
				for(int j = 0; j < 4; j++) {
					if (map[i][j] == 0) {
						temp[index][j] = map[i][j];
						map[i][j] = 4;
					}
				}
				index--;
			}
			if (index < 0) break;
		}

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[6 + i][j] = temp[i][j];
			}
		}
	}


	public void resetMap() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = 1;
			}
		}

		for(int i = 4; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = 2;
			}
		}

		for(int i = 0; i < 4; i++) {
			for(int j = 4; j < 6; j++) {
				map[i][j] = 3;
			}
		}

	}

	public int getZero() {
		int zeroCount = 0;

		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if (map[i][j] == 0) zeroCount++;
			}
		}

		return zeroCount;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[10][10];

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = 1;
			}
		}

		for(int i = 4; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = 2;
			}
		}

		for(int i = 0; i < 4; i++) {
			for(int j = 4; j < 6; j++) {
				map[i][j] = 3;
			}
		}

		for(int i = 6; i < 10; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = 4;
			}
		}

		for(int i = 0; i < 4; i++) {
			for(int j = 6; j < 10; j++) {
				map[i][j] = 5;
			}
		}

		for(int i = 4; i < 10; i++) {
			for(int j = 4; j < 10; j++) {
				map[i][j] = -1;
			}
		}

		blocks = new Block[K];

		for(int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int type = Integer.parseInt(tokenizer.nextToken());
			int row = Integer.parseInt(tokenizer.nextToken());
			int col = Integer.parseInt(tokenizer.nextToken());

			blocks[i] = new Block(type, row, col);
		}

	}

	public static void main(String[] args) throws IOException {
		new Tetris2D().solution();
	}

}
