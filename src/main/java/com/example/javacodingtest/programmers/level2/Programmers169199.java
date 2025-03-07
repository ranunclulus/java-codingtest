package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.07
 @link https://school.programmers.co.kr/learn/courses/30/lessons/169199
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers169199 {
    static char[][] map;
    static int answer = Integer.MAX_VALUE;
    static Deque<int[]> toVisit;
    static boolean[][] visited;
    static int endRow, endCol;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int solution(String[] board) {
        map = new char[board.length][board[0].length()];
        for(int i = 0; i < board.length; i++) {
            map[i] = board[i].toCharArray();
        }
        
        toVisit = new ArrayDeque<>();
        visited = new boolean[board.length][board[0].length()];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length(); j++) {
                if (map[i][j] == 'R') {
                    toVisit.add(new int[] {i, j, 0});
                    visited[i][j] = true;
                }
                if (map[i][j] == 'G') {
                    endRow = i;
                    endCol = j;
                }
            }
        }
        
        
        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            
            if (now[0] == endRow && now[1] == endCol) {
                answer = Math.min(answer, now[2]);
            }
            
            for(int d = 0; d < 4; d++) {
                int row = now[0];
                int col = now[1];
                int nextRow = row;
                int nextCol = col;
                while (nextRow >= 0 && nextRow < board.length &&
                      nextCol >= 0 && nextCol < board[0].length() &&
                      map[nextRow][nextCol] != 'D') {
                    row = nextRow;
                    col = nextCol;
                    nextRow = row + deltas[d][0];
                    nextCol = col + deltas[d][1];
                }
                if (visited[row][col]) continue;
                toVisit.add(new int[] {row, col, now[2] + 1});
                visited[row][col] = true;
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
