package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class three17124 {
    private long result;
    private int[] a;
    private int[] b;


    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for (int test = 0; test < testCase; test++) {
            result = 0;
            int n = sc.nextInt();
            int m = sc.nextInt();
            a = new int[n];
            b = new int[m];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = sc.nextInt();
            }
            Arrays.sort(b);

            int lIndex = 0;
            int uIndex = 0;
            for (int i = 0; i < n; i++) {
                uIndex = Arrays.binarySearch(b, a[i]);

                // binarySearch는 배열에 같은 값이 없다면 위치 정보를 음수로 반환
                // 음수로 반환된 값에 +1 후 절대값을 취해주면 index를 찾을 수 있음
                if (uIndex < 0) {
                    uIndex = (uIndex + 1) * (-1);
                }
                if (uIndex >= m) {
                    uIndex--;
                }
                if (uIndex > 0) {
                    lIndex = uIndex - 1;
                }
                result += getMatchedNumber(a[i], lIndex, uIndex);
            }
            System.out.println(result);
        }
    }

    private int getMatchedNumber(int target, int lIndex, int uIndex) {
        int diffL = Math.abs(target - b[lIndex]);
        int diffU = Math.abs(target - b[uIndex]);

        if (diffL > diffU) {
            return b[uIndex];
        } else {
            return b[lIndex];
        }
    }

    public static void main(String[] args) throws IOException {
        new three17124().solution();
    }
}
