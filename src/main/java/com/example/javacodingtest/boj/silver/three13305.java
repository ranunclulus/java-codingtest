package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13305
public class three13305 {
    public long solution () throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long cityCount = Integer.parseInt(reader.readLine());

        StringTokenizer cityDistToken = new StringTokenizer(reader.readLine());
        // 각 도시들 간의 거리기 때문에 크기는 n - 1
        long[] cityDistance = new long[(int) (cityCount - 1)];
        for (int i = 0; i < cityCount - 1; i++) {
            cityDistance[i] = Integer.parseInt(cityDistToken.nextToken());
        }

        StringTokenizer cityFuelToken = new StringTokenizer(reader.readLine());
        long[] cityFuel = new long[(int) cityCount];
        for (int i = 0; i < cityCount; i++) {
            cityFuel[i] = Integer.parseInt(cityFuelToken.nextToken());
        }

        // 여태까지 확인한 최소 기름값 저장할 변수
        long currentMin = Integer.MAX_VALUE;
        // 현재 도시에서 현재 도시보다 기름값이 싼 도시까지 걸린 거리
        long needToGo = 0;
        // 총 주유비
        long totalPrice = 0;
        // 이동하는 횟수 만큼만 반복
        for (int i = 0; i < cityCount - 1; i++) {
            // 현재 도시의 기름값이 여태까지 중 제일 싸다
            if (cityFuel[i] < currentMin) {
                // 이 도시까지 도달하는 데 걸린 거리 만큼은
                // 이전 최소값 도시에서 넣어야 한다
                totalPrice += currentMin * needToGo;
                // 기름값 최솟값 갱신
                currentMin = cityFuel[i];
                // 여기서부터 다음 도시까지의 거리
                needToGo = cityDistance[i];
            } else {
                // 주유 안 하고 지나치니까 거리 더해 주기
                needToGo += cityDistance[i];
            }
        }
        // 마지막 주유비를 계산한다
        return totalPrice + needToGo * currentMin;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(new three13305().solution());
    }
}
