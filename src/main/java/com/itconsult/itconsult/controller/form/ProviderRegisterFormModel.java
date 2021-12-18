package com.itconsult.itconsult.controller.form;

import com.itconsult.itconsult.security.passwordValidation.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class ProviderRegisterFormModel {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String competence;
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String phoneNumber;
    @NotNull
    @NotEmpty
    private String street;
    @NotNull
    @NotEmpty
    private String postalCode;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String country;
    @NotNull
    @NotEmpty
    private String password;
    private String confirmPassword;
}
