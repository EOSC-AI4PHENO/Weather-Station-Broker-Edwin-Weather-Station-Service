package com.siseth.edwin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeteoStationRequestDTO {
    private String stationId;
    private String after;
    private String before;
    private Integer page;
    private Integer size;
    private String sort;
}