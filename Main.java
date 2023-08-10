package StudentManagement;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        String quantity;
        boolean isRunning = true;

        // get the students information here
        System.out.println("Enter the student's quantity: ");
        quantity = sc.next();
        while (Validator.isNotInteger(quantity)|| Integer.parseInt(quantity)<0) {
            System.out.println("Invalid input, enter the quantity again: ");
            quantity = sc.next();
        }
        for (int i = 0; i < Integer.parseInt(quantity); i++) {
            manager.addStudent();
        }

        // Menu
        String choice;
        do {
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
