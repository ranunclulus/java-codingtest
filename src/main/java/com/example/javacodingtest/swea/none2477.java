package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.06
 @link
 @timecomplex
 @performance 31768kb 182ms
 @category
 @note
 */
public class none2477 {
    static class Desk implements Comparable<Desk>{
        int number;
        int workTime;
        Person person;

        public Desk(int number, int workTime) {
            this.number = number;
            this.workTime = workTime;
        }
        @Override
        public int compareTo(Desk o) {
            return Integer.compare(this.number, o.number);
        }
    }
    static class Person {
        int number;
        int arrivalTime;
        int time;
        int receptDesk;
        int repairDesk;


        public Person(int number, int arrivalTime) {
            this.number = number;
            this.arrivalTime = arrivalTime;
        }

        public void usingDesk(Desk desk, Type type) {
            desk.person = this;
            this.time = desk.workTime;
            if (type.equals(Type.RECEPT)) {
                this.receptDesk = desk.number;
            } else {
                this.repairDesk = desk.number;
            }
        }
    }
    static class CarRepairSystem {
        Deque<Person> waitingReceptions= new ArrayDeque<>();
        List<Desk> receptions = new LinkedList<>();
        PriorityQueue<Desk> emptyReceptions = new PriorityQueue<>();

        Deque<Person> waitingRepairs = new ArrayDeque<>();
        List<Desk> repairs = new LinkedList<>();
        PriorityQueue<Desk> emptyRepairs = new PriorityQueue<>();

        public void addDesk(int number, int workTime, Type type) {
            Desk desk = new Desk(number, workTime);
            if (type == Type.RECEPT) {
                receptions.add(desk);
                emptyReceptions.add(desk);
            } else {
                repairs.add(desk);
                emptyRepairs.add(desk);
            }
        }

        public void addPerson(int number, int arrivalTime) {
            waitingReceptions.add(new Person(number, arrivalTime));
        }

        public int work() {
            int result = 0;
            int time = 0;
            while (k > 0) {
                waitToReception(time);
                receptionTask();
                waitToRepair();
                result += repairTask();
                time++;
            }
            return result;
        }

        private int repairTask() {
            int sum = 0;
            for (Desk repair : repairs) {
                if (repair.person != null) {
                    Person customer = repair.person;
                    customer.time--;
                    if (customer.time == 0) {
                        if (customer.receptDesk == a && customer.repairDesk == b) {
                            sum += customer.number;
                        }
                        repair.person = null;
                        emptyRepairs.add(repair);
                        k--;
                    }
                }
            }
            return sum;
        }

        private void waitToRepair() {
            while (!waitingRepairs.isEmpty() && !emptyRepairs.isEmpty()) {
                Person customer = waitingRepairs.poll();
                Desk repair = emptyRepairs.poll();
                customer.usingDesk(repair, Type.REPAIR);
            }
        }

        private void receptionTask() {
            for (Desk reception : receptions) {
                if (reception.person != null) {
                    Person customer = reception.person;
                    customer.time--;
                    if (customer.time == 0) {
                        waitingRepairs.add(customer);
                        reception.person = null;
                        emptyReceptions.offer(reception);
                    }
                }
            }
        }

        private void waitToReception(int time) {
            while (!waitingReceptions.isEmpty() && !emptyReceptions.isEmpty()) {
                Person customer = waitingReceptions.peek();
                if (customer.arrivalTime <= time) {
                    customer = waitingReceptions.poll();
                    Desk reception = emptyReceptions.poll();
                    customer.usingDesk(reception, Type.RECEPT);
                } else break;
            }
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n, m, k, a, b;
    static enum Type {
        REPAIR, RECEPT
    }

    static CarRepairSystem carRepairSystem;


    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());

            carRepairSystem = new CarRepairSystem();
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                carRepairSystem.addDesk(i + 1, Integer.parseInt(tokenizer.nextToken()), Type.RECEPT);
            }
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < m; i++) {
                carRepairSystem.addDesk(i + 1, Integer.parseInt(tokenizer.nextToken()), Type.REPAIR);
            }

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < k; i++) {
                carRepairSystem.addPerson(i + 1, Integer.parseInt(tokenizer.nextToken()));
            }

            int result = carRepairSystem.work();
            if (result == 0) result = -1;
            builder.append("#").append(test).append(" ").append(result).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }


    public static void main(String[] args) throws IOException {
        new none2477().solution();
    }
}



/*
#1 3
#2 7
#3 2
#4 22
#5 18
#6 15
#7 -1
#8 259
#9 100
#10 164
 */


/*
4번 테케
1
4 1 10 3 1
4 6 4 8
1
0 3 4 4 5 6 9 9 9 10
 */
