package com.smartplanner.controller;

import com.smartplanner.exception.ResourceNotFoundException;
import com.smartplanner.model.dto.UserDto;
import com.smartplanner.model.entity.User;
import com.smartplanner.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService) {
        this(userService, new ModelMapper());
    }

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signup")
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserDto newUserDto = modelMapper.map(userService.save(userDto), UserDto.class);

        return newUserDto;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserDto getUserById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        if (!userService.findUserById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        UserDto userDto = modelMapper.map(userService.getUserById(id), UserDto.class);

        return userDto;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> listUsers() {
        List<User> users = userService.findAll();
        List<UserDto> usersDto = new ArrayList<>();

        users.forEach(x -> {
                    UserDto userDto = modelMapper.map(x, UserDto.class);
                    usersDto.add(userDto);
                }
        );

        return usersDto;
    }
}
