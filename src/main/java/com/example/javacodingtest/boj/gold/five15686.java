package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class five15686 {
    private int[][] map;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(infoToken.nextToken()); // 도시의 크기
        int m = Integer.parseInt(infoToken.nextToken()); // 남겨야 하는 치킨 집

        List<int[]> chain = new ArrayList<>();
        List<int[]> house = new ArrayList<>();

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer mapInfo = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
                if (map[i][j] == 1) {
                    house.add(new int[] {i, j});
                }
                else if (map[i][j] == 2) {
                    chain.add(new int[] {i, j});
                }
            }
        }

        int[][] dist = new int[house.size()][chain.size()];
        for (int i = 0; i < house.size(); i++) {
            int[] currentHouse = house.get(i);
            for (int j = 0; j < chain.size(); j++) {
                int[] currentChain = chain.get(j);
                dist[i][j] = Math.abs(currentChain[0] - currentHouse[0]) + Math.abs(currentChain[1] - currentHouse[1]);
            }
        }


        int minDist = Integer.MAX_VALUE;

        List<List<Integer>> combinations = generateCombinations(chain.size(), m);


        for (int i = 0; i < combinations.size(); i++) {
            List<Integer> shops = combinations.get(i);

            int temp = 0;

            for (int j = 0; j < house.size(); j++) {
                int minDistEachHouse = Integer.MAX_VALUE;
                for (Integer shop : shops) {
                    minDistEachHouse = Math.min(minDistEachHouse, dist[j][shop - 1]);
                }
                temp += minDistEachHouse;
            }

            minDist = Math.min(minDist, temp);
        }

        System.out.println(minDist);
    }

    public static List<List<Integer>> generateCombinations(int n, int r) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        generateCombinationsHelper(result, combination, 1, n, r);
        return result;
    }

    private static void generateCombinationsHelper(List<List<Integer>> result, List<Integer> combination, int start, int n, int r) {
        if (r == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= n; i++) {
            combination.add(i);
            generateCombinationsHelper(result, combination, i + 1, n, r - 1);
            combination.remove(combination.size() - 1);
        }
    }


    public static void main(String[] args) throws IOException {
        new five15686().solution();
    }
}
