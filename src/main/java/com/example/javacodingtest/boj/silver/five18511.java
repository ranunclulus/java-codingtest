package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class five18511 {
    public int numInt;
    public int numLength;
    public Integer[] numbers;
    public int result;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        numInt = Integer.parseInt(infoToken.nextToken());
        numLength = Integer.parseInt(infoToken.nextToken());
        numbers = new Integer[numLength];

        StringTokenizer numberToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numLength; i++) {
            numbers[i] = Integer.parseInt(numberToken.nextToken());
        }
        Arrays.sort(numbers);
        dfs(0);
        System.out.println(result);
    }

    private void dfs(int now) {
        if (now > numInt) return;
        if (now > result) result = now;
        for (int i = numLength - 1; i >= 0; i--) {
            dfs(now * 10 + numbers[i]);
        }
    }


    public static void main(String[] args) throws IOException {
        new five18511().solution();
    }
}
