package com.example.demo.validation.dto;

import com.example.demo.validation.annotation.ValidCity;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {
    @NotEmpty(message = "{username.must.not.empty}")
    private String username;
    @Min(value = 18, message = "{age.must.greater.than.18}")
    private int age;
    @ValidCity
    private String city;
}
