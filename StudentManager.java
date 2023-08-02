package StudentManagement;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentManager {
    LinkedList<Student> students = new LinkedList<>();
    Scanner sc = new Scanner(System.in);
    String name, address, studentID, school;
    double height, weight, cpa;
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
    public void addStudent() {

        name = Validator.getNameInput();
        birthday = Validator.getBirthdayInput();
        address = Validator.getAddressInput();
        height = Validator.getHeightInput();
        weight = Validator.getWeightInput();
        studentID = Validator.getStudentID();
        school = Validator.getSchoolInput();
        year = Validator.getStartSchoolYear();
        cpa = Validator.getcpaInput();
        Student student = new Student(name, birthday, address, height, weight, studentID, school, year, cpa);
        students.add(student);
        System.out.println(student.toString());
    }

    public Student findByID() {
        if (students.isEmpty()) {
            return null;
        }
        String idString;
        System.out.println("Enter student's id: ");
        idString = sc.next();
        while (Validator.isNotInteger(idString)) {
            System.out.println("Invalid id, enter again: ");
            idString = sc.next();
        }
        int id = Integer.parseInt(idString);

        for (Student student : students) {
            if (id == student.getId()) {
                return student;
            }
        }
        return null;

    }

    public void updateByID() {
        Student student = findByID();
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        String choice;
        boolean isChoosing = true;
        do {
            System.out.println("Update: ");
            System.out.println("1. Name");
            System.out.println("2. Birthday");
            System.out.println("3. Address\n" +
                    "4. Height\n" +
                    "5. Weight\n" +
                    "6. Student id\n" +
                    "7. school\n" +
                    "8. Year go to university\n" +
                    "9. Medium score\n" +
                    "10. All\n" +
                    "11. Exit");
            choice = sc.next();
            switch (choice) {
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
                    student.setCPA(cpa);
                    ;
                    student.setSchoolStartDate(year);
                    student.setStudentID(studentID);
                    isChoosing = false;
                    break;
                case "11":
                    return;
                default:

                    System.out.println("Invalid choice :" + choice);

                    break;

            }

        } while (isChoosing);
        System.out.println(student.toString());
    }

    public void deleteByID() {
        Student student = findByID();

        if (student == null)
            System.out.println("Failed");
        else {
            students.remove(student);
            System.out.println("Successfully");
        }
    }

    public AcademicAbility getAcademicAbility(Student student) {
        // get the ability of student
        return AcademicAbility.getAcademicAbility(student.getCPA());
    }

    public double toPercent(int number, int all) {
        return Math.round((double) number / all * 10000.0) / 100.0;
    }

    public void showAbilityPercent() {
        AcademicAbility ability;
        int index;
        // clear if has the old list, prevent save the old student
        for(int i = 0; i< 6 ; i++){
            countByAbility[i] = 0;
        }
        //count student by ability
        for (Student student : students) {
            ability = student.getAbility();
            index = abilities.indexOf(ability);
            countByAbility[index]++;
        }
        // get percent of student by ability
        for (int i = 0; i < 6; i++) {
            percentByAbility[i] = toPercent(countByAbility[i], students.size());
            System.out.println(abilities.get(i) + " : " + percentByAbility[i] + "%");
        }

    }
    public  void showMediumScorePercent(){

        double mediumScore;
        mediumScorePercent.clear();
        for (Student student : students){
            int count = 0;
            mediumScore = student.getCPA();
            for (Student student1 : students){
                if (mediumScore == student1.getCPA()) count++;
            }
            mediumScorePercent.put(mediumScore,toPercent(count,students.size()));
        }
        for (Double i : mediumScorePercent.keySet()){
            System.out.println(i + ": "+ mediumScorePercent.get(i)+"%");
        }
    }

    public void matchStudentAndAbility(AcademicAbility ability){
        for (Student student : students){
            if (student.getAbility() == ability){
                System.out.println(student.toString());
                System.out.println("--------------------------------");
            }
        }
    }
    public void showStudentByAbility(){
        String choice;
        int index = 1;
        System.out.println("Choose: ");
        for (AcademicAbility abilitiy : abilities){
            System.out.println(index + ". "+ abilitiy);
            index +=1;
        }

        choice = sc.nextLine();
        switch (choice){
            case "1":
                matchStudentAndAbility(abilities.get(0));
                break;
            case "2":
                matchStudentAndAbility(abilities.get(1));
                break;
            case "3":
                matchStudentAndAbility(abilities.get(2));
                break;
            case "4":
                matchStudentAndAbility(abilities.get(3));
                break;
            case "5":
                matchStudentAndAbility(abilities.get(4));
                break;
            case "6":
                matchStudentAndAbility(abilities.get(5));
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}
