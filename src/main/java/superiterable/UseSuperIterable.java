package superiterable;

import java.util.List;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(List.of("Fred", "Jim", "Sheila"));

    for (String s : sis) {
      System.out.println("> " + s);
    }

    var sis2 = sis
        .filter(s -> s.length() > 3)
        .filter(s -> s.length() < 6);
    for (String s : sis2) {
      System.out.println(">>>> " + s);
    }
    System.out.println("=======================");

    var sis3 = sis
        .filter(s -> s.length() > 3)
        .map(s -> "Dear " + s + " your name has " + s.length() + " characters");

    for (String s : sis3) {
      System.out.println(s);
    }
  }
}
