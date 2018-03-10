package ro.ubb.LabProb.UI;

import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Service.StudentService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

public class Console
{
    private StudentService studentService;

    public Console(StudentService studentService) {
        this.studentService = studentService;
    }

    public void runConsole()
    {
        while (true)
        {
            printMenu();
            Scanner scan = new Scanner(System.in);
            int s = scan.nextInt();
            if (s == 1) addStudent();
            else if (s == 2) deleteStudent();
            else if (s == 3) updateStudent();
            else if (s == 4) printAllStudents();
            else if (s == 5) filterStudents();
            else if (s == 0) break;
        }
    }

    private void printMenu()
    {
        System.out.println("\n\n\t1. Add a student.");
        System.out.println("\t2. Delete a Student.");
        System.out.println("\t3. Update a student.");
        System.out.println("\t4. Print all students.");
        System.out.println("\t5. Filter by name.");
        System.out.println("\t0. Exit.");
        System.out.print("\t\tInput an option: ");
    }

    private void filterStudents()
    {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.print("Name: ");
            String name = bufferRead.readLine();

            Set<Student> students = studentService.filterStudentsByName(name);
            students.stream().forEach(System.out::println);
        }
        catch (IOException | RuntimeException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private void printAllStudents()
    {
        System.out.println("\n");
        Set<Student> students = studentService.getAllStudents();
        students.stream().forEach(System.out::println);
    }

    private Student readStudent()
    {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.print("\nID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("Serial number: ");
            String serialNumber = bufferRead.readLine();
            System.out.print("Name: ");
            String name = bufferRead.readLine();

            Student student = new Student(serialNumber, name);
            student.setId(id);

            return student;
        }
        catch (IOException | RuntimeException ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private void addStudent()
    {
        Student student = readStudent();
        try
        {
            studentService.addStudent(student);
            System.out.println("\tStudent added !");
        }
        catch (ValidatorException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void deleteStudent()
    {
        try
        {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("ID: ");
            Long id = Long.valueOf(bufferRead.readLine());
            studentService.deleteStudent(id);
            System.out.println("\tStudent deleted !");
        }
        catch (ValidatorException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void updateStudent()
    {
        Student student = readStudent();
        try
        {
            studentService.updateStudent(student);
            System.out.println("\tStudent updated !");
        }
        catch (ValidatorException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
