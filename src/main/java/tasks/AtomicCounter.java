package tasks;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter{

  private AtomicInteger value = new AtomicInteger();

  public int getValue() {
    return value.get();
  }

  public int incrementAndGet() {
    return value.incrementAndGet();
  }
}
