package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.12
 @link https://school.programmers.co.kr/learn/courses/30/lessons/388354
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers388354 {
    class TreeInfo {
        int evenNode;
        int oddNode;
        int reverseEvenNode;
        int reverseOddNode;
        
        TreeInfo () {
            this.evenNode = 0;
            this.oddNode = 0;
            this.reverseEvenNode = 0;
            this.reverseOddNode = 0;
        }
        
        public boolean isTree() {
            if ((evenNode == 1 && oddNode == 0) || (evenNode == 0 && oddNode == 1))return true;
            return false;
        }
        
        public boolean isReverseTree() {
            if ((reverseEvenNode == 1 && reverseOddNode == 0) || (reverseEvenNode == 0 && reverseOddNode == 1)) return true;
            return false;
        }
    }
    
    private int[] inDegree;
    private int[] parents;
    private int lastNode;
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        for (int node : nodes) {
            lastNode = Math.max(lastNode, node);
        }
        
        inDegree = new int[lastNode + 1];
        parents = new int[lastNode + 1];
        
        for (int i = 1; i <= lastNode; i++) {
            parents[i] = i;
        }
        
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            inDegree[a]++;
            inDegree[b]++;
            union(a, b);
        }
        
        Map<Integer, TreeInfo> treeGroup = new HashMap<>();
        
        for (int node : nodes) {
            int group = find(node);
    
            TreeInfo treeinfo = treeGroup.getOrDefault(group, new TreeInfo());
            if ((node % 2 == 0) && (inDegree[node] % 2 == 0)) { // 짝수노드
                treeinfo.evenNode++;
            } else if ((node % 2 == 1) && (inDegree[node] % 2 == 1)) { // 홀수노드
                treeinfo.oddNode++;
            } else if ((node % 2 == 0) && (inDegree[node] % 2 == 1)) { // 역짝수노드
                treeinfo.reverseEvenNode++;
            } else if ((node % 2 == 1) && (inDegree[node] % 2 == 0)) { // 역홀수노드
                treeinfo.reverseOddNode++;
            }
            treeGroup.put(group, treeinfo);
        }
        
        for(TreeInfo treeinfo : treeGroup.values()) {
            if (treeinfo.isTree()) answer[0]++;
            if (treeinfo.isReverseTree()) answer[1]++;
        }
        return answer;
    }
    
    public int find (int target) {
        if (parents[target] == target) return target;
        else return parents[target] = find(parents[target]);
    }
    
    public void union (int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent != bParent) parents[bParent] = aParent;
    }
}
