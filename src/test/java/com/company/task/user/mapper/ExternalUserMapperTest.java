package com.company.task.user.mapper;

import com.company.task.user.dto.ExternalUserDto;
import com.company.task.user.dto.ExternalUsersWrapper;
import com.company.task.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static com.company.task.common.UserTestHelper.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ExternalUserMapperTest {
    private final ExternalUserMapper externalUserMapper = new ExternalUserMapper();

    @Test
    public void shouldMapToDto() {
        //given
        var expected = USER_1_DTO;

        //when
        var result = externalUserMapper.mapToUserDto(EXTERNAL_USER_1_DTO);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    public void shouldReturnNull(ExternalUserDto externalUserDto) {
        //when
        var result = externalUserMapper.mapToUserDto(externalUserDto);

        //then
        assertThat(result).isNull();
    }

    @Test
    public void shouldReturnEmptyDtoGivenNullNestedFields() {
        //given
        ExternalUserDto externalUserDto = new ExternalUserDto();

        //when
        var result = externalUserMapper.mapToUserDto(externalUserDto);

        //then
        assertThat(result).isEqualTo(new UserDto());
    }

    @ParameterizedTest
    @NullSource
    public void shouldReturnEmptyListGivenNullWrapper(ExternalUsersWrapper externalUsersWrapper) {
        //when
        var result = externalUserMapper.mapToUserDtoList(externalUsersWrapper);

        //then
        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldReturnEmptyList(List<ExternalUserDto> externalUserDtoList) {
        //given
        var externalUsersWrapper = new ExternalUsersWrapper();
        externalUsersWrapper.setResults(externalUserDtoList);

        //when
        var result = externalUserMapper.mapToUserDtoList(externalUsersWrapper);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldMapToDtoList() {
        //given
        var externalUsersWrapper = new ExternalUsersWrapper();
        externalUsersWrapper.setResults(asList(null, EXTERNAL_USER_1_DTO, EXTERNAL_USER_2_DTO, null));

        //when
        var result = externalUserMapper.mapToUserDtoList(externalUsersWrapper);

        //then
        assertThat(result).containsExactly(USER_1_DTO, USER_2_DTO);
    }

}