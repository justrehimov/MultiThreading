package io.desofme.deadlock;

import io.desofme.Account;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private Account a1 = new Account();
    private Account a2 = new Account();
    private Random random = new Random();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void controlLocks(Lock a, Lock b){
        boolean a_has_been_get = false;
        boolean b_has_been_get = false;
        while (true){
            a_has_been_get = a.tryLock();
            b_has_been_get = b.tryLock();

            if(a_has_been_get & b_has_been_get)
                return;
            if(a_has_been_get){
                a.unlock();
            }if (b_has_been_get){
                b.unlock();
            }
        }
    }
    public void thread1Function(){

        controlLocks(lock1, lock2);
        for (int i = 0; i < 5000; i++) {
            Account.transfer(a1, a2, random.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }

    public void thread2Function(){
        controlLocks(lock2, lock1);
        for (int i = 0; i < 5000; i++) {
            Account.transfer(a2, a1, random.nextInt(100));
        }
        lock2.unlock();
        lock1.unlock();
    }

    public void threadOver(){
        System.out.println("Account1 balance: " + a1.getBalance() + "  Account2 balance: " + a2.getBalance());
        System.out.println("Total balance: " + (a1.getBalance() + a2.getBalance()));
    }
}
