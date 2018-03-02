public class MyRunnable implements Runnable {

  private final long maxCounter;

  public MyRunnable(long maxCounter) {
    this.maxCounter = maxCounter;
  }

  public void run() {
    int sum = 0;
    for (int i = 0; i < maxCounter; i++) {
      sum++;
    }
    System.out.println(sum);
  }
}
