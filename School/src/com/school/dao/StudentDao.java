package com.school.dao;

import com.school.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple DAO that persists a List<Student> to a file using Java serialization.
 * All I/O operations use try/catch blocks to handle errors gracefully.
 */
public class StudentDao {

    private final File file;

    public StudentDao() {
        // store data file in current working directory named students.dat
        this.file = new File(System.getProperty("user.dir") + File.separator + "students.dat");
    }

    @SuppressWarnings("unchecked")
    private List<Student> readAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                return (List<Student>) obj;
            }
        } catch (FileNotFoundException e) {
            // not found -> empty list
        } catch (EOFException e) {
            // empty file -> empty list
        } catch (Exception e) {
            System.err.println("Failed to read students: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeAll(List<Student> list) {
        // ensure parent directory exists (though we write to working dir)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (Exception e) {
            System.err.println("Failed to write students: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addStudent(Student s) {
        try {
            List<Student> list = readAll();
            list.add(s);
            writeAll(list);
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        try {
            return readAll();
        } catch (Exception e) {
            System.err.println("Error getting students: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Student findById(int id) {
        try {
            for (Student s : readAll()) {
                if (s.getId() == id) return s;
            }
        } catch (Exception e) {
            System.err.println("Error finding student: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        try {
            List<Student> list = readAll();
            boolean removed = list.removeIf(s -> s.getId() == id);
            if (removed) writeAll(list);
            return removed;
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudent(Student updated) {
        try {
            List<Student> list = readAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == updated.getId()) {
                    list.set(i, updated);
                    writeAll(list);
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
