package ro.ubb.jpa.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.jpa.domain.Problem;
import ro.ubb.jpa.domain.Student;
import ro.ubb.jpa.service.ProblemService;
import ro.ubb.jpa.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Console {

    @Autowired
    private StudentService studentService;
    private ProblemService problemService;

    public Console(StudentService studentService, ProblemService problemService) {
        this.studentService = studentService;
        this.problemService = problemService;
    }

    public void runConsole() {
        try {
            while (true) {
                printMenu();
                int command, command2;
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                command = Integer.valueOf(bufferRead.readLine());
                if (command == 1) {
                    while (true) {
                        printStudentMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Student student = readStudent();
                            studentService.addStudent(student);
                            System.out.println("Student added !");
                        } else if (command2 == 2) {
                            Integer ID = Integer.valueOf(readID());
                            studentService.deleteStudent(ID);
                            System.out.println("Student deleted !");
                        } else if (command2 == 3) {
                            Student student = readStudent();
                            studentService.updateStudent(student);
                            System.out.println("Student updated !");
                        } else if (command2 == 4) {
                            studentService.findAll().forEach(student -> System.out.println(student.toString()));
                        } else if (command2 == 0) break;
                    }
                } else if (command == 0) break;
                else if (command == 2) {
                    while (true) {
                        printProblemMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Problem problem = readProblem();
                            problemService.addProblem(problem);
                            System.out.println("Problem added !");
                        } else if (command2 == 2) {
                            Integer ID = Integer.valueOf(readID());
                            problemService.deleteProblem(ID);
                            System.out.println("Problem deleted !");
                        } else if (command2 == 3) {
                            Problem problem = readProblem();
                            problemService.updateProblem(problem);
                            System.out.println("Problem updated !");
                        } else if (command2 == 0) break;
                        else if (command2 == 4) {
                            problemService.findAll().forEach(problem -> System.out.println(problem.toString()));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printMenu() {
        System.out.println("\n\n\t1. Student operations.");
        System.out.println("\t2. Problem operations.");
        System.out.println("\t3. Assign operations.");
        System.out.println("\t4. Grading operations.");
        System.out.println("\t0. Exit.");
        System.out.print("\t\tInput an option: ");
    }

    private void printStudentMenu() {
        System.out.println("\n\n\t\t\t1. Add a student.");
        System.out.println("\t\t\t2. Delete a Student.");
        System.out.println("\t\t\t3. Update a student.");
        System.out.println("\t\t\t4. Print all students.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }

    private void printProblemMenu() {
        System.out.println("\n\n\t\t\t1. Add a problem.");
        System.out.println("\t\t\t2. Delete a problem.");
        System.out.println("\t\t\t3. Update a problem.");
        System.out.println("\t\t\t4. Print all problems.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }

    private Student readStudent() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Serial number: ");
            String serialNumber = bufferRead.readLine();
            System.out.print("Name: ");
            String name = bufferRead.readLine();
            Student student = new Student(serialNumber, name);
            return student;
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private Problem readProblem() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {

            System.out.print("Description: ");
            String description = bufferRead.readLine();

            Problem problem = new Problem(description);

            return problem;
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


    private String readID() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Integer id = Integer.valueOf(bufferRead.readLine());
            return String.valueOf(id);
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return String.valueOf(-1);
    }
}
