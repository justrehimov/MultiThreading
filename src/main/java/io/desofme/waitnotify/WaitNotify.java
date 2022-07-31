package io.desofme.waitnotify;

import java.util.Scanner;

public class WaitNotify{

    private final Object lock = new Object();
    public void thread1Function(){
        synchronized (lock){
            System.out.println("Thread 1 is running...");
            System.out.println("Thread 1 waiting Thread 2 for make wake up himself");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 1 woke up");
        }
    }

    public void thread2Function(){
        Scanner scanner = new Scanner(System.in);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (lock){
            System.out.println("Thread 2 is running ....");
            System.out.println("Enter any key for continue");
            scanner.nextLine();
            lock.notify();
            System.out.println("Thread 2 make woke up Thread 1 but Thread 1 will wake up after 4 seconds");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
