package prodcons;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class LabStartingPoint {
  public static void main(String[] args) throws InterruptedException {
    // create an ArrayBlockingQueue<int[]> give it ten cells
    BlockingQueue<int[]> queue = new ArrayBlockingQueue<>(10);

    Runnable producer = () -> {
      try {
        // count from 0 to 10_000
        for (int count = 0; count < 10_000; count++) {
          // create a NEW array with {0, count}
          int[] data = {0, count};
          // if count < 100, pause "data is transactionally invalid!!!"
          if (count < 100) {
            Thread.sleep(1);
          }
          // modify the array to {count, count}
          data[0] = count;
          if (count == 5_000) {
            data[1] = -1; // test the test
          }
          // put the array into the queue, null out the reference
          queue.put(data);
        }
        // end loop
      } catch (InterruptedException ie) {
        System.out.println("Producer received request to shutdown");
      }
      System.out.println("Producer shutting down");
    };
    // make a thread and start it
    Thread prod = new Thread(producer);
    prod.start();
    Runnable consumer = () -> {
      try {
        // count from 0 to 10_000
        for (int count = 0; count < 10_000; count++) {
          // take an item from the queue
          int [] data = queue.take();
          // verify that the two items in the array have the same value as the count
          if (data[0] != data[1] || data[0] != count) {
            // (expect one item to fail the test!)
            System.out.printf("**** ERROR at item %d, values %d, %d\n",
                count, data[0], data[1]);
          }
          // if count > 9_900 pause a bit...
          if (count > 9_900) {
            Thread.sleep(1);
          }
        }
        // end loop
      } catch (InterruptedException ie) {
        System.out.println("Consumer receved shutdown request");
      }
      System.out.println("Consumer shutting down");
    };
    // make the second thread and start it
    Thread cons = new Thread(consumer);
    cons.start();
    // wait for both threads to finish then print goodbye
    prod.join();
    cons.join();
    System.out.println("All threads shut down, main exiting");
  }
}
