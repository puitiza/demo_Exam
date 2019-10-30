package com.forceclose.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponse implements Serializable {
    private static final long serialVersionUID = 3090356645132056363L;


    private String name;
    private String password;
}