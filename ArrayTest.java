package StudentManagement;

import java.util.Scanner;

public class ArrayTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagerArray manager = new StudentManagerArray();
        boolean isRunning = true;

        // Menu
        String choice;
        do {
            String statement = (manager.students.length <2)?"There's "+manager.students.length +
                    " student":"There's "+manager.students.length + " students";
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
                case "1" -> manager.addStudent();
                case "2" -> {
                    Student student = manager.findByID();
                    if (student == null) {
                        System.out.println("Student not found");
                        break;
                    }
                    System.out.println(student);
                }
                case "3" -> manager.updateByID();
                case "4" -> manager.deleteByID();
                case "5" -> manager.showAbilityPercent();
                case "6" -> manager.showMediumScorePercent();
                case "7" -> manager.showStudentByAbility();
                case "8" -> {
                    isRunning = false;
                    manager.saveStudentsInfo();
                    manager.saveStudentObjects();
                    System.out.println("Good bye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        } while (isRunning);

    }
}
