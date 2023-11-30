package com.siseth.edwin.dto;

import com.siseth.edwin.enumerate.StationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObservationStationLocationRequestDTO {
    private double latitude;
    private double longitude;
    private Boolean active;
    private Integer distance;
    private StationType type;
    private Integer page;
    private Integer size;
    private String sort;
}