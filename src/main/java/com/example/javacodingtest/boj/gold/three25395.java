package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.01.27
 @link https://www.acmicpc.net/problem/25395
 @timecomplex
 @performance 252396kb 1088ms
 @category
 @note
 */
public class three25395 {

    class Car {
        int number;
        int position;
        int fuel;

        public Car(int number, int position, int fuel) {
            this.number = number;
            this.position = position;
            this.fuel = fuel;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, s;
    static Car[] cars;
    static boolean[] visited;
    static Deque<Car> toVisit;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        s = Integer.parseInt(tokenizer.nextToken()) - 1;

        cars = new Car[n];
        StringTokenizer positionToken = new StringTokenizer(reader.readLine());
        StringTokenizer fuelToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(i,
                    Integer.parseInt(positionToken.nextToken()),
                    Integer.parseInt(fuelToken.nextToken()));
        }

        Arrays.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return Integer.compare(o1.position, o2.position);
            }
        });

        visited = new boolean[n];
        toVisit = new ArrayDeque<>();

        visited[s] = true;
        toVisit.add(cars[s]);

        while(!toVisit.isEmpty()) {
            Car currentCar = toVisit.poll();
            int currentNum = currentCar.number;

            for (int i = currentNum - 1; i >= 0; i--) {
                if ((currentCar.position - cars[i].position) > currentCar.fuel) break;
                if (visited[i]) continue;

                visited[cars[i].number] = true;
                toVisit.add(cars[i]);
            }

            for (int i = currentNum + 1; i < n; i++) {
                if ((cars[i].position - currentCar.position) > currentCar.fuel) break;
                if (visited[i]) continue;

                visited[cars[i].number] = true;
                toVisit.add(cars[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) builder.append(i + 1).append(" ");
        }

        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three25395().solution();
    }
}
