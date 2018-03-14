package ro.ubb.LabProb.Domain;

public class Problem extends ro.ubb.LabProb.Domain.BaseEntity<Long> {
    private String description;

    public Problem(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Problem problem = (Problem) o;


        return description.equals(problem.description);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Problem{ description = " + description + "} " + super.toString();
    }
}
