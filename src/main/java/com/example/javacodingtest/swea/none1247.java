package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.23
 @link
 @timecomplex
 @performance 20820KB, 1693MS
 @category
 @note
 */
public class none1247 {
    class Company{
        int x;
        int y;

        public Company(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    class Home{
        int x;
        int y;

        public Home(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    class Customer{
        int x;
        int y;

        public Customer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    class Seller {
        int x;
        int y;

        public Seller(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int testNum;
    static int customerNum;
    static Customer[] customers;
    static Company company;
    static Home home;
    static Seller seller;
    static int[] permutation;
    static boolean[] visited;
    static int minDistance;

   public void solution() throws IOException {
       testNum = Integer.parseInt(br.readLine());
       for (int test = 1; test <= testNum; test++) {
           customerNum = Integer.parseInt(br.readLine());
           visited = new boolean[customerNum];
           permutation = new int[customerNum];
           customers = new Customer[customerNum];
           st = new StringTokenizer(br.readLine());
           minDistance = Integer.MAX_VALUE;

           company = new Company(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
           home = new Home(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

           for (int i = 0; i < customerNum; i++) {
               customers[i] = new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
           }
           // 셀러의 처음 위치
           seller = new Seller(company.x, company.y);

           dfs(0);

           sb.append("#").append(test).append(" ").append(minDistance).append("\n");
       }
       bw.write(sb.toString().trim());
       bw.flush();
   }

    private void dfs(int depth) {
        if (depth == customerNum) {
            calculation();
            return;
        }
        for (int i = 0; i < customerNum; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    private void calculation() {
        int sellerX = seller.x;
        int sellerY = seller.y;
        int totalDistance = 0;
        for (int i = 0; i < customerNum; i++) {
            totalDistance += (Math.abs(customers[permutation[i]].x - seller.x) + Math.abs(customers[permutation[i]].y - seller.y));
            seller.x = customers[permutation[i]].x;
            seller.y = customers[permutation[i]].y;
        }
        totalDistance += (Math.abs(seller.x - home.x) + Math.abs(seller.y - home.y));
        seller.x = sellerX;
        seller.y = sellerY;

        minDistance = Math.min(minDistance, totalDistance);
    }


    public static void main(String[] args) throws IOException {
        new none1247().solution();
    }
}
