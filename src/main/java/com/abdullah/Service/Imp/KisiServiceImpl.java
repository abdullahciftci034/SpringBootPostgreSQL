package com.abdullah.Service.Imp;

import com.abdullah.Service.KisiService;
import com.abdullah.dto.AdresDto;
import com.abdullah.dto.KisiDto;
import com.abdullah.entity.Adres;
import com.abdullah.entity.Kisi;
import com.abdullah.repotsitory.AdresRepository;
import com.abdullah.repotsitory.KisiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KisiServiceImpl implements KisiService {

    private final KisiRepository kisiRepository;
    private final AdresRepository adresRepository;

    @Override
    public KisiDto save(KisiDto kisiDto) {
        Kisi kisi =KisiDtoToKisi(kisiDto);
        Kisi kisi1 = kisiRepository.save(kisi);
        for (Adres adres : kisi.getAdresList()) {
            adres.setKisi(kisi1);
            this.adresRepository.save(adres);
        }
        kisiDto.setId(kisi1.getId());
        for (int j = 0; j <kisi1.getAdresList().size(); j++) {
            kisiDto.getAdresDtoList().get(j).setId(kisi.getAdresList().get(j).getId());
        }
        return kisiDto;

    }


    @Override
    public boolean delete(KisiDto kisiDto) {
        Optional<Kisi> optionalKisi;
        System.out.println(kisiDto);
        try {
            optionalKisi = kisiRepository.findById(kisiDto.getId());
            Kisi kisi= optionalKisi.orElse(null);
            kisi.getAdresList().forEach(e->
                    this.adresRepository.delete(e)
                    );
            kisiRepository.delete(kisi);
            return  true;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public List<KisiDto> getAll() {
        List<Kisi> kisiList=this.kisiRepository.findAll();
        return KisiListToKisiDtoList(kisiList);
    }


    @Override
    public Page<KisiDto> getAll(Pageable pageable) {
        return null;
    }


    /**
     * Kisi listesini KisiDto listesine cevirdik
     * @param
     * @return
     */
    private List<KisiDto> KisiListToKisiDtoList(List<Kisi> kisiList) {
        List<KisiDto> kisiDtoList=new ArrayList<>();
        for (Kisi kisi : kisiList) {
            kisiDtoList.add(KisiToKisiDto(kisi));
        }
        return kisiDtoList;
    }
    private KisiDto KisiToKisiDto(Kisi kisi) {
        KisiDto kisiDto = new KisiDto();
        kisiDto.setId(kisi.getId());
        kisiDto.setAdi(kisi.getAdi());
        kisiDto.setSoyadi(kisi.getSoyadi());
        kisiDto.setAdresDtoList(AdresListToAdresDtoList(kisi.getAdresList()));
        return kisiDto;
    }
    private List<AdresDto> AdresListToAdresDtoList(List<Adres> adresList){
        List<AdresDto> adresDtoList = new ArrayList<>();
        for (Adres adres : adresList) {
            adresDtoList.add(AdresToAdressDto(adres));
        }
        return  adresDtoList;
    }
    private AdresDto AdresToAdressDto(Adres adres){
        return new AdresDto(adres.getId(), adres.getAdres(), adres.getAdresTip());
    }


    /**
     * KisiDTO -> Kisi
     * @param kisiDto
     * @return
     */
    private Kisi KisiDtoToKisi(KisiDto kisiDto){
        Kisi kisi =new Kisi();
        kisi.setAdi(kisiDto.getAdi());
        kisi.setSoyadi(kisiDto.getSoyadi());
        kisi.setAdresList(AddresDtoListToAdresList(kisiDto.getAdresDtoList()));
        return kisi;
    }
    private List<Adres> AddresDtoListToAdresList(List<AdresDto> adresDtoList){
        List<Adres> adresList = new ArrayList<>();

        for (AdresDto adresDto:  adresDtoList) {
            adresList.add(AdresDtoToAdres(adresDto));
        }
        return adresList;
    }

    private Adres AdresDtoToAdres(AdresDto adresDto) {
        Adres adres = new Adres();
        adres.setAdresTip(adresDto.getAdresTip());
        adres.setAdres(adresDto.getAdres());
        return adres;
    }
}
