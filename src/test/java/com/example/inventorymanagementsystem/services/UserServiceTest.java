/*package com.example.inventorymanagementsystem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class UserServiceTest {

    UserService userMock;
    @BeforeEach
    void setUp() {
       userMock = mock(UserService.class);
    }


    @Test
    void isUser() {
        when(userMock.isUser("manager", "mgr")).thenReturn(true);
    }

    @Test
    void isInvalidUser(){
        when(userMock.isUser("user", "user")).thenReturn(false);
    }
}*/