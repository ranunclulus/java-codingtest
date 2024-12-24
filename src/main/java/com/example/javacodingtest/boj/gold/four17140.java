package com.example.javacodingtest.boj.gold;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.24
 @link https://www.acmicpc.net/problem/17140
 @timecomplex
 @performance 18616kb 184ms
 @category
 @note
 */
public class four17140 {
    class Node implements Comparable<Node>{
        int value;
        int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            if (this.count == o.count) return Integer.compare(this.value, o.value);
            else return Integer.compare(this.count, o.count);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int r, c, k, time, rowNum, colNum;
    static int[][] map;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        r = Integer.parseInt(tokenizer.nextToken()) - 1;
        c = Integer.parseInt(tokenizer.nextToken()) - 1;
        k = Integer.parseInt(tokenizer.nextToken());

        map = new int[100][100];
        for (int i = 0; i < 3; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        rowNum = 3;
        colNum = 3;
        while (map[r][c] != k && time <= 100) {
            time++;
            if (rowNum >= colNum) { // R 연산
                int newColNum = colNum;
                for (int row = 0; row < rowNum; row++) {
                    int tempColNum = 0;
                    Map<Integer, Integer> temp = new HashMap<>();
                    for (int col = 0; col < colNum; col++) {
                        if (map[row][col] == 0) continue;
                        if (temp.containsKey(map[row][col]))
                            temp.put(map[row][col], temp.get(map[row][col]) + 1);
                        else temp.put(map[row][col], 1);
                        map[row][col] = 0;
                    }
                    PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
                    for(Entry<Integer, Integer> entry : temp.entrySet()) {
                        priorityQueue.offer(new Node(entry.getKey(), entry.getValue()));
                        tempColNum += 2;
                    }
                    newColNum = Math.max(newColNum, tempColNum);
                    int index = 0;
                    while(!priorityQueue.isEmpty()) {
                        Node node = priorityQueue.poll();
                        map[row][index++] = node.value;
                        map[row][index++] = node.count;
                    }
                }
                if (newColNum > 100) newColNum = 100;
                colNum = newColNum;
            } else { // C 연산
                int newRowNum = rowNum;
                for (int col = 0; col < colNum; col++) {
                    int tempRowNum = 0;
                    Map<Integer, Integer> temp = new HashMap<>();
                    for (int row = 0; row < rowNum; row++) {
                        if (map[row][col] == 0) continue;
                        if (temp.containsKey(map[row][col]))
                            temp.put(map[row][col], temp.get(map[row][col]) + 1);
                        else temp.put(map[row][col], 1);
                        map[row][col] = 0;
                    }
                    PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
                    for(Entry<Integer, Integer> entry : temp.entrySet()) {
                        priorityQueue.offer(new Node(entry.getKey(), entry.getValue()));
                        tempRowNum += 2;
                    }
                    newRowNum = Math.max(newRowNum, tempRowNum);
                    int index = 0;
                    while(!priorityQueue.isEmpty()) {
                        Node node = priorityQueue.poll();
                        map[index++][col] = node.value;
                        map[index++][col] = node.count;
                    }
                }
                if (newRowNum > 100) newRowNum = 100;
                rowNum = newRowNum;
            }
        }
        builder.append(time > 100 ? -1 : time);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four17140().solution();
    }
}
