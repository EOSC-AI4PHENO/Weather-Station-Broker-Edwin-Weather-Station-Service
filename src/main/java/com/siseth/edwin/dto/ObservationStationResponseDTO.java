package com.siseth.edwin.dto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class ObservationStationResponseDTO {
    private List<ObservationStationDTO> content;
    private PageDTO page;
    private List<LinksDTO> links;
}