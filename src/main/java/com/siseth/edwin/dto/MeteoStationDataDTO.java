package com.siseth.edwin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeteoStationDataDTO {
    private String measurementDate;
    private String stationId;
    private Double airTemperature;
    private Double relativeHumidity;
    private Double windSpeed;
    private Double windDirection;
    private Double precipitation;
    private List<LinksDTO> links;
}