package threads;

import java.util.concurrent.atomic.AtomicLong;

public class CounterWithAtomic {
  private static AtomicLong count = new AtomicLong(0);

  public static void main(String[] args) throws Throwable {
// Lambda implementing the void run() method of the Runnable interface
    Runnable r = () -> {
      System.out.println("Worker thread is called: " + Thread.currentThread().getName());
      for (int i = 0; i < 100_000; i++) {
        count.incrementAndGet();
      }
    };
//    r.run();
//    r.run();
    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    t1.start();
    t2.start();
//    Thread.sleep(1);
    t1.join();
    t2.join();
    System.out.println("count = " + count.get());
    System.out.println("Name of thread running main method is "
        + Thread.currentThread().getName());
  }
}
