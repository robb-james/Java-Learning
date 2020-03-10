package Thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumerTest {
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(50);
    private static ReentrantLock lock = new ReentrantLock();
    //线程消费
    private static Condition condition = lock.newCondition();
    private static Random random = new Random() ;
    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
    }
    static class Producer implements Runnable{
        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    if (queue.size() < 50 ){
                        Integer task = random.nextInt(30);
                        queue.add(task);
                        condition.signal();
                        System.out.println(Thread.currentThread().getName() + "生产任务" + task);
                    }else {
                        System.out.println("任务满了");
                    }
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    static class Consumer implements Runnable {
        @Override
        public void run(){
            while (true){
                lock.lock();
                Integer task = null;
                try {
                    if (queue.size() > 0) {
                        task = queue.poll();
                    }else {
                        //空跑一次
                        System.out.println(Thread.currentThread().getName() + "等待任务");
                        condition.await();
                        task = queue.poll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                if (task != null){
                    String msg = Thread.currentThread().getName() + "消费任务" + task;
                    for (int i = 0; i < task; i++){
                        msg += ".";
                    }
                    System.out.println(msg);
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
