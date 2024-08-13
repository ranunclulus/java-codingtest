package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.13
 @link https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 @timecomplex
 @performance 20980 3972
 @category #permutation
 @note
 */
public class three6808 {
    static final int TOTAL_GAME = 362880;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] kyu;
    static int[] in;
    static boolean[] visited;
    static int kyuWin;

    public void solution() throws IOException {
        int testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum ; test++) {
            kyu = new int[9];
            in = new int[9];
            visited = new boolean[19];
            kyuWin = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                kyu[i] = Integer.parseInt(st.nextToken());
                visited[kyu[i]] = true;
            }

            perm(0);
            sb.append('#').append(test).append(' ').append(kyuWin)
                    .append(' ').append(TOTAL_GAME - kyuWin)
                    .append('\n');

        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void perm(int depth) {
        if (depth == 9) {
            game();
            return;
        }
        for (int i = 1; i <= 18; i++) {
            if (!visited[i]) {
                visited[i] = true;
                in[depth] = i;
                perm(depth + 1);
                visited[i] = false;
            }
        }
    }

    private void game() {
        int kyuScore = 0;
        int inScore = 0;
        for (int i = 0; i < 9; i++) {
            if (in[i] > kyu[i]) {
                inScore += in[i] + kyu[i];
            } else {
                kyuScore += kyu[i] + in[i];
            }
        }
        if (kyuScore > inScore) {
            kyuWin++;
        }
    }

    public static void main(String[] args) throws IOException {
        new three6808().solution();
    }
}

/*
4
1 3 5 7 9 11 13 15 17
18 16 14 12 10 8 6 4 2
13 17 9 5 18 7 11 1 15
1 6 7 9 12 13 15 17 18
*/
