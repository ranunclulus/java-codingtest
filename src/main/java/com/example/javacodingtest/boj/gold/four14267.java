package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.06.23
 @link https://www.acmicpc.net/problem/14267
 @timecomplex
 @performance 79000KB 540MS
 @category
 @note
 */
public class four14267 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n;

  static int n, m;
  static int[] compliments;
  static List<Integer>[] juniors;

  public void solution() throws IOException {
    tokenizer = new StringTokenizer(reader.readLine());
    n = Integer.parseInt(tokenizer.nextToken());
    m = Integer.parseInt(tokenizer.nextToken());
    compliments = new int[n + 1];
    juniors = new List[n + 1];
    
    for(int i = 0; i <= n; i++) {
      juniors[i] = new ArrayList<>();
    }
    
    tokenizer = new StringTokenizer(reader.readLine());
    for(int i = 1; i <= n; i++) {
      int boss = Integer.parseInt(tokenizer.nextToken());
      
      if (boss == -1) continue;
      juniors[boss].add(i);
    }
    for(int i = 0; i < m; i++) {
      tokenizer = new StringTokenizer(reader.readLine());
      int index = Integer.parseInt(tokenizer.nextToken());
      int weight = Integer.parseInt(tokenizer.nextToken());
      compliments[index] += weight;
    }
    
    dfs(1);
    
    for(int i = 1; i <= n; i++) {
      builder.append(compliments[i]).append(' ');
    }
    
    writer.write(builder.toString());
    writer.close();
  }
  
  public void dfs(int index) {
    for(int next : juniors[index]) {
      compliments[next] += compliments[index];
      dfs(next);
    }
  }

	public static void main(String[] args) throws IOException {
		new four14267().solution();
	}
}
