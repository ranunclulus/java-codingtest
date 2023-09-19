package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class five5639 {
    static class Node{
        int value;
        Node left, right;

        Node(int value){
            this.value = value;
        }
        Node(int value, Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
        void insert(int value){
            if(value < this.value){
                if(this.left == null)
                    this.left = new Node(value);
                else this.left.insert(value);
            }
            else{
                if(this.right == null)
                    this.right = new Node(value);
                else this.right.insert(value);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Node root=new Node(Integer.parseInt(reader.readLine()));
        String num;
        while (true){
            num = reader.readLine();
            if(num == null || num.equals("")) break;
            root.insert(Integer.parseInt(num));
        }
        postOrder(root);
    }
    static void postOrder(Node node){
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }
}

