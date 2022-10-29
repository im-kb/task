package com.company.task.user.dto;

import com.company.task.user.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalUserDto {

    private Gender gender;
    private NameDto name;
    private LocationDto location;
    private LoginDto login;
    private RegisteredDto registered;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NameDto {
        String first;
        String last;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationDto {
        String city;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginDto {
        String uuid;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisteredDto {
        //Timezone annotation is required, because jacksons objectMapper uses UTC by default, and converts the time to match the jvm defaults
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Europe/Berlin")
        Date date;
    }

}