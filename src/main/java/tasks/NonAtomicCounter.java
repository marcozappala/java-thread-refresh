package tasks;

public class NonAtomicCounter implements Counter {

  private Integer value = new Integer(0);

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public int incrementAndGet() {
    return value++;
  }
}
