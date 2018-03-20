import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureWithCallback {

  public static void main(String[] args) {
    long startedAtMillis = System.currentTimeMillis();

    CompletableFuture<Integer> futureCount = getSuppliedCompletableFutureCount().thenApply((Integer count) -> {
      System.out.println("i am in the thenApply. Doubled Count is: " + count * 2);
      return count * 2;
    });

    System.out.println("Start FutureCount  Took " + (System.currentTimeMillis() - startedAtMillis) + " milliseconds");

    waitForFutureCountcompletionAndPrintResult(futureCount);

    System.out.println("at the end of main");

  }

  private static void waitForFutureCountcompletionAndPrintResult(CompletableFuture<Integer> futureCount) {
    try {
      System.out.println("Result: " + futureCount.get());
    }
    catch (InterruptedException | ExecutionException e) {

    }
  }

  private static CompletableFuture<Integer> getSuppliedCompletableFutureCount() {
    CompletableFuture<Integer> futureCount = CompletableFuture.supplyAsync(() -> {
      try {
        // simulate long running task
        Thread.sleep(5000);
        System.out.println("after 5 seconds");
      }
      catch (InterruptedException e) {
      }
      return 20;
    });
    return futureCount;
  }

}
