package com.company.task.user;

import com.company.task.user.dto.UserDto;
import com.company.task.user.mapper.ExternalUserMapper;
import com.company.task.user.service.UserRestService;
import com.company.task.utils.CustomJsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class UserClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);

    private final UserRestService userRestService;
    private final ExternalUserMapper externalUserMapper;
    private final CustomJsonParser customJsonParser;

    public UserClient(UserRestService userRestService, ExternalUserMapper externalUserMapper, CustomJsonParser customJsonParser) {
        this.userRestService = userRestService;
        this.externalUserMapper = externalUserMapper;
        this.customJsonParser = customJsonParser;
    }

    public List<UserDto> users() {
        var usersJson = userRestService.getUsersFromExternalApi();
        if (usersJson == null) {
            LOGGER.error("Couldn't get users from external api");
            return emptyList();
        }
        var externalUserWrapper = customJsonParser.parseJsonToExternalUserDto(usersJson);
        return externalUserWrapper != null ? externalUserMapper.mapToUserDtoList(externalUserWrapper) : emptyList();
    }

}