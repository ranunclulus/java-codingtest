package com.example.javacodingtest.programmers.level3;

import java.util.*;
/*
 @author ranuinclulus
 @since 2025.03.01
 @link https://school.programmers.co.kr/learn/courses/30/lessons/81303
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers81303 {
	Deque<Node> stack = new ArrayDeque<>();

	class Node {
		int data;
		Node prev, next;

		public Node() {};

		public Node(int data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		public Node remove() {
			prev.next = next;
			next.prev = prev;

			if (this.next.data == -1) {
				return this.prev;
			} else {
				return this.next;
			}
		}

		public void restore() {
			prev.next = this;
			next.prev = this;
		}
	}

	public Node init(int size) {
		Node initNode = new Node(-1);
		Node prevNode = initNode;
		Node currentNode = null;

		for(int i = 0; i < size; i++) {
			currentNode = new Node(i);
			prevNode.next = currentNode;
			currentNode.prev = prevNode;

			prevNode = currentNode;
		}

		Node endNode = new Node(-1);
		currentNode.next = endNode;
		return initNode.next;
	}


	public String solution(int n, int k, String[] cmds) {
		Node cursor = init(n);
		for(int i = 0; i < k; i++) {
			cursor = cursor.next;
		}

		for(String cmd : cmds) {
			String[] line = cmd.split(" ");
			char operation = line[0].charAt(0);

			if (operation == 'D') {
				int value = Integer.parseInt(line[1]);
				cursor = down(cursor, value);
			} else if (operation == 'U') {
				int value = Integer.parseInt(line[1]);
				cursor = up(cursor, value);
			} else if (operation == 'C') {
				cursor = delete(cursor);
			} else {
				restore();
			}
		}

		StringBuilder answer = new StringBuilder();
		for(int i = 0; i < n; i++) {
			answer.append("O");
		}
		while(!stack.isEmpty()) {
			Node deleted = stack.pop();
			answer.setCharAt(deleted.data, 'X');
		}
		return answer.toString();
	}

	public Node down(Node cursor, int value) {
		for(int i = 0; i < value; i++) {
			cursor = cursor.next;
		}
		return cursor;
	}

	public Node up(Node cursor, int value) {
		for(int i = 0; i < value; i++) {
			cursor = cursor.prev;
		}
		return cursor;
	}

	public Node delete(Node cursor) {
		stack.push(cursor);
		cursor = cursor.remove();
		return cursor;
	}

	public void restore() {
		Node node = stack.pop();
		node.restore();
	}
}
