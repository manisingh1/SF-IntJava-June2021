package newschool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class School {
  public static <E, F> BiPredicate<E, F> both(Predicate<E> testE, Predicate<F> testF) {
    return (e, f) -> testE.test(e) && testF.test(f);
  }

  public static <E, F> BiPredicate<E, F> and(BiPredicate<E, F> first, BiPredicate<E, F> second) {
    return (e, f) -> first.test(e, f) && second.test(e, f);
  }

  public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
    return s -> first.test(s) && second.test(s);
  }

  public static <E> Predicate<E> negate(Predicate<E> pred) {
    return s -> !pred.test(s);
  }


  public static <E> void showAll(List<E> roster) {
    for (E s : roster) {
      System.out.println("> " + s);
    }
  }

  public static <E> List<E> filter(List<E> roster, Predicate<E> crit) {
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

    Predicate<Student> smart = Student.getSmartPredicate(3.5);
    System.out.println("smart: -------------------------");
    showAll(filter(roster, smart));
    System.out.println("notSmart: -------------------------");
    showAll(filter(roster, negate(smart)));

    Predicate<Student> enthusiastic = Student.getEnthusiasticPredicate(1);
    System.out.println("enthusiastic: -------------------------");
    showAll(filter(roster, enthusiastic));
    System.out.println("not enthusiastic: -------------------------");
    showAll(filter(roster, negate(enthusiastic)));

    System.out.println("smart and not enthusiastic: -------------------------");
    showAll(filter(roster, and(enthusiastic, negate(smart))));

    System.out.println("long and not first half --------------------------");
    List<String> names = List.of("Fred", "Jim", "Sheila");
    Predicate<String> longString = s -> s.length() > 3;
    Predicate<String> firstHalf = s -> s.charAt(0) <= 'M';
    showAll(filter(names, and(longString, negate(firstHalf))));

    System.out.println("long and not first half --------------------------");
    showAll(filter(names, longString.and(Predicate.not(firstHalf))));
  }
}
