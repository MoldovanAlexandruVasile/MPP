package ro.ubb.LabProb.UI;

import ro.ubb.LabProb.Domain.Assign;
import ro.ubb.LabProb.Domain.Grading;
import ro.ubb.LabProb.Domain.Problem;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Service.AssignService;
import ro.ubb.LabProb.Service.GradingService;
import ro.ubb.LabProb.Service.ProblemService;
import ro.ubb.LabProb.Service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Console {
    private StudentService studentService;
    private ProblemService problemService;
    private AssignService assignService;
    private GradingService gradingService;

    public Console(StudentService studentService, ProblemService problemService, AssignService assignService, GradingService gradingService) {
        this.studentService = studentService;
        this.problemService = problemService;
        this.assignService = assignService;
        this.gradingService = gradingService;
    }

    public void runConsole() {
        while (true) {
            printMenu();
            Scanner scan = new Scanner(System.in);
            int s = scan.nextInt();
            if (s == 1) {
                while (true) {
                    printStudentMenu();
                    Scanner scan2 = new Scanner(System.in);
                    int s2 = scan2.nextInt();
                    if (s2 == 1) addStudent();
                    else if (s2 == 2) deleteStudent();
                    else if (s2 == 3) updateStudent();
                    else if (s2 == 4) printAllStudents();
                    else if (s2 == 5) filterStudents();
                    else if (s2 == 6) studentLongName();
                    else if (s2 == 0) break;
                }
            } else if (s == 2) {
                while (true) {
                    printProblemMenu();
                    Scanner scan2 = new Scanner(System.in);
                    int s2 = scan2.nextInt();
                    if (s2 == 1) addProblem();
                    else if (s2 == 2) deleteProblem();
                    else if (s2 == 3) updateProblem();
                    else if (s2 == 4) printAllProblems();
                    else if (s2 == 5) filterProblems();
                    else if (s2 == 0) break;
                }
            } else if (s == 3) {
                while (true) {
                    printAssignMenu();
                    Scanner scan2 = new Scanner(System.in);
                    int s2 = scan2.nextInt();
                    if (s2 == 1) addAssign();
                    else if (s2 == 2) deleteAssign();
                    else if (s2 == 3) updateAssign();
                    else if (s2 == 4) printAllAssigns();
                    else if (s2 == 5) filterAssign();
                    else if (s2 == 6) mostAssigned();
                    else if (s2 == 0) break;
                }
            } else if (s == 4) {
                while (true) {
                    printGradingMenu();
                    Scanner scan2 = new Scanner(System.in);
                    int s2 = scan2.nextInt();
                    if (s2 == 1) addGrading();
                    else if (s2 == 2) deleteGrading();
                    else if (s2 == 3) updateGrading();
                    else if (s2 == 4) printAllGradings();
                    else if (s2 == 5) filterGradings();
                    else if (s2 == 0) break;
                }
            } else if (s == 0) break;
        }
    }

    private void printMenu() {
        System.out.println("\n\n\t1. Student operations.");
        System.out.println("\t2. Problem operations.");
        System.out.println("\t3. Assign operations.");
        System.out.println("\t4. Grading operations.");
        System.out.println("\t0. Back.");
        System.out.print("\t\tInput an option: ");
    }

    private void printStudentMenu() {
        System.out.println("\n\n\t\t\t1. Add a student.");
        System.out.println("\t\t\t2. Delete a Student.");
        System.out.println("\t\t\t3. Update a student.");
        System.out.println("\t\t\t4. Print all students.");
        System.out.println("\t\t\t5. Filter students by name.");
        System.out.println("\t\t\t6. Get longest student name.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }

    private void printProblemMenu() {
        System.out.println("\n\n\t\t\t1. Add a problem.");
        System.out.println("\t\t\t2. Delete a problem.");
        System.out.println("\t\t\t3. Update a problem.");
        System.out.println("\t\t\t4. Print all problems.");
        System.out.println("\t\t\t5. Filter problems by description.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }

    private void printAssignMenu() {
        System.out.println("\n\n\t\t\t1. Add an assign.");
        System.out.println("\t\t\t2. Delete an assign.");
        System.out.println("\t\t\t3. Update an assign.");
        System.out.println("\t\t\t4. Print all assigns.");
        System.out.println("\t\t\t5. Filter assigns by student ID.");
        System.out.println("\t\t\t6. Most assigned problem.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }

    private void printGradingMenu() {
        System.out.println("\n\n\t\t\t1. Add a grading.");
        System.out.println("\t\t\t2. Delete a grading.");
        System.out.println("\t\t\t3. Update a grading.");
        System.out.println("\t\t\t4. Print all gradings.");
        System.out.println("\t\t\t5. Filter gradings by assign ID.");
        System.out.println("\t\t\t0. Back.");
        System.out.print("\t\t\t\tInput an option: ");
    }


    //============================STUDENT================================
    private void studentLongName() {
        Set<Student> students = studentService.getAllStudents();
        Optional<String> longestName = students.stream().map(student -> student.getName())
                .reduce((student1, student2) -> student1.length() >= student2.length() ? student1 : student2);
        System.out.println(longestName.toString());
    }

    private void filterStudents() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Name: ");
            String name = bufferRead.readLine();

            Set<Student> students = studentService.filterStudentsByName(name);
            students.stream().forEach(System.out::println);
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printAllStudents() {
        System.out.println("\n");
        Set<Student> students = studentService.getAllStudents();
        students.stream().forEach(System.out::println);
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

    private void addStudent() {
        Student student = readStudent();
        try {
            studentService.addStudent(student);
            System.out.println("\tStudent added !");
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("ID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            studentService.deleteStudent(id);
            System.out.println("\tStudent deleted !");
        } catch (ValidatorException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStudent() {
        Student student = readStudent();
        try {
            studentService.updateStudent(student);
            System.out.println("\tStudent updated !");
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    //============================PROBLEM================================

    private void filterProblems() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nDescription: ");
            String description = bufferRead.readLine();

            Set<Problem> problems = problemService.filterProblemsByDescr(description);
            System.out.println("\n");
            problems.stream().forEach(System.out::println);
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printAllProblems() {
        System.out.println("\n");
        Set<Problem> problems = problemService.getAllProblems();
        problems.stream().forEach(System.out::println);
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

    private void addProblem() {
        Problem problem = readProblem();
        try {
            problemService.addProblem(problem);
            System.out.println("\tProblem added !");
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProblem() {
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("ID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            problemService.deleteProblem(id);
            System.out.println("\tProblem deleted !");
        } catch (ValidatorException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProblem() {
        Problem problem = readProblem();
        try {
            problemService.updateProblem(problem);
            System.out.println("\tProblem updated !");
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    //=========================ASSIGN==================================

    private void mostAssigned() {
        System.out.println(problemService.getProblem(Long.valueOf(assignService.mostAssigned())));
    }

    private void filterAssign() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nStudent ID: ");
            String SID = bufferRead.readLine();

            Set<Assign> assigns = assignService.filterAssignsBySID(SID);
            System.out.println("\n");
            assigns.stream().forEach(System.out::println);
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printAllAssigns() {
        System.out.println("\n");
        Set<Assign> assigns = assignService.getAllAssigns();
        assigns.stream().forEach(System.out::println);
    }

    private Assign readAssign() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(bufferRead.readLine());

            System.out.print("Student ID: ");
            String SID = bufferRead.readLine();

            System.out.print("Problem ID: ");
            String PID = bufferRead.readLine();

            Assign assign = new Assign(SID, PID);
            assign.setId(id);

            return assign;
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private void addAssign() {
        Assign assign = readAssign();
        try {
            assignService.addAssign(assign);
            System.out.println("\tAssign added !");
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteAssign() {
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("ID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            assignService.deleteAssign(id);
            System.out.println("\tAssign deleted !");
        } catch (ValidatorException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateAssign() {
        Assign assign = readAssign();
        try {
            assignService.updateAssign(assign);
            System.out.println("\tAssign updated !");
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    //============================Gradings================================
    private Grading readGrading() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(br.readLine());

            System.out.print("Assign ID: ");
            String AID = br.readLine();

            System.out.print("Grade: ");
            String gr = br.readLine();
            int grade = Integer.parseInt(gr);

            Grading grading = new Grading(AID, grade);
            grading.setId(id);

            return grading;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private void printAllGradings() {
        System.out.println("\n");
        Set<Grading> gradings = gradingService.getAllGradings();
        gradings.stream().forEach(System.out::println);
    }

    private void filterGradings() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("\nAssign ID:");
            String AID = br.readLine();
            Set<Grading> gradings = gradingService.filterGradingsByAID(AID);
            gradings.stream().forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addGrading() {
        Grading grading = readGrading();
        try {
            gradingService.addGrading(grading);
            System.out.println("\tGrading added!");
        } catch (ValidatorException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void deleteGrading() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Grading ID: ");
            String id1 = br.readLine();
            Long id = Long.valueOf(id1);
            gradingService.deleteGrading(id);
            System.out.println("\tGrading deleted!\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void updateGrading() {
        Grading grading = readGrading();
        try {
            gradingService.updateGrading(grading);
            System.out.println("\tGrading updated!\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}