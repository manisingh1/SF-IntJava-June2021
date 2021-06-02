package school;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//interface Criterion<E> {
//  boolean test(E s);
//}

//interface StudentCriterion {
//  boolean test(Student s);
//}

//class SmartStudentCriterion implements StudentCriterion {
class SmartStudentCriterion implements Criterion<Student> {
  @Override
  public boolean test(Student s) {
    return s.getGpa() > 3;
  }
}

class EnthustiasticCriterion implements Criterion<Student> {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

class LongStringCriterion implements Criterion<String> {
  @Override
  public boolean test(String s) {
    return s.length() > 4;
  }
}

public class School {
  public static <E> void showAll(List<E> roster) {
    for (E s : roster) {
      System.out.println("> " + s);
    }
  }

//  public static List<Student> getByCriterion(List<Student> roster, StudentCriterion crit) {
  public static <E> List<E> getByCriterion(List<E> roster, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : roster) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return Collections.unmodifiableList(res);
  }

//  public static List<Student> getSmart(List<Student> roster, double threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : roster) {
//      if (s.getGpa() > threshold) {
//        res.add(s);
//      }
//    }
//    // violates Liskov substitution principle, but oh well...
//    return Collections.unmodifiableList(res);
//  }
//
//  public static List<Student> getEnthusiastic(List<Student> roster, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : roster) {
//      if (s.getCourses().size() > threshold) {
//        res.add(s);
//      }
//    }
//    return Collections.unmodifiableList(res);
//  }

//  public static void showSmart(List<Student> roster, double threshold) {
//    for (Student s : roster) {
//      if (s.getGpa() > 3.5) {
//      if (s.getGpa() > threshold) {
//        System.out.println(">> " + s);
//      }
//    }
//  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );
    showAll(roster);
//    showSmart(roster);
//    showAll(getSmart(roster, 3.5));
    System.out.println("--------------");
    showAll(getByCriterion(roster, new SmartStudentCriterion()));
    // "generics" does consistency checking. This fails because "E" can't be
    // Student and String at the same time!?
//    showAll(getByCriterion(roster, new LongStringCriterion()));
    System.out.println("--------------");
    showAll(getByCriterion(roster, new EnthustiasticCriterion()));
    System.out.println("--------------");
    List<String> names = List.of("Fred", "Jim", "Sheila", "Alice");
    showAll(getByCriterion(names, new LongStringCriterion()));

    // ??? MUST BE a Criterion<String> or the code won't compile
//    showAll(getByCriterion(names, ???));
//    showAll(getByCriterion(names, /* something incomplete -- use for building Criterion<String> */));
    showAll(getByCriterion(names,
//    new class LongStringCriterion implements Criterion<String>
//    {
//      @Override
      /*public boolean test*/(String s) -> {
        return s.length() > 4;
      }
//    }
        ));

    showAll(getByCriterion(names,
          (String s) -> {
            return s.length() > 4;
          }
        ));

//    Criterion<String> cs;
//    cs =
    Object cs =
        (Criterion<String>)((String s) -> {
          return s.length() > 4;
        });

    Object obj = cs;

    System.out.println("instanceof Criterion? " + (cs instanceof Criterion));
    System.out.println("class of cs is " + cs.getClass());
    Method[] ma = cs.getClass().getMethods();
    for (Method m : ma) {
      System.out.println(">> " + m);
    }
  }
}
