package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.17
 @link https://www.acmicpc.net/problem/14425
 @timecomplex
 @performance 873460kb 2472ms
 @category
 @note
 */
public class four14425 {
    class TrieNode {
        TrieNode[] children;
        boolean end;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.end = false;
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = this.root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.end = true;
        }

        public boolean search(String word) {
            TrieNode node = this.root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] != null) node = node.children[c - 'a'];
                else return false;
            }
            return node.end;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static Trie trie;
    static int n, m, count;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        trie = new Trie();

        for (int i = 0; i < n; i++) {
            trie.insert(reader.readLine());
        }

        for (int i = 0; i < m; i++) {
            if (trie.search(reader.readLine())) count++;
        }

        builder.append(count);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four14425().solution();
    }
}
