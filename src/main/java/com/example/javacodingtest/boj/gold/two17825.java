package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.18
 @link https://www.acmicpc.net/problem/17825
 @timecomplex
 @performance 17380kb 272ms
 @category
 @note
 */
public class two17825 {
    class Node {
        int number;
        boolean isEmpty;
        boolean isFinished;
        Node next;
        Node fastPath;

        public Node(int number) {
            this.number = number;
            this.isEmpty = true;
        }

        Node addNext(int number) {
            this.next = new Node(number);
            return this.next;
        }

        Node getNode(Node start, int target) {
            Node node = start;
            while (true) {
                if (node == null) {
                    return null;
                }
                if (node.number == target) {
                    return node;
                }
                node = node.next;
            }
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int[] movement, order;
    static int maxScore;
    static Node[] dice;
    static Node start;


    public void solution() throws IOException {
        movement = new int[10];
        order = new int[10];
        dice = new Node[4];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 10; i++) {
            movement[i] = Integer.parseInt(tokenizer.nextToken());
        }

        initMap();
        makeOrder(0);

        builder.append(maxScore);
        writer.write(builder.toString());
        writer.flush();
    }

    private void initMap() {
        start = new Node(0);
        Node node = start;

        for (int i = 2; i <= 40; i+=2) {
            node = node.addNext(i);
        }
        Node end = node.addNext(0);
        end.isFinished = true;
        end.next = end;

        Node cross = new Node(25);
        node = cross.addNext(30);
        node = node.addNext(35);
        node.next = start.getNode(start, 40);

        node = start.getNode(start, 10);
        node = node.fastPath = new Node(13);
        node = node.addNext(16);
        node = node.addNext(19);
        node.next = cross;

        node = start.getNode(start, 20);
        node = node.fastPath = new Node(22);
        node = node.addNext(24);
        node.next = cross;

        node = start.getNode(start, 30);
        node = node.fastPath = new Node(28);
        node = node.addNext(27);
        node = node.addNext(26);
        node.next = cross;

    }

    private void makeOrder(int depth) {
        if (depth == 10) {
            maxScore = Math.max(maxScore, game());
            return;
        }
        for (int i = 0; i < 4; i++) {
            order[depth] = i;
            makeOrder(depth + 1);
        }
    }

    private int game() {
        Arrays.fill(dice, start);
        int score = 0;

        for (int i = 0; i < 10; i++) {
            Node current = dice[order[i]];
            current.isEmpty = true;
            for (int j = 0; j < movement[i]; j++) {
                if (j == 0 && current.fastPath != null) {
                    current = current.fastPath;
                } else current = current.next;
            }
            dice[order[i]] = current;

            if (!current.isEmpty && !current.isFinished) {
                score = 0;
                break;
            } else {
                current.isEmpty = false;
                score += current.number;
            }
        }

        for (int i = 0; i < 4; i++) {
           dice[i].isEmpty = true;
        }
        return score;
    }

    public static void main(String[] args) throws IOException {
        new two17825().solution();
    }
}
