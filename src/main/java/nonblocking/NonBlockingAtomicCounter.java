package nonblocking;

import tasks.AtomicCounter;

public class NonBlockingAtomicCounter {

  public static void main(String[] args) {
    AtomicCounter counter = new AtomicCounter();
    CounterAlgorithm counterAlgorithm = new CounterAlgorithm(counter);
    counterAlgorithm.count();
  }

}
