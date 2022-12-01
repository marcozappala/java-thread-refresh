package tutorial.guardedblock;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class GuardedBlockUsingBlockingQueue {

    public static void printSomething(String name, String message){
        System.out.println(name+": "+message);
    }
    static class Consumer implements Runnable {
        private BlockingQueue<String> queue;
        private final String name;

        public Consumer(BlockingQueue queue, String name) {
            this.queue = queue;
            this.name = name;
        }

        public void run() {
            Random random = new Random();
            try {
                for (String message = queue.take();
                     ! message.equals("DONE");
                     message = queue.take()) {
                    printSomething(this.name,String.format("MESSAGE RECEIVED: %s%n", message));
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {}
                }
                printSomething(this.name,"DONE RECEIVED");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Producer implements Runnable {
        private BlockingQueue bus;

        public Producer(BlockingQueue bus) {
            this.bus = bus;
        }

        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };
            Random random = new Random();

            for (int i = 0;
                 i < importantInfo.length;
                 i++) {
                try {
                    bus.put(importantInfo[i]);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("MESSAGE SENT: "+ importantInfo[i]);
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {}
            }
            try {
                bus.put("DONE");
//                bus.put("DONE");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // a queue with capacity 0 -> wait until the only put message is taken and wait until there is a message
        BlockingQueue bus = new SynchronousQueue();


        (new Thread(new Producer(bus))).start();
        Thread firstConsumer = new Thread(new Consumer(bus, "PRIMO"));
//        Thread secondConsumer = new Thread(new Consumer(bus, "SECOND"));
        firstConsumer.start();
//        secondConsumer.start();

    }
}
