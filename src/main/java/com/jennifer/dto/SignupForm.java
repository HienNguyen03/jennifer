package com.jennifer.dto;

import com.jennifer.entity.UserInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Contains signed up information
 */

public class SignupForm {

    @NotNull
    @Size(min = 6, max = 50, message = "{Size.signupForm.fullname}")
    private String fullname;

    @NotNull
    @Size(max = 100, message = "{Size.signupForm.email}")
    @Pattern(regexp = UserInfo.EMAIL_PATTERN, message = "{Pattern.signupForm.email}")
    private String email;

    @NotNull
    @Size(min = 6, max = 20, message = "{Size.signupForm.password}")
    private String password;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Signup Form [fullname=" + fullname + "email=" + email + ", password=" + password + "]";
    }
}
