package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class three2997 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        arr[1] = Integer.parseInt(st.nextToken());
        arr[2] = Integer.parseInt(st.nextToken());

        //정렬
        Arrays.sort(arr);

        //작은수부터 큰수까지의 값의 차를 저장
        int N = arr[1] - arr[0];
        int N2 = arr[2] - arr[1];

        //두 값을 비교 후 해당 값 출력
        if (N == N2) {
            System.out.println(arr[2] + N);
        } else if (N < N2) {
            System.out.println(arr[1] + N);
        } else {
            System.out.println(arr[0] + N2);
        }
    }

}
