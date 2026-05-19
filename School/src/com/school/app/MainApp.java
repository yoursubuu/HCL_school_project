package com.school.app;

import com.school.dao.StudentDao;
import com.school.model.Student;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Find student by id");
            System.out.println("4. Update student");
            System.out.println("5. Delete student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String option = sc.nextLine();
            switch (option) {
                case "1":
                    try {
                        System.out.print("Enter id: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        Student s = new Student(id, name, age);
                        dao.addStudent(s);
                        System.out.println("Student added.");
                    } catch (Exception e) {
                        System.out.println("Failed to add student: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        List<Student> list = dao.getAllStudents();
                        if (list.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            list.forEach(System.out::println);
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to list students: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        System.out.print("Enter id: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Student s = dao.findById(id);
                        if (s == null) System.out.println("Student not found."); else System.out.println(s);
                    } catch (Exception e) {
                        System.out.println("Failed to find student: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    try {
                        System.out.print("Enter id to update: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Student existing = dao.findById(id);
                        if (existing == null) {
                            System.out.println("Student not found.");
                            break;
                        }
                        System.out.print("Enter new name (current: " + existing.getName() + "): ");
                        String name = sc.nextLine();
                        if (name.trim().isEmpty()) name = existing.getName();
                        System.out.print("Enter new age (current: " + existing.getAge() + "): ");
                        String ageStr = sc.nextLine();
                        int age = ageStr.trim().isEmpty() ? existing.getAge() : Integer.parseInt(ageStr);
                        Student updated = new Student(id, name, age);
                        boolean ok = dao.updateStudent(updated);
                        System.out.println(ok ? "Student updated." : "Update failed.");
                    } catch (Exception e) {
                        System.out.println("Failed to update student: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    try {
                        System.out.print("Enter id to delete: ");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean removed = dao.deleteById(id);
                        System.out.println(removed ? "Student deleted." : "Student not found.");
                    } catch (Exception e) {
                        System.out.println("Failed to delete student: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
