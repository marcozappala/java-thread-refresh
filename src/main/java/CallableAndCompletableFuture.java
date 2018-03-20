import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CallableAndCompletableFuture {

  public static void main(String[] args) {
    long startedAtMillis = System.currentTimeMillis();

    CompletableFuture<Integer> futureCount = getSuppliedCompletableFutureCount();

    System.out.println("Start FutureCount  Took " + (System.currentTimeMillis() - startedAtMillis) + " milliseconds");

    waitForFutureCountcompletionAndPrintResult(startedAtMillis, futureCount);


  }


  private static void waitForFutureCountcompletionAndPrintResult(long startedAtMillis,
      CompletableFuture<Integer> futureCount) {
    try {
      int count = futureCount.get();
      System.out.println("CompletableFuture took " + (System.currentTimeMillis() - startedAtMillis) + " milliseconds");
      System.out.println("Result " + count);
    }
    catch (InterruptedException | ExecutionException ex) {
      // Exceptions from the future should be handled here
    }
  }

  private static CompletableFuture<Integer> getSuppliedCompletableFutureCount() {
    CompletableFuture<Integer> futureCount = CompletableFuture.supplyAsync(() -> {
      try {
        // simulate long running task
        Thread.sleep(5000);
      }
      catch (InterruptedException e) {
      }
      return 20;
    });
    return futureCount;
  }

}
