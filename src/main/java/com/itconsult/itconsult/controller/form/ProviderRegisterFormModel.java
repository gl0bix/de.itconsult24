package com.itconsult.itconsult.controller.form;

import com.itconsult.itconsult.enums.OrderType;
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
    @NotEmpty(message = "Das Feld Firmenname darf nicht leer sein")
    private String company;
    @NotNull
    @NotEmpty(message = "Das Feld Branche darf nicht leer sein")
    private OrderType competence;
    @NotNull
    @NotEmpty(message = "Das Feld Email darf nicht leer sein")
    @Email
    private String email;
    @NotNull
    @NotEmpty(message = "Das Feld Straße darf nicht leer sein")
    private String street;
    @NotNull
    @NotEmpty(message = "Das Feld Postleitzahl darf nicht leer sein")
    private String postalCode;
    @NotNull
    @NotEmpty(message = "Das Feld Stadt darf nicht leer sein")
    private String city;
    @NotNull
    @NotEmpty(message = "Das Feld Land darf nicht leer sein")
    private String country;
    @NotNull
    @NotEmpty(message = "Das Feld Passwort darf nicht leer sein")
    private String password;
    private String confirmPassword;
}
