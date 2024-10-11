package com.example.javacodingtest.programmers.level2;


class Programmers340212 {
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 1;
        int end = 100000;
        int answer = 0;
        while (start <= end) {
            int level = (start + end) / 2;
            long totalTime = canClear(diffs, times, limit, level);
            if (totalTime == -1) {
                start = level + 1;
            } else {
                answer = level;
                end = level - 1;
            }
        }
        return answer;
    }

    public long canClear(int[] diffs, int[] times, long limit, int level) {
        long time = 0;
        for(int i = 0; i < diffs.length; i++) {
            if (level >= diffs[i]) {
                time += times[i];
            } else {
                int wrongCount = diffs[i] - level;
                int wrongTime = times[i - 1] + times[i];
                time += wrongCount * wrongTime;
                time += times[i];
            }
            if (time > limit) return -1;
        }
        return time;
    }
}
