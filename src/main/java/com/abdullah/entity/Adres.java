package com.abdullah.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name= "kisi_adres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"})
public class Adres implements Serializable {

    @Id
    @SequenceGenerator(name="seq_kisi_adres",allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi_adres",strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 500)
    private String adres;
    @Enumerated
    private AdresTip adresTip;


    private boolean aktif;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name= "kisi_adres_id")
    private Kisi kisi;
}
