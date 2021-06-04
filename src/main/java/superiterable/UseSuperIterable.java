package superiterable;

import newschool.Student;

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
    System.out.println("=======================");

    sis
        .filter(s -> s.length() > 3)
        .map(s -> "Dear " + s + " your name still has " + s.length() + " characters")
        .forEvery(s -> System.out.println(s)); // forEvery is illustrative, forEach is built in :)

    System.out.println("=======================");
    SuperIterable<Student> roster = new SuperIterable<>(List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    ));
    roster
        .filter(s -> s.getGpa() > 3.0)
//        .map(s -> s.getCourses())
        .map(Student::getCourses)
//        .forEach(s -> System.out.println(s));
        .forEach(System.out::println);

    System.out.println("=======================");
    roster
        .filter(s -> s.getGpa() > 3.0)
        .flatMap(s -> new SuperIterable(s.getCourses()))
        .forEach(System.out::println);
  }
}
