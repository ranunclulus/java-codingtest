package com.example.javacodingtest.programmers.level2;

import java.util.*;
/*
 @author ranunclulus
 @since 2025.06.02
 @link https://school.programmers.co.kr/learn/courses/30/lessons/155651
 @timecomplex
 @performance
 @category
 @note
 */

class Book implements Comparable<Book> {
    int startTime;
    int endTime;
    
    Book(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    @Override
    public int compareTo(Book o) {
        if (this.startTime == o.startTime) 
            return Integer.compare(this.endTime, o.endTime);
        return Integer.compare(this.startTime, o.startTime);
    }
}

class Programmers155651 {
    Book[] books;
    List<Integer> endTimes;
    
    public int solution(String[][] book_time) {
        books = new Book[book_time.length];
        
        for(int i = 0; i < book_time.length; i++) {
            int startTime = Integer.parseInt(book_time[i][0].substring(0, 2)) * 60
                + Integer.parseInt(book_time[i][0].substring(3, 5));
            int endTime = Integer.parseInt(book_time[i][1].substring(0, 2)) * 60
                + Integer.parseInt(book_time[i][1].substring(3, 5));
            books[i] = new Book(startTime, endTime);
        }
        Arrays.sort(books);
        
        endTimes = new ArrayList<>(1000);
        endTimes.add(books[0].endTime);
        
        for(int i = 1; i < books.length; i++) {
            for(int j = 0; j < endTimes.size(); j++) {
                if (endTimes.get(j) + 10 <= books[i].startTime) {
                    endTimes.remove(endTimes.get(j));
                    break;
                }
            }
            
            endTimes.add(books[i].endTime);
            Collections.sort(endTimes);
        }
    
        return endTimes.size();
    }
}
