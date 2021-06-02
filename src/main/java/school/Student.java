package school;

import java.util.Arrays;
import java.util.List;

class Person {
  Person(String name) {}
}

public class Student/* extends Person */{
  private String name;
  private double gpa;
  private List<String> courses; // Set is "tricky" (but arguably better here...)

  private Student(String name, double gpa, List<String> courses) {
    // initialize "parent class" aspects.
//    super(name); // if you don't write this, the compiler generates it!
//    super();
    if (!isValid(name, gpa)) throw new IllegalArgumentException("Bad data for Student");
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  public static Student of(String name, double gpa, String ... courses) {
//    return new Student(name, gpa, Arrays.asList(courses));
    return new Student(name, gpa, List.of(courses)); // Java 9, "truly" unmodifiable list
  }

  public static boolean isValid(String name, double gpa) {
    return name != null && !name.isBlank() && gpa >= 0 && gpa < 5;
  }
}
