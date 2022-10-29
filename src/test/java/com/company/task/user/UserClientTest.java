package com.company.task.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.company.task.common.UserTestHelper.USER_1_DTO;
import static com.company.task.common.UserTestHelper.USER_2_DTO;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserClientTest {

    private static final String API_RESPONSE_SINGLE_USER = "external_user.json";
    private static final String API_RESPONSE_USERS = "external_users.json";

    @Autowired
    private UserClient userClient;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void shouldReturnMappedUserGivenPreparedApiResponse() throws IOException, URISyntaxException {
        //given
        String usersApiResponseJson = getResourceAsString(API_RESPONSE_SINGLE_USER);
        ResponseEntity<String> responseEntity = ResponseEntity.ok(usersApiResponseJson);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        //when
        var result = userClient.users();

        //then
        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(asList(USER_1_DTO));
    }

    @Test
    public void shouldReturnMappedUsersGivenPreparedApiResponse() throws IOException, URISyntaxException {
        //given
        String usersApiResponseJson = getResourceAsString(API_RESPONSE_USERS);
        ResponseEntity<String> responseEntity = ResponseEntity.ok(usersApiResponseJson);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        //when
        var result = userClient.users();

        //then
        assertThat(result).isNotEmpty();
        assertThat(result).containsExactly(USER_1_DTO, USER_2_DTO);
    }

    @Test
    public void shouldReturnEmptyList() {
        //given
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(ResponseEntity.internalServerError().build());

        //when
        var result = userClient.users();

        //then
        assertThat(result).isEmpty();
    }

    private String getResourceAsString(String path) throws URISyntaxException, IOException {
        return Files.readString(Paths.get(getClass().getClassLoader().getResource(path).toURI()));
    }

}