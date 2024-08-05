package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class three15655 {
    static StringBuilder builder = new StringBuilder();
    static int n;
    static int m;
    static int[] arr;
    static int[] result;
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        result = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        recursive(0, 0);
        System.out.println(builder.toString());
    }

    private void recursive(int index, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                builder.append(result[i]).append(" ");
            }
            builder.append('\n');
            return;
        }
        for (int i = index; i < n; i++) {
            result[depth] = arr[i];
            recursive(i + 1, depth + 1);
        }

    }


    public static void main(String[] args) throws IOException {
        new three15655().solution();
    }
}
