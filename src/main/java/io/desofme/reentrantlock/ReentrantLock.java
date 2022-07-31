package io.desofme.reentrantlock;


import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ReentrantLock {

    private int num = 0;
    private Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase(){
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    public void thread1Function(){
        lock.lock();
        System.out.println("Thread 1 is running...");
        System.out.println("Thread waiting make wake up...");
        try {
            condition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("Thread 1 has been make woke up and running...");
            increase();
        }finally {
            lock.unlock();
        }

    }

    public void thread2Function(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);
        lock.lock();
        System.out.println("Thread 2 is running");
        try {
            increase();
            System.out.println("Enter key for continue...");
            scanner.nextLine();
            condition.signal();
            System.out.println("Thread 2 make woke up Thread 1");
        }finally {
            lock.unlock();
        }
    }
    public void threadOver(){
        System.out.println("num field value = " + num);
    }

}
