package com.itconsult.itconsult.controller.form;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFormModel {
        private String lastname;
        private String firstname;
        private String phoneNumber;
        private String street;
        private String postalCode;
        private String city;
        private String country;
        private String email;

}










