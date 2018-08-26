package ua.company.entity;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name = Department.GET_ALL_SORTED, query = "select d from Department d order by d.id asc")
})

@Entity
@Table(name = "departments")
public class Department extends AbstractEntity {

    public static final String GET_ALL_SORTED = "Department.getAll";

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department department = (Department) o;

        return id != null ? id.equals(department.id) : department.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
