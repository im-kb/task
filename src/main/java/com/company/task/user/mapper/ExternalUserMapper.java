package com.company.task.user.mapper;

import com.company.task.user.dto.ExternalUserDto;
import com.company.task.user.dto.ExternalUsersWrapper;
import com.company.task.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Component
public class ExternalUserMapper {

    public UserDto mapToUserDto(ExternalUserDto externalUserDto) {
        if (externalUserDto == null) {
            return null;
        }

        var userDto = new UserDto();
        userDto.setGender(externalUserDto.getGender());
        if (externalUserDto.getName() != null) {
            userDto.setFirstName(externalUserDto.getName().getFirst());
            userDto.setLastName(externalUserDto.getName().getLast());
        }
        if (externalUserDto.getLocation() != null) {
            userDto.setCity(externalUserDto.getLocation().getCity());
        }
        if (externalUserDto.getLogin() != null) {
            userDto.setLoginUuid(externalUserDto.getLogin().getUuid());
        }
        if (externalUserDto.getRegistered() != null) {
            userDto.setRegistrationDate(externalUserDto.getRegistered().getDate());
        }

        return userDto;
    }

    public List<UserDto> mapToUserDtoList(ExternalUsersWrapper externalUsersWrapper) {
        if (isEmpty(externalUsersWrapper) || isEmpty(externalUsersWrapper.getResults())) {
            return emptyList();
        }

        return externalUsersWrapper.getResults().stream()
                .map(this::mapToUserDto)
                .filter(Objects::nonNull)
                .collect(toList());
    }

}