package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

public class AutonomousElectricCar {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, C;
	static int[][] map;
	static Car car;
	static Customer[] customers;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	class Car{
		int row;
		int col;
		int battery;

		Car(int row, int col) {
			this.row = row;
			this.col = col;
			this.battery = C;
		}
	}

	class Customer {
		int startRow;
		int startCol;
		int endRow;
		int endCol;
		boolean isFinished;
		int distance;
		boolean canGo;

		Customer(int startRow, int startCol, int endRow, int endCol) {
			this.startRow = startRow;
			this.startCol = startCol;
			this.endRow = endRow;
			this.endCol = endCol;
			this.isFinished = false;
			this.distance = -1;
			boolean canGo = false;
		}

	}

	class CustomerNode implements Comparable<CustomerNode>{
		Customer customer;
		int distance;


		CustomerNode(Customer customer, int distance) {
			this.customer = customer;
			this.distance = distance;
		}

		@Override
		public int compareTo(CustomerNode o) {
			if (this.distance == o.distance) {
				if (this.customer.startRow == o.customer.startRow) return Integer.compare(this.customer.startCol, o.customer.startCol);
				return Integer.compare(this.customer.startRow, o.customer.startRow);
			}
			return Integer.compare(this.distance, o.distance);
		}

	}

	class Point {
		int row;
		int col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public void solution() throws IOException {
		boolean canStart = init();
		if (canStart) {
			while(true) {
				CustomerNode node = chooseCustomer();
				if (node == null) {
					car.battery = -1;
					break;
				}
				moveStartPosition(node);
				if (car.battery <= 0){
					car.battery = -1;
					break;
				}
				moveEndPosition(node);
				if (car.battery <= 0){
					car.battery = -1;
					break;
				}
				if (allFinished()) break;
			}
		} else {
			car.battery = -1;
		}


		builder.append(car.battery);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public CustomerNode chooseCustomer() {
		PriorityQueue<CustomerNode> pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			Customer customer = customers[i];
			if (customer.isFinished) continue;

			Deque<int[]> toVisit = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			toVisit.add(new int[] {customer.startRow, customer.startCol, 0});
			visited[customer.startRow][customer.startCol] = true;

			while(!toVisit.isEmpty()) {
				int[] now = toVisit.poll();

				if (now[0] == car.row && now[1] == car.col) {
					pq.add(new CustomerNode(customer, now[2]));
					break;
				}

				for(int[] delta : deltas) {
					int nextRow = now[0] + delta[0];
					int nextCol = now[1] + delta[1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (visited[nextRow][nextCol]) continue;
					if (map[nextRow][nextCol] == 1) continue;

					toVisit.add(new int[] {nextRow, nextCol, now[2] +1});
					visited[nextRow][nextCol] = true;
				}
			}
		}
		return pq.poll();
	}

	public void moveStartPosition(CustomerNode node) {
		car.row = node.customer.startRow;
		car.col = node.customer.startCol;
		car.battery -= node.distance;
	}

	public void moveEndPosition(CustomerNode node) {
		if (car.battery < node.customer.distance) {
			car.battery -= node.customer.distance;
		} else {
			car.battery -= node.customer.distance;
			node.customer.isFinished = true;
			car.battery += (2 * node.customer.distance);
			car.row = node.customer.endRow;
			car.col = node.customer.endCol;
		}
	}


	public boolean init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		tokenizer = new StringTokenizer(reader.readLine());
		int row = Integer.parseInt(tokenizer.nextToken()) - 1;
		int col = Integer.parseInt(tokenizer.nextToken()) - 1;
		car = new Car(row, col);

		customers = new Customer[M];
		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int startRow = Integer.parseInt(tokenizer.nextToken()) - 1;
			int startCol = Integer.parseInt(tokenizer.nextToken()) - 1;
			int endRow = Integer.parseInt(tokenizer.nextToken()) - 1;
			int endCol = Integer.parseInt(tokenizer.nextToken()) - 1;
			customers[i] = new Customer(startRow, startCol, endRow, endCol);
		}

		for(int i = 0; i < M; i++) {
			Customer customer = customers[i];
			Deque<int[]> toVisit = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			toVisit.add(new int[] {customer.startRow, customer.startCol, 0});
			visited[customer.startRow][customer.startCol] = true;
			int distance = -1;

			while (!toVisit.isEmpty()) {
				int[] now = toVisit.poll();

				if (now[0] == customer.endRow && now[1] == customer.endCol) {
					customer.distance = now[2];
					customer.canGo = true;
					break;
				}

				for(int[] delta : deltas) {
					int nextRow = now[0] + delta[0];
					int nextCol = now[1] + delta[1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (map[nextRow][nextCol] == 1) continue;
					if (visited[nextRow][nextCol]) continue;

					toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
					visited[nextRow][nextCol] = true;
				}
			}
		}

		boolean canStart = true;
		for(int i = 0; i < M; i++) {
			Customer customer = customers[i];
			if (!customer.canGo) {
				canStart = false;
				break;
			}
		}
		return canStart;
	}

	public boolean allFinished() {
		boolean isFinished = true;
		for(int i = 0; i < M; i++) {
			Customer customer = customers[i];
			if (!customer.isFinished) {
				isFinished = false;
				break;
			}
		}
		return isFinished;
	}

	public static void main(String[] args) throws IOException {
		new AutonomousElectricCar().solution();
	}

}
