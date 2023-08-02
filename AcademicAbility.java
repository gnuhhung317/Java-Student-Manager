package StudentManagement;

public enum AcademicAbility {
    POOR,
    WEAK,
    AVERAGE,
    PRETTY_GOOD,
    GOOD,
    EXCELLENCE;
    
    public static AcademicAbility getAcademicAbility(double score) {
        AcademicAbility ability;
        if (score < 3)
            ability = POOR;
        else if (score >= 3 && score < 5)
            ability = WEAK;
        else if (score >= 5 && score < 6.5)
            ability = AVERAGE;
        else if (score >= 6.5 && score < 7.5)
            ability = PRETTY_GOOD;
        else if (score >= 7.5 && score < 9)
            ability = GOOD;
        else
            ability = EXCELLENCE;

        return ability;
    }
}
