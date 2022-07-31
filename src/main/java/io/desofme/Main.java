package io.desofme;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

//    public static void main(String[] args) throws IOException {
//
//        ProducerConsumer producerConsumer = new ProducerConsumer();
//
//        Thread t1 = new Thread(()->{
//            producerConsumer.produce();
//        });
//        Thread t2 = new Thread(()->{
//            producerConsumer.consume();
//        });
//
//        t1.start();
//        t2.start();
//
//    }


//    public static void main(String[] args) {
//        WaitNotify waitNotify = new WaitNotify();
//
//        Thread t1 = new Thread(()->{
//            waitNotify.thread1Function();
//        });
//
//        Thread t2 = new Thread(()->{
//            waitNotify.thread2Function();
//        });
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static void main(String[] args) {
//        List<Integer> integers = new ArrayList<>(10);
//
//        System.out.println(integers.size());
//    }

//    public static void main(String[] args) {
//        ReentrantLock re = new ReentrantLock();
//        Thread t1 = new Thread(() -> {
//            re.thread1Function();
//        });
//
//        Thread t2 = new Thread(() -> {
//            re.thread2Function();
//        });
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        re.threadOver();
//    }

//    public static void main(String[] args) {
//        DeadLock deadLock = new DeadLock();
//        Thread t1 = new Thread(()->{
//            deadLock.thread1Function();
//        });
//        Thread t2 = new Thread(()->{
//            deadLock.thread2Function();
//        });
//        t1.start();
//        t2.start();
//
//        try{
//            t1.join();
//            t2.join();
//        }catch (InterruptedException e){
//            throw new RuntimeException(e);
//        }
//
//        deadLock.threadOver();
//
//    }

//    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore();
//        Thread t1 = new Thread(()->{
//            semaphore.threadFunction(1);
//        });
//        Thread t2 = new Thread(()->{
//            semaphore.threadFunction(2);
//        });
//        Thread t3 = new Thread(()->{
//            semaphore.threadFunction(3);
//        });
//        Thread t4 = new Thread(()->{
//            semaphore.threadFunction(4);
//        });
//        Thread t5 = new Thread(()->{
//            semaphore.threadFunction(5);;
//        });
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
//        try{
//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//            t5.join();
//        }catch (InterruptedException e){
//            throw new RuntimeException(e);
//        }
//    }


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        /*executor.submit(()->{
            Random random = new Random();
            System.out.println("Thread is running...");

            int time = random.nextInt(1000) + 2000;
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread is stopping...");
        });*/

        Callable<Integer> callable = ()->{
            Random random = new Random();
            System.out.println("Thread is running...");

            int time = random.nextInt(1000) + 2000;
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread is stopping...");
            return time;
        };
        Future<Integer> future = executor.submit(callable);

        executor.shutdown();

        try {
            System.out.println("returned time " + future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}