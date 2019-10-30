package com.forceclose.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private static final long serialVersionUID = 3090356645132056363L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "last_name", nullable = false)
    private String last_name;
    @Column(name = "age", nullable = false)
    private int age;
}
