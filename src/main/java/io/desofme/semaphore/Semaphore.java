package io.desofme.semaphore;

public class Semaphore {
    private java.util.concurrent.Semaphore sem = new java.util.concurrent.Semaphore(5);


    public void threadFunction(int id){
        try {
            sem.acquire(); //take thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread is starting id = " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread is stopping id = " + id);
        sem.release(); //break thread
    }
}
