package com.company.task.user.dto;

import com.company.task.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Gender gender;
    private String firstName;
    private String lastName;
    private String city;
    private String loginUuid;
    private Date registrationDate;

}