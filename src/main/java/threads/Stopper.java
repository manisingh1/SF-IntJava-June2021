package threads;

public class Stopper {
  static /*volatile*/ boolean stop = false;
  public static void main(String[] args) throws Throwable {
    Runnable r = () -> {
      System.out.println("Starting");
      while (!stop)
        System.out.print(".");
        ;
      System.out.println("stopping");
    };
    new Thread(r).start();
    Thread.sleep(1000);
    stop = true;
    System.out.println("Shutting down");
  }
}
