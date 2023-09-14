package studentmanagement.model;

import studentmanagement.model.Constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Scanner;

public class Validator {
    static String name;
    static LocalDate birthday = null;
    static String address;
    static Double height;
    static Double weight;
    static String studentCode;
    static String school;
    static int schoolStartDate;
    static double cpa;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> studentCodes = new ArrayList<>();

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
        System.out.println("Enter name ( <"+ Constant.MAX_NAME_LENGTH+" characters):");
        name = sc.nextLine();
        while (name.isEmpty() || name.length() > Constant.MAX_NAME_LENGTH) {
            System.out.println("Invalid input, enter name ( <"+Constant.MAX_NAME_LENGTH+"):");
            name = sc.nextLine();
        }
        return name;
    }

    public static LocalDate getBirthdayInput() {
        String birthdayString, actualBirthday;
        boolean isValidDate = false;
        do {
            System.out.println("Enter birthday (dd/mm/yyyy) (after 1900s) : ");
            birthdayString = sc.nextLine().trim();

            try {
                try{
                    birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    actualBirthday = birthday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }catch (Exception e){
                    try{
                        birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("d/M/yyyy"));
                        actualBirthday = birthday.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
                    }catch (Exception f){
                        try{
                            birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("dd/M/yyyy"));
                            actualBirthday = birthday.format(DateTimeFormatter.ofPattern("dd/M/yyyy"));
                        }catch (Exception j){
                            birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("d/MM/yyyy"));
                            actualBirthday = birthday.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
                        }
                    }


                }


                if (actualBirthday.equals(birthdayString)&&birthday.getYear() >= Constant.MIN_BORN_YEAR){
                    isValidDate = true;
                }
                else {
                    System.out.print("Invalid input! ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input! ");

            }
        } while (!isValidDate);
        return birthday;
    }



    public static String getAddressInput() {
        System.out.println("Enter address ( < "+Constant.MAX_ADDRESS_LENGTH+" characters):");

        address = sc.nextLine();
        while (address.isEmpty() || address.length() > Constant.MAX_ADDRESS_LENGTH) {
            System.out.println("Invalid input, please enter address ( < "+Constant.MAX_ADDRESS_LENGTH+"):");
            address = sc.nextLine();
        }

        return address;
    }

    public static double getHeightInput() {
        String heightString;
        boolean isInvalid = true;
        do {
            System.out.println("Enter height ( "+Constant.MIN_HEIGHT+" - "+Constant.MAX_HEIGHT+" )(cm): ");
            heightString = sc.nextLine();
            while (isNotDouble(heightString)) {
                System.out.println("Not number input! Enter height again ( "+Constant.MIN_HEIGHT+" < "+Constant.MAX_HEIGHT+" )(cm): ");
                heightString = sc.nextLine();
            }
            height = Double.parseDouble(heightString);
            if (heightString.contains(".")){
                heightString = String.valueOf(height);
            }

            String minHeight = Constant.MIN_HEIGHT.toString(), maxHeight = Constant.MAX_HEIGHT.toString();
            if (heightString.split("\\.")[0].length() == minHeight.length() - 2 && heightString.compareTo(minHeight) < 0
                    ||
                    heightString.split("\\.")[0].length() == maxHeight.length() - 2
                            && heightString.compareTo(maxHeight) > 0
                    ||
                    height > Constant.MAX_HEIGHT || height < Constant.MIN_HEIGHT ) {
                // minHeight.length() -2 to remove the length of .0
                // str.split("\\.")[0] to get the natural part
                System.out.print("Invalid input! ");
            } else {
                isInvalid = false;

            }
        } while (isInvalid);
        return height;
    }

    public static double getWeightInput() {
        String weightString;
        boolean isInvalid = true;
        do {
            System.out.println("Enter weight ( "+Constant.MIN_WEIGHT+" - "+Constant.MAX_WEIGHT+" )(kg): ");
            weightString = sc.nextLine();
            while (isNotDouble(weightString)) {
                System.out.println("Not number input! Enter weight again ( "+Constant.MIN_WEIGHT+" - "+Constant.MAX_WEIGHT+" )(kg): ");
                weightString = sc.nextLine();
            }
            weight = Double.parseDouble(weightString);
            if (!weightString.contains(".")){
                weightString = String.valueOf(weight);
            }

            String minWeight = Constant.MIN_WEIGHT.toString(), maxWeight = Constant.MAX_WEIGHT.toString();
            if (weightString.split("\\.")[0].length() == minWeight.length() - 2 && weightString.compareTo(minWeight) < 0
                    ||
                    weightString.split("\\.")[0].length() == maxWeight.length() - 2
                            && weightString.compareTo(maxWeight) > 0
                    ||
                    weight > Constant.MAX_WEIGHT || weight < Constant.MIN_WEIGHT ) {
                // minWeight.length() -2 to remove the length of .0
                // str.split("\\.")[0] to get the natural part
                System.out.print("Invalid input! ");

            } else {
                isInvalid = false;

            }
        } while (isInvalid);
        return weight;
    }

    public static void main(String[] args) {
        weight = getWeightInput();
        height = getHeightInput();
    }
    public static String getStudentCode() {
        boolean isNotValid = true;
        System.out.println("Enter student code ("+Constant.ID_LENGTH+" characters): ");
        studentCode = sc.nextLine();
        while (isNotValid) {
            if(studentCode.strip().length() != Constant.ID_LENGTH){
                System.out.println("The code's length should be "+Constant.ID_LENGTH);
                System.out.println("Enter student code again (10 characters) : ");
                studentCode = sc.nextLine();
            }
            else if(studentCodes.contains(studentCode)) {
                System.out.println("This code existed");
                System.out.println("Enter student code again (10 characters) : ");
                studentCode = sc.nextLine();
            }
            else {
                isNotValid = false;
            }

        }
        // add student id to a arraylist to prevent same student id
        studentCodes.add(studentCode);
        return studentCode;
    }

    public static String getSchoolInput() {
        System.out.println("Enter school name ( < "+Constant.MAX_SCHOOL_NAME_LENGTH+" characters): ");
        school = sc.nextLine();

        while (school.length() > Constant.MAX_SCHOOL_NAME_LENGTH || school.isBlank()) {
            System.out.println("Invalid input! Enter school name ( < "+Constant.MAX_SCHOOL_NAME_LENGTH+" characters): ");
            school = sc.nextLine();
        }
        return school;
    }

    public static int getStartSchoolYear() {
        String yearString;
        boolean isInvalid;
        do {
            isInvalid = false;
            System.out.println("Enter start university year ( "+birthday.getYear() +"-"+ Calendar.getInstance().get(Calendar.YEAR)+" ):  ");
            yearString = sc.nextLine();
            while (isNotInteger(yearString)) {
                System.out.println("Invalid input! Enter start university year( "+birthday.getYear() +"-"+ Calendar.getInstance().get(Calendar.YEAR)+" ):  ");
                yearString = sc.nextLine();
            }
            schoolStartDate = Integer.parseInt(yearString);
            if (schoolStartDate < Constant.MIN_START_SCHOOL_YEAR || schoolStartDate > Constant.MAX_START_SCHOOL_YEAR||schoolStartDate <= birthday.getYear()) {
                System.out.print("Invalid input! ");
                isInvalid = true;
            }
        } while (isInvalid);
        return schoolStartDate;
    }

    public static double getCpaInput() {
        String cpaString;
        do {
            System.out.println("Enter medium score ( "+Constant.MIN_CPA+"-"+Constant.MAX_CPA+" ): ");
            cpaString = sc.nextLine();
            while (isNotDouble(cpaString)) {
                System.out.println("Invalid input! Enter medium score ( "+Constant.MIN_CPA+"-"+Constant.MAX_CPA+" ): ");
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
