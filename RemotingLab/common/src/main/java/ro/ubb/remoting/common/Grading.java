package ro.ubb.remoting.common;

public class Grading extends BaseEntity<Long> {
    private String AID;
    private int grade;

    public Grading(String aid, int gr) {
        this.AID = aid;
        this.grade = gr;
    }

    public String getAID() {
        return this.AID;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setAID(String aid) {
        this.AID = aid;
    }

    public void setGrade(int gr) {
        this.grade = gr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Grading grading = (Grading) o;

        if (!AID.equals(grading.AID))
            return false;

        return grade == grading.grade;
    }

    @Override
    public int hashCode() {
        int result = AID != null ? AID.hashCode() : 0;
        result = 31 * result + (grade > 0 ? grade : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grading{" + "AID = " + AID + ", grade = " + grade + "} " + super.toString();
    }
}
