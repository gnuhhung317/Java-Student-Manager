package StudentManagement;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        String quantity;
        boolean isRunning = true;
        // get the students's information here
        System.out.println("Enter the student's quantity: ");
        quantity = sc.next();
        while (Validator.isNotInteger(quantity)) {
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
            System.out.println("5. Show academical ability");
            System.out.println("6. List by academical ability");
            System.out.println("7. Exit");
            choice = sc.next();

            switch (choice) {

                case "1":
                    manager.addStudent();
                    break;
                case "2":
                    Student student = manager.findByID();
                    if (student == null) {
                        System.out.println("Student not found");
                        break;
                    }
                    System.out.println(student.toString());
                    break;
                case "3":
                    manager.updateByID();
                    break;
                case "4":
                    manager.deleteByID();
                    break;
                case "5":

                    break;
                case "6":

                    break;
                case "7":
                    isRunning = false;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (isRunning);

    }
}
