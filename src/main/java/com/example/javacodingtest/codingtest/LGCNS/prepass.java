package com.example.javacodingtest.codingtest.LGCNS;

import java.io.*;
import java.util.*;

class prepass {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer token;
    static StringBuilder builder = new StringBuilder();

    static int n, m, t, s, e;
    static int[] prepass;
    static int[][] edge;
    static int[][] oneDistance;
    static int[][] twoDistance;
    static int answer;

    public static void main(String[] args) throws Exception {
        token = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        t = Integer.parseInt(token.nextToken());
        s = Integer.parseInt(token.nextToken()) ;
        e = Integer.parseInt(token.nextToken());

        prepass = new int[t];
        token = new StringTokenizer(reader.readLine());
        for (int i = 0; i < t; i++) {
            prepass[i] = Integer.parseInt(token.nextToken());
        }

        edge = new int[n + 1][n + 1];
        oneDistance = new int[n + 1][n + 1];
        twoDistance = new int[n + 1][n + 1];

        for(int i = 0; i <= n; i++) {
            Arrays.fill(oneDistance[i], Integer.MAX_VALUE / 2);
            Arrays.fill(twoDistance[i], Integer.MAX_VALUE / 2);
            oneDistance[i][i] = 0;
            twoDistance[i][i] = 0;
        }

        for(int i = 0; i < m; i++) {
            token = new StringTokenizer(reader.readLine());
            edge[Integer.parseInt(token.nextToken())][Integer.parseInt(token.nextToken())] = 1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if (edge[i][j] == 0) continue;
                oneDistance[i][j] = 1;
                twoDistance[i][j] = 1;
                twoDistance[j][i] = 1;
            }
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if (oneDistance[i][j] > oneDistance[i][k] + oneDistance[k][j]) {
                        oneDistance[i][j] = oneDistance[i][k] + oneDistance[k][j];
                    }
                    if (twoDistance[i][j] > twoDistance[i][k] + twoDistance[k][j]) {
                        twoDistance[i][j] = twoDistance[i][k] + twoDistance[k][j];
                    }
                }
            }
        }

        // s -> 프리패스 지점 고르기
        List<Integer> middle = new ArrayList<>();
        int shortestMid = Integer.MAX_VALUE;

        for(int mid : prepass) {
            shortestMid = Math.min(shortestMid, oneDistance[s][mid]);
        }

        if (shortestMid == Integer.MAX_VALUE / 2) { // 프리패스 구매 불가능
            answer = -1;
            builder.append(answer);
        } else {
            answer += shortestMid;
            for(int mid : prepass) {
                if (oneDistance[s][mid] == shortestMid) middle.add(mid);
            }

            int f = -1;
            int finalDistance = Integer.MAX_VALUE;
            for(int mid : middle) {
                if (finalDistance > twoDistance[mid][e]) {
                    f = mid;
                    finalDistance = twoDistance[mid][e];
                }
            }

            if (finalDistance == Integer.MAX_VALUE) {
                answer = -1;
                builder.append(answer);
            } else {
                answer += finalDistance;
                builder.append(f).append(" ").append(answer);
            }
        }

        writer.write(builder.toString());
        writer.flush();
    }
}
