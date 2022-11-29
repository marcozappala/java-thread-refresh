package tutorial.guardedblock;

public class GuardedBlock {
    public static void main(String[] args) {
        Bus bus = new Bus();
        (new Thread(new Producer(bus))).start();
        (new Thread(new Consumer(bus))).start();
    }
}
