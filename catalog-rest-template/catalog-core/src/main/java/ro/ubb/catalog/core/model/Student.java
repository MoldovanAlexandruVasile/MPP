package ro.ubb.catalog.core.model;

import javax.persistence.Entity;

@Entity
public class Student extends BaseEntity<Long> {
    
    private String serialNumber;
    private String name;

    public Student() {
    }

    public Student(String serialNumber, String name) {
        this.serialNumber = serialNumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                '}' + super.toString();
    }
}
