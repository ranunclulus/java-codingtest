package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.21
 @link https://www.acmicpc.net/problem/16935
 @timecomplex
 @performance 85008KB, 516MS
 @category #simulation
 @note
 */
public class five16935 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int m;
    static int operationNum;
    static int[] operation;
    static int[][] arr;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        operationNum = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        operation = new int[operationNum];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < operationNum; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < operationNum; i++) {
            calculate(operation[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void calculate(int oper) {
        switch (oper) {
            case 1:
                upDownFlip();
                break;
            case 2:
                leftRightFlip();
                break;
            case 3:
                rightRotation();
                break;
            case 4:
                leftRotation();
                break;
            case 5:
                rightShift();
                break;
            case 6:
                leftShift();
                break;
        }
    }

    private void upDownFlip() {
        for (int i = 0; i < n / 2; i++) {
            int[] row = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = row;
        }
    }

    private void leftRightFlip() {
        for (int i = 0; i < m / 2; i++) {
            int[] row = new int[n];
            for (int j = 0; j < n; j++) {
                row[j] = arr[j][i];
            }
            for (int j = 0; j < n; j++) {
                arr[j][i] = arr[j][m - i - 1];
                arr[j][m - i - 1] = row[j];
            }
        }
    }

    private void rightRotation() {
        int temp = n;
        n = m;
        m = temp;
        int[][] answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] row = new int[m];
            for (int j = 0; j < m; j++) {
                row[j] = arr[m - j - 1][i];
            }
            answer[i] = row;
        }
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = answer[i][j];
            }
        }
    }

    private void leftRotation() {
        int temp = n;
        n = m;
        m = temp;
        int[][] answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] row = new int[m];
            for (int j = 0; j < m; j++) {
                row[j] = arr[j][n - 1 - i];
            }
            answer[i] = row;
        }
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = answer[i][j];
            }
        }
    }

    private void rightShift() {
        int[][] one = new int[n / 2][m / 2];
        int[][] two = new int[n / 2][m / 2];
        int[][] three = new int[n / 2][m / 2];
        int[][] four = new int[n / 2][m / 2];

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                one[i][j] = arr[i][j];
                two[i][j] = arr[i][j + m / 2];
                three[i][j] = arr[i + n / 2][j + m / 2];
                four[i][j] = arr[i + n / 2][j];
            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                arr[i][j] = four[i][j];
                arr[i][j + m / 2] = one[i][j];
                arr[i + n / 2][j + m / 2] = two[i][j];
                arr[i + n / 2][j] = three[i][j];
            }
        }
    }

    private void leftShift() {
        int[][] one = new int[n / 2][m / 2];
        int[][] two = new int[n / 2][m / 2];
        int[][] three = new int[n / 2][m / 2];
        int[][] four = new int[n / 2][m / 2];

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                one[i][j] = arr[i][j];
                two[i][j] = arr[i][j + m / 2];
                three[i][j] = arr[i + n / 2][j + m / 2];
                four[i][j] = arr[i + n / 2][j];
            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                arr[i][j] = two[i][j];
                arr[i][j + m / 2] = three[i][j];
                arr[i + n / 2][j + m / 2] = four[i][j];
                arr[i + n / 2][j] = one[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five16935().solution();
    }
}
