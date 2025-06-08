package com.valride.service;

import com.valride.model.Reserva;
import com.valride.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    public ReservaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarReservaPorId() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        Optional<Reserva> found = reservaService.findById(1L);
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(1L);
    }
}
