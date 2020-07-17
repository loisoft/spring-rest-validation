package com.example.demo.validation.controller;

import com.example.demo.validation.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateUserOk() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testing");
        userDto.setAge(30);
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users", userDto, Void.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldCreateUserReturnBadRequest() {
        UserDto userDto = new UserDto();
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users", userDto, Void.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
