package com.nonsense.util;

import com.nonsense.config.TestConfig;
import com.nonsense.model.User;
import com.nonsense.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;

    //mocked dependencies
    @Autowired
    private UserService userService;

    private static final String email = "123@mail.com";
    private static final User user = mock(User.class);

    @BeforeAll
    public static void setUp() {
        when(user.getEmail()).thenReturn(email);
    }

    @Test
    public void validateShouldAcceptUserWithNewEmail() {
        when(userService.getOne(email)).thenReturn(null);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors, never()).rejectValue(eq("email"), any(), any());
    }

    @Test
    public void validateShouldRejectUserWithExistingEmail() {
        when(userService.getOne(email)).thenReturn(user);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors).rejectValue(eq("email"), any(), any());
    }
}
