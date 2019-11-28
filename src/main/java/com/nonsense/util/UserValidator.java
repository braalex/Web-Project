package com.nonsense.util;

import com.nonsense.dao.UserDAO;
import com.nonsense.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Qualifier("hibernateUserDAO")
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userDAO.getOne(user.getEmail()) != null) {
            errors.rejectValue("email", "",
                    "This email is already in use");
        }
    }
}
