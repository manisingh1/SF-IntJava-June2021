package school;

import java.util.List;

class Person {
  Person(String name) {}
}

class EnthusiasticStudent extends Student2 {
  private double enthusiasmMultiplier;
  EnthusiasticStudent(String name, double gpa, List<String> courses, double enthusiamMultipler) {
    super(name, gpa, courses);
    this.enthusiasmMultiplier = enthusiamMultipler;
  }
}

public class Student2/* extends Person */{
  private String name;
  private double gpa;
  private List<String> courses; // Set is "tricky" (but arguably better here...)

  // new causes allocation and zeroing of memory for THIS SPECIFIC object
  // constructors can ONLY return a NEW object of EXACTLY the specified type,
  // or an exception
  /*private*/ Student2(String name, double gpa, List<String> courses) {

    // initialize "parent class" aspects.
//    super(name); // if you don't write this, the compiler generates it!
//    super();
    if (!isValid(name, gpa)) throw new IllegalArgumentException("Bad data for Student");
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  // Factories (any method that returns something) can return:
  // A new object
  // A preexisting object
  // Of any assignment compatible type
  // or an exception
  public static Student2 of(String name, double gpa, String ... courses) {
//    return new Student(name, gpa, Arrays.asList(courses));
    if (courses.length >= 4) {
      return new EnthusiasticStudent(name, gpa, List.of(courses), 1.1);
    } else {
      return new Student2(name, gpa, List.of(courses)); // Java 9, "truly" unmodifiable list
    }
  }

  public static boolean isValid(String name, double gpa) {
    return name != null && !name.isBlank() && gpa >= 0 && gpa < 5;
  }
}
