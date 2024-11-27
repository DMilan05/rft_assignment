package hu.nye;

import hu.nye.model.UniversitySystem;
import hu.nye.nyestakeholders.Administrator;
import hu.nye.nyestakeholders.Student;
import hu.nye.nyestakeholders.Teacher;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        UniversitySystem system = new UniversitySystem();
        Scanner scanner = new Scanner(System.in);

        Teacher teacher = new Teacher("Dr. Smith");
        Administrator admin = new Administrator("Ms. Johnson");
        Student student = new Student("Alice");

        while (true) {
            System.out.println("Choose an action: create, delete, register, quit");
            String action = scanner.nextLine();
            String examName = "";
            switch (action.toLowerCase()) {
                case "create" : {
                    System.out.print("Enter exam name to create: ");
                    examName = scanner.nextLine();
                    teacher.createExam(system, examName);
                }
                case "delete" : {
                    System.out.print("Enter exam name to delete: ");
                    examName = scanner.nextLine();
                    admin.deleteExam(system, examName);
                }
                case "register" : {
                    System.out.print("Enter exam name to register: ");
                    examName = scanner.nextLine();
                    student.registerForExam(system, examName);
                }
                case "quit" : {
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                }
                default : System.out.println("Invalid action.");
            }
        }
    }
}
