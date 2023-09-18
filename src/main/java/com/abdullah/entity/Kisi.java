package com.abdullah.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;


@Entity
@Table(name= "kisi")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"})
public class Kisi {
    @Id
    @SequenceGenerator(name="seq_kisi",allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String adi;

    private String soyadi;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "kisi_adres_id")
    private List<Adres> adresList;
}
