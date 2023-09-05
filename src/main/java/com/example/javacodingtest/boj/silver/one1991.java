package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one1991 {
    static class Node {
        char value;
        Node left;
        Node right;

        Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private int size;
    private char[] tree;
    private Node head = new Node('A', null, null);

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine());
        tree = new char[size];

        for (int i = 0; i < size; i++) {
            StringTokenizer treeToken = new StringTokenizer(reader.readLine());

            char root = treeToken.nextToken().charAt(0);
            char left = treeToken.nextToken().charAt(0);
            char right = treeToken.nextToken().charAt(0);
            insertNode(head,root, left, right);

        }

        preOrder(head);
        System.out.println();
        inOrder(head);
        System.out.println();
        postOrder(head);
    }

    private void postOrder(Node head) {
        if (head == null) return;
        postOrder(head.left);
        postOrder(head.right);
        System.out.printf("%c", head.value);
    }

    private void inOrder(Node head) {
        if (head == null) return;
        inOrder(head.left);
        System.out.printf("%c", head.value);
        inOrder(head.right);
    }

    private void preOrder(Node head) {
        if (head == null) return;
        // root -> left -> right
        System.out.printf("%c", head.value);
        preOrder(head.left);
        preOrder(head.right);
    }

    private void insertNode(Node head, char root, char left, char right) {
        if (head.value == root) {
            head.left = (left == '.') ? null : new Node(left, null, null);
            head.right = (right == '.') ? null : new Node(right, null, null);
        }
        else {
            if (head.left != null) insertNode(head.left, root, left, right);
            if (head.right != null) insertNode(head.right, root, left, right);
        }
    }


    public static void main(String[] args) throws IOException {
        new one1991().solution();
    }
}
