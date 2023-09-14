package studentmanagement.main;

import studentmanagement.controller.StudentManagerList;
import studentmanagement.model.Student;

import java.util.Scanner;

public class ListTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagerList manager = new StudentManagerList();
        boolean isRunning = true;

        // Menu
        String choice;
        do {
            String statement = (manager.students.size() <2)?"There's "+manager.students.size() +
                    " student":"There's "+manager.students.size() + " students";
            statement += " in list";
            System.out.println(statement);
            System.out.println("Enter your choice:");
            System.out.println("1. Add student");
            System.out.println("2. Find student by ID");
            System.out.println("3. Update student by ID");
            System.out.println("4. Delete student by ID");
            System.out.println("5. Show academical ability percent");
            System.out.println("6. Show medium score percent");
            System.out.println("7. List by academical ability");
            System.out.println("8. Exit");
            choice = sc.next();

            switch (choice) {
                case "1" :
                    manager.addStudent();
                    break;
                case "2" :
                    Student student = manager.findByID();
                    if (student == null) {
                        System.out.println("Student not found");
                        break;
                    }
                    System.out.println(student);
                    break;

                case "3" :
                    manager.updateByID();
                    break;
                case "4" :
                    manager.deleteByID();
                    break;
                case "5" :
                    manager.showAbilityPercent();
                    break;
                case "6" :
                    manager.showMediumScorePercent();
                    break;
                case "7" :
                    manager.showStudentByAbility();
                    break;
                case "8" :
                    isRunning = false;
                    manager.saveStudentsInfo();
                    manager.saveStudentObjects();
                    System.out.println("Good bye!");
                    break;

                default :
                    System.out.println("Invalid choice!");
                    break;

            }
        } while (isRunning);

    }
}
