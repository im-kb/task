package com.company.task.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserClientIntegrationTest {

    @Autowired
    private UserClient userClient;

    @Test
    public void shouldReturnMappedUserGivenRealResponse() {
        //when
        var result = userClient.users();

        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getGender()).isNotNull();
        assertThat(result.get(0).getFirstName()).isNotNull();
        assertThat(result.get(0).getLastName()).isNotNull();
        assertThat(result.get(0).getCity()).isNotNull();
        assertThat(result.get(0).getLoginUuid()).isNotNull();
        assertThat(result.get(0).getRegistrationDate()).isNotNull();
    }

}