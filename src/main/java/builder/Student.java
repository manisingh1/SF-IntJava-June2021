package builder;

import java.util.ArrayList;
import java.util.List;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses = new ArrayList<>();

  private Student() {}

  public static void validate(String name, double gpa, List<String> courses) {
    if (name == null || name.isBlank() || gpa < 0 || gpa > 5) {
      throw new IllegalArgumentException("Bad!");
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }

  public static class Builder {
    private Student self = new Student();

    private Builder() {}

    public Builder name(String n) {
      self.name = n;
      return this;
    }

    public Builder gpa(double gpa) {
      self.gpa = gpa;
      return this;
    }

    public Builder course(String c) {
      self.courses.add(c);
      return this;
    }

    public Student build() {
      Student.validate(self.name, self.gpa, self.courses);
      // should null out self so this can't be reused to damage the
      // student we are creating.
      return self;
    }
  }
}
