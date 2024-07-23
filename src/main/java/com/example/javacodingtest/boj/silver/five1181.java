package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class five1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Set<String> strs = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) return o1.compareTo(o2);
                else return Integer.compare(o1.length(), o2.length());
            }
        });
        for (int i = 0; i < n; i++) {
            strs.add(reader.readLine());
        }
        for(String str : strs) {
            System.out.println(str);
        }

//        Scanner in = new Scanner(System.in);
//
//        int N = in.nextInt();
//        String[] arr = new String[N];
//
//        in.nextLine();	// 개행 버림
//
//        for (int i = 0; i < N; i++) {
//            arr[i] = in.nextLine();
//        }
//
//        Arrays.sort(arr, new Comparator<String>() {
//            public int compare(String s1, String s2) {
//                // 단어 길이가 같을 경우
//                if (s1.length() == s2.length()) {
//                    return s1.compareTo(s2);
//                }
//                // 그 외의 경우
//                else {
//                    return s1.length() - s2.length();
//                }
//            }
//        });
//
//
//        System.out.println(arr[0]);
//
//        for (int i = 1; i < N; i++) {
//            // 중복되지 않는 단어만 출력
//            if (!arr[i].equals(arr[i - 1])) {
//                System.out.println(arr[i]);
//            }
//        }
    }

}
