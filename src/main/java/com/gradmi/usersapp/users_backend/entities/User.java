package com.gradmi.usersapp.users_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.*;

import com.gradmi.usersapp.users_backend.dtos.user.UserData;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Column(name = "lastName")
    private String lastName;

    private String email;

    @Column(name = "userName")
    private String userName;

    private String password;

    public User(UserData data) {
        this.name = data.name();
        this.lastName = data.lastName();
        this.email = data.email();
        this.userName = data.userName();
        this.password = data.password();
    }
}
