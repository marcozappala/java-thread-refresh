package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import tasks.MyRunnable;

public class ThreadUsingExecutorFramework {

  private static final int MAX_NUM_THREADS = 10;

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(MAX_NUM_THREADS);
    executeRunnables(500, executor);
    executor.shutdown();
    try {
      executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("main finished");
  }

  private static void executeRunnables(int numberOfRunners, ExecutorService executorService) {
    for (int i = 0; i < numberOfRunners; i++) {
      Runnable task = new MyRunnable(100000L + i);
      executorService.execute(task);
    }
  }

}
