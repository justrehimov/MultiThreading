package io.desofme.awaitsignal;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProducerConsumer {

    private int limit = 10;
    Queue<Integer> queue = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    Random random = new Random();


    public void produce(){
      while (true){
          try {
              Thread.sleep(500);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          lock.lock();
          if(queue.size()==limit){
              try {
                  condition.await();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
          int value = random.nextInt(100);
          queue.offer(value);
          System.out.println("Producer produce " + value);
          condition.signal();
          lock.unlock();
      }
    }

    public void consume(){
        while (true){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.lock();
            if(queue.size()==0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int value = queue.poll();
            System.out.println("Consumer consume " + value);
            System.out.println("Queue size " + queue.size());
            condition.signal();
            lock.unlock();
        }
    }

}
