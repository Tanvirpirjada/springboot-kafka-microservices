package com.HireMatrix.authservice.model;

import com.HireMatrix.authservice.entity.USERTYPE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SignUpModel(String name,String email, String password,@Enumerated(EnumType.STRING) USERTYPE type) {

}
