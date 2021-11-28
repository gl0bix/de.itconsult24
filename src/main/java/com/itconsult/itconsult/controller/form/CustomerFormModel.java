package com.itconsult.itconsult.controller.form;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "itconsult_customer")
@Table(name = "itconsult_customer")
public class CustomerFormModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String lastname;
        private String firstname;
        private String phoneNumber;
        private String street;
        private String postalCode;
        private String city;
        private String country;

        public String getfirstname() {

                return firstname;
        }

        public String getlastname() {

                return lastname;
        }
}










