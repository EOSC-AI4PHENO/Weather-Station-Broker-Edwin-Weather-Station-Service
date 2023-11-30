package com.siseth.edwin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ObservationStationIdResponseDTO {
    private String stationId;
    private String name;
    private String type;
    private boolean active;
}