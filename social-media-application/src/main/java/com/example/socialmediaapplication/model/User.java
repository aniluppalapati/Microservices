package com.example.socialmediaapplication.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonFilter("someUserBean")
public class User {
    public int id;


    @Size(min = 2,max = 10,message = "Minimum Input Size is 2 and Max Input Size is 10")
    public String name;

    @Past
    public LocalDate dateOfBirth;

}
