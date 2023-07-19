package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class three15651 {
    private int n;
    private int m;
    private int[] arr;
    private StringBuilder answer;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());
        // 순열 저장용 배열 생성
        arr = new int[m];
        // 정답 저장용 StringBuilder 생성
        answer = new StringBuilder();
        dfs(0);

        System.out.println(answer);
    }

    // 몇 번쨰 숫자를 고르고 있느냐
    public void dfs(int level) {
        if(level == m) {
            // 정답 저장
            for (int i = 0; i < m; i++) {
                answer.append(arr[i]).append(' ');
            }
            answer.append('\n');
        }
        else for (int i = 1; i <= n; i++) {
            arr[level] = i;
            dfs(level + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new three15651().solution();
    }
}
