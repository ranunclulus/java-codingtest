package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.07
 @link https://www.acmicpc.net/problem/2109
 @timecomplex
 @performance 20472kb 276ms
 @category
 @note
 */
public class three2109 {
    class Lecture {
        int day;
        int pay;

        public Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }

    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, result;
    static Lecture[] lectures;
    static boolean[] visited;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());

        lectures = new Lecture[n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            lectures[i] = new Lecture(
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()));
        }

        Arrays.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                if (o1.pay == o2.pay) return -Integer.compare(o1.day, o2.day);
                return -Integer.compare(o1.pay, o2.pay);
            }
        });

        visited = new boolean[10001];
        for (int i = 0; i < n; i++) {
            // 비용에 해당하는 날짜를 D라고 하자.
            // D일부터 1일까지 역순으로 해당 비용을 받는 강연 스케줄이 들어갈
            // 자리가 있는지 확인한다.
            for (int j = lectures[i].day; j >= 1; j--) {
                if (!visited[j]) {
                    visited[j] = true;
                    result += lectures[i].pay;
                    break;
                }
            }
        }
        builder.append(result);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new three2109().solution();
    }
}

/*
3
100 2
50 2
30 1
 */
