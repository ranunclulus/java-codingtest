package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.25
 @link https://www.acmicpc.net/problem/19942
 @timecomplex
 @performance
 @category
 @note
 */
public class four19942 {
    class Food {
        int p;
        int f;
        int s;
        int v;
        int c;

        public Food(int p, int f, int s, int v, int c) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.c = c;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static Food[] foods;
    static int mp;
    static int mf;
    static int ms;
    static int mv;
    static int mc = Integer.MAX_VALUE;
    static int result = Integer.MAX_VALUE;
    static boolean success;

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());;
        foods = new Food[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            foods[i] = new Food(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        recursive(0, 0);
        if (success) {
            sb.append(mc).append('\n');
            for (int i = 0; i < n; i++) {
                if ((result & (1 << i)) > 0) {
                    sb.append(i + 1).append(' ');
                }
            }
        } else sb.append(-1);
        bw.write(sb.toString());
        bw.flush();
    }

    private void recursive(int depth, int check) {
        if (depth == n) {
            calculate(check);
            return;
        }
        recursive(depth + 1, check);
        recursive(depth + 1, check | (1 << depth));
    }

    private void calculate(int check) {
        int totalP = 0;
        int totalF = 0;
        int totalS = 0;
        int totalV = 0;
        int totalC = 0;
        for (int i = 0; i < n; i++) {
            if ((check & (1 << i)) > 0) {
                totalP += foods[i].p;
                totalF += foods[i].f;
                totalS += foods[i].s;
                totalV += foods[i].v;
                totalC += foods[i].c;
            }
        }
        if (totalP < mp || totalF < mf || totalS < ms || totalV < mv) return;
        if (totalC <= mc) {
            mc = totalC;
            if (check < result) result = check;
            success = true;
        }
    }

    public static void main(String[] args) throws IOException {
        new four19942().solution();
    }
}

// 1 0 0 0 0 0
// 1 0 1 1 0 0 -> 3 4 6
// 0 0 1 1 1 0 -> 2 3 4

/*
3
0 0 0 1
0 0 0 0 0
0 0 0 0 0
0 0 0 1 0

1 2 3 나와야 하는데 3 나옴
 */
