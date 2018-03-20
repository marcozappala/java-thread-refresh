package tasks;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {

  private final long maxCounter;

  public MyCallable(long maxCounter) {
    this.maxCounter = maxCounter;
  }

  @Override
  public Long call() throws Exception {
    long sum = 0;
    for (int i = 0; i < maxCounter; i++) {
      sum++;
    }
    return sum;
  }
}
