package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.14
 @link https://www.acmicpc.net/problem/20166
 @timecomplex
 @performance 228060kb 432ms
 @category
 @note
 */
public class four20166 {
    class Node {
        HashMap<Character, Node> child;
        int count;

        public Node() {
            this.child = new HashMap<>();
            this.count = 0;
        }
    }

    class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node node = this.root;

            for(char ch : word.toCharArray()) {
                node.child.putIfAbsent(ch, new Node());
                node = node.child.get(ch);
            }
            node.count++;
        }

        public int search(String word) {
            Node node = this.root;

            for(char ch : word.toCharArray()) {
                if (node.child.get(ch) == null) {
                    return 0;
                }
                node = node.child.get(ch);
            }
            return node.count;
        }

    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, count;
    static char[][] map;
    static String word;
    static Trie trie;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        trie = new Trie();

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = reader.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                builder.append(map[i][j]);
                trie.insert(builder.toString());
                initTrie(i, j, 1);
                builder.setLength(0);
            }
        }

        for (int i = 0; i < k; i++) {
            word = reader.readLine();
            builder.append(trie.search(word));
            builder.append('\n');
        }

        writer.write(builder.toString());
        writer.flush();
    }

    private void initTrie(int row, int col, int depth) {
        if (depth == 5) return;

        for (int i = 0; i < 8; i++) {
            int nextRow = (row + deltas[i][0] + n) % n;
            int nextCol = (col + deltas[i][1] + m) % m;

            builder.append(map[nextRow][nextCol]);
            trie.insert(builder.toString());
            initTrie(nextRow, nextCol, depth + 1);
            builder.setLength(builder.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new four20166().solution();
    }
}
