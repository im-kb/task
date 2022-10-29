package com.company.task.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRestService {
    private static final String EXTERNAL_USER_API_URL = "https://randomuser.me/api";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestService.class);

    private final RestTemplate restTemplate;

    public UserRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUsersFromExternalApi() {
        var responseJson = restTemplate.getForEntity(EXTERNAL_USER_API_URL, String.class);
        if(responseJson == null){
            LOGGER.error("Couldn't obtain a response from external api");
            return null;
        }

        if (responseJson.getStatusCode() == HttpStatus.OK) {
            LOGGER.info("Successfully fetched person from external api, external status code : {}", responseJson.getStatusCode());
            return (responseJson.getBody());
        } else {
            LOGGER.warn("Error while fetching person from main external api, external status code : {}", responseJson.getStatusCode());
            return null;
        }
    }

}