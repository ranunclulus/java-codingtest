package com.example.javacodingtest.swea;

import java.io.*;
import java.security.KeyStore;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.27
 @link
 @timecomplex
 @performance 110836KB, 507MS
 @category
 @note Simulation
 */
public class none2382 {
    class Micro implements Comparable<Micro> {
        int row;
        int col;
        int num;
        int direction;
        int index;

        public Micro(int row, int col, int num, int direction, int index) {
            this.row = row;
            this.col = col;
            this.num = num;
            this.direction = direction;
            this.index = index;
        }

        @Override
        public int compareTo(Micro o) {
            return -Integer.compare(num, o.num);
        }
    }

    class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return 10 * row + col;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int m;
    static int k;
    static Micro[] micros;
    static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            micros = new Micro[k];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                micros[i] = new Micro(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()) - 1,
                        i);
            }

            while (m --> 0) {
                HashMap<Position, Integer> map = new HashMap<>();
                for (Micro micro : micros) {
                    if (micro.num == 0) continue; // 이미 죽은 애면 패스
                    int nextRow = micro.row + deltas[micro.direction][0];
                    int nextCol = micro.col + deltas[micro.direction][1];

                    // 벽에 부딪힌 경우
                    if (nextRow == 0 || nextRow == n - 1 || nextCol == 0 || nextCol == n - 1) {
                        micro.num /= 2;
                        // 죽지 않았다면
                        if (micro.num != 0) {
                            // 방향 반대
                            if (micro.direction == 0 || micro.direction == 2) {
                                micro.direction++;
                            } else micro.direction--;
                        }
                    }

                    // 벽에 부딪히지 않은 경우
                    micro.row = nextRow;
                    micro.col = nextCol;

                    Position position = new Position(micro.row, micro.col);
                    map.put(position, map.getOrDefault(position, 0) + 1);
                }
                for(Map.Entry<Position, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == 1) continue;
                    List<Micro> candidate = new LinkedList<>();
                    for(Micro micro : micros) {
                        if (micro.row == entry.getKey().row && micro.col == entry.getKey().col) {
                            candidate.add(micro);
                        }
                    }
                    Collections.sort(candidate);
                    int plus = 0;
                    for (int i = 1; i < candidate.size(); i++) {
                        plus += candidate.get(i).num;
                        micros[candidate.get(i).index].num = 0;
                    }
                    micros[candidate.get(0).index].num += plus;
                }
            }
            int total = 0;
            for(Micro micro : micros) {
                total += micro.num;
            }
            sb.append("#").append(test).append(" ").append(total).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new none2382().solution();
    }
}

