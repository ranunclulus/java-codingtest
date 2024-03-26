package com.example.javacodingtest.programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Programmers178870 {
    class SubSequence implements Comparable<SubSequence> {
        int right;
        int left;
        int size;

        public SubSequence(int right, int left) {
            this.right = right;
            this.left = left;
            this.size = right - left;
        }

        @Override
        public int compareTo(SubSequence o) {
            if (this.size == o.size) {
                return this.size - o.size;
            }
            return this.size - o.size;
        }
    }

    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int right = 0, left = 0;
        int partitionSum = sequence[0];

        List<SubSequence> candidate = new ArrayList<>();
        while (true) {
            if (right == n && left == n) {
                break;
            }
            if (partitionSum == k) {
                candidate.add(new SubSequence(right, left));
            }
            if (partitionSum <= k && right < n) {
                right++;
                if (right < n) partitionSum += sequence[right];
            } else {
                if (left < n) partitionSum -= sequence[left];
                left++;
            }
        }

        Collections.sort(candidate);

        return new int[]{candidate.get(0).left, candidate.get(0).right};
    }

    public static void main(String[] args) {
        int[] sequence = {1, 2, 3, 4, 5};
        int k = 7;
        System.out.println(Arrays.toString(new Programmers178870().solution(sequence, k)));
    }
}
