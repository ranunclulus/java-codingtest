package com.example.javacodingtest.programmers.level3;

import java.util.*;
public class Programmers42861 {
    int[] parents;
    public int solution(int n, int[][] costs) {
        parents = new int[n];

        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        int answer = 0;
        for(int[] edge : costs) {
            int start = edge[0];
            int end = edge[1];
            int cost = edge[2];

            if (find(start) != find(end)) {
                union(start, end);
                answer += cost;
            }
        }
        return answer;
    }

    public int find(int target) {
        if (parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }

    public void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);

        if (startParent < endParent) parents[endParent] = startParent;
        else parents[startParent] = endParent;
    }
}
