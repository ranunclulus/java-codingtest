package com.example.javacodingtest.programmers.level1;

public class Programmers250137 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        // 현재 체력 초기화
        int currentHealth = health;
        // 마지막 게임 시간
        int lastTime = attacks[attacks.length - 1][0];
        // 유지 시간
        int holdingTime = 0;

        for (int time = 1; time <= lastTime; time++) {
            boolean flag = false;
            for (int i = 0; i < attacks.length; i++) {
                // 공격을 받을 때
                if (attacks[i][0] == time) {
                    flag = true;
                    holdingTime = 0; // 유지 시간 0
                    currentHealth -= attacks[i][1]; // 공격받음
                    if (currentHealth <= 0) return -1; // 음수면 죽었다 반환
                    break;
                }
            }

            if (!flag) { // 공격받지 않았을 때
                currentHealth = Math.min(currentHealth + bandage[1], health);
                holdingTime++;
                if (holdingTime == bandage[0]) { // 시전 시간을 달성하면
                    holdingTime = 0; // 초기화
                    currentHealth = Math.min(currentHealth + bandage[2], health); // 추가 회복
                }
            }
        }
        return currentHealth;
    }

    public static void main(String[] args) {
        int[] bandage = {3, 2, 7};
        int health = 20;
        int[][] attacks = {{1, 15},
                {5, 16},
                {8, 6}};
        System.out.println(new Programmers250137().solution(bandage, health, attacks));
    }
}
