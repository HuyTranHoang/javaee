package com.ebook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.userId DESC"),
        @NamedQuery(name = "User.count", query = "SELECT COUNT(u) FROM User u"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    private String email;

    @Column(name = "full_name")
    private String fullName;

    private String password;
}
