package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

class Gear {
    int[] gear;
    int point;

    public Gear(int[] gear, int point) {
        this.gear = gear;
        this.point = point;
    }

    public void turnRight() {
        int temp = gear[7];
        for (int i = 7; i > 0; i--) {
            gear[i] = gear[i - 1];
        }
        gear[0] = temp;
    }

    public void turnLeft() {
        int temp = gear[0];
        for (int i = 1; i < 8; i++) {
            gear[i - 1] = gear[i];
        }
        gear[7] = temp;
    }

    public int getRight() {
        return gear[2];
    }

    public int getLeft() {
        return gear[6];
    }

    public int getPoint() {
        return (gear[0] == 1) ? point : 0;
    }
}

public class five14891 {
    private Gear[] gears;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        gears = new Gear[4];

        for (int i = 0; i < 4; i++) {
            String rawGear = sc.next();
            int[] gearValue = new int[8];
            for (int j = 0; j < 8; j++) {
                gearValue[j] = Character.getNumericValue(rawGear.charAt(j));
            }
            int point = i == 0 ? 1 : (int) Math.pow(2, i);
            gears[i] = new Gear(gearValue, point);
        }

        int moveCount = sc.nextInt();


        for (int move = 0; move < moveCount; move++) {
            int index = sc.nextInt() - 1;
            int direction = sc.nextInt();

            roll(index, direction, true, true);
        }
        System.out.println(sumPoint());
    }

    private int sumPoint() {
        int sumPoint = 0;
        for (Gear gear : gears) {
            sumPoint += gear.getPoint();
        }
        return sumPoint;
    }

    private void roll(int index, int direction, boolean right, boolean left) {
        if (index == -1 || index == 4) return;
        int rightValue = gears[index].getRight();
        int leftValue = gears[index].getLeft();

        int rightIndex = index + 1;
        int leftIndex = index - 1;

        if (direction == 1) gears[index].turnRight();
        else gears[index].turnLeft();

        if (right && rightIndex < 4) {
            if (gears[rightIndex].getLeft() != rightValue) {
                roll(rightIndex, -direction, true, false);
            }
        }
        if (left && leftIndex >= 0) {
            if (gears[leftIndex].getRight() != leftValue) {
                roll(leftIndex, -direction, false, true);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five14891().solution();
    }
}
