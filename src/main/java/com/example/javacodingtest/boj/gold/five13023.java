package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class five13023 {
    int answer = 0;
    int n;
    int m;
    List<Integer>[] relationships;
    boolean[] visited;
    int[] distance;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());

        relationships = new List[n];
        for (int i = 0; i < n; i++) {
            relationships[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int person1 = Integer.parseInt(infoToken.nextToken());
            int person2 = Integer.parseInt(infoToken.nextToken());
            relationships[person1].add(person2);
            relationships[person2].add(person1);
        }


        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            distance = new int[n];
            dfs(i);
        }

        System.out.println(answer);
    }

    private void dfs(int index) {
        visited[index] = true;
        for (int next : relationships[index]) {
            if (!visited[next]) {
                distance[next] = distance[index] + 1;
                if (distance[next] >= 4) {
                    answer = 1;
                    return;
                }
                dfs(next);
                visited[next] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five13023().solution();
    }
}
