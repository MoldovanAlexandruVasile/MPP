package ro.ubb.remoting.client.ui;

import ro.ubb.remoting.common.StudentService;
import ro.ubb.remoting.common.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClientConsole {
    private StudentService studentService;

    public ClientConsole(StudentService studentService) {
        this.studentService = studentService;
    }

    private void printStudentMenu() {
        System.out.println("\n\n\t\t\t1. Add a student.");
        System.out.println("\t\t\t2. Delete a Student.");
        System.out.println("\t\t\t3. Update a student.");
        System.out.println("\t\t\t4. Print all students.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }

    private Student readStudent() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("Serial number: ");
            String serialNumber = bufferRead.readLine();
            System.out.print("Name: ");
            String name = bufferRead.readLine();
            Student student = new Student(serialNumber, name);
            student.setId(id);
            return student;
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private String readID() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            return String.valueOf(id);
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return String.valueOf(-1);
    }

    public void runConsole() {
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                printStudentMenu();
                int command2 = Integer.valueOf(bufferRead.readLine());
                if (command2 == 1) {
                    Student student = readStudent();
                    studentService.addStudent(student);
                    System.out.println("Student added.");
                } else if (command2 == 2) {
                    Long ID = Long.valueOf(readID());
                    studentService.deleteStudent(ID);
                    System.out.println("Student deleted.");
                } else if (command2 == 3) {
                    Student student = readStudent();
                    studentService.updateStudent(student);
                    System.out.println("Student updated.");
                } else if (command2 == 4) {
                    System.out.println("\n");
                    studentService.findAllStudents().forEach(student -> {
                        System.out.println(student.toString());
                    });
                } else if (command2 == 0) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}