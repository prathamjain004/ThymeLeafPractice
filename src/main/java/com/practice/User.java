package com.practice;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "User Details")
public class User {
    @Id
    private int id;
    @NotEmpty(message = "Name is required")
    @Indexed(unique = true)
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Indexed(unique = true)
    private String email;
    @NotNull(message = "Phone number is required")
    @Digits(integer = 15, fraction = 0, message = "Invalid phone number")
    @Min(value = 9, message = "At least 9 Numbers required")
    @Indexed(unique = true)
    private Long phone;

    public User(int id, String name, String email, Long phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(email, other.email) && Objects.equals(phone, other.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }
}
