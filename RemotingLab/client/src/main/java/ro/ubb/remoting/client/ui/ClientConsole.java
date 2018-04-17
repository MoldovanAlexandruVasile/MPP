package ro.ubb.remoting.client.ui;

import ro.ubb.remoting.common.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClientConsole {

    private StudentService studentService;
    private ProblemService problemService;
    private AssignService assignService;
    private GradingService gradingService;

    public ClientConsole(StudentService studentService, ProblemService problemService, AssignService assignService, GradingService gradingService) {
        this.studentService = studentService;
        this.problemService = problemService;
        this.assignService = assignService;
        this.gradingService = gradingService;
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

    private void printAssignMenu() {
        System.out.println("\n\n\t\t\t1. Add an assign.");
        System.out.println("\t\t\t2. Delete an assign.");
        System.out.println("\t\t\t3. Update an assign.");
        System.out.println("\t\t\t4. Print all assigns.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }

    private void printGradingMenu() {
        System.out.println("\n\n\t\t\t1. Add a grading.");
        System.out.println("\t\t\t2. Delete a grading.");
        System.out.println("\t\t\t3. Update a grading.");
        System.out.println("\t\t\t4. Print all gradings.");
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

    private Problem readProblem() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(bufferRead.readLine());

            System.out.print("Description: ");
            String description = bufferRead.readLine();

            Problem problem = new Problem(description);
            problem.setId(id);

            return problem;
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private Assign readAssign() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("SID: ");
            String sid = bufferRead.readLine();
            System.out.print("PID: ");
            String pid = bufferRead.readLine();
            Assign assign = new Assign(sid, pid);
            assign.setId(id);
            return assign;
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private Grading readGrading() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("SID: ");
            String aid = bufferRead.readLine();
            System.out.print("PID: ");
            Integer grade = Integer.valueOf(bufferRead.readLine());
            Grading grading = new Grading(aid, grade);
            grading.setId(id);
            return grading;
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
                            Long ID = Long.valueOf(readID());
                            studentService.deleteStudent(ID);
                            System.out.println("Student deleted !");
                        } else if (command2 == 3) {
                            Student student = readStudent();
                            studentService.updateStudent(student);
                            System.out.println("Student updated !");
                        } else if (command2 == 4) {
                            studentService.findAllStudents().forEach(student -> System.out.println(student.toString()));
                        } else if (command2 == 0) break;
                    }
                } else if (command == 2) {
                    while (true) {
                        printProblemMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Problem problem = readProblem();
                            problemService.addProblem(problem);
                            System.out.println("Problem added !");
                        } else if (command2 == 2) {
                            Long ID = Long.valueOf(readID());
                            problemService.deleteProblem(ID);
                            System.out.println("Problem deleted !");
                        } else if (command2 == 3) {
                            Problem problem = readProblem();
                            problemService.updateProblem(problem);
                            System.out.println("Problem updated !");
                        } else if (command2 == 0) break;
                        else if (command2 == 4) {
                            problemService.findAllProblems().forEach(problem -> System.out.println(problem.toString()));
                        }
                    }
                } else if (command == 3) {
                    while (true) {
                        printAssignMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Assign assign = readAssign();
                            assignService.addAssign(assign);
                            System.out.println("Assign added !");
                        } else if (command2 == 2) {
                            Long ID = Long.valueOf(readID());
                            assignService.deleteAssign(ID);
                            System.out.println("Assign deleted !");
                        } else if (command2 == 3) {
                            Assign assign = readAssign();
                            assignService.updateAssign(assign);
                            System.out.println("Assign updated !");
                        } else if (command2 == 0) break;
                        else if (command2 == 4) {
                            assignService.findAllAssigns().forEach(assign -> System.out.println(assign.toString()));
                        }
                    }
                } else if (command == 4) {
                    while (true) {
                        printGradingMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Grading grading = readGrading();
                            gradingService.addGrading(grading);
                            System.out.println("grading added !");
                        } else if (command2 == 2) {
                            Long ID = Long.valueOf(readID());
                            gradingService.deleteGrading(ID);
                            System.out.println("grading deleted !");
                        } else if (command2 == 3) {
                            Grading grading = readGrading();
                            gradingService.updateGrading(grading);
                            System.out.println("grading updated !");
                        } else if (command2 == 0) break;
                        else if (command2 == 4) {
                            gradingService.findAllGradings().forEach(grading -> System.out.println(grading.toString()));
                        }
                    }
                } else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}