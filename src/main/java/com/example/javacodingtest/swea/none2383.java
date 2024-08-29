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
 @performance 29640 KB, 216 MB
 @category
 @note
 */
public class none2383 {
    class Stair {
        int row;
        int col;
        int length;

        public Stair(int row, int col, int length) {
            this.row = row;
            this.col = col;
            this.length = length;
        }
    }
    class Person {
        int row;
        int col;
        int distance;

        public Person(int row, int col) {
            this.row = row;
            this.col = col;
            this.distance = 0;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "row=" + row +
                    ", col=" + col +
                    ", distance=" + distance +
                    '}';
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
    static List<Person> stairOne;
    static List<Person> stairTwo;
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

    private void calculateDistance(int bitMask) {
        stairOne = new LinkedList<>();
        stairTwo = new LinkedList<>();
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            if ((bitMask & (1 << i)) == 0) { // 첫 번째 계단 이용
                Stair stair = stairs.get(0);
                person.distance = Math.abs(person.row - stair.row) + Math.abs(person.col - stair.col);
                stairOne.add(person);
            } else {
                Stair stair = stairs.get(1);
                person.distance = Math.abs(person.row - stair.row) + Math.abs(person.col - stair.col);
                stairTwo.add(person);
            }
        }
        stairOne.sort((o1, o2) -> o1.distance - o2.distance);
        stairTwo.sort((o1, o2) -> o1.distance - o2.distance);

        moveStair();
    }


    private void moveStair() {
        for (int i = 0; i < stairOne.size(); i++) {
            if (i < MAX_PEOPLE) { // 세 명 미만이면
                stairOne.get(i).distance += stairs.get(0).length;
            } else { // 세 명 이상이면
                if (stairOne.get(i - 3).distance > stairOne.get(i).distance) { // 대기 시간이 발생하는 경우
                    stairOne.get(i).distance = stairOne.get(i - 3).distance + stairs.get(0).length;
                } else {
                    stairOne.get(i).distance += stairs.get(0).length;
                }
            }
        }
        for (int i = 0; i < stairTwo.size(); i++) {
            if (i < MAX_PEOPLE) { // 세 명 미만이면
                stairTwo.get(i).distance += stairs.get(1).length;
            } else { // 세 명 이상이면
                if (stairTwo.get(i - 3).distance > stairTwo.get(i).distance) { // 대기 시간이 발생하는 경우
                    stairTwo.get(i).distance = stairTwo.get(i - 3).distance + stairs.get(1).length;
                } else {
                    stairTwo.get(i).distance += stairs.get(1).length;
                }
            }
        }
        calculateMinTime();
    }

    private void calculateMinTime() {
        int stairOneTime = (!stairOne.isEmpty()) ? stairOne.get(stairOne.size() - 1).distance : 0;
        int stairTwoTime = (!stairTwo.isEmpty()) ? stairTwo.get(stairTwo.size() - 1).distance : 0;
        int resultTime = Math.max(stairOneTime, stairTwoTime) + 1;
        minTime = Math.min(minTime, resultTime);
    }


    public static void main(String[] args) throws IOException {
        new none2383().solution();
    }
}

/*
1
6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
1 0 0 0 0 0
0 0 0 2 0 4
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
