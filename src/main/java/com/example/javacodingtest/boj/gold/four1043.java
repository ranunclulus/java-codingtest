package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class four1043 {
    private int[] parents;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int peopleNum = Integer.parseInt(infoToken.nextToken());
        int partyNum = Integer.parseInt(infoToken.nextToken());
        parents = new int[peopleNum + 1];

        for (int i = 0; i < peopleNum + 1; i++) {
            parents[i] = i;
        }

        StringTokenizer trueToken = new StringTokenizer(reader.readLine());
        boolean[] knowTrue = new boolean[peopleNum + 1];
        int knowTrueNum = Integer.parseInt(trueToken.nextToken());
        for (int i = 0; i < knowTrueNum; i++) {
            int temp = Integer.parseInt(trueToken.nextToken());
            knowTrue[temp] = true;
        }

        HashSet<Integer>[] parties = new HashSet[partyNum + 1];
        for (int i = 0; i < partyNum + 1; i++) {
            parties[i] = new HashSet<>();
        }

        for (int i = 0; i < partyNum; i++) {
            String[] partyToken = reader.readLine().split(" ");

            int participatientsNum = Integer.parseInt(partyToken[0]);
            if (participatientsNum <= 1) {
                parties[i].add(Integer.parseInt(partyToken[1]));
                continue;
            }

            for (int j = 1; j < participatientsNum; j++) {
                int a = Integer.parseInt(partyToken[j]);
                int b = Integer.parseInt(partyToken[j + 1]);
                if (findParents(a) != findParents(b)) {
                    union(a, b);
                }
                parties[i].add(a);
                parties[i].add(b);
            }
        }

        boolean[] visited = new boolean[peopleNum + 1];
        for (int i = 1; i < peopleNum + 1; i++) {
            if (knowTrue[i] && !visited[i]) {
                int parent = findParents(i);
                for (int j = 1; j < peopleNum + 1; j++) {
                    if (findParents(j) == parent) {
                        knowTrue[j] = true;
                        visited[j] = true;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < partyNum; i++) {
            boolean flag = false;
            for (int person : parties[i]) {
                if (knowTrue[person]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) result++;
        }

        System.out.println(result);
    }

    private void union(int firstPerson, int nextPerson) {
        parents[findParents(nextPerson)] = firstPerson;
    }

    private int findParents(int person) {
        if (person == parents[person]) return person;
        else return findParents(parents[person]);
    }

    public static void main(String[] args) throws IOException {
        new four1043().solution();
    }
}
