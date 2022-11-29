package tutorial;

import static java.lang.Thread.sleep;

public class Deadlock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                            + "  has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
                new Friend("Alphonse");
        final Friend gaston =
                new Friend("Gaston");
        Thread alphonseThread = new Thread(new Runnable() {
            public void run() {
                alphonse.bow(gaston);
            }
        });
        Thread gastonThread = new Thread(new Runnable() {
            public void run() {
                gaston.bow(alphonse);
            }
        });

        alphonseThread.start();
//        If we introduce a delay on the start of the two, the deadlock disappear
//        cause alphonse thread acquire/release the lock before gaston asks for it
//        try {
//            sleep(10);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        gastonThread.start();

        while (gastonThread.isAlive()){
            try {
                gastonThread.join(2000);
                System.out.println("i think gaston is stuck waiting");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Nope, unblocked!");




    }
}
