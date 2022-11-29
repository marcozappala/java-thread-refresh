package tutorial.guardedblock;

import java.util.Random;

public class Consumer implements Runnable {
    private Bus bus;

    public Consumer(Bus bus) {
        this.bus = bus;
    }

    public void run() {
        Random random = new Random();
        for (String message = bus.fetchOneMessage();
             ! message.equals("DONE");
             message = bus.fetchOneMessage()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
    }
}
