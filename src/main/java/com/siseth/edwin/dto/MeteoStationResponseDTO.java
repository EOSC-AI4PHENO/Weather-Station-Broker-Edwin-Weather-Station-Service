package com.siseth.edwin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeteoStationResponseDTO {
    private List<MeteoStationDataDTO> content;
    private List<LinksDTO> links;
    private PageDTO page;
}