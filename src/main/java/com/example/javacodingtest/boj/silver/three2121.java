package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class three2121 {

    static int N, A, B;
    static List<Point> list;
    static StringBuilder sb = new StringBuilder();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {

        for (Point p : list) {
            Point p1 = new Point(p.y + B, p.x + A);
            Point p2 = new Point(p.y, p.x + A);
            Point p3 = new Point(p.y + B, p.x);

            if (find(p1) && find(p2) && find(p3)) result++;
        }

        System.out.println(result);
    }

    private static boolean find(Point p) {
        int start = 0, end = N;

        while (start + 1 < end) {
            int mid = (start + end) / 2;
            Point current = list.get(mid);
            if (p.y > current.y || (p.y == current.y && p.x >= current.x)) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (list.get(start).y == p.y && list.get(start).x == p.x) return true;
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        list = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(y, x));
        }
        Collections.sort(list);
    }

    static class Point implements Comparable<Point> {
        final int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point o) {
            if (o.y == y) {
                return x - o.x;
            }
            return y - o.y;
        }
    }
}
