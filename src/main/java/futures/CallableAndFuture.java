package futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tasks.MyCallable;

public class CallableAndFuture {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    List<Future<Long>> list = getListOfSubmittedFutureTasks(500, executor);
    int sum = 0;
    System.out.println(list.size());
    for (Future<Long> future : list) {
      try {
        sum += future.get();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    System.out.println(sum);
    executor.shutdown();

  }

  private static List<Future<Long>> getListOfSubmittedFutureTasks(int numberOfCallables, ExecutorService executorService) {
    List<Future<Long>> listOfLongFutures = new ArrayList<>();
    for (int i = 0; i < numberOfCallables; i++) {
      Callable task = new MyCallable(100000L + i);
      listOfLongFutures.add(executorService.submit(task));
    }
    return listOfLongFutures;
  }

}