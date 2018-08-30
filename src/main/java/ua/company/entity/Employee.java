package ua.company.entity;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.binary.Base64;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;


@Entity
@Table(name = "employees")
public class Employee extends AbstractEntity{

    @NotBlank (message = "Must not be empty")
    @Size(min = 3, message = "Must be longer than or equal 3")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active")
    private boolean active;

    @Lob
    @Column(name = "image")
    private byte[] image;

//    This field needed for rendering byte array to image in jsp page
    @Transient
    private String base64imageFile;

    public String getBase64imageFile() {
        byte[] encodeBase64 = Base64.encodeBase64(getImage());
        String base64Encoded = "A";
        try {
            base64Encoded = new String(encodeBase64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64Encoded;
    }

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id != null ? id.equals(employee.id) : employee.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", active=" + active +
                ", id=" + id +
                '}';
    }
}
