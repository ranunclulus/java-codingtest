package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.18
 @link https://school.programmers.co.kr/learn/courses/30/lessons/150367
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers150367 {
    static long[] numbers;
    static int[] answer;
    
    public int[] solution(long[] numbers) {
        this.numbers = numbers;
        answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++) {
            if (isBinaryTree(numbers[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    
    public boolean isBinaryTree(long number) {
        String fullTree = getFullTree(Long.toBinaryString(number));
        int root = fullTree.length() / 2;
        
        if (fullTree.charAt(root) == '0') return false;
        
        String leftTree = fullTree.substring(0, root);
        String rightTree = fullTree.substring(root + 1);
        
        return isBinaryTree(leftTree) && isBinaryTree(rightTree);
    }
    
    public boolean isBinaryTree(String tree) {
        if (tree.length() == 0) return true;
        int root = tree.length() / 2;
        
        String leftTree = tree.substring(0, root);
        String rightTree = tree.substring(root + 1);
        
        if (tree.charAt(root) == '0') 
            return isZeroTree(leftTree) && isZeroTree(rightTree);
        else return isBinaryTree(leftTree) && isBinaryTree(rightTree);
    }
    
    public boolean isZeroTree(String tree) {
        if (tree.length() == 0) return true;
        int root = tree.length() / 2;
        
        String leftTree = tree.substring(0, root);
        String rightTree = tree.substring(root + 1);
        if (tree.charAt(root) == '1') 
            return false;
        return isZeroTree(leftTree) && isZeroTree(rightTree);
    }
    
    public String getFullTree(String temptree) {
        int length = temptree.length();
        int level = 1;
        int nodeCount = 1;
        
        while (length > nodeCount) {
            level *= 2;
            nodeCount += level;
        }
        
        int offset = nodeCount - length;
        
        return "0".repeat(offset) + temptree;
    }

} 
