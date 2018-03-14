import java.util.ArrayList;
import java.util.List;

public class ThreasManuallyCreated {

  public static void main(String[] args) {
    List<Thread> listOfRunners = new ArrayList<>();
    listOfRunners = getListOfRunnersForMyRunnable(500);
    printNumberOfRunnerStillRunningIfAny(listOfRunners);
    System.out.println("main finished");
  }

  private static List<Thread> getListOfRunnersForMyRunnable(int numberOfRunners) {
    List<Thread> listOfRunners = new ArrayList<>();
    for (int i = 0; i < numberOfRunners; i++) {
      Runnable task = new MyRunnable(100000L + i);
      Thread runner = createRunnerForTask(i, task);
      listOfRunners.add(runner);
    }
    return listOfRunners;
  }

  private static void printNumberOfRunnerStillRunningIfAny(List<Thread> listOfRunners) {
    int numberOfRunnersStillRunning = 0;
    do {
      numberOfRunnersStillRunning = howManyRunnersStillRunning(listOfRunners);
      System.out.println("We have " + numberOfRunnersStillRunning + " running threads. ");
    }
    while (numberOfRunnersStillRunning > 0);
  }

  private static int howManyRunnersStillRunning(List<Thread> listOfRunners) {
    int numberOfRunnersStillRunning;
    numberOfRunnersStillRunning = 0;
    for (Thread worker : listOfRunners) {
      if (worker.isAlive()) {
        numberOfRunnersStillRunning++;
      }
    }
    return numberOfRunnersStillRunning;
  }

  private static Thread createRunnerForTask(int i, Runnable task) {
    Thread runner = new Thread(task);
    runner.setName(String.valueOf(i));
    runner.start();
    return runner;
  }
}
