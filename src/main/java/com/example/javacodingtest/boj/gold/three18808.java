package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.31
 @link https://www.acmicpc.net/problem/18808
 @timecomplex
 @performance 15176kb 156ms
 @category
 @note
 */
public class three18808 {
    class Sticker {
        int row;
        int col;
        int[][] sticker;
        boolean isPatched;

        public Sticker(int row, int col) throws IOException {
            this.row = row;
            this.col = col;
            this.sticker = new int[row][col];
            this.isPatched = false;
            initialize();
        }

        public void initialize() throws IOException {
            for (int i = 0; i < row; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < col; j++) {
                    sticker[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
        }

        public void rotate() {
            int[][] temp = new int[col][row];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    temp[j][row - i - 1] = sticker[i][j];
                }
            }
            sticker = temp;
            row = sticker.length;
            col = sticker[0].length;
        }

        public boolean possible(int startRow, int startCol) {
            if (startRow + row > n || startCol + col > m) return false;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (sticker[i][j] == 0) continue; // 스티커 없는 칸이면 맵이 차 있든 아니든 상관없음
                    if (map[startRow + i][startCol + j] == 1) return false; // 스티커가 들어가야 하는데 이미 칸이 차 있으면 붙일 수 없음
                }
            }
            return true;
        }

        public void patch(int startRow, int startCol) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (sticker[i][j] == 0) continue; // 스티커 없는 칸이면 패스
                    map[startRow + i][startCol + j] = 1; // 붙이기
                }
            }
            this.isPatched = true;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, count;
    static Sticker[] stickers;
    static int[][] map;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        stickers = new Sticker[k];
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            stickers[i] = new Sticker(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        map = new int[n][m];

        for (int i = 0; i < k; i++) {
            patchSticker(stickers[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) count++;
            }
        }

        builder.append(count);
        writer.write(builder.toString());
        writer.flush();
    }

    private void patchSticker(Sticker sticker) {
        for (int rotateNum = 0; rotateNum < 4; rotateNum++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    //if (map[i][j] == 1 && sticker.sticker[0][0] == 1) continue; // 이미 차 있는 칸이면 패스
                    if (!sticker.possible(i, j)) continue; // 붙일 수 없으면 패스
                    sticker.patch(i, j);

                    if (sticker.isPatched) return; // 스티커 붙였으면 탐색 안 하고 리턴
                }
            }
            sticker.rotate();
        }
    }

    public static void main(String[] args) throws IOException {
        new three18808().solution();
    }
}
