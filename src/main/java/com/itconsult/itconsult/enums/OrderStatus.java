package com.itconsult.itconsult.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatus {
    OPEN("Offen"),
    IN_PROGRESS("in Bearbeitung"),
    FULFILLED("Abgeschlossen"),
    DISCARDED("Abgelehnt");

    @Getter
    private final String value;
}