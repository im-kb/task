package com.company.task.utils;

import com.company.task.user.dto.ExternalUsersWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomJsonParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomJsonParser.class);

    private final ObjectMapper mapper;

    public CustomJsonParser(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ExternalUsersWrapper parseJsonToExternalUserDto(String json) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, ExternalUsersWrapper.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while parsing json to object");
            return null;
        }
    }

}