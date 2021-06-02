package flyweight;

import java.util.HashMap;
import java.util.Map;

final class Course {
  private static Map<String, Course> thePool = new HashMap<>();
  private final String coursename; // pooled & shared objects should be immutable!

  private Course(String name) {
    this.coursename = name;
  }

  public static Course of(String name) {
    Course theCourse = thePool.get(name);
    if (theCourse == null) {
      System.out.println("Creating a new Course with name " + name);
      theCourse = new Course(name);
      thePool.put(name, theCourse);
    }
    return theCourse;
  }

  @Override
  public String toString() {
    return "Course{" +
        "coursename='" + coursename + '\'' +
        '}';
  }
}

public class Factory {
  public static void main(String[] args) {
    Course c1 = Course.of("Math");
    Course c2 = Course.of("Physics");
    Course c3 = Course.of("Math");
    System.out.println("c1 is " + c1);
    System.out.println("c2 is " + c2);
    System.out.println("c3 is " + c3);
    System.out.println("c1 == c2? " + (c1 == c2));
    System.out.println("c1 == c3? " + (c1 == c3));
  }
}
