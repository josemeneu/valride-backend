package com.valride.controller;

import com.valride.model.Moto;
import com.valride.service.MotoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MotoControllerTest {
    @Mock
    private MotoService motoService;

    @InjectMocks
    private MotoController motoController;

    public MotoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMotos() {
        Moto moto1 = new Moto();
        moto1.setNombre("Yamaha MT-07");
        Moto moto2 = new Moto();
        moto2.setNombre("Suzuki GSX-S750");
        when(motoService.findAll()).thenReturn(Arrays.asList(moto1, moto2));
        List<Moto> motos = motoController.getAll();
        assertThat(motos).hasSize(2);
        assertThat(motos.get(0).getNombre()).isEqualTo("Yamaha MT-07");
        assertThat(motos.get(1).getNombre()).isEqualTo("Suzuki GSX-S750");
    }
}
