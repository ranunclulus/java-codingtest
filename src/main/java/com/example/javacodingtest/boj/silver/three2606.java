package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2606
public class three2606 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int computerNum = Integer.parseInt(reader.readLine());
        int networkNum = Integer.parseInt(reader.readLine());

        List<List<Integer>> network = new ArrayList<>();
        for (int i = 0; i < computerNum; i++) {
            network.add(new ArrayList<>());
        }

        for (int i = 0; i < networkNum; i++) {
            StringTokenizer networkToken = new StringTokenizer(reader.readLine());
            int fromNetwork = Integer.parseInt(networkToken.nextToken()) - 1;
            int toNetwork = Integer.parseInt(networkToken.nextToken()) - 1;
            network.get(fromNetwork).add(toNetwork);
            network.get(toNetwork).add(fromNetwork);
        }

        Queue<Integer> toVisit = new LinkedList<>();
        boolean[] visited = new boolean[computerNum];
        toVisit.offer(0);

        int virus = 0;
        while(!toVisit.isEmpty()) {
            int current = toVisit.poll();
            if (visited[current] == true) continue;
            visited[current] = true;
            virus++;
            for (Integer nextVisit:network.get(current)) {
                if (visited[nextVisit] == false) {
                    toVisit.offer(nextVisit);
                }
            }
        }
        System.out.println(virus - 1);
    }

    public static void main(String[] args) throws IOException {
        new three2606().solution();
    }
}
