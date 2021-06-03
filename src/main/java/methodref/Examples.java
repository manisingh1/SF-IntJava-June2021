package methodref;

import java.util.ArrayList;
import java.util.List;

public class Examples {
  public static int backwardString(String s1, String s2) {
    return s2.compareTo(s1);
  }

  public static void main(String[] args) {
    List<String> names = new ArrayList<>(List.of("Fred", "Jim", "Sheila"));
    names.sort(
        (s1, s2) -> Examples.backwardString(s1, s2)
    );
    names.sort(
        Examples::backwardString
    );
  }
}
