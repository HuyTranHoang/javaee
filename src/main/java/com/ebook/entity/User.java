package com.ebook.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.userId DESC"),
        @NamedQuery(name = "User.count", query = "SELECT COUNT(u) FROM User u"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name = "User.findByEmailAndPassword", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    private String email;

    @Column(name = "full_name")
    private String fullName;

    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email) && Objects.equals(fullName, user.fullName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, fullName, password);
    }
}
