package ro.ubb.socket.client.ui;

import ro.ubb.socket.common.Domain.Assign;
import ro.ubb.socket.common.Domain.Grading;
import ro.ubb.socket.common.Domain.Problem;
import ro.ubb.socket.common.Domain.Student;
import ro.ubb.socket.common.ServiceAG;
import ro.ubb.socket.common.ServiceSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientConsole {
    private ServiceAG serviceAG;
    private ServiceSP serviceSP;

    public ClientConsole(ServiceAG serviceAG, ServiceSP serviceSP) {
        this.serviceAG = serviceAG;
        this.serviceSP = serviceSP;
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
                            addStudent(student.getId(), student.getSerialNumber(), student.getName());
                        } else if (command2 == 2) {
                            Long ID = Long.valueOf(readID());
                            deleteStudent(ID);
                        } else if (command2 == 3) {
                            Student student = readStudent();
                            updateStudent(student.getId(), student.getSerialNumber(), student.getName());
                        } else if (command2 == 4) {
                            printAllStudents();
                        } else if (command2 == 0) break;
                        else if (command2 == 5) {
                            String name = readFilter();
                            filterStudents(name);
                        }
                    }
                } else if (command == 2) {
                    while (true) {
                        printProblemMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Problem problem = readProblem();
                            addProblem(problem.getId(), problem.getDescription());
                        } else if (command2 == 2) {
                            Long ID = Long.valueOf(readID());
                            deleteProblem(ID);
                        } else if (command2 == 3) {
                            Problem problem = readProblem();
                            updateProblem(problem.getId(), problem.getDescription());
                        } else if (command2 == 0) break;
                        else if (command2 == 4) {
                            printAllProblems();
                        } else if (command2 == 5) {
                            String desc = readFilter();
                            filterProblems(desc);
                        }
                    }
                } else if (command == 3) {
                    while (true) {
                        printAssignMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Assign assign = readAssign();
                            addAssign(assign.getId(), assign.getSID(), assign.getPID());
                        } else if (command2 == 2) {
                            Long ID = Long.valueOf(readID());
                            deleteAssign(ID);
                        } else if (command2 == 3) {
                            Assign assign = readAssign();
                            updateAssign(assign.getId(), assign.getSID(), assign.getPID());
                        } else if (command2 == 0) break;
                        else if (command2 == 4) {
                            printAllAssigns();
                        } else if (command2 == 5) {
                            String desc = readFilter();
                            filterAssigns(desc);
                        }
                    }
                } else if (command == 4) {
                    while (true) {
                        printGradingMenu();
                        command2 = Integer.valueOf(bufferRead.readLine());
                        if (command2 == 1) {
                            Grading grading = readGrading();
                            addGrading(grading.getId(), grading.getAID(), grading.getGrade());
                        } else if (command2 == 2) {
                            Long ID = Long.valueOf(readID());
                            deleteGrading(ID);
                        } else if (command2 == 3) {
                            Grading grading = readGrading();
                            updateGrading(grading.getId(), grading.getAID(), grading.getGrade());
                        } else if (command2 == 0) break;
                        else if (command2 == 4) {
                            printAllGradings();
                        } else if (command2 == 5) {
                            String desc = readFilter();
                            filterGradings(desc);
                        }
                    }
                } else if (command == 0) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addStudent(Long ID, String sn, String name) {
        Future<String> res = serviceSP.addStudent(ID, sn, name);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent(Long ID) {
        Future<Long> res = serviceSP.deleteStudent(ID);
        try {
            System.out.println(res.get().toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void updateStudent(Long ID, String serialNumber, String name) {
        Future<String> res = serviceSP.updateStudent(ID, serialNumber, name);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private void addProblem(Long ID, String description) {
        Future<String> res = serviceSP.addProblem(ID, description);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void deleteProblem(Long ID) {
        Future<Long> res = serviceSP.deleteProblem(ID);
        try {
            System.out.println(res.get().toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void updateProblem(Long ID, String description) {
        Future<String> res = serviceSP.updateProblem(ID, description);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private void addAssign(Long ID, String SID, String PID) {
        Future<String> res = serviceAG.addAssign(ID, SID, PID);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void deleteAssign(Long ID) {
        Future<Long> res = serviceAG.deleteAssign(ID);
        try {
            System.out.println(res.get().toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void updateAssign(Long ID, String SID, String PID) {
        Future<String> res = serviceAG.updateAssign(ID, SID, PID);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private void addGrading(Long ID, String AID, Integer grade) {
        Future<String> res = serviceAG.addGrading(ID, AID, grade);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void deleteGrading(Long ID) {
        Future<Long> res = serviceAG.deleteGrading(ID);
        try {
            System.out.println(res.get().toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void updateGrading(Long ID, String AID, Integer grade) {
        Future<String> res = serviceAG.updateGrading(ID, AID, grade);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void printAllStudents() {
        Future<String> res = serviceSP.printAllStudents();
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void printAllProblems() {
        Future<String> res = serviceSP.printAllProblems();
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void printAllAssigns() {
        Future<String> res = serviceAG.printAllAssigns();
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void printAllGradings() {
        Future<String> res = serviceAG.printAllGradings();
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void filterStudents(String name) {
        Future<String> res = serviceSP.filterStudents(name);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void filterProblems(String desc) {
        Future<String> res = serviceSP.filterProblems(desc);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void filterAssigns(String desc) {
        Future<String> res = serviceAG.filterAssigns(desc);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void filterGradings(String desc) {
        Future<String> res = serviceAG.filterGradings(desc);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
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
        System.out.println("\t\t\t5. Filter students by name.");
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

    private Grading readGrading() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nID: ");
            Long id = Long.valueOf(br.readLine());

            System.out.print("Assign ID: ");
            String AID = br.readLine();

            System.out.print("Grade: ");
            String gr = br.readLine();
            int grade = Integer.valueOf(gr);

            Grading grading = new Grading(AID, grade);
            grading.setId(id);

            return grading;
        } catch (Exception ex) {
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

    private String readFilter() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nName: ");
            String s = bufferRead.readLine();
            return s;
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
}
