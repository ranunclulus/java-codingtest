package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class four17298 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nums = new int[n];

        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
           nums[i] = Integer.parseInt(infoToken.nextToken());
        }

        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (nums[stack.peek()] >= nums[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    nums[stack.pop()] = nums[i];
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            nums[stack.pop()] = -1;
        }
// TODO 또또또 시간초과 ㅡㅡ
//        nge.push(-1);
//        while (stack.size() > 1) {
//            int num = stack.pop();
//            if (num > stack.peek()) {
//                nge.push(num);
//            }
//            else {
//                boolean flag = false;
//                for (int i : nge) {
//                    if (i > stack.peek()) {
//                        nge.push(i);
//                        flag = true;
//                        break;
//                    }
//                }
//                if (!flag) nge.push(-1);
//
//            }
//        }


// TODO 방법 3 메모리 초과
//        for (int i = 0; i < n - 1; i++) {
//            boolean flag = false;
//            for (int j = 1; j <= (n - i) / 2; j++) {
//                int leftNum = nums[i + j];
//                int rightNum = stack.get(n - j);
//                if (leftNum > nums[i]) {
//                    builder.append(leftNum).append(" ");
//                    flag = true;
//                } else if (rightNum > nums[i]) {
//                    builder.append(rightNum).append(" ");
//                    flag = true;
//                }
//            }
//            if (!flag) builder.append(-1).append(" ");
//        }
//        builder.append(-1).append(" ");


//TODO 방법 2 시간 초과
//        for (int i = 0; i < n; i++) {
//            boolean flag = false;
//            for (int j = i + 1; j < n; j++) {
//                if (nums[i] < nums[j]) {
//                    builder.append(nums[j]).append(" ");
//                    flag = true;
//                    break;
//                }
//            }
//            if (!flag) builder.append(-1).append(" ");
//        }

//TODO 방법 1 시간 초과
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                stack.push(nums[j]);
//            }
//            int rightBigNum = -1;
//            while (!stack.isEmpty()) {
//                int cur = stack.pop();
//                if (cur > nums[i]) {
//                    rightBigNum = cur;
//                }
//            }
//            builder.append(rightBigNum).append(" ");
//        }
        for (int i = 0; i < n; i++) {
            builder.append(nums[i]).append(" ");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four17298().solution();
    }
}
