package com.example.javacodingtest.swea;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.18
 @link https://www.acmicpc.net/problem/2470
 @timecomplex
 @performance 26876KB, 178MS
 @category
 @note
 */
public class none4008 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int[] nums;
    static int[] operator = new int[4];
    static Set<Integer> count;
    static int min;
    static int max;
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            n = Integer.parseInt(br.readLine());
            nums = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operator[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            count = new HashSet<>();

            recursive(0, nums[0]);
            for(int val : count) {
                min = Math.min(min, val);
                max = Math.max(max, val);
            }

            sb.append("#").append(test).append(" ").append(max - min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void recursive(int depth, int result) {
        if (depth == n - 1) {
            count.add(result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operator[i] == 0) continue;
            operator[i]--;
            if (i == 0) {
                recursive(depth + 1, result + nums[depth + 1]);
            } else if (i == 1) {
                recursive(depth + 1, result - nums[depth + 1]);
            } else if (i == 2)  {
                recursive(depth + 1, result * nums[depth + 1]);
            } else {
                recursive(depth + 1, result / nums[depth + 1]);
            }
            operator[i]++;
        }
    }

    public static void main(String[] args) throws IOException {
        new none4008().solution();
    }
}

