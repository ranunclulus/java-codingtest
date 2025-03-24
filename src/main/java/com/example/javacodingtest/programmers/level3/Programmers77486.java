package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.24
 @link https://school.programmers.co.kr/learn/courses/30/lessons/77486
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers77486 {
	class Person {
		Person parent;
		int coin;

		Person(Person parent) {
			this.parent = parent;
			this.coin = 0;
		}

		public void selling(int amount) {
			int next = (int) (amount * 0.1);
			this.coin += (amount - next);
			if (this.parent != null) {
				this.parent.selling(next);
			}
		}
	}

	static String[] enrolls, referrals, sellers;
	static int[] amounts, answer;
	static Map<String, Person> people;

	public int[] solution(String[] enrolls,
		String[] referrals,
		String[] sellers,
		int[] amounts) {
		this.enrolls = enrolls;
		this.referrals = referrals;
		this.sellers = sellers;
		this.amounts = amounts;
		this.people = new HashMap<>();
		this.answer = new int[enrolls.length];

		for(int i = 0; i < enrolls.length; i++) {
			people.put(enrolls[i], new Person(referrals[i].equals("-") ? null : people.get(referrals[i])));
		}

		for(int i = 0; i < sellers.length; i++) {
			people.get(sellers[i]).selling(amounts[i] * 100);
		}


		for(int i = 0; i < enrolls.length; i++) {
			answer[i] = people.get(enrolls[i]).coin;
		}

		return answer;
	}
}