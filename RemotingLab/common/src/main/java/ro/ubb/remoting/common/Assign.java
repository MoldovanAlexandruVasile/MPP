package ro.ubb.remoting.common;

public class Assign extends BaseEntity<Long> {
    private String PID;
    private String SID;

    public Assign(String SID, String PID) {
        this.SID = SID;
        this.PID = PID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Assign assign = (Assign) o;

        if (!PID.equals(assign.PID))
            return false;

        return SID.equals(assign.SID);
    }

    @Override
    public int hashCode() {
        int result = PID != null ? PID.hashCode() : 0;
        result = 31 * result + (SID != null ? SID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Assign{" + "SID = " + SID + ", PID = " + PID + "} " + super.toString();
    }
}