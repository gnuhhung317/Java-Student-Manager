package StudentManagement;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

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

    public void getAllFields(){
        name = Validator.getNameInput();
        birthday = Validator.getBirthdayInput();
        address = Validator.getAddressInput();
        height = Validator.getHeightInput();
        weight = Validator.getWeightInput();
        studentID = Validator.getStudentID();
        school = Validator.getSchoolInput();
        year = Validator.getStartSchoolYear();
        cpa = Validator.getCpaInput();
    }
    public void addStudent() {

        this.getAllFields();
        Student student = new Student(name, birthday, address, height, weight, studentID, school, year, cpa);
        students.add(student);
        System.out.println(student);
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
        //update student by fields
        Student student = findByID();
        if (students.isEmpty()){
            System.out.println("There's no student in list!");
            return;
        }
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
            System.out.println("3. Address");
            System.out.println("4. Height");
            System.out.println("5. Weight");
            System.out.println("6. Student ID");
            System.out.println("7. School");
            System.out.println("8. Year of University Entry");
            System.out.println("9. Average Score");
            System.out.println("10. All");
            System.out.println("11. Exit");

            choice = sc.next();
            switch (choice) {
                case "1" -> {
                    name = Validator.getNameInput();
                    student.setName(name);
                    isChoosing = false;
                }
                case "2" -> {
                    birthday = Validator.getBirthdayInput();
                    student.setBirthday(birthday);
                    isChoosing = false;
                }
                case "3" -> {
                    address = Validator.getAddressInput();
                    student.setAddress(address);
                    isChoosing = false;
                }
                case "4" -> {
                    height = Validator.getHeightInput();
                    student.setHeight(height);
                    isChoosing = false;
                }
                case "5" -> {
                    weight = Validator.getWeightInput();
                    student.setWeight(weight);
                    isChoosing = false;
                }
                case "6" -> {
                    studentID = Validator.getStudentID();
                    student.setStudentID(studentID);
                    isChoosing = false;
                }
                case "7" -> {
                    school = Validator.getSchoolInput();
                    student.setSchool(school);
                    isChoosing = false;
                }
                case "8" -> {
                    year = Validator.getStartSchoolYear();
                    student.setSchoolStartDate(year);
                    isChoosing = false;
                }
                case "9" -> {
                    cpa = Validator.getCpaInput();
                    student.setCPA(cpa);
                    isChoosing = false;
                }
                case "10" -> {

                    this.getAllFields();
                    student.setName(name);
                    student.setCPA(cpa);
                    student.setWeight(weight);
                    student.setHeight(height);
                    student.setBirthday(birthday);
                    student.setAddress(address);
                    student.setSchoolStartDate(year);
                    student.setStudentID(studentID);
                    isChoosing = false;
                }
                case "11" -> {
                    return;
                }
                default -> System.out.println("Invalid choice :" + choice);
            }

        } while (isChoosing);
        System.out.println(student);
    }

    public void deleteByID() {
        //delete student by id
        Student student = findByID();

        if (students.isEmpty()){
            System.out.println("There's no student in list!");

        }
        else if (student == null) {
            System.out.println("Student not found");

        }
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
        // clear list, prevent save the old student
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
        //show student percent by medium score
        double mediumScore;
        mediumScorePercent.clear();
        for (Student student : students){
            int count = 0;
            mediumScore = student.getCPA();
            for (Student student1 : students){
                //count each score exist
                if (mediumScore == student1.getCPA()) count++;
            }
            mediumScorePercent.put(mediumScore,toPercent(count,students.size()));
        }
        for (Double i : mediumScorePercent.keySet()){
            System.out.println(i + ": "+ mediumScorePercent.get(i)+"%");
        }
    }

    public void matchStudentAndAbility(AcademicAbility ability){
        //find students by ability
        boolean notFound = true;
        for (Student student : students){
            if (student.getAbility() == ability){
                System.out.println(student);
                System.out.println("--------------------------------");
                notFound = false;
            }
        }
        if (notFound) System.out.println("No student is "+ ability);
    }
    public void showStudentByAbility(){
        //let user choose the ability to show students
        String choice;
        int index = 1;
        System.out.println("Choose: ");
        for (AcademicAbility ability : abilities){
            System.out.println(index + ". "+ ability);
            index +=1;
        }

        choice = sc.nextLine();
        switch (choice) {
            case "1" -> matchStudentAndAbility(abilities.get(0));
            case "2" -> matchStudentAndAbility(abilities.get(1));
            case "3" -> matchStudentAndAbility(abilities.get(2));
            case "4" -> matchStudentAndAbility(abilities.get(3));
            case "5" -> matchStudentAndAbility(abilities.get(4));
            case "6" -> matchStudentAndAbility(abilities.get(5));
            default -> System.out.println("Invalid choice!");
        }
    }
    public void saveStudentsInfo() {
        File f = new File(Constant.INFO_FILE);
        try{
            PrintWriter pw = new PrintWriter(f);
            for (Student student : students){
                pw.println(student.toString());
            }
            pw.flush();
            pw.close();
        }catch (Exception e){
            System.out.println("Save objects fail!");
            e.printStackTrace();
        }

    }
    public void saveStudentObjects(){
        File f = new File(Constant.OBJECT_FILE);
        try {
            OutputStream os = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(students);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Save objects fail!");
        }
    }


}
