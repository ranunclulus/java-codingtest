package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class two2477 {
	
	public static void main(String[] args) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer infoToken = new StringTokenizer(reader.readLine());
		
		int k = Integer.parseInt(infoToken.nextToken());
		

		int[] input = new int[6];
		for (int i = 0; i < 6; i++) {
			infoToken = new StringTokenizer(reader.readLine());
			int direction = Integer.parseInt(infoToken.nextToken()) - 1;
			int size = Integer.parseInt(infoToken.nextToken());
			
			input[i] = size;
		}
		
		int index = 0;
		int big = 0;
		int small = 0;
		for (int i = 0; i < 6; i++) {
			int val = (input[i] *input[(i + 1) % 6]);
			if (big < val) {
				big = val;
				index = i;
			}
		}
		
		small = (input[(index + 3) % 6] * input[(index + 4)% 6]);
		
	
		System.out.println((big - small) * k);
	}

}
