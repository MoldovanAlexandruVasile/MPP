package ro.ubb.socket.common.Domain;

public class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "ID = " + id + '}';
    }
}
