package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.13
 @link
 @timecomplex
 @performance 30748 304
 @category #dfs
 @note
 */
public class four8275 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int x;
    static int m;
    static int[][] records;
    static int[] houses;
    static List<int[]> list;

    public void solution() throws IOException {
        int testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum ; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            records = new int[m][3];
            houses = new int[n + 1];
            list = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    // records[i][0] 에서 records[i][1]까지 records[i][2]마리 
                    records[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append('#').append(test).append(' ');
            dfs(0, 0);
            if (!list.isEmpty()) {
                Collections.sort(list, new Comparator<int[]>() {

                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return -Integer.compare(o1[n], o2[n]);
                    }
                });
                for (int i = 0; i < n; i++) {
                    sb.append(list.get(0)[i]).append(' ');
                }
            } else {
                sb.append(-1);
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void dfs(int depth, int totalSum) {
        
        for(int[] record : records) {
            int sum = 0;
            if (depth < record[1]) continue;
            for (int i = record[0] - 1; i <= record[1] - 1; i++) {
                sum += houses[i];
            }
            if (sum != record[2]) {
                return;
            }
        }

        if (depth == n) {
            houses[n] = totalSum;
            list.add(houses.clone());
            return;
        }

        for (int i = 0; i <= x ; i++) {
            houses[depth] = i;
            dfs(depth + 1, totalSum + i);
            houses[depth] = 0;
        }
        
    }

    public static void main(String[] args) throws IOException {
        new four8275().solution();
    }
}


