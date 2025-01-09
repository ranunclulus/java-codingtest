package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.09
 @link https://www.acmicpc.net/problem/22861
 @timecomplex
 @performance 243736kb 740ms
 @category
 @note
 */
public class one22861 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, q, count;
    static HashMap<String, String> parents = new HashMap<>();
    static HashMap<String, HashSet<String>> files = new HashMap<>();
    static HashMap<String, HashSet<String>> folders = new HashMap<>();
    static HashSet<String> result = new HashSet<>();

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < n + m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String parent = tokenizer.nextToken();
            String child = tokenizer.nextToken();
            int type = Integer.parseInt(tokenizer.nextToken());

            if (type == 0) { // 파일
                if (!files.containsKey(parent)) {
                    files.put(parent, new HashSet<>());
                }
                files.get(parent).add(child);
            } else { // 폴더
                if (!folders.containsKey(parent)) {
                    folders.put(parent, new HashSet<>());
                }
                folders.get(parent).add(child);
                parents.put(child, parent);
            }
        }
        
        k = Integer.parseInt(reader.readLine());
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String[] before = tokenizer.nextToken().split("/");
            String[] after = tokenizer.nextToken().split("/");
            String target = before[before.length - 1];
            String destination = after[after.length - 1];

            if (files.get(target) != null) {
                if (files.get(destination) == null) {
                    files.put(destination, new HashSet<>());
                }
                for(String file : files.get(target)) {
                    files.get(destination).add(file);
                }
            }

            if (folders.get(target) != null) {
                if (folders.get(destination) == null) {
                    folders.put(destination, new HashSet<>());
                }
                for(String folder : folders.get(target)) {
                    folders.get(destination).add(folder);
                }
            }

            String root = before[before.length - 2];
            folders.get(root).remove(target);
        }

        q = Integer.parseInt(reader.readLine());
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String[] queries = tokenizer.nextToken().split("/");
            String target = queries[queries.length - 1];
            count  = 0;
            result.clear();
            search(target);
            builder.append(result.size() + " " + count + "\n");
        }

        writer.write(builder.toString());
        writer.flush();
    }

    private void search(String target) {
        if (files.get(target) != null) {
            for (String file : files.get(target)) {
                result.add(file);
                count++;
            }
        }

        if (folders.get(target) != null) {
            for (String next : folders.get(target)) {
                search(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one22861().solution();
    }
}
