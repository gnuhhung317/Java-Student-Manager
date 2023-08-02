package StudentManagement;

import java.lang.reflect.Array;
import java.sql.DataTruncation;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentManager {
    LinkedList<Student> students = new LinkedList<>();
    Scanner sc = new Scanner(System.in);
    String name,address,studentID,school;
    double height, weight,cpa;
    int year;
    LocalDate birthday;
    public void addStudent(){


        name = Validator.getNameInput();
        birthday = Validator.getBirthdayInput();
        address = Validator.getAddressInput();
        height = Validator.getHeightInput();
        weight = Validator.getWeightInput();
        studentID = Validator.getStudentID();
        school = Validator.getSchoolInput();
        year = Validator.getStartSchoolYear();
        cpa = Validator.getcpaInput();
        Student student = new Student(name,birthday,address,height,weight,studentID,school,year,cpa);
        students.add(student);
        System.out.println(student.toString());
    }
    public Student findByID(){
        if (students.isEmpty()){
            return null;
        }
        String idString;
        System.out.println("Enter student's id: ");
        idString = sc.next();
        while (Validator.isNotInteger(idString)){
            System.out.println("Invalid id, enter again: ");
            idString = sc.next();
        }
        int id = Integer.parseInt(idString);

        for (Student student : students){
            if (id == student.getId()){
                return student ;
            }
        }
        return null;

    }
    public void updateByID(){
        Student student = findByID();
        if (student == null){
            System.out.println("Student not found");
            return;
        }
        String choice;
        boolean isChoosing = true;
        do{
            System.out.println("Update: ");
            System.out.println("1. Name");
            System.out.println("2. Birthday");
            System.out.println("3. Address\n" +
                    "4. Height\n" +
                    "5. Weight\n" +
                    "6. Student id\n" +
                    "7. school\n" +
                    "8. Year go to university\n" +
                    "9. Medium score\n"+
                    "10. All\n"+
                    "11. Exit");
            choice = sc.next();
            switch (choice){
                case "1":
                    name = Validator.getNameInput();
                    student.setName(name);
                    isChoosing = false;
                    break;
                case "2":
                    birthday = Validator.getBirthdayInput();
                    student.setBirthday(birthday);
                    isChoosing = false;
                    break;
                case "3":
                    address = Validator.getAddressInput();
                    student.setAddress(address);
                    isChoosing = false;
                        break;
                case "4":
                    height = Validator.getHeightInput();
                    student.setHeight(height);
                    isChoosing = false;
                    break;
                case "5":
                    weight = Validator.getWeightInput();
                    student.setWeight(weight);
                    isChoosing = false;

                    break;
                case "6":
                    studentID = Validator.getStudentID();
                    student.setStudentID(studentID);
                    isChoosing = false;

                    break;
                case "7":
                    school = Validator.getSchoolInput();
                    student.setSchool(school);
                    isChoosing = false;
                    break;
                case "8":
                    year = Validator.getStartSchoolYear();
                    student.setSchoolStartDate(year);
                    isChoosing = false;

                    break;
                case "9":
                    cpa = Validator.getcpaInput();
                    student.setCPA(cpa);
                    isChoosing = false;

                    break;
                case "10":
                    name = Validator.getNameInput();
                    birthday = Validator.getBirthdayInput();
                    address = Validator.getAddressInput();
                    height = Validator.getHeightInput();
                    weight = Validator.getWeightInput();
                    studentID = Validator.getStudentID();
                    school = Validator.getSchoolInput();
                    year = Validator.getStartSchoolYear();
                    cpa = Validator.getcpaInput();
                    student.setName(name);
                    student.setCPA(cpa);
                    student.setWeight(weight);
                    student.setHeight(height);
                    student.setBirthday(birthday);
                    student.setAddress(address);
                    student.setCPA(cpa);;
                    student.setSchoolStartDate(year);
                    student.setStudentID(studentID);
                    isChoosing = false;
                    break;
                case "11":
                    return;
                default:

                    System.out.println("Invalid choice :"+ choice);

                    break;

            }


        }while (isChoosing);
        System.out.println(student.toString());
    }

    public void deleteByID(){
        System.out.println("Enter the id:");
        String id = sc.next();
        if (Validator.isNotInteger(id)){
            System.out.println("Invalid input!");
            return;
        }
        for (Student student1 : students){
            if (Integer.parseInt(id) == student1.getId()){
                students.remove(student1);
                System.out.println("Remove successfully");
                return;
            }
        }
        System.out.println("Failed");

    }

    public void getAcademicAbility(Student student){
        double mediumScore = student.getCPA();
        if(mediumScore < 3);
    }

}
