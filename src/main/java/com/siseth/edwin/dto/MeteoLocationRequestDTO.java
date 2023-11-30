package com.siseth.edwin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeteoLocationRequestDTO {
    private double latitude;
    private double longitude;
    private String after;
    private String before;
    private Integer stationsCount;
    private Integer page;
    private Integer size;
    private String sort;
}