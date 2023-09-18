package com.abdullah.controller;

import com.abdullah.Service.KisiService;
import com.abdullah.dto.KisiDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.results.graph.entity.EntityResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/kisi")
public class KisiController {
    private final KisiService kisiService;

    public KisiController(KisiService kisiService) {
        this.kisiService = kisiService;
    }

    @PostMapping
    public ResponseEntity<KisiDto> save(@RequestBody  KisiDto kisiDto){
        System.out.println(kisiDto);
        return ResponseEntity.ok(this.kisiService.save(kisiDto));
    }
    @GetMapping
    public ResponseEntity<List<KisiDto>> getAll(){
        return ResponseEntity.ok(this.kisiService.getAll());
    }
    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestBody KisiDto  kisiDto){
        return ResponseEntity.ok(this.kisiService.delete(kisiDto));
    }
}
