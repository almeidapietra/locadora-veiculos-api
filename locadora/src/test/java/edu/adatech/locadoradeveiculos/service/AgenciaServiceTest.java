package edu.adatech.locadoradeveiculos.service;

import edu.adatech.locadoradeveiculos.model.AgenciaModel;
import edu.adatech.locadoradeveiculos.repository.AgenciaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgenciaServiceTest {

    @InjectMocks
    private AgenciaService agenciaService;

    @Mock
    private AgenciaRepository agenciaRepository;

    public AgenciaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarAgencia() {
        AgenciaModel agencia = new AgenciaModel("Agência Centro", "Rua 123");
        when(agenciaRepository.save(agencia)).thenReturn(agencia);

        AgenciaModel resultado = agenciaService.save(agencia);

        assertNotNull(resultado);
        assertEquals("Agência Centro", resultado.getNome());
        verify(agenciaRepository, times(1)).save(agencia);
    }

    @Test
    void deveBuscarAgenciaPorId() {
        AgenciaModel agencia = new AgenciaModel("Agência Centro", "Rua 123");
        agencia.setId(1L);
        when(agenciaRepository.findById(1L)).thenReturn(Optional.of(agencia));

        Optional<AgenciaModel> resultado = agenciaService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(agenciaRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarVazioAoBuscarAgenciaInexistente() {
        when(agenciaRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<AgenciaModel> resultado = agenciaService.findById(999L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveDeletarAgenciaPorId() {
        when(agenciaRepository.findById(1L)).thenReturn(Optional.of(new AgenciaModel()));

        agenciaService.deleteById(1L);

        verify(agenciaRepository, times(1)).deleteById(1L);
    }

    @Test
    void naoDeveDeletarAgenciaInexistente() {

        Long idInexistente = 999L;
        when(agenciaRepository.findById(idInexistente)).thenReturn(Optional.empty());


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            agenciaService.deleteById(idInexistente);
        });

        assertEquals("Agência com ID " + idInexistente + " não encontrada", exception.getMessage());

        verify(agenciaRepository, times(0)).deleteById(idInexistente);
    }
}
