package threads;

public class Counter {
  private static long count = 0;

  public static void main(String[] args) throws Throwable {
// Lambda implementing the void run() method of the Runnable interface
    Runnable r = () -> {
      System.out.println("Worker thread is called: " + Thread.currentThread().getName());
      for (int i = 0; i < 100_000; i++) {
        count++;
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
    System.out.println("count = " + count);
    System.out.println("Name of thread running main method is "
        + Thread.currentThread().getName());
  }
}
