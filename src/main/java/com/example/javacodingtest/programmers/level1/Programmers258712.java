package com.example.javacodingtest.programmers.level1;


public class Programmers258712 {

    public int solution(String[] friends, String[] gifts) {
        int[][] giftsRecord = new int[friends.length][friends.length];
        int[] giveCnt = new int[friends.length];
        int[] takeCnt = new int[friends.length];
        for (String gift : gifts) {
            String[] giveAndTake = gift.split(" ");
            String give = giveAndTake[0];
            String take = giveAndTake[1];

            int giveIndex = -1;
            for (int i = 0; i < friends.length; i++) {
                if (friends[i].equals(give)) {
                    giveIndex = i;
                    giveCnt[giveIndex]++;
                    break;
                }
            }

            int takeIndex = -1;
            for (int i = 0; i < friends.length; i++) {
                if (friends[i].equals(take)) {
                    takeIndex = i;
                    takeCnt[takeIndex]++;
                    break;
                }
            }

            giftsRecord[giveIndex][takeIndex]++;
        }

        int[] nextGift = new int[friends.length];

        for (int i = 0; i < friends.length - 1; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (giftsRecord[i][j] == giftsRecord[j][i]) {
                    int iPoint = giveCnt[i] - takeCnt[i];
                    int jPoint = giveCnt[j] - takeCnt[j];
                    if (giftsRecord[i][j] != 0 && giftsRecord[j][i] != 0 && iPoint == jPoint) continue;
                    else if (iPoint > jPoint) {
                        nextGift[i]++;
                    } else if (iPoint < jPoint) {
                        nextGift[j]++;
                    }
                } else if (giftsRecord[i][j] != giftsRecord[j][i]) {
                    if (giftsRecord[i][j] > giftsRecord[j][i]) {
                        nextGift[i]++;
                    } else if (giftsRecord[i][j] < giftsRecord[j][i]) {
                        nextGift[j]++;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < friends.length; i++) {
            if (answer < nextGift[i]) answer = nextGift[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        System.out.println(new Programmers258712().solution(friends, gifts));
    }
}
