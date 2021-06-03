package newschool;

import java.util.List;
import java.util.function.Predicate;

public final class Student/* extends Person */{
  private String name;
  private double gpa;
  private List<String> courses; // Set is "tricky" (but arguably better here...)

  private Student(String name, double gpa, List<String> courses) {
    if (!isValid(name, gpa)) throw new IllegalArgumentException("Bad data for Student");
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  public static Student of(String name, double gpa, String ... courses) {
      return new Student(name, gpa, List.of(courses)); // Java 9, "truly" unmodifiable list
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return courses;
  }

  public static boolean isValid(String name, double gpa) {
    return name != null && !name.isBlank() && gpa >= 0 && gpa < 5;
  }

  public static Predicate<Student> getSmartPredicate(/*final*/ double threshold) {
//    threshold++;
    return s -> s.getGpa() > /*++*/threshold;
  }

//  public static Predicate<Student> getNotSmartPredicate(/*final*/ double threshold) {
////    threshold++;
//    return s -> s.getGpa() <= /*++*/threshold;
//  }

  public static Predicate<Student> getEnthusiasticPredicate(int threshold) {
    return s -> s.getCourses().size() > threshold;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}
