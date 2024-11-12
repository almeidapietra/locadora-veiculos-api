package edu.adatech.locadoradeveiculos.service;
import edu.adatech.locadoradeveiculos.model.ClienteModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaFisicaModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaJuridicaModel;
import edu.adatech.locadoradeveiculos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public ClientePessoaFisicaModel criarClientePessoaFisica(ClientePessoaFisicaModel cliente) {
        return clienteRepository.save(cliente);
    }

    public ClientePessoaJuridicaModel criarClientePessoaJuridica(ClientePessoaJuridicaModel cliente) {
        return clienteRepository.save(cliente);
    }

    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<ClienteModel> atualizarCliente(Long id, ClienteModel clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setLogradouro(clienteAtualizado.getLogradouro());
            return clienteRepository.save(cliente);
        });
    }

    public boolean deletarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ClientePessoaFisicaModel> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Optional<ClientePessoaJuridicaModel> buscarPorCnpj(String cnpj) {
        return clienteRepository.findByCnpj(cnpj);
    }

}
