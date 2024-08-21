package com.example.javacodingtest.swea;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.21
 @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWbrg9uabZsDFAWQ&categoryId=AWbrg9uabZsDFAWQ&categoryType=CODE&problemTitle=6109&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&
 @timecomplex
 @performance 29484KB, 154MS
 @category
 @note
 */
public class four6109 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static String directionStr;
    static int[][] map;
    static int[][] result;
                                        // 상 하 좌 우
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            directionStr = st.nextToken();
            map = new int[n][n];
            result = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            switch(directionStr) {
                case "up":
                    for (int j = 0; j < n; j++) {
                        for (int i = 0; i < n - 1; i++) {
                            if (map[i][j] == 0) continue;

                            int k = i + 1;
                            while (k < n - 1 && map[k][j] == 0) k++;

                            if (map[i][j] == map[k][j]) {
                                map[i][j] *= 2;
                                map[k][j] = 0;
                            }
                        }
                        int idx = 0;
                        for (int i = 0; i < n; i++) {
                            if (map[i][j] != 0) result[idx++][j] = map[i][j];
                        }
                    }
                    break;
                case "down":
                    for (int j = 0; j < n; j++) {
                        for (int i = n - 1; i > 0; i--) {
                            if (map[i][j] == 0) continue;

                            int k = i - 1;
                            while (k > 0 && map[k][j] == 0) k--;

                            if (map[i][j] == map[k][j]) {
                                map[i][j] *= 2;
                                map[k][j] = 0;
                            }
                        }
                        int idx = n - 1;
                        for (int i = n - 1; i >= 0; i--) {
                            if (map[i][j] != 0) result[idx--][j] = map[i][j];
                        }
                    }
                    break;
                case "left":
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n - 1; j++) {
                            if (map[i][j] == 0) continue;

                            int k = j + 1;
                            while (k < n - 1 && map[i][k] == 0) k++;

                            if (map[i][j] == map[i][k]) {
                                map[i][j] *= 2;
                                map[i][k] = 0;
                            }
                        }
                        int idx = 0;
                        for (int j = 0; j < n; j++) {
                            if (map[i][j] != 0) result[i][idx++] = map[i][j];
                        }
                    }
                    break;
                case "right":
                    for (int i = 0; i < n; i++) {
                        for (int j = n - 1; j > 0; j--) {
                            if (map[i][j] == 0) continue;

                            int k = j - 1;
                            while (k > 0 && map[i][k] == 0) k--;

                            if (map[i][j] == map[i][k]) {
                                map[i][j] *= 2;
                                map[i][k] = 0;
                            }
                        }
                        int idx = n - 1;
                        for (int j = n - 1; j >= 0; j--) {
                            if (map[i][j] != 0) result[i][idx--] = map[i][j];
                        }
                    }
                    break;
            }

            sb.append("#").append(test).append("\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(result[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new four6109().solution();
    }
}
