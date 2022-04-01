package com.myvision.Super.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "user")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotEmpty
    @NotNull
    private String username;

    @Column(name = "email")
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;


    @Column(name = "first_name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    @NotEmpty
    @NotNull
    private String gender;
    @NotEmpty
    @NotNull
    private String passwordConfirm;


}
