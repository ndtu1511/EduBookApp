package com.example.edubookapp.validator;

import com.example.edubookapp.model.User;
import com.example.edubookapp.repository.UserRepository;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "NotEmpty.user.confirmPassword");

        if (!user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword","Match.user.confirmPassword");
        }
        if (userService.findByUserName(user.getUsername())!=null){
            errors.rejectValue("username","Exits.user.username");
        }
        if (userService.findByEmail(user.getEmail())!=null){
            errors.rejectValue("email","Exits.user.email");
        }
    }
}
