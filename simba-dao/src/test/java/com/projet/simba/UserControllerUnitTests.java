package com.projet.simba;

import com.projet.simba.mapper.UserMapper;
import com.projet.simba.repository.UserRepository;
import com.projet.simba.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserControllerUnitTests {
    @MockBean
    private  UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;



}
