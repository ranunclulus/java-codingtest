package com.example.javacodingtest.swea;
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYtrCJQaDb4DFAR-

import java.io.IOException;
import java.util.*;

public class three19003not {
    private int result;
    private int n, m;
    private String[] messages;
    private boolean[] isSelected;
    private List<String> subsetList;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();
        for (int test = 1; test <= testNum; test++) {
            result = 0;
            n = sc.nextInt();
            m = sc.nextInt();


            messages = new String[n];
            for (int i = 0; i < n; i++) {
                messages[i] = sc.next();
            }

            isSelected = new boolean[n];
            subsetList = new ArrayList<>();
            generateSubset(0);

            for (String message : subsetList) {
                palindrom(message);
            }

            if (result % m != 0) result = 0;

            System.out.printf("#%d %d\n", test, result);
        }
    }

    private void generateSubset(int depth) {
        if (depth == n) {
            String subset = "";
            for (int i = 0; i < n; i++) {
                subset += isSelected[i] ? messages[i] : "";
            }
            subsetList.add(subset);
            return;
        }
        isSelected[depth] = true;
        generateSubset(depth + 1);
        isSelected[depth] = false;
        generateSubset(depth + 1);
    }


    private void palindrom(String message) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        int index = message.length();

        for (int i = 0; i < index; i++) {
            queue.add(message.charAt(i));
            stack.add(message.charAt(i));
        }

        int count = 0;
        for (int i = 0; i < index; i++) {
            char queueChar = queue.poll();
            char stackChar = stack.pop();
            if (queueChar == stackChar) {
                count++;
            }
        }

        result = Math.max(result, count);
    }

    public static void main(String[] args) throws IOException {
        new three19003not().solution();
    }
}
