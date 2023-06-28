package com.example.javacodingtest.programmers.level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers43163 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        // words 내에 타겟이 존재하지 않으면 바로 문제 종료
        if (!Arrays.asList(words).contains(target)) return answer;

        // 문제에는 단어를 다시 활용하면 안 된다는 조건이 없지만
        // 최단 거리를 구하기 때문에 방문한 곳은 탐색하지 않음
        // 단어가 없을 경우 탐색을 끝내고 무한 루프에 빠지지 않게 하기 위해
        boolean[] visited = new boolean[words.length];

        // 거리를 저장하기 위한 distance 배열
        // begin에서 words[i]까지 도달하는 데 걸린 최소 변환 횟수
        // 원래는 Queue<int[]> 형식으로 주던 것을 Queue<integer>로, 두 번째 값은 외부 배열로
        int[] distance = new int[words.length];

        // begin에서 일단 도달할 수 있는 단어를 Queue에 넣기
        Queue<Integer> toVisit = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            // 시작 단어와 유사 단어일 경우
            if (similar(begin, words[i])){
                // 큐에 넣기
                toVisit.offer(i);
                // 거리 정보 업데이트
                distance[i] = 1;
                visited[i] = true;
            }
        }

        // BFS 진행
        while (!toVisit.isEmpty()) {
            int nextIdx = toVisit.poll();
            String nextWord = words[nextIdx];
            // 이번 단어가 타겟이라면
            if(nextWord.equals(target)) {
                answer = distance[nextIdx];
                break;
            }

            // 다음 방문 대상 선정
            for (int i = 0; i < words.length; i++) {
                // 유사하고 방문하지 않았다면
                if(similar(nextWord, words[i]) && !visited[i]) {
                    toVisit.offer(i);
                    visited[i] = true;
                    distance[i] = distance[nextIdx] + 1;
                }
            }
        }
        return answer;
    }

    // 인접 판단 메소드
    // 두 단어가 한 글자 제외 동일한지
    private boolean similar(String word, String target) {
        int same = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == target.charAt(i)) same++;
        }
        return (same + 1 == word.length());
    }

    public static void main(String[] args) {
        System.out.println(
                new Programmers43163().solution("hit", "cog", new String[]{
                        "hot", "dot", "dog", "lot", "log", "cog"
                })
        );
        System.out.println(
                new Programmers43163().solution("hit", "cog", new String[]{
                        "hot", "dot", "dog", "lot", "log"
                })
        );
    }
}
