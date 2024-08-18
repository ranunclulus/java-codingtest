package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.18
 @link https://www.acmicpc.net/problem/2470
 @timecomplex
 @performance 28268KB, 340MS
 @category
 @note
 */
public class five2470 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] nums;
    static boolean[] isSelected;
    static int min = Integer.MAX_VALUE;

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        isSelected = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                sb.replace(0, sb.length(), "");
                sb.append(nums[left]).append(' ').append(nums[right]);
            }
            if (sum < 0) left++;
            else if (sum > 0) right--;
            else break;
        }

        bw.write(sb.toString());
        bw.flush();
    }
    public static void main(String[] args) throws IOException {
        new five2470().solution();
    }
}
