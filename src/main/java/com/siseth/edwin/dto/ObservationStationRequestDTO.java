package com.siseth.edwin.dto;

import com.siseth.edwin.enumerate.StationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObservationStationRequestDTO {
    private String contains;
    private StationType type;
    private Boolean active;
    private Integer page;
    private Integer size;
    private String sort;

   
}
