package school;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UseOverride extends Object {
  @Override
  public String toString() {
    return "I'm a UseOverride";
  }
}

@FunctionalInterface
interface Criterion<E> {
  boolean test(E s);
//  void bad();
  static void doStuff() {}
  default void doMoreStuff() {}
}

public class LambdaSchool {
  public static <E> void showAll(List<E> roster) {
    for (E s : roster) {
      System.out.println("> " + s);
    }
  }

  public static <E> List<E> getByCriterion(List<E> roster, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : roster) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return Collections.unmodifiableList(res);
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    // Use showAll and getByCriterion with lambda expressions to show:

    System.out.println("-----------------------------");
    // less-smart students gpa < 3.5
    showAll(getByCriterion(roster,
    (Student s) -> {
      return s.getGpa() < 3.5;
    }
    ));
    System.out.println("-----------------------------");
    // students with names shorter than 4 characters
    showAll(getByCriterion(roster,
        (Student s) -> {
          return s.getName().length() < 4;
        }
        ));
    System.out.println("-----------------------------");
    // students with more than 3 courses
    showAll(getByCriterion(roster,
        (Student s) -> {
          return s.getCourses().size() > 3;
        }
        ));
    System.out.println("-----------------------------");

    System.out.println("-----------------------------");
    // less-smart students gpa < 3.5
    showAll(getByCriterion(roster,
        (Student s) -> {
          return s.getGpa() < 3.5;
        }
    ));
    System.out.println("-----------------------------");
    // students with names shorter than 4 characters
    showAll(getByCriterion(roster,
// BLOCK LAMBDA has a FULL method body ( loops, try/catch, complex calculations, local var decls
//        (String s) -> {
//        (var s) -> {
//        (s) -> {
//        s -> {
//          return s.getName().length() < 4;
//        }
/// IFF lambda function body is of the form "return <expr>;" (NOTHING MORE)
//  can replace with <expr>
// "expression lambda"
        s -> s.getName().length() < 4
    ));
    System.out.println("-----------------------------");
    // students with more than 3 courses
    showAll(getByCriterion(roster, s -> s.getCourses().size() > 3));
    System.out.println("-----------------------------");
//    (String s, String t) -> {...}
//    (@Deprecated var s, var t) -> {...}
//    (s, t) -> {...}
//    () -> {} // GOOD (given context :)
//    -> {} // Headless?? BAD

    List<Student> mutableList = new ArrayList<>(roster);
//    mutableList.add(null);
    System.out.println(mutableList);
    System.out.println("ordered by descending gpa");
//    int a;
    mutableList.sort(
        (a, b) -> Double.compare(b.getGpa(), a.getGpa())
    );
    System.out.println(mutableList);
    System.out.println("ordered by enthusiam");
    mutableList.sort(
        (a, b) -> Integer.compare(a.getCourses().size(), b.getCourses().size())
    );
    System.out.println(mutableList);
  }
}
