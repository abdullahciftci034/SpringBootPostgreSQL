package com.abdullah.dto;


import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KisiDto {
    private Long id;
    private String adi;
    private String soyadi;
    private List<AdresDto> adresDtoList;

}
