package com.example.javacodingtest.swea;
import java.io.*;

/*
 @author ranuinclulus
 @since 2024.08.21
 @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgqsAlKr9sDFAW0&categoryId=AWgqsAlKr9sDFAW0&categoryType=CODE&problemTitle=%ED%98%84%EC%A3%BC%EA%B0%80+%EC%A2%8B%EC%95%84%ED%95%98%EB%8A%94&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 @timecomplex
 @performance 27016KB, 155MS
 @category
 @note
 */
public class five6782 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static long n;
    static int count;
                                        // 상 하 좌 우
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            n = Long.parseLong(br.readLine());
            count = 0;

            while (n > 2) {
                double root = Math.sqrt(n);
                if (root == (long) root) {
                    n = (long) root;
                    count++;
                } else {
                    // 제일 큰 제곱근으로 이동한 뒤에 제곱하기
                    // Math.pow((long) root + 1, 2) - n -> 가장 큰 제곱근으로 이동하는 수
                    // + 1 -> 제곱근 취하기
                    count += (int) (Math.pow((long) root + 1, 2) - n + 1);
                    n = (long)root + 1;
                }
            }
            sb.append("#").append(test).append(" ").append(count).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }


    public static void main(String[] args) throws IOException {
        new five6782().solution();
    }
}
