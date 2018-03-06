package ro.ubb.LabProb.Domain;

public class Problem extends ro.ubb.LabProb.Domain.BaseEntity<Long>
{
    private String ID;
    private String description;

    public Problem(String ID, String description)
    {
        this.ID = ID;
        this.description = description;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Problem problem = (Problem) o;

        if (!ID.equals(problem.ID))
            return false;

        return description.equals(problem.description);
    }

    @Override
    public int hashCode()
    {
        int result = ID.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
    @Override
    public String toString() {
        return "Problem{" + "ID='" + ID + '\'' + ", description='" + description + '\'' + '}';
    }
}
