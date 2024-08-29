package com.example.javacodingtest.swea;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/*
 @author ranuinclulus
 @since 2024.08.29
 @link
 @timecomplex
 @performance
 @category
 @note
 */
public class none2383 {
    //TODO isFull 다시 고민하기
    class Stair {
        int row;
        int col;
        int length;
        int[] capacities;

        public Stair(int row, int col, int length) {
            this.row = row;
            this.col = col;
            this.length = length;
            this.capacities = new int[length + 1];
        }

        public void addPerson() {
            capacities[0]++;
        }

        public void move() {
            for (int i = length; i >= 1; i--) {
                capacities[i] = capacities[i - 1];
            }
            capacities[0] = 0;
        }

        public boolean canUse() {
            int totalUse = 0;
            for (int i = 0; i <= length; i++) {
                totalUse += capacities[i];
            }
            return totalUse < MAX_PEOPLE;
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
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Person> people;
    static List<Stair> stairs;
    static int testNum;
    static int n;
    static final int MAX_PEOPLE = 3;
    static List<int[]> distances;
    static int minTime;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            n = Integer.parseInt(br.readLine());
            people = new LinkedList<>();
            stairs = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) people.add(new Person(i, j));
                    if (val != 1 && val != 0) stairs.add(new Stair(i, j, val));
                }
            }

            minTime = Integer.MAX_VALUE;
            usingStair(0, 0);
            sb.append("#").append(test).append(" ").append(minTime).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void usingStair(int depth, int bitMask) throws IOException {
        if (depth == people.size()) {
            calculateDistance(bitMask);
            return;
        }
        usingStair(depth + 1, bitMask);
        usingStair(depth + 1, bitMask | (1 << depth));
    }

    private void calculateDistance(int bitMask) throws IOException {
        distances = new LinkedList<>();
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            if ((bitMask & (1 << i)) == 0) { // 첫 번째 계단 이용
                Stair stair = stairs.get(0);
                int distance = Math.abs(person.row - stair.row) + Math.abs(person.col - stair.col);
                distances.add(new int[] {0, distance});
            } else {
                Stair stair = stairs.get(1);
                int distance = Math.abs(person.row - stair.row) + Math.abs(person.col - stair.col);
                distances.add(new int[] {1, distance});
            }
        }
        distances.sort((o1, o2) -> o1[1] - o2[1]);
        moveStair();
    }

    private void moveStair() {
        int time = 0;

        while (!allClear() || !allEmpty()) {
            time++;
            // 시간이 지나면 게단 하나씩 이동하기
            for (Stair stair : stairs) {
                stair.move();
            }

            for(int[] distance : distances) {
                if (distance[0] == -1) continue;
                distance[1]--;
                if (distance[1] == 0) {
                    if (stairs.get(distance[0]).canUse()) {
                        stairs.get(distance[0]).addPerson();
                        distance[0] = -1;
                        distance[1]++;
                    } else {
                        distance[1]++;
                    }
                }
            }
        }
        minTime = Math.min(minTime, time);
    }

    // 계단이 모두 비었는지
    private boolean allEmpty() {
        for(Stair stair : stairs) {
            for (int i = 0; i <= stair.length; i++) {
                if (stair.capacities[i] != 0) return false;
            }
        }
        return true;
    }

    // 모두 -1번 계단을 이용하면 다 통과했다
    private boolean allClear() {
        for (int[] distance : distances) {
            if (distance[0] != -1) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new none2383().solution();
    }
}

/*
1
9
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 8
7 0 0 0 0 1 0 0 0
0 0 0 0 0 1 1 0 0
0 0 0 0 0 0 0 0 0
1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
 */


/*
#1 9
#2 8
#3 9
#4 7
#5 8
#6 8
#7 11
#8 11
#9 18
#10 12
 */
