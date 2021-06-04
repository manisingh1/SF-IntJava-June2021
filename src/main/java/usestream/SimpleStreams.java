package usestream;

import newschool.Student;

import java.util.List;

public class SimpleStreams {
  public static void main(String[] args) {
    System.out.println("=======================");
    List<Student> students = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

//    students.stream()

  }
}
