package ro.ubb.catalog.core.model;

import javax.persistence.Entity;

@Entity
public class Problem extends BaseEntity<Long> {

    private String description;

    public Problem() {
    }

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
    public String toString() {
        return "Problem{ description = " + description + "} " + super.toString();
    }
}
