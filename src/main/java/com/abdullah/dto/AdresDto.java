package com.abdullah.dto;

import com.abdullah.entity.AdresTip;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdresDto {
    private Long id;
    private String adres;
    private AdresTip adresTip;


}
