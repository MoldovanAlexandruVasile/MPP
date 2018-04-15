package ro.ubb.remoting.common;

public class Student extends BaseEntity<Long> {
    private String serialNumber;
    private String name;

    public Student(String serialNumber, String name) {
        this.serialNumber = serialNumber;
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Student student = (Student) o;

        if (!serialNumber.equals(student.serialNumber))
            return false;

        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        int result = serialNumber.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" + "serialNumber = " + serialNumber + ' ' + ", name = " + name + "} " + super.toString();
    }
}
