package StudentManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;

public class Validator {
    static String name;
    static String address;
    static Double height;
    static Double weight;
    static String studentID;
    static String school;
    static int schoolStartDate;
    static double cpa;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> studentIDs = new ArrayList<>();
    public static boolean isNotInteger(String s) {

        try {
            int number = Integer.parseInt(s);

        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static boolean isNotDouble(String s) {

        try {
            double number = Double.parseDouble(s);
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
            birthdayString = sc.nextLine().trim();

            try {
                birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("d/M/yyyy"));
                isValidDate = true;
            } catch (Exception e) {
                System.out.print("Invalid input! ");

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
        boolean isInvalid = true;
        do {
            System.out.println("Enter your height(cm): ");
            heightString = sc.nextLine();
            while (isNotDouble(heightString)) {
                System.out.println("Invalid input! Enter your height(cm): ");
                heightString = sc.nextLine();
            }
            height = Double.parseDouble(heightString);
            String minHeight = Constant.MIN_HEIGHT.toString(), maxHeight = Constant.MAX_HEIGHT.toString();
            if (heightString.split("\\.")[0].length() == minHeight.length()-2&&heightString.compareTo(minHeight) < 0 ||
                heightString.split("\\.")[0].length() == maxHeight.length()-2&& heightString.compareTo(maxHeight) > 0||
                height > Constant.MAX_HEIGHT || height < Constant.MIN_HEIGHT||
                heightString.length()>10) {
                // minHeight.length() -2 to remove the length of .0
                // str.split("\\.")[0] to get the natural part
                System.out.print("Invalid input! ");
            }else{
                isInvalid = false;

            }
        } while (isInvalid);
        return height;
    }

    public static double getWeightInput() {
        String weightString;
        boolean isInvalid = true;
        do {
            System.out.println("Enter your weight(kg): ");
            weightString = sc.nextLine();
            while (isNotDouble(weightString)) {
                System.out.println("Invalid input! Enter you weight(kg): ");
                weightString = sc.nextLine();
            }
            weight = Double.parseDouble(weightString);

            String minWeight = Constant.MIN_WEIGHT.toString(), maxWeight = Constant.MAX_WEIGHT.toString();
            if (weightString.split("\\.")[0].length() == minWeight.length()-2&&weightString.compareTo(minWeight) < 0 ||
                    weightString.split("\\.")[0].length() == maxWeight.length()-2&& weightString.compareTo(maxWeight) > 0||
                    weight > Constant.MAX_WEIGHT ||  weight < Constant.MIN_WEIGHT||
                    weightString.length()>10) {
                // minHeight.length() -2 to remove the length of .0
                // str.split("\\.")[0] to get the natural part
                System.out.print("Invalid input! ");
            }else{
                isInvalid = false;

            }
        } while (isInvalid);
        return weight;
    }


    public static String getStudentID() {
        System.out.println("Enter your student ID: ");
        studentID = sc.nextLine();
        while (studentID.strip().length() != Constant.ID_LENGTH || studentIDs.contains(studentID)) {
            System.out.println("Invalid input! Enter your student ID: ");
            studentID = sc.nextLine();
        }
        //add student id to a arraylist to prevent same student id
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
                System.out.println("Invalid input! Enter the year you start university: ");
                yearString = sc.nextLine();
            }
            schoolStartDate = Integer.parseInt(yearString);
            if (schoolStartDate < Constant.MIN_START_SCHOOL_YEAR || schoolStartDate > Constant.MAX_START_SCHOOL_YEAR) {
                System.out.print("Invalid input! ");
            }
        } while (schoolStartDate < Constant.MIN_START_SCHOOL_YEAR || schoolStartDate > Constant.MAX_START_SCHOOL_YEAR);
        return schoolStartDate;
    }

    public static double getCpaInput() {
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
