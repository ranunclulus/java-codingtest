package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1389
public class one1389 {
    static class Bacon {
        int num;
        int value;

        public Bacon(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        int userNum = Integer.parseInt(infoToken.nextToken());
        int relationNum = Integer.parseInt(infoToken.nextToken());

        List<List<Integer>> relationship = new ArrayList<>();
        for (int i = 0; i < userNum; i++) {
            relationship.add(new ArrayList<>());
        }

        for (int i = 0; i < relationNum; i++) {
            StringTokenizer relationshipToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(relationshipToken.nextToken()) - 1;
            int to = Integer.parseInt(relationshipToken.nextToken()) - 1;
            relationship.get(from).add(to);
            relationship.get(to).add(from);
        }

        for (List<Integer> row:relationship) {
            Collections.sort(row);
        }

        int min_count = Integer.MAX_VALUE;
        int result = -1;

        for (int start = 0; start < userNum; start++) {
            Queue<Bacon> toVisit = new LinkedList<>();
            int count = 0;
            boolean[] visit = new boolean[userNum];
            visit[start] = true;
            toVisit.offer(new Bacon(start, 0));

            while (!toVisit.isEmpty()) {
                Bacon current = toVisit.poll();
                count += current.value;

                for (Integer canVisit:relationship.get(current.num)) {
                    if (visit[canVisit] == false) {
                        visit[canVisit] = true;
                        toVisit.offer(new Bacon(canVisit, current.value + 1));
                    }
                }
            }

            if(min_count > count) {
                min_count = count;
                result = start;
            }
        }
        System.out.println(result + 1);
    }


    public static void main(String[] args) throws IOException {
        new one1389().solution();
    }
}
