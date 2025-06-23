package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.06.23
 @link https://www.acmicpc.net/problem/17299
 @timecomplex
 @performance 142752KB 972MS
 @category
 @note
 */
public class three17299 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n;
  static int[] A, F, NGF;

  public void solution() throws IOException {
    tokenizer = new StringTokenizer(reader.readLine());
    n = Integer.parseInt(tokenizer.nextToken());
    
    tokenizer = new StringTokenizer(reader.readLine());
    F = new int[1000001];
    A = new int[n];
    
    for(int i = 0; i < n; i++) {
      A[i] = Integer.parseInt(tokenizer.nextToken());
      F[A[i]]++;
    }
    
    NGF = new int[n];
    Arrays.fill(NGF, -1);
    Stack<Integer> stack = new Stack<>();
    
    for(int i = 0; i < n; i++) {
      while (!stack.empty() && F[A[i]] > F[A[stack.peek()]]) {
        NGF[stack.pop()] = A[i];
      }
      stack.push(i);
    }
    
    for(int ngf : NGF) {
      builder.append(ngf).append(' ');
    }
    
    writer.write(builder.toString());
    writer.close();
  }


	public static void main(String[] args) throws IOException {
		new three17299().solution();
	}
}
