package io.desofme.Interrupt;

import java.util.LinkedList;
import java.util.List;

public class Interrupt {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Thread t = new Thread(()->{
            System.out.println("Thread is working...");
            for (int i = 0; i < 1000000; i++) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread interrupted");
                    return;
                }
                list.add(i);
            }
            System.out.println("Thread finished without interrupt");
//            for (int i = 1; i <= 10; i++) {
//                System.out.println("Thread is writing " + i);
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("I'm on the " + i + ") second...");
//                } catch (InterruptedException e) {
//                    System.out.println("Thread woke up");
//                }
//            }

        });


        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t.interrupt();
    }
}
