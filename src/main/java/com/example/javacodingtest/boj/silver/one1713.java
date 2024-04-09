package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class one1713 {
    class Candidate implements Comparable<Candidate> {
        int recommand;
        int index;
        int num;

        public Candidate(int recommand, int index, int num) {
            this.recommand = recommand;
            this.index = index;
            this.num = num;
        }

        @Override
        public int compareTo(Candidate o) {
            return num - o.num;
        }
    }

    public int contains(int num) {
        for (int i = 0; i < pictures.size(); i++) {
            if (pictures.get(i).num == num) return i;
        }
        return -1;
    }

    public List<Candidate> pictures = new ArrayList<>();
    public int pictureNum;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        pictureNum = Integer.parseInt(reader.readLine());
        int recommandNum = Integer.parseInt(reader.readLine());
        StringTokenizer recommandToken = new StringTokenizer(reader.readLine());

        for (int i = 0; i < recommandNum; i++) {
            int num = Integer.parseInt(recommandToken.nextToken());
            int contains = contains(num);
            if (contains != -1) { // 이미 가지고 있을 때
                pictures.get(contains).recommand++; // 추천 수만 증가
            } else { // 중복되지 않았을 때
                Candidate newCandidate = new Candidate(1, i, num);
                addPicture(newCandidate);
            }
        }

        Collections.sort(pictures);
        for (Candidate picture : pictures) {
            System.out.printf("%d ", picture.num);
        }
    }

    private void addPicture(Candidate newCandidate) {
        if (pictures.size() < pictureNum) {
            pictures.add(newCandidate);
        } else {
            int minVote = Integer.MAX_VALUE;
            int delete = -1;
            for (int i = 0; i < pictures.size(); i++) {
                if (pictures.get(i).recommand < minVote) {
                    minVote = pictures.get(i).recommand;
                    delete = i;
                } else if (pictures.get(i).recommand == minVote) {
                    if (pictures.get(i).index < pictures.get(delete).index) {
                        delete = i;
                    }
                }
            }
            pictures.remove(delete);
            pictures.add(newCandidate);
        }
    }

    public static void main(String[] args) throws IOException {
        new one1713().solution();
    }
}
