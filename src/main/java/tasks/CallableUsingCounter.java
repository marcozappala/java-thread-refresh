package tasks;

import java.util.concurrent.Callable;

public class CallableUsingCounter implements Callable {

  Counter counter;

  public CallableUsingCounter(Counter counter) {
    this.counter = counter;
  }

  @Override
  public Integer call() throws Exception {
    Integer newValue = this.counter.incrementAndGet();
    System.out.println(newValue);
    return newValue;
  }
}
