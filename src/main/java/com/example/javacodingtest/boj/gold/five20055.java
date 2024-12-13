package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.12.13
 @link https://www.acmicpc.net/problem/20055
 @timecomplex
 @performance 27280kb 1716ms
 @category
 @note
 */
public class five20055 {
    class Node {
        int durability;
        boolean onRobot;

        public Node(int durability) {
            this.durability = durability;
            this.onRobot = false;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k, size, step;
    static LinkedList<Node> conveyor;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        size = 2 * n;

        conveyor = new LinkedList<>();
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 2 * n; i++) {
            conveyor.add(new Node(Integer.parseInt(tokenizer.nextToken())));
        }
        
        while (k > 0) {
            moveConveyor();
            moveRobot();
        }

        builder.append(step);
        writer.write(builder.toString());
        writer.flush();
    }

    private void moveRobot() {
        for (int i = n - 1; i > 0; i--) {
            // 로봇이 없으면 패스
            if (!conveyor.get(i).onRobot) continue;
            // 다음 칸에 로봇이 있거나 내구도가 없다면 패스
            if (conveyor.get(i + 1).onRobot || conveyor.get(i + 1).durability <= 0) continue;

            // 로봇의 이동
            conveyor.get(i).onRobot = false;
            conveyor.get(i + 1).onRobot = true;
            conveyor.get(i + 1).durability--;
            if (conveyor.get(i + 1).durability == 0) k--;

            if (i + 1 == n - 1) conveyor.get(i + 1).onRobot = false;
        }

        // 새로운 로봇 올리기
        if (conveyor.get(0).durability > 0) {
            conveyor.get(0).onRobot = true;
            conveyor.get(0).durability--;
            if (conveyor.get(0).durability == 0) k--;
        }
    }

    private void moveConveyor() {
        step++;
        conveyor.add(0, conveyor.removeLast());
        if (conveyor.get(n - 1).onRobot) conveyor.get(n - 1).onRobot = false;
    }

    public static void main(String[] args) throws IOException {
        new five20055().solution();
    }
}
