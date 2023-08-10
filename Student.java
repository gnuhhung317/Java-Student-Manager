package StudentManagement;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {
    private String studentID;
    private String school;
    private int schoolStartDate;
    private double CPA;
    private AcademicAbility ability;
    public Student(){
        super();
    }
    public Student(String name, LocalDate birthday, String address, double height, double weight, String studentID,
            String school, int schoolStartDate, double CPA) {
        super(name, birthday, address, height, weight);
        this.studentID = studentID;
        this.school = school;
        this.schoolStartDate = schoolStartDate;
        this.CPA = CPA;
        this.ability = AcademicAbility.getAcademicAbility(CPA);
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getSchoolStartDate() {
        return this.schoolStartDate;
    }

    public void setSchoolStartDate(int schoolStartDate) {
        this.schoolStartDate = schoolStartDate;
    }

    public double getCPA() {
        return this.CPA;
    }

    public void setCPA(double CPA) {
        ability = AcademicAbility.getAcademicAbility(CPA);
        this.CPA = CPA;
    }

    public AcademicAbility getAbility() {
        return this.ability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return schoolStartDate == student.schoolStartDate && Double.compare(CPA, student.CPA) == 0
                && Objects.equals(studentID, student.studentID) && Objects.equals(school, student.school);
    }

    @Override
    public String toString() {
        return "Id: " + getId() + "\n" +
                "Name: " + getName() + "\n" +
                "Birthday: " + getBirthday() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Height: " + getHeight() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "StudentID:" + getStudentID() + "\n" +
                "School: " + getSchool() + "\n" +
                "SchoolStartDate: " + getSchoolStartDate() + "\n" +
                "CPA: " + getCPA()+
                "\n----------------------------------";
    }


}
