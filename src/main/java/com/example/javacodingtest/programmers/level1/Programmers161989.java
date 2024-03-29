package com.example.javacodingtest.programmers.level1;

public class Programmers161989 {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        boolean[] colored = new boolean[section.length];
        String now = "05:13";
        int nowClock = Integer.parseInt(now.substring(0, 1));

        for (int i = 0; i < section.length; i++) {
            if (colored[i]) continue;

            int rollerStart = section[i];
            int rollerEnd = rollerStart + m - 1;
            colored[i] = true;

            for (int j = i + 1; j < section.length; j++) {
                if (section[j] > rollerEnd) break;
                colored[j] = true;
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 8;
        int m = 4;
        int[] section = {2, 3, 6};
        System.out.println(new Programmers161989().solution(n, m, section));
    }
}
