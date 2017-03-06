package com.jennifer.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Contains change password information
 */
public class ChangePasswordForm {
    @NotNull
    @Size(min = 6, max = 20, message = "{Size.signupForm.password}")
    private String newPassword;

    @NotNull
    @Size(min = 6, max = 20, message = "{Size.signupForm.password}")
    private String confirmPassword;
    @NotNull

    @Size(min = 6, max = 20, message = "{Size.signupForm.password}")
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
