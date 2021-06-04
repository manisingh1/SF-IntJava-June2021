package usestream;

import newschool.Student;
import superiterable.SuperIterable;

import java.util.List;

public class SimpleStreams {
  public static void main(String[] args) {
    System.out.println("=======================");
    List<Student> students = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    students.stream()
        .filter(s -> s.getGpa() > 3.0)
        .flatMap(s -> s.getCourses().stream())
        .forEach(System.out::println);

    students.stream()
        .forEach(System.out::println);

//    All starting with: students.stream()
//    All ending with: .forEach(System.out::println)

//    Print all students
    System.out.println("all students --------------------------");
    students.stream()
        .forEach(System.out::println);

//    Print all student names
    System.out.println("all student names --------------------------");
    students.stream()
//        .map(s -> s.getName())
        .map(Student::getName)
        .forEach(System.out::println);

//    Print "name has grade" for all students
    System.out.println("name has grade --------------------------");
    students.stream()
        .map(s -> s.getName() + " has grade " + s.getGpa())
        .forEach(System.out::println);

//    Print "name takes <n> courses" for all smart students
    System.out.println("name takes <n> courses --------------------------");
    students.stream()
        .filter(s -> s.getGpa() > 3.0)
        .map(s -> s.getName() + " takes " + s.getCourses().size() + " courses")
        .forEach(System.out::println);

//    Print all courses
    System.out.println("all courses --------------------------");
    students.stream()
        .flatMap(s -> s.getCourses().stream())
        .forEach(System.out::println);

//    Print all courses taken by students with gpa > 3
    System.out.println("all courses of students with gpa > 3 --------------------------");
    students.stream()
        .filter(s -> s.getGpa() > 3.0)
        .flatMap(s -> s.getCourses().stream())
        .forEach(System.out::println);

//    Print "name takes course" for all student/course pairs
    System.out.println("name takes course --------------------------");
    students.stream()
//        .flatMap((Student s) -> {
//          return s.getCourses().stream().map(c -> s.getName() + " takes " + c);
//        })
        .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
        .forEach(System.out::println);

    System.out.println("curious --------------------------");
    students.stream()
        .map(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
        .flatMap(s -> s)
        .forEach(System.out::println);
  }
}
