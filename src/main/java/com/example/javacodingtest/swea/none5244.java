package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.23
 @link
 @timecomplex
 @performance 24980KB, 154MS
 @category
 @note
 */
public class none5244 {
    class Battery {
        int row;
        int col;
        int distance;
        int charge;

        public Battery(int col, int row, int distance, int charge) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.charge = charge;
        }
    }

    class Person {
        int row;
        int col;

        public Person(int row, int col) {
            this.row = row;
            this.col = col;

        }
    }

    class Combination {
        int a;
        int b;

        public Combination(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o){
            if (o instanceof Combination){
                Combination c = (Combination)o;
                return (a == c.a) && (b == c.b);
            }
            else return false;
        }

        @Override
        public int hashCode() {
            return 10 * a + b;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int testNum;
    static int moveNum;
    static int batteryNum;
    static int[] personAMove;
    static int[] personBMove;
    static Battery[] batteries;
    static int[][] deltas = new int[][] {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
                                        // 이동 안 함, 상, 우, 하, 좌
    static Person personA;
    static Person personB;
    static List<Combination> possible;
    static int answer;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            moveNum = Integer.parseInt(st.nextToken());
            batteryNum = Integer.parseInt(st.nextToken());
            personAMove = new int[moveNum + 1]; // 초기에도 충전 가능해서
            personBMove = new int[moveNum + 1];
            batteries = new Battery[batteryNum];
            // A사람의 이동 경로
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= moveNum; i++) {
                personAMove[i] = Integer.parseInt(st.nextToken());
            }
            // B 사람의 이동 경로
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= moveNum; i++) {
                personBMove[i] = Integer.parseInt(st.nextToken());
            }
            // 배터리 정보
            for (int i = 0; i < batteryNum; i++) {
                st = new StringTokenizer(br.readLine());
                batteries[i] = new Battery(
                        Integer.parseInt(st.nextToken()) - 1,
                        Integer.parseInt(st.nextToken()) - 1,
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
            }

            // 사람 초기화
            personA = new Person(0, 0);
            personB = new Person(9, 9);

            // 이동합시다
            answer = 0;
            for (int i = 0; i <= moveNum; i++) {
                int moveA = personAMove[i];
                int moveB = personBMove[i];

                personA.row += deltas[moveA][0];
                personA.col += deltas[moveA][1];

                personB.row += deltas[moveB][0];
                personB.col += deltas[moveB][1];

                boolean[] possibleA = new boolean[batteryNum];
                boolean[] possibleB = new boolean[batteryNum];

                for (int j = 0; j < batteryNum; j++) {
                    Battery battery = batteries[j];
                    int distanceA = Math.abs(battery.row - personA.row) + Math.abs(battery.col - personA.col);
                    int distanceB = Math.abs(battery.row - personB.row) + Math.abs(battery.col - personB.col);

                    if (distanceA <= battery.distance) {
                        possibleA[j] = true;
                    }
                    if (distanceB <= battery.distance) {
                        possibleB[j] = true;
                    }
                }

                possible = new LinkedList<>();
                for (int j = 0; j < batteryNum; j++) {
                    for (int k = 0; k < batteryNum; k++) {
                        Combination combination = new Combination(
                                possibleA[j] ? j : -1,
                                possibleB[k] ? k : -1);
                        if (!possible.contains(combination)) {
                            possible.add(combination);
                        }
                    }
                }

                int maxValue = 0;
                for (int j = 0; j < possible.size(); j++) {
                    Combination combination = possible.get(j);
                    // B만 충전
                    if (combination.a == -1 && combination.b != -1) {
                        if (batteries[combination.b].charge > maxValue) {
                            maxValue = batteries[combination.b].charge;

                        }
                    } else if (combination.a != -1 && combination.b == -1) {
                        if (batteries[combination.a].charge > maxValue) {
                            maxValue = batteries[combination.a].charge;

                        }
                    } else if (combination.a != -1 && combination.b != -1) {
                        if (combination.a == combination.b) {
                            if (batteries[combination.b].charge / 2 > maxValue) {
                                maxValue = batteries[combination.b].charge / 2;

                            }
                        } else {
                            if (batteries[combination.a].charge + batteries[combination.b].charge > maxValue) {
                                maxValue = batteries[combination.a].charge + batteries[combination.b].charge;

                            }
                        }
                    }
                }
                answer += maxValue;

            }
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }



    public static void main(String[] args) throws IOException {
        new none5244().solution();
    }
}
