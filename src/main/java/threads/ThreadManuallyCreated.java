package threads;

import java.util.ArrayList;
import java.util.List;

import tasks.MyRunnable;

public class ThreadManuallyCreated {

  public static void main(String[] args) {
    List<Thread> listOfRunners = getListOfRunnersForMyRunnable(500);
    Thread observer = createRunnerForTask(000, new Observer(listOfRunners));

    listOfRunners.stream().forEach(runner -> runner.start());
    observer.start();

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



  private static Thread createRunnerForTask(int i, Runnable task) {
    Thread runner = new Thread(task);
    runner.setName(String.valueOf(i));
    return runner;
  }
}


class Observer implements Runnable{

  List<Thread> listOfRunners;
  public Observer(List<Thread> listOfRunners) {
    this.listOfRunners = listOfRunners;
  }

  @Override
  public void run() {
    while (anyRunnerAlive()){
      System.out.println("We have some running threads. ");
    }
  }

  private boolean anyRunnerAlive() {
    return this.listOfRunners.stream().anyMatch(Thread::isAlive);
  }

}
