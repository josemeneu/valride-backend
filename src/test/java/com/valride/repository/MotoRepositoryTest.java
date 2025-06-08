package com.valride.repository;

import com.valride.model.Moto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MotoRepositoryTest {
    @Mock
    private MotoRepository motoRepository;

 public MotoRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAndFindById() {
        Moto moto = new Moto();
        moto.setId(1L);
        moto.setNombre("Honda CBR");
        moto.setImagen("img.jpg");
        moto.setAlquilada(false);
        moto.setPrecio(new java.math.BigDecimal("100.00"));
        when(motoRepository.save(moto)).thenReturn(moto);
        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));
        Moto saved = motoRepository.save(moto);
        Optional<Moto> found = motoRepository.findById(1L);
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Honda CBR");
    }
}
