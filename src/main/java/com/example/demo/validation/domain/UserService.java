package com.example.demo.validation.domain;

import com.example.demo.validation.annotation.SafeToGo;
import com.example.demo.validation.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @SafeToGo(city = "[0].city")
    public void create(UserDto userDto) {
        log.info("Create user from city {}", userDto.getCity());
    }
}
