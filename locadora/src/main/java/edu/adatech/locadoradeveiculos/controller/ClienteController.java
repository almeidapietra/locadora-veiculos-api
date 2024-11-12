package edu.adatech.locadoradeveiculos.controller;


import edu.adatech.locadoradeveiculos.model.ClienteModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaFisicaModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaJuridicaModel;
import edu.adatech.locadoradeveiculos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {


    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/pessoa-fisica")
    public ResponseEntity<ClientePessoaFisicaModel> criarClientePessoaFisica(@RequestBody ClientePessoaFisicaModel cliente) {
        ClientePessoaFisicaModel novoCliente = clienteRepository.save(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PostMapping("/pessoa-juridica")
    public ResponseEntity<ClientePessoaJuridicaModel> criarClientePessoaJuridica(@RequestBody ClientePessoaJuridicaModel cliente) {
        ClientePessoaJuridicaModel novoCliente = clienteRepository.save(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> buscarClientePorId(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable Long id, @RequestBody ClienteModel clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setLogradouro(clienteAtualizado.getLogradouro());
            ClienteModel clienteSalvo = clienteRepository.save(cliente);
            return new ResponseEntity<>(clienteSalvo, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClientePessoaFisicaModel> buscarPorCpf(@PathVariable String cpf) {
        Optional<ClientePessoaFisicaModel> cliente = clienteRepository.findByCpf(cpf);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<ClientePessoaJuridicaModel> buscarPorCnpj(@PathVariable String cnpj) {
        Optional<ClientePessoaJuridicaModel> cliente = clienteRepository.findByCnpj(cnpj);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
