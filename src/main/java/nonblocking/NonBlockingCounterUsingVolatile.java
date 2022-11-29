package nonblocking;

import tasks.CounterUsingVolatile;

public class NonBlockingCounterUsingVolatile {

  public static void main(String[] args) {
    CounterUsingVolatile counter = new CounterUsingVolatile();
    CounterAlgorithm counterAlgorithm = new CounterAlgorithm(counter, 50);
    counterAlgorithm.count();
  }
}
