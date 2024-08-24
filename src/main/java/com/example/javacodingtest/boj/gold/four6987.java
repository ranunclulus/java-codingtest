package com.example.javacodingtest.boj.gold;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.22
 @link https://www.acmicpc.net/problem/6987
 @timecomplex
 @performance 14212 KB, 100 MS
 @category
 @note
 */
public class four6987 {
    class Country {
        int win;
        int loss;
        int draw;
        int game;

        public Country(int win, int draw, int loss) {
            this.win = win;
            this.draw = draw;
            this.loss = loss;
            this.game = win + loss + draw;
        }


    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static boolean possible;
    static Country[] countries;
    static int[][] combination = new int[][] {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
            {1, 2}, {1, 3}, {1, 4}, {1, 5},
            {2, 3}, {2, 4}, {2, 5},
            {3, 4}, {3, 5},
            {4, 5}};

    // 총 15번의 경기
    public void solution() throws IOException {
        for (int i = 0; i < 4; i++) {
            possible = false;
            countries = new Country[6];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                countries[j] = new Country(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                );
            }

            game(0);

            for (Country c : countries) {
                if (c.game != 5) possible = false;
            }
            sb.append(possible ? 1 : 0).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void game(int depth) {
        if (depth == 15) {
            possible = true;
            return;
        }

        Country team1 = countries[combination[depth][0]];
        Country team2 = countries[combination[depth][1]];

        if (team1.win > 0 && team2.loss > 0) {
            team1.win--;
            team2.loss--;
            game(depth + 1);
            team1.win++;
            team2.loss++;
        }

        if (team1.draw > 0 & team2.draw > 0) {
            team1.draw--;
            team2.draw--;
            game(depth + 1);
            team1.draw++;
            team2.draw++;
        }

        if (team1.loss > 0 && team2.win > 0) {
            team1.loss--;
            team2.win--;
            game(depth + 1);
            team1.loss++;
            team2.win++;
        }
    }


    public static void main(String[] args) throws IOException {
        new four6987().solution();
    }
}
