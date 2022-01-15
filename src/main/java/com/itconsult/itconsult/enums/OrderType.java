package com.itconsult.itconsult.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderType {
    SECURITY_ARCHITECTURE("Sicherheitsarchitektur"),
    URGENT_SECURITY_PROBLEM("akutes Sicherheitsproblem"),
    HARDWARE("Hardware"),
    SCALABILITY("Leistung, Skalierung"),
    CLOUD("Cloudanwendungen"),
    INFRASTRUCTURE("internes Netzwerk"),
    SOFTWARE_DEVELOPMENT("Softwareentwicklung"),
    NETWORK("Netzwerkinfrastruktur"),
    ADMINISTRATION("Systemadministration");

    @Getter
    private final String value;
}
