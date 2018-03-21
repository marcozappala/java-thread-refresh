package nonblocking;

import tasks.AtomicCounter;

public class NonBlockingAtomicCounter {

  public static void main(String[] args) {
    AtomicCounter counter = new AtomicCounter();
    CounterAlgorithm counterAlgorithm = new CounterAlgorithm(counter, 50);
    counterAlgorithm.count();
  }

}
