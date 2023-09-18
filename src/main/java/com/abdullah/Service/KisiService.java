package com.abdullah.Service;

import com.abdullah.dto.KisiDto;
import com.abdullah.entity.Kisi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KisiService {
    KisiDto save(KisiDto kisiDto);
    boolean delete(KisiDto kisiDto);
    List<KisiDto> getAll();
    Page<KisiDto> getAll(Pageable  pageable);

}
