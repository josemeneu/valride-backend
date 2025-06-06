package com.valride.controller;

import com.valride.model.Moto;
import com.valride.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/motos")
@CrossOrigin
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping
    public List<Moto> getAll() {
        return motoService.findAll();
    }

    @GetMapping("/{id}")
    public Moto getById(@PathVariable Long id) {
        return motoService.findById(id).orElse(null);
    }
}
