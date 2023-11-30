package com.siseth.edwin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageDTO {
    private Long size;
    private Long totalElements;
    private Long totalPages;
    private Long number;
}