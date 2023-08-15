package StudentManagement;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import javax.xml.validation.Validator;

public class StudentManagerArray {
    int[] students;
    Scanner sc = new Scanner(System.in);
    String name, address, studentCode, school;
    double weight, height;
    int year;
    LocalDate birthday;
    LinkedList<AcademicAbility> abilities = new LinkedList<>(Arrays.asList(AcademicAbility.POOR,
            AcademicAbility.WEAK,
            AcademicAbility.AVERAGE,
            AcademicAbility.PRETTY_GOOD,
            AcademicAbility.GOOD,
            AcademicAbility.EXCELLENCE));
    // Array save student's quantity by ability
    int[] countByAbility = new int[6];
    double[] percentByAbility = new double[6];
    //HashMap save cpa percent
    HashMap<Double,Double> mediumScorePercent = new HashMap<>();

    public void getAllFields(){
        name = Validator.getNameInput();
        birthday = Validator.getBirthdayInput();
        address = Validator.getAddressInput();
        height = Validator.getHeightInput();
        weight = Validator.getWeightInput();
        studentCode = Validator.getStudentCode();
        school = Validator.getSchoolInput();
        year = Validator.getStartSchoolYear();
        cpa = Validator.getCpaInput();
    }

    public void addStudent(){
        System.out.println("Enter the student's quantity: ");
        String quantity = sc.next();
        while (Validator.isNotInteger(quantity)|| Integer.parseInt(quantity)<0) {
            System.out.println("Invalid input, enter the quantity again: ");
            quantity = sc.next();
        }
        for (int i = 0 ; i < Integer.parseInt(quantity); i++){
            this.getAllFields();
            Student student = new Student(name, birthday, address, height, weight, studentCode, school, year, cpa);
            students[i] = student;
            System.out.println("Student's information:");
            System.out.println(student);
        }
        System.out.println("Add student complete!");
    }
}
