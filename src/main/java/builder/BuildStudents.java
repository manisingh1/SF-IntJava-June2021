package builder;

public class BuildStudents {
  public static void main(String[] args) {
    Student thing = Student.builder()
        .name("Fred")
        .course("Math")
        .gpa(3.2)
        .course("Physics")
        .gpa(3.6)
        .build();

    System.out.println(thing);
  }
}
