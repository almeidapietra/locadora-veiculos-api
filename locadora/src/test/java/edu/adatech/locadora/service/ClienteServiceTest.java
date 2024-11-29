package edu.adatech.locadora.service;


import edu.adatech.locadoradeveiculos.model.ClienteModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaFisicaModel;
import edu.adatech.locadoradeveiculos.repository.ClienteRepository;
import edu.adatech.locadoradeveiculos.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    ClienteServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarClientePessoaFisica() {
        ClientePessoaFisicaModel cliente = new ClientePessoaFisicaModel();
        cliente.setNome("Gustavo");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClientePessoaFisicaModel resultado = clienteService.criarClientePessoaFisica(cliente);

        assertEquals("Gustavo", resultado.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void deveBuscarClientePorId() {
        ClienteModel clienteModel = new ClientePessoaFisicaModel();
        clienteModel.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteModel));

        Optional<ClienteModel> resultado = clienteService.buscarClientePorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void deveDeletarCliente() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        boolean resultado = clienteService.deletarCliente(1L);

        assertTrue(resultado);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

}
