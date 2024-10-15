package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.14
 @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWErcQmKy6kDFAXi&categoryId=AWErcQmKy6kDFAXi&categoryType=CODE&problemTitle=3421&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 @timecomplex
 @performance 20940KB, 239MS
 @category
 @note
 */
public class five3421 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int m;
    static boolean[] isSelected;
    static int cnt;
    static int[][] preferences;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
     
        for (int test = 1; test <= testNum; test++) {
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            isSelected = new boolean[n];

            preferences = new int[m][2];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                preferences[i][0] = Integer.parseInt(st.nextToken()) - 1;
                preferences[i][1] = Integer.parseInt(st.nextToken()) - 1;
            }

            subset(0);
            sb.append("#").append(test).append(" ").append(cnt).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void subset(int depth) {
        if (depth == n) {
            check();
            return;
        }
        isSelected[depth] = true;
        subset(depth + 1);
        isSelected[depth] = false;
        subset(depth + 1);
    }

    private void check() {
        for (int[] preference : preferences) {
            if (isSelected[preference[0]] && isSelected[preference[1]]) {
                return;
            }
        }
        cnt++;
    }

    public static void main(String[] args) throws IOException {
        new five3421().solution();
    }

}
