package nonblocking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tasks.CallableUsingCounter;
import tasks.Counter;

public class CounterAlgorithm {

  Counter counter;

  public CounterAlgorithm(Counter counter) {
    this.counter = counter;
  }

  public void count() {
    List<Future<Integer>> listOfFutures = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 500; i++) {
      Callable<Integer> task = new CallableUsingCounter(counter);
      listOfFutures.add(executorService.submit(task));
    }

    executorService.shutdown();

    waitUntilExecutorEndAllSubmittedTasks(executorService);

    verifiesNoDoubleEntriesInCalculatedIntegers(listOfFutures);
  }

  private void verifiesNoDoubleEntriesInCalculatedIntegers(List<Future<Integer>> listOfFutures) {
    Set<Integer> set = new HashSet<Integer>();
    for (Future<Integer> future : listOfFutures) {
      try {
        set.add(future.get());
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    if (listOfFutures.size() != set.size()) {
      throw new RuntimeException("Double-entries!!!");
    }
  }

  private static void waitUntilExecutorEndAllSubmittedTasks(ExecutorService executorService) {
    while (!executorService.isTerminated()) {
    }
  }
}
