package tutorial.guardedblock;


import static tutorial.guardedblock.Bus.OWNER.CONSUMER;
import static tutorial.guardedblock.Bus.OWNER.PRODUCER;

/**
 * There are several data structure already thread safe. This is a simple implementation example!
 * (this Bus should be like a BlockingQueue with a max size of 1 message at a time)
 */
public class Bus {
    enum OWNER{
        CONSUMER,
        PRODUCER
    }
    // Message sent from producer
    // to consumer.
    private String message;
    private OWNER owner = PRODUCER;

    /**
     * Why is this method synchronized?
     * A thread that will call this method will at some point execute a Bus.wait(), and to do that it MUST
     * own the intrinsic lock for Bus — otherwise an error (see java.lang.IllegalMonitorStateException) is thrown.
     * Invoking wait inside a synchronized method is a simple way to acquire the intrinsic lock.
     *
     */
    public synchronized String fetchOneMessage() {
        // Wait until message is
        // available.
        while (PRODUCER.equals(this.owner)) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.owner = PRODUCER;

        // Notify producer that
        // status has changed.
        notifyAll();

        return message;
    }

    public synchronized void sendOneMessage(String message) {
        while (CONSUMER.equals(this.owner)) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        this.message = message;
        this.owner = CONSUMER;

        // Notify consumer that status
        // has changed.
        notifyAll();
    }
}
