package com.itconsult.itconsult.controller.form;

import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireFormModel {

    @NotNull(message = "orderType null")
    private OrderType orderType;

    @NotNull(message = "urgency null")
    @NotEmpty(message = "urgency empty")
    private String urgency;

    @NotNull(message = "duration null")
    @NotEmpty(message = "duration empty")
    private String duration;

    @NotNull(message = "companyDescription null")
    @NotEmpty(message = "Die Unternehmensbeschreibung darf nicht leer sein")
    private String companyDescription;

    @NotNull(message = "problemDescription null")
    @NotEmpty(message = "Die Problembeschreibung darf nicht leer sein")
    private String problemDescription;

    private String typeOfMeasure;   //Art der Maßnahme
    private String typeOfAttack;    //Art des Angriffs
    private String typeOfDevices;   //Art der Leistung (Hardware)
    private String typeOfSoftware;  //Art der Leistung (Software)
    private String typeOfCloud;     //Cloud-Bereich
    private String network;         //Art der Leistung (Netzwerk)
    private String networkDetails;  //Details zum Netzwerkpunkt
    private String projectStatus;   //Softwareentwicklung: Projektstatus
    private String typeOfProject;   //Softwareentwicklung: Projektbereich
    private String systemAdmin;     //Systemadministration

    private Customer customer;

}
