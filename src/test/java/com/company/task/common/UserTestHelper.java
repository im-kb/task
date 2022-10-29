package com.company.task.common;

import com.company.task.user.Gender;
import com.company.task.user.dto.ExternalUserDto;
import com.company.task.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserTestHelper {
    static final Logger LOGGER = LoggerFactory.getLogger(UserTestHelper.class);

    public static final String USER_1_FIRSTNAME = "Rue";
    public static final String USER_1_LASTNAME = "Grande";
    public static final Gender USER_1_GENDER = Gender.female;
    public static final String USER_1_CITY = "France";
    public static final String USER_1_UUID = "8602a2b8-1ae8-4fbc-a1c5-4404b8bc219c";
    public static final String USER_1_REGISTERED = "2007-07-18T16:36:36.811Z";
    public static final ExternalUserDto EXTERNAL_USER_1_DTO = prepareExternalUserDto(USER_1_GENDER, USER_1_FIRSTNAME, USER_1_LASTNAME, USER_1_CITY, USER_1_UUID, USER_1_REGISTERED);
    public static final UserDto USER_1_DTO = new UserDto(USER_1_GENDER, USER_1_FIRSTNAME, USER_1_LASTNAME, USER_1_CITY, USER_1_UUID, stringToDate(USER_1_REGISTERED));


    public static final String USER_2_FIRSTNAME = "Gustavo";
    public static final String USER_2_LASTNAME = "Laureano";
    public static final Gender USER_2_GENDER = Gender.male;
    public static final String USER_2_CITY = "Ciudad Valles";
    public static final String USER_2_UUID = "670df2ff-c3e1-4533-8504-eb5a9377d100";
    public static final String USER_2_REGISTERED = "2009-03-28T13:16:37.003Z";
    public static final ExternalUserDto EXTERNAL_USER_2_DTO = prepareExternalUserDto(USER_2_GENDER, USER_2_FIRSTNAME, USER_2_LASTNAME, USER_2_CITY, USER_2_UUID, USER_2_REGISTERED);
    public static final UserDto USER_2_DTO = new UserDto(USER_2_GENDER, USER_2_FIRSTNAME, USER_2_LASTNAME, USER_2_CITY, USER_2_UUID, stringToDate(USER_2_REGISTERED));

    private static ExternalUserDto prepareExternalUserDto(Gender gender, String firstName, String lastName, String locationCity, String loginUuid, String registered) {
        return ExternalUserDto.builder()
                .gender(gender)
                .name(prepareName(firstName, lastName))
                .location(prepareLocation(locationCity))
                .login(prepareLogin(loginUuid))
                .registered(prepareRegistered(registered))
                .build();
    }

    private static ExternalUserDto.NameDto prepareName(String firstName, String lastName) {
        return new ExternalUserDto.NameDto(firstName, lastName);
    }

    private static ExternalUserDto.LocationDto prepareLocation(String locationCity) {
        return new ExternalUserDto.LocationDto(locationCity);
    }

    private static ExternalUserDto.LoginDto prepareLogin(String login) {
        return new ExternalUserDto.LoginDto(login);
    }

    private static ExternalUserDto.RegisteredDto prepareRegistered(String registeredDate) {
        return new ExternalUserDto.RegisteredDto(stringToDate(registeredDate));
    }

    private static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = new Date();
        try {
            date = sdf.parse(dateString);
        } catch (ParseException parseException) {
            LOGGER.error("Failed parsing the date for string: {}", dateString);
        }
        return date;
    }

}