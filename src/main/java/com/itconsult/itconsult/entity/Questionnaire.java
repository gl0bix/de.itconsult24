package com.itconsult.itconsult.entity;

import com.itconsult.itconsult.enums.OrderType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "questionnaire")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private OrderType orderType;
    private String urgency;
    private String duration;

    @Column(length = 1000)
    @Size(max = 1000)
    private String companyDescription;
    @Column(length = 1000)
    @Size(max = 1000)
    private String problemDescription;

    /**
     * 10 Strings for Questions -> Questionnaire.md starting 6.
     */
    private String typeOfAttack;    //Art des Angriffs
    private String typeOfMeasure;   //Art der Maßnahme
    private String typeOfDevices;   //Art der Leistung (Hardware)
    private String typeOfSoftware;  //Art der Leistung (Software)
    private String typeOfCloud;     //Cloud-Bereich
    private String network;         //Art der Leistung (Netzwerk)
    private String networkDetails;  //Details zum Netzwerkpunkt
    private String projectStatus;   //Softwareentwicklung: Projektstatus
    private String typeOfProject;   //Softwareentwicklung: Projektbereich
    private String systemadmin;     //Systemadministration


}
