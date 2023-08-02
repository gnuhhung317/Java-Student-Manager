package StudentManagement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import java.util.Scanner;

public class Validator {
    static String name;
    static String address;
    static double height;
    static double weight;
    static String studentID;
    static String school;
    static int schoolStartDate;
    static double cpa;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> studentIDs = new ArrayList<>();
    public static boolean isNotInteger(String s) {
        int number;
        try {
            number = Integer.parseInt(s);

        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static boolean isNotDouble(String s) {
        double number;
        try {
            number = Double.parseDouble(s);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static String getNameInput() {
        System.out.println("Enter your name:");
        name = sc.nextLine();
        while (name.isEmpty() || name.length() > Constant.MAX_NAME_LENGTH) {
            System.out.println("Invalid input, enter your name:");
            name = sc.nextLine();
        }
        return name;
    }

    public static LocalDate getBirthdayInput() {
        String birthdayString;
        LocalDate birthday = null;
        boolean isValidDate = false;
        do {
            System.out.println("Enter your birthday (dd/mm/yyyy): ");
            birthdayString = sc.nextLine();

            try {
                birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                isValidDate = true;
            } catch (Exception e) {
                System.out.print("Invalid input! ");
                isValidDate = false;
            }
        } while (!isValidDate);
        return birthday;
    }

    public static String getAddressInput() {
        System.out.println("Enter your address");

        address = sc.nextLine();
        while (address.isEmpty() || address.length() > Constant.MAX_ADDRESS_LENGTH) {
            System.out.println("Invalid input, please enter your address: ");
            address = sc.nextLine();
        }

        return address;
    }

    public static double getHeightInput() {
        String heightString;
        do {
            System.out.println("Enter your height(cm): ");
            heightString = sc.nextLine();
            while (isNotDouble(heightString)) {
                System.out.println("Invalid input! Enter your height(cm): ");
                heightString = sc.nextLine();
            }
            height = Double.parseDouble(heightString);
            if (height < Constant.MIN_HEIGHT || height > Constant.MAX_HEIGHT) {
                System.out.print("Invalid input! ");
            }
        } while (height < Constant.MIN_HEIGHT || height > Constant.MAX_HEIGHT);
        return height;
    }

    public static double getWeightInput() {
        String weightString;
        do {
            System.out.println("Enter your weight(kg): ");
            weightString = sc.nextLine();
            while (isNotDouble(weightString)) {
                System.out.println("Invalid input! Enter you weight(kg): ");
                weightString = sc.nextLine();
            }
            weight = Double.parseDouble(weightString);
            if (weight < Constant.MIN_WEIGHT || weight > Constant.MAX_WEIGHT) {
                System.out.print("Invalid input! ");
            }

        } while (weight < Constant.MIN_WEIGHT || weight > Constant.MAX_WEIGHT);
        return weight;
    }

    public static String getStudentID() {
        System.out.println("Enter your student ID: ");
        studentID = sc.nextLine();
        while (studentID.strip().length() != Constant.ID_LENGTH || studentIDs.contains(studentID)) {
            System.out.println("Invalid input! Enter your student ID: ");
            studentID = sc.nextLine();
        }
        //add studentid to a arraylist to prevent same studentid
        studentIDs.add(studentID);
        return studentID;
    }

    public static String getSchoolInput() {
        System.out.println("Enter your school name: ");
        school = sc.nextLine();

        while (school.length() > Constant.MAX_SCHOOL_NAME_LENGTH || school.isBlank()) {
            System.out.println("Invalid input! Enter your school name: ");
            school = sc.nextLine();
        }
        return school;
    }

    public static int getStartSchoolYear() {
        String yearString;
        do {
            System.out.println("Enter the year your start university:  ");
            yearString = sc.nextLine();
            while (isNotInteger(yearString)) {
                System.out.println("Invalid input! Enter the year you start univerity: ");
                yearString = sc.nextLine();
            }
            schoolStartDate = Integer.parseInt(yearString);
            if (schoolStartDate < Constant.MIN_START_SCHOOL_YEAR || schoolStartDate > Constant.MAX_START_SCHOOL_YEAR) {
                System.out.print("Invalid input! ");
            }
        } while (schoolStartDate < Constant.MIN_START_SCHOOL_YEAR || schoolStartDate > Constant.MAX_START_SCHOOL_YEAR);
        return schoolStartDate;
    }

    public static double getcpaInput() {
        String cpaString;
        do {
            System.out.println("Enter you medium score: ");
            cpaString = sc.nextLine();
            while (isNotDouble(cpaString)) {
                System.out.println("Invalid input! Enter you medium score: ");
                cpaString = sc.nextLine();
            }
            cpa = Double.parseDouble(cpaString);
            if (cpa < Constant.MIN_CPA || cpa > Constant.MAX_CPA) {
                System.out.print("Invalid input!");
            }
        } while (cpa < Constant.MIN_CPA || cpa > Constant.MAX_CPA);

        return cpa;
    }

}
