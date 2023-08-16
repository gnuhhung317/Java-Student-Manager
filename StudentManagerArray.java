package StudentManagement;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class StudentManagerArray {
    public Student[] students = new Student[0];
    private final Scanner sc = new Scanner(System.in);
    private String name, address, studentCode, school;
    private double weight, height;
    private int year;
    private LocalDate birthday;
    private double cPA;

    LinkedList<AcademicAbility> abilities = new LinkedList<>(Arrays.asList(AcademicAbility.POOR,
            AcademicAbility.WEAK,
            AcademicAbility.AVERAGE,
            AcademicAbility.PRETTY_GOOD,
            AcademicAbility.GOOD,
            AcademicAbility.EXCELLENCE));
    // Array save student's quantity by ability
    int[] countByAbility = new int[6];
    double[] percentByAbility = new double[6];
    // HashMap save cpa percent
    HashMap<Double, Double> mediumScorePercent = new HashMap<>();



    public double toPercent(int number, int all) {
        return Math.round((double) number / all * 10000.0) / 100.0;
    }

    public Student[] removeStudent(int index) {

        Student[] newStudents = new Student[students.length - 1];
        if (index >= 0) System.arraycopy(students, 0, newStudents, 0, index);
        if (newStudents.length - index >= 0)
            System.arraycopy(students, index + 1, newStudents, index, newStudents.length - index);
        return newStudents;
    }

    public void getAllFields() {
        name = Validator.getNameInput();
        birthday = Validator.getBirthdayInput();
        address = Validator.getAddressInput();
        height = Validator.getHeightInput();
        weight = Validator.getWeightInput();
        studentCode = Validator.getStudentCode();
        school = Validator.getSchoolInput();
        year = Validator.getStartSchoolYear();
        cPA = Validator.getCpaInput();
    }

    public Student[] createNewList(int newLength) {
        return Arrays.copyOf(students, newLength);
    }

    public void addStudent() {
        System.out.println("Enter the student's quantity: ");
        String quantityString = sc.next();
        while (Validator.isNotInteger(quantityString) || Integer.parseInt(quantityString) < 0) {
            System.out.println("Invalid input, enter the quantity again: ");
            quantityString = sc.next();
        }
        int quantity = Integer.parseInt(quantityString);
        if (students == null) {
            for (int i = 0; i < quantity; i++) {
                this.getAllFields();
                Student student = new Student(name, birthday, address, height, weight, studentCode, school, year, cPA);
                students[i] = student;
                System.out.println("Student's information:");
                System.out.println(student);
            }
        } else {
            int oldLength = students.length;
            students = createNewList(students.length + quantity);
            for (int i = oldLength; i < students.length; i++) {
                this.getAllFields();
                Student student = new Student(name, birthday, address, height, weight, studentCode, school, year, cPA);
                students[i] = student;
                System.out.println("Student's information:");
                System.out.println(student);
            }
        }

        System.out.println("Add student complete!");
    }

    public Student findByID() {
        if (students == null || students.length == 0) {
            return null;
        }
        String iDString;
        System.out.println("Enter student's id: ");
        iDString = sc.next();
        while (Validator.isNotInteger(iDString)) {
            System.out.println("Invalid id, enter again: ");
            iDString = sc.next();
        }
        int iD = Integer.parseInt(iDString);

        for (Student student : students) {
            if (iD == student.getID()) {
                return student;
            }
        }
        return null;
    }

    public void updateByID() {
        // update student by fields
        Student student = findByID();
        if (students == null || students.length == 0) {
            System.out.println("There's no student in list");
            return;
        }
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        String choice;
        boolean isChoosing;
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

            isChoosing = false;
            choice = sc.next();
            switch (choice) {
                case "1" -> {
                    name = Validator.getNameInput();
                    student.setName(name);
                }
                case "2" -> {
                    birthday = Validator.getBirthdayInput();
                    student.setBirthday(birthday);
                }
                case "3" -> {
                    address = Validator.getAddressInput();
                    student.setAddress(address);
                }
                case "4" -> {
                    height = Validator.getHeightInput();
                    student.setHeight(height);
                }
                case "5" -> {
                    weight = Validator.getWeightInput();
                    student.setWeight(weight);
                }
                case "6" -> {
                    studentCode = Validator.getStudentCode();
                    student.setStudentCode(studentCode);
                }
                case "7" -> {
                    school = Validator.getSchoolInput();
                    student.setSchool(school);
                }
                case "8" -> {
                    year = Validator.getStartSchoolYear();
                    student.setSchoolStartDate(year);
                }
                case "9" -> {
                    cPA = Validator.getCpaInput();
                    student.setCPA(cPA);
                }
                case "10" -> {

                    this.getAllFields();
                    student.setName(name);
                    student.setCPA(cPA);
                    student.setWeight(weight);
                    student.setHeight(height);
                    student.setBirthday(birthday);
                    student.setAddress(address);
                    student.setSchoolStartDate(year);
                    student.setStudentCode(studentCode);
                }
                case "11" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid choice :" + choice);
                    isChoosing = true;
                }
            }

        } while (isChoosing);
        System.out.println(student);

    }

    public void deleteByID() {
        // delete student by id
        Student student = findByID();
        if (students == null || students.length == 0) {
            System.out.println("There's no student in list");
        } else if (student == null) {
            System.out.println("Student not found");
        } else {
            int index = Arrays.binarySearch(students, student);
            students = removeStudent(index);
            System.out.println("Delete succesfully!");
        }
    }

    public void showAbilityPercent() {
        AcademicAbility ability;
        int index;
        // clear list, prevent save the old student
        for (int i = 0; i < 6; i++) {
            countByAbility[i] = 0;
        }
        // count student by ability
        for (Student student : students) {
            ability = student.getAbility();
            index = abilities.indexOf(ability);
            countByAbility[index]++;
        }
        // get percent of student by ability
        for (int i = 0; i < 6; i++) {
            percentByAbility[i] = toPercent(countByAbility[i], students.length);
            System.out.println(abilities.get(i) + " : " + percentByAbility[i] + "%");
        }

    }

    public void showMediumScorePercent() {
        // show student percent by medium score
        double mediumScore;
        mediumScorePercent.clear();
        for (Student student : students) {
            int count = 0;
            mediumScore = student.getCPA();
            for (Student student1 : students) {
                // count each score exist
                if (mediumScore == student1.getCPA())
                    count++;
            }
            mediumScorePercent.put(mediumScore, toPercent(count, students.length));
        }
        for (Double i : mediumScorePercent.keySet()) {
            System.out.println(i + ": " + mediumScorePercent.get(i) + "%");
        }
    }

    public void matchStudentAndAbility(AcademicAbility ability) {
        // find students by ability
        boolean notFound = true;
        for (Student student : students) {
            if (student.getAbility() == ability) {
                System.out.println(student);
                notFound = false;
            }
        }
        if (notFound)
            System.out.println("No student is " + ability);
    }

    public void showStudentByAbility() {
        // let user choose the ability to show students
        String choice;
        int index = 1;
        System.out.println("Choose: ");
        for (AcademicAbility ability : abilities) {
            System.out.println(index + ". " + ability);
            index += 1;
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
        try {
            PrintWriter pw = new PrintWriter(f);
            for (Student student : students) {
                pw.println(student.toString());
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Save objects fail!");
            e.printStackTrace();
        }

    }

    public void saveStudentObjects() {
        File f = new File(Constant.OBJECT_FILE);
        try {
            OutputStream os = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(students);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Save objects fail!");
        }
    }

}
