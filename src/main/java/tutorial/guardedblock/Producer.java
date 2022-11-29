package tutorial.guardedblock;

import java.util.Random;

public class Producer implements Runnable {
    private Bus bus;

    public Producer(Bus bus) {
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
            bus.sendOneMessage(importantInfo[i]);
            System.out.println("MESSAGE SENT: "+ importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        bus.sendOneMessage("DONE");
    }
}
