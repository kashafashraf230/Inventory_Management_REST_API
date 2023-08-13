package com.example.inventorymanagementsystem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class UserCrudTest {

    UserCrud userMock;
    @BeforeEach
    void setUp() {
       userMock = mock(UserCrud.class);
    }

    @AfterEach
    void tearDown() {
        userMock.setUserConnection(null);
    }

    @Test
    void isUser() {
        when(userMock.isUser("manager", "mgr")).thenReturn(true);
    }

    @Test
    void isInvalidUser(){
        when(userMock.isUser("user", "user")).thenReturn(false);
    }
}