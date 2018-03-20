package nonblocking;

import tasks.NonAtomicCounter;

public class NonBlockingNonAtomicCounter {

  public static void main(String[] args) {
    NonAtomicCounter counter = new NonAtomicCounter();
    CounterAlgorithm counterAlgorithm = new CounterAlgorithm(counter);
    counterAlgorithm.count();
  }
}
