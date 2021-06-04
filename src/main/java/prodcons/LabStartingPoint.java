package prodcons;

public class LabStartingPoint {
  public static void main(String[] args) {
    // create an ArrayBlockingQueue<int[]> give it ten cells

    Runnable producer = () -> {
      // count from 0 to 10_000
      // create a NEW array with {0, count}
      // if count < 100, pause "data is transactionally invalid!!!"
      // modify the array to {count, count}
      // put the array into the queue, null out the reference
      // end loop
    };
    // make a thread and start it
    Runnable consumer = () -> {
      // count from 0 to 10_000
      // take an item from the queue
      // verify that the two items in the array have the same value as the count
      // (expect one item to fail the test!)
      // if count > 9_900 pause a bit...
      // end loop
    };
    // make the second thread and start it
    // wait for both threads to finish then print goodbye
  }

}
