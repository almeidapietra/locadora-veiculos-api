package edu.adatech.locadoradeveiculos.controller;

import edu.adatech.locadoradeveiculos.model.ClienteModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaFisicaModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaJuridicaModel;
import edu.adatech.locadoradeveiculos.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteController clienteController;

    public ClienteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarClientePessoaFisica() {
        ClientePessoaFisicaModel cliente = new ClientePessoaFisicaModel();
        cliente.setNome("Gustavo");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ResponseEntity<ClientePessoaFisicaModel> response = clienteController.criarClientePessoaFisica(cliente);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cliente, response.getBody());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void criarClientePessoaJuridica() {
        ClientePessoaJuridicaModel cliente = new ClientePessoaJuridicaModel();
        cliente.setNome("Gustavo");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ResponseEntity<ClientePessoaJuridicaModel> response = clienteController.criarClientePessoaJuridica(cliente);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cliente, response.getBody());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void listarClientes() {
        ClienteModel cliente1 = new ClientePessoaFisicaModel();
        ClienteModel cliente2 = new ClientePessoaJuridicaModel();
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        var clientes = clienteController.listarClientes();

        assertEquals(2, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void buscarClientePorIdExistente() {
        ClienteModel cliente = new ClientePessoaFisicaModel();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ResponseEntity<ClienteModel> response = clienteController.buscarClientePorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void buscarClientePorIdInexistente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ClienteModel> response = clienteController.buscarClientePorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void atualizarClienteExistente() {
        ClienteModel cliente = new ClientePessoaFisicaModel();
        cliente.setNome("Gustavo");
        ClienteModel clienteAtualizado = new ClientePessoaFisicaModel();
        clienteAtualizado.setNome("Gustavo Atualizado");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(clienteAtualizado);

        ResponseEntity<ClienteModel> response = clienteController.atualizarCliente(1L, clienteAtualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Gustavo Atualizado", response.getBody().getNome());
        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void atualizarClienteInexistente() {
        ClienteModel clienteAtualizado = new ClientePessoaFisicaModel();
        clienteAtualizado.setNome("Gustavo Atualizado");

        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ClienteModel> response = clienteController.atualizarCliente(1L, clienteAtualizado);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void deletarClienteExistente() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = clienteController.deletarCliente(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clienteRepository, times(1)).existsById(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletarClienteInexistente() {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = clienteController.deletarCliente(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteRepository, times(1)).existsById(1L);
    }

    @Test
    void buscarPorCpfExistente() {
        ClientePessoaFisicaModel cliente = new ClientePessoaFisicaModel();
        cliente.setCpf("12345678900");
        when(clienteRepository.findByCpf("12345678900")).thenReturn(Optional.of(cliente));

        ResponseEntity<ClientePessoaFisicaModel> response = clienteController.buscarPorCpf("12345678900");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
        verify(clienteRepository, times(1)).findByCpf("12345678900");
    }

    @Test
    void buscarPorCpfInexistente() {
        when(clienteRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        ResponseEntity<ClientePessoaFisicaModel> response = clienteController.buscarPorCpf("12345678900");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteRepository, times(1)).findByCpf("12345678900");
    }

    @Test
    void buscarPorCnpjExistente() {
        ClientePessoaJuridicaModel cliente = new ClientePessoaJuridicaModel();
        cliente.setCnpj("98765432100012");
        when(clienteRepository.findByCnpj("98765432100012")).thenReturn(Optional.of(cliente));

        ResponseEntity<ClientePessoaJuridicaModel> response = clienteController.buscarPorCnpj("98765432100012");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
        verify(clienteRepository, times(1)).findByCnpj("98765432100012");
    }

    @Test
    void buscarPorCnpjInexistente() {
        when(clienteRepository.findByCnpj("98765432100012")).thenReturn(Optional.empty());

        ResponseEntity<ClientePessoaJuridicaModel> response = clienteController.buscarPorCnpj("98765432100012");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteRepository, times(1)).findByCnpj("98765432100012");
    }

}
