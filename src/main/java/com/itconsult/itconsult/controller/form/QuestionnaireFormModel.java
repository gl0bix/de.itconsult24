package com.itconsult.itconsult.controller.form;

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

    @NotNull
    private OrderType orderType;

    @NotNull
    @NotEmpty
    private String urgency;

    @NotNull
    @NotEmpty
    private String duration;

    @NotNull
    @NotEmpty
    private String companyDescription;

    @NotNull
    @NotEmpty
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


}
