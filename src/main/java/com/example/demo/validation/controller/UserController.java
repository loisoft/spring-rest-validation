package com.example.demo.validation.controller;

import com.example.demo.validation.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UserDto userDto) {
        log.info("Create user with username = {}", userDto.getUsername());
    }
}
