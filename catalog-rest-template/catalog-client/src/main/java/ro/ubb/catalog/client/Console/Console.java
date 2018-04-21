package ro.ubb.catalog.client.Console;

import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.core.model.Problem;
import ro.ubb.catalog.core.model.Student;
import ro.ubb.catalog.web.dto.ProblemDto;
import ro.ubb.catalog.web.dto.ProblemsDto;
import ro.ubb.catalog.web.dto.StudentDto;
import ro.ubb.catalog.web.dto.StudentsDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private RestTemplate restTemplate;

    public Console(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
                            Student myStudent = readStudent();
                            StudentDto studentDto = new StudentDto(myStudent.getSerialNumber(), myStudent.getName());
                            restTemplate.postForObject("http://localhost:8080/api/students",
                                            studentDto, StudentDto.class);
                            System.out.println("Student added !");
                        } else if (command2 == 2) {
                            Integer ID = Integer.valueOf(readID());
                            restTemplate.delete("http://localhost:8080/api/students/{id}", ID);
                            System.out.println("Student deleted !");
                        } else if (command2 == 3) {
                            Integer ID = Integer.valueOf(readID());
                            Student myStudent = readStudent();
                            restTemplate.put("http://localhost:8080/api/students/{id}", myStudent, ID);
                            System.out.println("Student updated !");
                        } else if (command2 == 4) {
                            StudentsDto studentsDto = restTemplate
                                    .getForObject("http://localhost:8080/api/students", StudentsDto.class);
                            System.out.println(studentsDto);
                        } else if (command2 == 0) break;
                    }
                } else if (command == 0) break;
                else if (command == 2) {
                    while (true) {
                        printProblemMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Problem myProblem = readProblem();
                            ProblemDto problemDto = new ProblemDto(myProblem.getDescription());
                            restTemplate.postForObject("http://localhost:8080/api/problems",
                                    problemDto, ProblemDto.class);
                            System.out.println("Problem added !");
                        } else if (command2 == 2) {
                            Integer ID = Integer.valueOf(readID());
                            restTemplate.delete("http://localhost:8080/api/problems/{id}", ID);
                            System.out.println("Problem deleted !");
                        } else if (command2 == 3) {
                            Integer ID = Integer.valueOf(readID());
                            Problem myProblem = readProblem();
                            restTemplate.put("http://localhost:8080/api/problems/{id}", myProblem, ID);
                            System.out.println("Problem updated !");
                        } else if (command2 == 4) {
                            ProblemsDto problemsDto = restTemplate
                                    .getForObject("http://localhost:8080/api/problems", ProblemsDto.class);
                            System.out.println(problemsDto);
                        } else if (command2 == 0) break;
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
