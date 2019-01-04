package com.smartplanner.service;

import com.smartplanner.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean findUserById(int id);

    User getUserById(int id);
}
