package tasks;

public class CounterUsingVolatile implements Counter{

    private volatile int counter;

    @Override
    public int getValue() {
        return counter;
    }

    @Override
    public int incrementAndGet() {
        return counter++;
    }
}
