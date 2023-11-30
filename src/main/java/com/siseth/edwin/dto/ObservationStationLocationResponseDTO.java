package com.siseth.edwin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObservationStationLocationResponseDTO {
    private List<ObservationStationDTO> content;
    private PageDTO page;
    private List<LinksDTO> links;
}