package com.siseth.edwin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeteoLocationResponseDTO {
    private List<MeteoLocationDataDTO> content;
    private List<LinksDTO> links;
    private PageDTO page;
    
}