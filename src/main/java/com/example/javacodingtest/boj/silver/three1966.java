package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1966
public class three1966 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        for (int test = 0;  test < testCase; test++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            int papaerNum = Integer.parseInt(infoToken.nextToken());
            int printNum = Integer.parseInt(infoToken.nextToken());
            StringTokenizer paperToken = new StringTokenizer(reader.readLine());

            LinkedList<int[]> prints = new LinkedList<>();

            // 문서, 중요도로 초기화
            for (int i = 0; i < papaerNum; i++) {
                prints.offer(new int[] {i, Integer.parseInt(paperToken.nextToken())});
            }

            int count = 0;

            while(!prints.isEmpty()) {
                int[] front = prints.poll();
                boolean isMax = true;

                for (int i = 0; i < prints.size(); i++) {
                    if (front[1] < prints.get(i)[1]) {
                        prints.offer(front);
                        for (int j = 0; j < i; j++) {
                            prints.offer(prints.poll());
                        }

                        isMax = false;
                        break;
                    }
                }

                if (!isMax) continue;

                count++;
                if (front[0] == printNum)
                    System.out.println(count);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three1966().solution();
    }
}
