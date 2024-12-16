package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.12.16
 @link https://www.acmicpc.net/problem/14725
 @timecomplex
 @performance 16336KB 160MS
 @category
 @note
 */
public class three14725 {
    class TrieNode {
        Map<String, TrieNode> children;

        public TrieNode() {
            this.children = new HashMap<>();
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String[] words) {
            TrieNode node = this.root;
            for(String word : words) {
                if (node.children.get(word) == null) {
                    node.children.put(word, new TrieNode());
                }
                node = node.children.get(word);
            }
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k;
    static String[] words;
    static Trie trie;

    public void solution() throws IOException {
        trie = new Trie();
        n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            k = Integer.parseInt(tokenizer.nextToken());
            words = new String[k];

            for (int j = 0; j < k; j++) {
                words[j] = tokenizer.nextToken();
            }
            trie.insert(words);
        }

        print(trie.root, 0);
        writer.write(builder.toString());
        writer.flush();
    }

    private void print(TrieNode node, int depth) {
        List<String> keys = new ArrayList<>();
        for(String key : node.children.keySet()) {
            keys.add(key);
        }
        Collections.sort(keys);

        for(String key : keys) {
            builder.append("--".repeat(depth)).append(key).append('\n');
            print(node.children.get(key), depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new three14725().solution();
    }
}
